package com.virgil.nenuoj.controller;

import com.alibaba.fastjson2.JSON;
import com.virgil.nenuoj.common.exception.StatusFailException;
import com.virgil.nenuoj.common.result.CommonResult;
import com.virgil.nenuoj.pojo.vo.UserVO;
import com.virgil.nenuoj.service.oj.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;

@Api("账户相关 API 说明")
@RestController
@RequestMapping("/user")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public ResponseEntity<String> login( @RequestBody HashMap<String, String> requestBody,
                                         @RequestHeader HashMap<String, String> requestHeader ) throws StatusFailException {
        String username = requestBody.get("username").trim();
        String password = requestBody.get("password").trim();
        HttpHeaders headers = new HttpHeaders();
        HashMap<String, Object> response = accountService.login(username, password, requestHeader);
        headers.set("Authorization", (String) response.get("token"));
        return ResponseEntity.ok()
                .headers(headers)
                .body(JSON.toJSONString(response.get("data")));
    }

    @GetMapping("/register-code")
    @ApiOperation("获取邮箱验证码")
    public void registerCode(@RequestParam(value = "email") String email ) throws StatusFailException {
        email = email.trim();
        accountService.sendRegisterCode(email);
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public ResponseEntity<String> register(@RequestBody HashMap<String, String> requestBody) throws StatusFailException{
        String username = requestBody.get("username").trim();
        String password = requestBody.get("password").trim();
        String email = requestBody.get("email").trim();
        String verify = requestBody.get("code").trim();
        HttpHeaders headers = new HttpHeaders();
        HashMap<String, Object> response = accountService.register(username, password, email, verify);
        headers.set("Authorization", (String) response.get("token"));
        return ResponseEntity.ok()
                .headers(headers)
                .body(JSON.toJSONString(response.get("data")));
    }

    @PostMapping("/modify-password")
    @ApiOperation("修改密码")
    public CommonResult<Void> modifyPassword( @RequestBody HashMap<String, String> requestBody,
                                              @RequestHeader("Authorization")String token ) {
        String oldPassword = requestBody.get("oldPassword").trim();
        String newPassword = requestBody.get("newPassword").trim();
        token = token.trim();
        return accountService.modifyPassword(oldPassword, newPassword, token);
    }

    @PostMapping("/modify-email")
    @ApiOperation("修改邮箱")
    public CommonResult<Void> modifyEmail( @RequestBody HashMap<String, String> requestBody,
                                           @RequestHeader("Authorization")String token ) {
        String email = requestBody.get("email").trim();
        token = token.trim();
        return accountService.modifyEmail(email, token);
    }

    @GetMapping("/verify-email")
    @ApiOperation("验证邮箱")
    public CommonResult<Void> verifyEmail( @RequestHeader("Authorization")String token, @RequestParam("code")String code ) {
        return accountService.verifyEmail(token, code);
    }

    @PostMapping("/modify-info")
    @ApiOperation("修改用户信息")
    public CommonResult<UserVO> modifyInfo( @RequestBody HashMap<String, String> requestBody,
                                            @RequestHeader("Authorization")String token ) {
        token = token.trim();
        return accountService.modifyInfo(requestBody, token);
    }

    @GetMapping("/getUserProfile")
    @ApiOperation("获取用户信息")
    public UserVO getUserProfile(@RequestParam("username")String username) {
        username = username.trim();
        return accountService.getUserProfile(username);
    }

}
