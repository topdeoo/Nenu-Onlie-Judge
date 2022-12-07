package com.virgil.nenuoj.service.oj.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.virgil.nenuoj.common.exception.StatusFailException;
import com.virgil.nenuoj.common.result.CommonResult;
import com.virgil.nenuoj.mappers.AccountMappers;
import com.virgil.nenuoj.pojo.entity.UserInfo;
import com.virgil.nenuoj.pojo.vo.UserVO;
import com.virgil.nenuoj.service.common.EmailService;
import com.virgil.nenuoj.service.oj.AccountService;
import com.virgil.nenuoj.utils.Constant;
import com.virgil.nenuoj.utils.IPUtils;
import com.virgil.nenuoj.utils.JWTUtil;
import com.virgil.nenuoj.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.HashMap;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private AccountMappers accountMappers;

    @Autowired
    private EmailService emailService;

    @Override
    public HashMap<String, Object> login( String username ,String password ,HashMap<String, String> request_header ) throws StatusFailException {
        String ipAddress = IPUtils.getUserAddress(request_header);
        if(ipAddress == null)
            ipAddress = "";
        String key = Constant.Account.TRY_LOGIN_NUM.getCode() + username + '_' + ipAddress;
        Integer tryLoginCount = (Integer) redisUtils.get(key);
        if(tryLoginCount != null && tryLoginCount >= 20)
            throw new StatusFailException("频繁登录");
        if (tryLoginCount == null)
            tryLoginCount = 0;
        UserInfo user = accountMappers.selectOne(Wrappers.<UserInfo>query().eq("username", username));
        if(user == null)
            throw new StatusFailException("用户名错误");
        else if (!user.getPassword().equals(password)){
                tryLoginCount++;
                redisUtils.set(key, tryLoginCount, 60 * 30);
                throw new StatusFailException("密码错误");
        }
        HashMap<String, Object> response = new HashMap<>();
        UserVO userVO = new UserVO(user, accountMappers.selectPermissionById(user.getUid()));
        response.put("token", JWTUtil.buildToken(username));
        response.put("data", userVO);
        return response;
    }

    @Override
    public void sendRegisterCode( String email ) throws StatusFailException {
        if(Validator.isEmail(email)){

            QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("email", email);
            if(accountMappers.selectCount(queryWrapper) > 0)
                throw new StatusFailException("该邮箱已被注册");

            String expire = DateUtil.offsetMinute(new Date(), 10).toString();
            String key = Constant.Account.SEND_REGISTER_CODE.getCode() + "_" + email;

            if(redisUtils.hasKey(key))
                throw new StatusFailException("请勿频繁发送");

            String code = RandomUtil.randomNumbers(6);
            redisUtils.set(Constant.Account.SEND_REGISTER_CODE.getCode() + "_" + email + code, code, 60 * 5);
            redisUtils.set(key, 1, 60);
            try {
                emailService.sendMail(email ,code ,expire);
            }
            catch (MessagingException e) {
                throw new StatusFailException("发送失败");
            }

        }
        else
            throw new StatusFailException("邮箱格式错误");
    }

    @Override
    public HashMap<String, Object> register( String username ,String password ,String email ,String verify ) throws StatusFailException {
        if(accountMappers.exists(Wrappers.<UserInfo>query().eq("username", username)))
            throw new StatusFailException("用户名已存在");
        String key = Constant.Account.SEND_REGISTER_CODE.getCode() + "_" + email + verify;
        if(redisUtils.hasKey(key)){
            UserInfo userInfo = new UserInfo(username, password);
            userInfo.setEmail(email);
            userInfo.setGmtCreate(new Date());
            userInfo.setEmailVerified(1);
            userInfo.setNickname(username);
            accountMappers.insert(userInfo);
            accountMappers.insertToPermission(userInfo.getUid(), "student", new Date());
            redisUtils.del(key);
            HashMap<String, Object> response = new HashMap<>();
            UserVO userVO = new UserVO(userInfo, "student");
            response.put("token", JWTUtil.buildToken(username));
            response.put("data", userVO);
            return response;
        }
        else
            throw new StatusFailException("验证码过期/错误");
    }

    @Override
    public CommonResult<Void> modifyPassword( String oldPassword ,String newPassword ,String token ) {
        String username = JWTUtil.getUsername(token);
        UserInfo userInfo = accountMappers.selectOne(
                Wrappers.<UserInfo>query().eq("username", username)
        );
        if(userInfo.getPassword().equals(oldPassword)){
            userInfo.setPassword(newPassword);
            userInfo.setGmtModified(new Date());
            accountMappers.updateById(userInfo);
            return CommonResult.successResponse();
        }
        else
            return CommonResult.errorResponse("原密码错误");
    }

    @Override
    public CommonResult<Void> modifyEmail( String email ,String token ) {
        if(!Validator.isEmail(email))
            return CommonResult.errorResponse("邮箱格式错误");
        String username = JWTUtil.getUsername(token);
        UserInfo userInfo = accountMappers.selectOne(
                Wrappers.<UserInfo>query().eq("username", username)
        );
        if(userInfo.getEmail().equals(email))
            return CommonResult.errorResponse("新邮箱与原邮箱相同");
        else{
            userInfo.setEmail(email);
            userInfo.setGmtModified(new Date());
            userInfo.setEmailVerified(0);
            accountMappers.updateById(userInfo);
            return CommonResult.successResponse();
        }
    }

    @Override
    public CommonResult<Void> verifyEmail( String token ,String code ) {
        String username = JWTUtil.getUsername(token);
        UserInfo userInfo = accountMappers.selectOne(
                Wrappers.<UserInfo>query().eq("username", username)
        );
        if (userInfo.getEmailVerified() == 1)
            return CommonResult.successResponse("邮箱已验证");
        String key = Constant.Account.SEND_REGISTER_CODE.getCode() + "_" + userInfo.getEmail() + code;
        if(redisUtils.get(key).equals(code)){
            userInfo.setEmailVerified(1);
            accountMappers.updateById(userInfo);
            redisUtils.del(key);
            return CommonResult.successResponse();
        }
        else
            return CommonResult.errorResponse("验证码错误");
    }

    @Override
    public CommonResult<UserVO> modifyInfo( HashMap<String, String> requestBody ,String token ) {
        String username = JWTUtil.getUsername(token);
        UserInfo user = accountMappers.selectOne(
                Wrappers.<UserInfo>query().eq("username", username)
        );
        for (String key: requestBody.keySet()) {
            switch (key){
                case "nickname":
                    user.setNickname(requestBody.get(key));
                    break;
                case "school":
                    user.setSchool(requestBody.get(key));
                    break;
                case "course":
                    user.setCourse(requestBody.get(key));
                    break;
                case "gender":
                    user.setGender(requestBody.get(key));
                    break;
                case "number":
                    user.setNumber(requestBody.get(key));
                    break;
                case "realname":
                    user.setRealname(requestBody.get(key));
                    break;
                case "github":
                    user.setGithub(requestBody.get(key));
                    break;
                case "blog":
                    user.setBlog(requestBody.get(key));
                    break;
                case "avatar":
                    user.setAvatar(requestBody.get(key));
                    break;
                case "signature":
                    user.setSignature(requestBody.get(key));
                    break;
            }
        }
        user.setGmtModified(new Date());
        return CommonResult.successResponse(new UserVO(user));
    }

    @Override
    public UserVO getUserProfile( String username ) {
        UserInfo user = accountMappers.selectOne(Wrappers.<UserInfo>query().eq("username", username));
        return new UserVO(user);
    }
}
