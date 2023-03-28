package org.example.oj.dao;

import org.example.oj.entity.BlogCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogCategory record);

    int insertSelective(BlogCategory record);

    BlogCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogCategory record);

    int updateByPrimaryKey(BlogCategory record);

    List<BlogCategory> listAll();

    List<BlogCategory> listByKeyWord(@Param("keyword") String keyword);
}