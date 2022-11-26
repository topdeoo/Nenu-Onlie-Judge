package com.virgil.nenuoj.service.oj;

import com.virgil.nenuoj.common.exception.StatusFailException;
import com.virgil.nenuoj.common.result.CommonResult;
import com.virgil.nenuoj.pojo.vo.RegisterCodeVO;
import com.virgil.nenuoj.pojo.vo.UserVO;

import java.util.HashMap;

public interface AccountService {

    HashMap<String, Object> login( String username, String password, HashMap<String, String> request_header ) throws StatusFailException;

    CommonResult<RegisterCodeVO> sendRegisterCode( String email );
}
