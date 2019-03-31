package com.fc.mapper;

import com.fc.model.MngResource;
import com.fc.model.MngResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MngResourceDao {
    long countByExample(MngResourceExample example);

    int deleteByExample(MngResourceExample example);

    int deleteByPrimaryKey(String resourceId);

    int insert(MngResource record);

    int insertSelective(MngResource record);

    List<MngResource> selectByExample(MngResourceExample example);

    MngResource selectByPrimaryKey(String resourceId);

    int updateByExampleSelective(@Param("record") MngResource record, @Param("example") MngResourceExample example);

    int updateByExample(@Param("record") MngResource record, @Param("example") MngResourceExample example);

    int updateByPrimaryKeySelective(MngResource record);

    int updateByPrimaryKey(MngResource record);
}