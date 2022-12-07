package com.virgil.nenuoj.mappers;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.virgil.nenuoj.pojo.entity.ProblemList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface ProblemListMappers extends BaseMapper<ProblemList> {
    ArrayList<ProblemList> selectPage( IPage<ProblemList> page);
}
