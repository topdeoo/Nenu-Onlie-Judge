package com.virgil.nenuoj.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.virgil.nenuoj.pojo.entity.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMappers extends BaseMapper<UserInfo> {

    @Select({"select permission from user_permission where uid=#{uid}"})
    String selectPermissionById( @Param ("uid") String uid );

}
