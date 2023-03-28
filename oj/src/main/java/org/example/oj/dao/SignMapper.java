package org.example.oj.dao;

import org.example.oj.entity.Sign;

public interface SignMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Sign record);

    int insertSelective(Sign record);

    Sign selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sign record);

    int updateByPrimaryKey(Sign record);
}