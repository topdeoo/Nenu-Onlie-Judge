package com.virgil.nenuoj.controller;

import com.virgil.nenuoj.common.result.CommonResult;
import com.virgil.nenuoj.pojo.entity.Poster;
import com.virgil.nenuoj.pojo.vo.PosterVO;
import com.virgil.nenuoj.service.common.PosterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("首页通知相关 API 说明")
@RestController
@RequestMapping("/poster")
public class PosterController {

    @Autowired
    private PosterService posterService;

    @GetMapping("/list")
    @ApiOperation("获取通知列表")
    public PosterVO getPosterList( @RequestParam(value = "current", defaultValue = "1") int current,
                                   @RequestParam(value = "size", defaultValue = "5") int size ) {
        return posterService.getPosterList(current, size);
    }

    @GetMapping("/details")
    @ApiOperation("获取通知详情")
    public Poster getPosterDetails( @RequestParam("id") int id ) {
        return posterService.getPosterDetails(id);
    }

    @PostMapping("/add")
    @ApiOperation("添加通知")
    public CommonResult<Void> addPoster( @RequestBody Poster poster ) {
        posterService.addPoster(poster);
        return CommonResult.successResponse();
    }

    @GetMapping("/delete")
    @ApiOperation("删除通知")
    public CommonResult<Void> deletePoster( @RequestParam("id") int id ) {
        posterService.deletePoster(id);
        return CommonResult.successResponse();
    }

    @PostMapping("/modify")
    @ApiOperation("修改通知")
    public CommonResult<Void> modifyPoster( @RequestBody Poster poster ) {
        posterService.modifyPoster(poster);
        return CommonResult.successResponse();
    }

}
