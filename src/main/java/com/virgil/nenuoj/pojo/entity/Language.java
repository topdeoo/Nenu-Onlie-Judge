package com.virgil.nenuoj.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("language")
@ApiModel(description = "语言")
public class Language {

        @ApiModelProperty(value = "语言id")
        @TableId(value = "id", type = IdType.ASSIGN_ID)
        private String id;

        @ApiModelProperty(value = "语言名称")
        @TableField(value = "name")
        private String name;

        @ApiModelProperty(value = "语言编译命令")
        @TableField(value = "compiler")
        private String compiler;

}
