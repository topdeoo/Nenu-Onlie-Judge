package com.virgil.nenuoj;

import com.virgil.nenuoj.mappers.AccountMappers;
import com.virgil.nenuoj.pojo.entity.user.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class NenuojApplicationTests {

    @Autowired
    private AccountMappers accountMappers;

    @Test
    void contextLoads() {
        List<UserInfo> list = accountMappers.selectList(null);
        list.forEach(System.out::println);
    }

}
