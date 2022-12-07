package com.virgil.nenuoj.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemList {
    private long id;
    private String title;
    private String tag;
    private long submitCount;
    private long acceptCount;
}
