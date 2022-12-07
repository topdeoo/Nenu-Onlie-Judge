package com.virgil.nenuoj.service.common;

import com.virgil.nenuoj.pojo.entity.Poster;
import com.virgil.nenuoj.pojo.vo.PosterVO;

public interface PosterService {

    PosterVO getPosterList(int current, int size);

    Poster getPosterDetails( long id );

    void addPoster( Poster poster );

    void deletePoster( long id );

    void modifyPoster( Poster poster );
}
