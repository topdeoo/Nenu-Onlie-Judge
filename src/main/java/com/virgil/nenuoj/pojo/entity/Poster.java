package com.virgil.nenuoj.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "poster")
public class Poster implements Serializable {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private long id;

    @TableField(value = "title")
    private String title;

    @TableField(value = "content")
    private String content;

    @TableField(value = "author")
    private String author;

    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

}
