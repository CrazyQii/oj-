package org.example.oj.dao;

import org.example.oj.entity.SystemConfig;

public interface SystemConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemConfig record);

    int insertSelective(SystemConfig record);

    SystemConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemConfig record);

    int updateByPrimaryKey(SystemConfig record);
}