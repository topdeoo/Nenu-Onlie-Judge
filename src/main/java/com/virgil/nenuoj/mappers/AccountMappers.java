package com.virgil.nenuoj.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.virgil.nenuoj.pojo.entity.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

@Mapper
public interface AccountMappers extends BaseMapper<UserInfo> {

    @Select({"select permission from user_permission where uid=#{uid}"})
    String selectPermissionById( @Param ("uid") String uid );

    @Insert({"insert into user_permission(uid, permission, gmt_create, gmt_modified) " +
            "values(#{uid}, #{permission}, #{gmtCreate}, #{gmtCreate})"})
    void insertToPermission( @Param("uid")String uid, @Param("permission")String permission, @Param("gmtCreate")Date gmtCreate);
}
