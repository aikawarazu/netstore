package com.fc.mapper;

import com.fc.model.UserIdentification;
import com.fc.model.UserIdentificationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserIdentificationDao {
    long countByExample(UserIdentificationExample example);

    int deleteByExample(UserIdentificationExample example);

    int insert(UserIdentification record);

    int insertSelective(UserIdentification record);

    List<UserIdentification> selectByExample(UserIdentificationExample example);

    int updateByExampleSelective(@Param("record") UserIdentification record, @Param("example") UserIdentificationExample example);

    int updateByExample(@Param("record") UserIdentification record, @Param("example") UserIdentificationExample example);
}