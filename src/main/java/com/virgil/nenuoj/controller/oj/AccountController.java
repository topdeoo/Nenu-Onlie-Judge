package com.virgil.nenuoj.controller.oj;

import com.virgil.nenuoj.common.exception.StatusFailException;
import com.virgil.nenuoj.common.result.CommonResult;
import com.virgil.nenuoj.pojo.vo.RegisterCodeVO;
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
                .body( (String) response.get("data"));
    }

    @GetMapping("/register-code")
    @ApiOperation("获取邮箱验证码")
    public CommonResult<RegisterCodeVO> registerCode(@RequestParam(value = "email") String email ){
        return accountService.sendRegisterCode(email);
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public ResponseEntity<String> register(@RequestBody HashMap<String, String> requestBody) throws StatusFailException{
        String username = requestBody.get("username").trim();
        String password = requestBody.get("password").trim();
        String email = requestBody.get("email").trim();
        String verify = requestBody.get("code").trim();
    }

}
