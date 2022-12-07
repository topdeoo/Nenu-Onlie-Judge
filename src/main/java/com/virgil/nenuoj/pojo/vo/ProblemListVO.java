package com.virgil.nenuoj.pojo.vo;

import com.virgil.nenuoj.pojo.entity.ProblemList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemListVO {
    private long current;
    private long size;
    private long total;
    ArrayList<ProblemList> problemLists;
}
