package com.fc.mapper;

import com.fc.model.MngRoleResource;
import com.fc.model.MngRoleResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MngRoleResourceDao {
    long countByExample(MngRoleResourceExample example);

    int deleteByExample(MngRoleResourceExample example);

    int deleteByPrimaryKey(Long roleId);

    int insert(MngRoleResource record);

    int insertSelective(MngRoleResource record);

    List<MngRoleResource> selectByExample(MngRoleResourceExample example);

    MngRoleResource selectByPrimaryKey(Long roleId);

    int updateByExampleSelective(@Param("record") MngRoleResource record, @Param("example") MngRoleResourceExample example);

    int updateByExample(@Param("record") MngRoleResource record, @Param("example") MngRoleResourceExample example);

    int updateByPrimaryKeySelective(MngRoleResource record);

    int updateByPrimaryKey(MngRoleResource record);
}