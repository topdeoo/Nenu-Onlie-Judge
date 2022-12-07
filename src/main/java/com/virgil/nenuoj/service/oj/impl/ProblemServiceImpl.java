package com.virgil.nenuoj.service.oj.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.virgil.nenuoj.mappers.ProblemListMappers;
import com.virgil.nenuoj.pojo.entity.Problem;
import com.virgil.nenuoj.pojo.entity.ProblemList;
import com.virgil.nenuoj.pojo.vo.ProblemListVO;
import com.virgil.nenuoj.service.oj.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    private ProblemListMappers problemListMappers;

    @Override
    public ProblemListVO getProblemList(int current, int size) {
        ProblemListVO problemListVO = new ProblemListVO();
        IPage<ProblemList> page = new Page<>(current, size);
        problemListMappers.selectPage(page);
        problemListVO.setCurrent(page.getCurrent());
        problemListVO.setSize(page.getSize());
        problemListVO.setTotal(page.getTotal());
        problemListVO.setProblemLists((ArrayList<ProblemList>) page.getRecords());
        return problemListVO;
    }

    @Override
    public ProblemListVO getProblemListByFilter( int current ,int size , String filter ) {
        //TODO
        return null;
    }

    @Override
    public Problem getProblemDetail( long problemId ) {
        //TODO
        return null;
    }

    @Override
    public void addProblem( Problem problem ) {
        //TODO
    }

    @Override
    public void updateProblem( Problem problem ) {
        //TODO
    }

    @Override
    public void deleteProblem( long problemId ) {
        //TODO
    }

}
