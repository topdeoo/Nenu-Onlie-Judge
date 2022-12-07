package com.virgil.nenuoj.service.oj;

import com.virgil.nenuoj.pojo.entity.Problem;
import com.virgil.nenuoj.pojo.vo.ProblemListVO;

public interface ProblemService {

    ProblemListVO getProblemList(int current, int size);

    ProblemListVO getProblemListByFilter( int current ,int size ,String filter );

    Problem getProblemDetail( long problemId );

    void addProblem( Problem problem );

    void updateProblem( Problem problem );

    void deleteProblem( long problemId );
}
