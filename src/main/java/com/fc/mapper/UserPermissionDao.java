package com.fc.mapper;

import com.fc.model.UserPermission;
import com.fc.model.UserPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserPermissionDao {
    long countByExample(UserPermissionExample example);

    int deleteByExample(UserPermissionExample example);

    int insert(UserPermission record);

    int insertSelective(UserPermission record);

    List<UserPermission> selectByExample(UserPermissionExample example);

    int updateByExampleSelective(@Param("record") UserPermission record, @Param("example") UserPermissionExample example);

    int updateByExample(@Param("record") UserPermission record, @Param("example") UserPermissionExample example);
}