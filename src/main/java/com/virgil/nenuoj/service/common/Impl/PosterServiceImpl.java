package com.virgil.nenuoj.service.common.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.virgil.nenuoj.mappers.PosterMappers;
import com.virgil.nenuoj.pojo.entity.Poster;
import com.virgil.nenuoj.pojo.vo.PosterVO;
import com.virgil.nenuoj.service.common.PosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PosterServiceImpl implements PosterService {

    @Autowired
    private PosterMappers posterMappers;

    @Override
    public PosterVO getPosterList( int current ,int size ) {
        IPage<Poster> posterIPage = new Page<>(current, size);
        posterMappers.selectPage(posterIPage, null);
        return new PosterVO(posterIPage.getCurrent(), posterIPage.getSize(),
                posterIPage.getTotal(), (ArrayList<Poster>) posterIPage.getRecords());
    }

    @Override
    public Poster getPosterDetails( long id ) {
        return posterMappers.selectById(id);
    }

    @Override
    public void addPoster( Poster poster ) {
        posterMappers.insert(poster);
    }

    @Override
    public void deletePoster( long id ) {
        posterMappers.deleteById(id);
    }

    @Override
    public void modifyPoster( Poster poster ) {
        posterMappers.updateById(poster);
    }
}
