package com.virgil.nenuoj.service.oj.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.virgil.nenuoj.common.exception.StatusFailException;
import com.virgil.nenuoj.common.result.CommonResult;
import com.virgil.nenuoj.mappers.AccountMappers;
import com.virgil.nenuoj.pojo.entity.user.UserInfo;
import com.virgil.nenuoj.pojo.vo.RegisterCodeVO;
import com.virgil.nenuoj.pojo.vo.UserVO;
import com.virgil.nenuoj.service.oj.AccountService;
import com.virgil.nenuoj.utils.Constant;
import com.virgil.nenuoj.utils.IPUtils;
import com.virgil.nenuoj.utils.JWTUtil;
import com.virgil.nenuoj.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private AccountMappers accountMappers;

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
    public CommonResult<RegisterCodeVO> sendRegisterCode( String email ) {
        return null;
    }
}
