package com.virgil.nenuoj.pojo.vo;

import com.virgil.nenuoj.pojo.entity.Poster;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PosterVO {
    private long current;
    private long size;
    private long total;
    private ArrayList<Poster> records;
}
