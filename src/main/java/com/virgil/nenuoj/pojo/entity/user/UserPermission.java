package com.virgil.nenuoj.pojo.entity.user;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("用户权限表")
@TableName(value = "user_permission")
public class UserPermission implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "权限id")
    private long id;

    @TableField(value = "uid")
    @ApiModelProperty(value = "用户uid")
    private String uid;

    @TableField(value = "permission")
    @ApiModelProperty(value = "用户权限")
    private String permission;

    @TableField(fill = FieldFill.INSERT, value = "gmt_create")
    @ApiModelProperty(value = "创建日期")
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE, value = "gmt_modified")
    @ApiModelProperty(value = "修改日期")
    private Date gmtModified;

}
