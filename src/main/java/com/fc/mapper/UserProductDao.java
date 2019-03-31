package com.fc.mapper;

import com.fc.model.UserProduct;
import com.fc.model.UserProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserProductDao {
    long countByExample(UserProductExample example);

    int deleteByExample(UserProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserProduct record);

    int insertSelective(UserProduct record);

    List<UserProduct> selectByExample(UserProductExample example);

    UserProduct selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserProduct record, @Param("example") UserProductExample example);

    int updateByExample(@Param("record") UserProduct record, @Param("example") UserProductExample example);

    int updateByPrimaryKeySelective(UserProduct record);

    int updateByPrimaryKey(UserProduct record);
}