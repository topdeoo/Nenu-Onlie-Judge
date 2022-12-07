package com.virgil.nenuoj.controller;

import com.virgil.nenuoj.common.result.CommonResult;
import com.virgil.nenuoj.pojo.entity.Problem;
import com.virgil.nenuoj.pojo.vo.ProblemListVO;
import com.virgil.nenuoj.service.oj.ProblemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("问题相关 API 说明")
@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @GetMapping("/problem-list")
    @ApiOperation("获取问题列表")
    public ProblemListVO getProblemList( @RequestParam("current")int current,@RequestParam("size")int size) {
        return problemService.getProblemList(current, size);
    }

    @GetMapping("/problem-filter")
    @ApiOperation("按条件获取问题列表")
    public ProblemListVO getProblemFilterList( @RequestParam("current")int current,@RequestParam("size")int size,
                                               @RequestParam("filter")String filter) {
        return problemService.getProblemListByFilter(current, size, filter);
    }

    @GetMapping("/problem-detail")
    @ApiOperation("获取问题详情")
    //TODO: change Problem to ProblemVO
    public Problem getProblemDetail( @RequestParam("problemId")long problemId) {
        return problemService.getProblemDetail(problemId);
    }

    @PostMapping("/add-problem")
    @ApiOperation("添加问题")
    public CommonResult<Void> addProblem( @RequestBody Problem problem) {
        problemService.addProblem(problem);
        return CommonResult.successResponse();
    }

    @PostMapping("/update-problem")
    @ApiOperation("更新问题")
    public CommonResult<Void> updateProblem( @RequestBody Problem problem) {
        problemService.updateProblem(problem);
        return CommonResult.successResponse();
    }

    @GetMapping("/delete-problem")
    @ApiOperation("删除问题")
    public CommonResult<Void> deleteProblem( @RequestParam("problemId")long problemId) {
        problemService.deleteProblem(problemId);
        return CommonResult.successResponse();
    }

}
