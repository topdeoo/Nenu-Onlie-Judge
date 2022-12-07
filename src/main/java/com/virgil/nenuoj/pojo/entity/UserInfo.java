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
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(description = "用户信息")
@TableName(value = "user_info")
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {

    @TableId(value = "uid", type = IdType.ASSIGN_ID)
    private String uid;

    @TableField(value = "username")
    @ApiModelProperty(value = "用户名")
    private String username;

    @TableField(value = "password")
    @ApiModelProperty(value = "密码")
    private String password;

    @TableField(updateStrategy = FieldStrategy.IGNORED, value = "nickname")
    @ApiModelProperty(value = "昵称")
    private String nickname;

    @TableField(updateStrategy = FieldStrategy.IGNORED, value = "school")
    @ApiModelProperty(value = "学校")
    private String school;

    @TableField(updateStrategy = FieldStrategy.IGNORED, value = "course")
    @ApiModelProperty(value = "专业")
    private String course;

    @TableField(updateStrategy = FieldStrategy.IGNORED, value = "number")
    @ApiModelProperty(value = "学号")
    private String number;

    @TableField(updateStrategy = FieldStrategy.IGNORED, value = "gender")
    @ApiModelProperty(value = "性别")
    private String gender;

    @TableField(updateStrategy = FieldStrategy.IGNORED, value = "realname")
    @ApiModelProperty(value = "真实姓名")
    private String realname;

    @TableField(updateStrategy = FieldStrategy.IGNORED, value = "github")
    @ApiModelProperty(value = "github地址")
    private String github;

    @TableField(updateStrategy = FieldStrategy.IGNORED, value = "blog")
    @ApiModelProperty(value = "博客地址")
    private String blog;

    @TableField(updateStrategy = FieldStrategy.IGNORED, value = "email")
    @ApiModelProperty(value = "邮箱")
    private String email;

    @TableField(value = "avatar")
    @ApiModelProperty(value = "头像地址")
    private String avatar;

    @TableField(updateStrategy = FieldStrategy.IGNORED, value = "signature")
    @ApiModelProperty(value = "个性介绍")
    private String signature;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT, value = "gmt_create")
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE, value = "gmt_modified")
    private Date gmtModified;

    @TableField(value = "email_verified")
    @ApiModelProperty(value = "邮箱是否验证")
    private int emailVerified;


    public UserInfo(String username, String password){
        this.username = username;
        this.password = password;
    }

}
