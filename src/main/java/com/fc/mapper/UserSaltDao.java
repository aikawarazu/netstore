package com.fc.mapper;

import com.fc.model.UserSalt;
import com.fc.model.UserSaltExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserSaltDao {
    long countByExample(UserSaltExample example);

    int deleteByExample(UserSaltExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(UserSalt record);

    int insertSelective(UserSalt record);

    List<UserSalt> selectByExample(UserSaltExample example);

    UserSalt selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") UserSalt record, @Param("example") UserSaltExample example);

    int updateByExample(@Param("record") UserSalt record, @Param("example") UserSaltExample example);

    int updateByPrimaryKeySelective(UserSalt record);

    int updateByPrimaryKey(UserSalt record);
}