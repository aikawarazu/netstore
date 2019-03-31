package com.fc.mapper;

import com.fc.model.UserInfoDetail;
import com.fc.model.UserInfoDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserInfoDetailDao {
    long countByExample(UserInfoDetailExample example);

    int deleteByExample(UserInfoDetailExample example);

    int insert(UserInfoDetail record);

    int insertSelective(UserInfoDetail record);

    List<UserInfoDetail> selectByExample(UserInfoDetailExample example);

    int updateByExampleSelective(@Param("record") UserInfoDetail record, @Param("example") UserInfoDetailExample example);

    int updateByExample(@Param("record") UserInfoDetail record, @Param("example") UserInfoDetailExample example);
}