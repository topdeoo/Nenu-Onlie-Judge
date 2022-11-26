package com.virgil.nenuoj.pojo.vo;

import com.virgil.nenuoj.pojo.entity.user.UserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel("返回给前端的用户类")
public class UserVO {

    @ApiModelProperty(value = "用户身份（教师/学生）")
    private String permission;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "学号")
    private String number;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "学校")
    private String school;

    @ApiModelProperty(value = "专业")
    private String course;

    @ApiModelProperty(value = "个性签名")
    private String signature;

    @ApiModelProperty(value = "真实姓名")
    private String realname;

    @ApiModelProperty(value = "github地址")
    private String github;

    @ApiModelProperty(value = "博客地址")
    private String blog;

    public UserVO( UserInfo user, String permission ){
        this.avatar = user.getAvatar();
        this.permission = permission;
        this.blog = user.getBlog();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.course = user.getCourse();
        this.gender = user.getGender();
        this.github = user.getGithub();
        this.number = user.getNumber();
        this.nickname = user.getNickname();
        this.school = user.getSchool();
        this.signature = user.getSignature();
        this.realname = user.getRealname();
    }

    @Override
    public String toString(){
        return    "username: " + username + "\n"
                + "permission: " + permission + "\n"
                + "avatar: " + avatar + "\n"
                + "email: " + email + "\n"
                + "blog: " + blog + "\n"
                + "course: " + course + "\n"
                + "number: " + number + "\n"
                + "nickname: " + nickname + "\n"
                + "school: " + school + "\n"
                + "signature: " + signature + "\n"
                + "github: " + github + "\n"
                + "gender: " + gender + "\n"
                + "realname" + realname;
    }

}
