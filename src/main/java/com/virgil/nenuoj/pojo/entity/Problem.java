package com.virgil.nenuoj.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@TableName("problem")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(description = "题目")
public class Problem implements Serializable {

    @ApiModelProperty(value = "题目id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "题目标题")
    @TableField(value = "title")
    private String title;

    @ApiModelProperty(value = "题目描述")
    @TableField(value = "description")
    private String description;

    @ApiModelProperty(value = "题目作者")
    @TableField(value = "author")
    private String author;

    @ApiModelProperty(value = "判题模式")
    @TableField(value = "judge_mode")
    private String judgeMode;

    @ApiModelProperty(value = "时间限制")
    @TableField(value = "time_limit")
    private int timeLimit;

    @ApiModelProperty(value = "内存限制")
    @TableField(value = "memory_limit")
    private int memoryLimit;

    @ApiModelProperty(value = "输入描述")
    @TableField(value = "input")
    private String input;

    @ApiModelProperty(value = "输出描述")
    @TableField(value = "output")
    private String output;

    @ApiModelProperty(value = "样例")
    @TableField(value = "examples")
    private String example;

    @ApiModelProperty(value = "提示")
    @TableField(value = "hint")
    private String hint;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private String gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private String gmtModified;

}
