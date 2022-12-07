package com.virgil.nenuoj.service.oj;

import com.virgil.nenuoj.common.exception.StatusFailException;
import com.virgil.nenuoj.common.result.CommonResult;
import com.virgil.nenuoj.pojo.vo.UserVO;

import java.util.HashMap;

public interface AccountService {

    HashMap<String, Object> login( String username, String password, HashMap<String, String> request_header ) throws StatusFailException;

    void sendRegisterCode( String email ) throws StatusFailException;

    HashMap<String, Object> register( String username ,String password ,String email ,String verify ) throws StatusFailException;


    CommonResult<Void> modifyPassword( String oldPassword ,String newPassword ,String token );

    CommonResult<Void> modifyEmail( String email ,String token );

    CommonResult<Void> verifyEmail( String token ,String code );

    CommonResult<UserVO> modifyInfo( HashMap<String, String> requestBody ,String token );

    UserVO getUserProfile( String username );
}
