package com.virgil.nenuoj.service.common.Impl;

import com.virgil.nenuoj.NenuojApplication;
import com.virgil.nenuoj.mappers.PosterMappers;
import com.virgil.nenuoj.pojo.entity.Poster;
import com.virgil.nenuoj.service.common.PosterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        classes = NenuojApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE
)
class PosterServiceImplTest {

    @Autowired
    private PosterService posterService;

    private

    @Test
    void getPosterList() {
        System.out.println(posterService.getPosterList(1, 5));
    }

    @Test
    void getPosterDetails() {
        System.out.println(posterService.getPosterDetails(0));
    }

    @Test
    void modifyPoster() {
        Poster poster = posterService.getPosterDetails(0);
        poster.setContent("this is a modify");
        posterService.modifyPoster(poster);
        System.out.println(posterService.getPosterDetails(0));
    }

    @Test
    void deletePoster() {
        posterService.deletePoster(0);
    }
}