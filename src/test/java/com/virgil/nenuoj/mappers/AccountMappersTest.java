package com.virgil.nenuoj.mappers;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.virgil.nenuoj.pojo.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Date;


@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountMappersTest {

    @Autowired
    private AccountMappers accountMappers;

    @Test
    void insertOne(){
        UserInfo userInfo = new UserInfo("virgil", "123456");
        userInfo.setEmail("949508510@qq.com");
        accountMappers.insert(userInfo);
        System.out.println(accountMappers.
                selectOne(Wrappers.<UserInfo>query().eq("username", "virgil")).getUid());
    }

    @Test
    void insertToPermission(){
        UserInfo userInfo = new UserInfo("virgil", "123456");
        userInfo.setEmail("949508510@qq.com");
        accountMappers.insert(userInfo);
        userInfo = accountMappers.selectOne(Wrappers.<UserInfo>query().eq("username", "virgil"));
        accountMappers.insertToPermission(userInfo.getUid(), "admin", new Date());
        System.out.println(accountMappers.selectPermissionById(userInfo.getUid()));
    }

}