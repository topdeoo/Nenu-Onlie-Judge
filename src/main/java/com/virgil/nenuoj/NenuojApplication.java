package com.virgil.nenuoj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.virgil.nenuoj.mappers")
public class NenuojApplication {

    public static void main( String[] args ) {
        SpringApplication.run(NenuojApplication.class ,args);
    }

}
