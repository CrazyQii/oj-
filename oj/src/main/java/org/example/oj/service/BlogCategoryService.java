package org.example.oj.service;

import com.github.pagehelper.PageInfo;
import org.example.oj.entity.BlogCategory;
import org.example.oj.response.RestResponseVO;

import java.util.List;

public interface BlogCategoryService {

    RestResponseVO getById(Integer id);

    RestResponseVO insert(BlogCategory blogCategory);

    RestResponseVO delById(Integer id);

    RestResponseVO updateById(BlogCategory blogCategory);

    RestResponseVO<List<BlogCategory>> listAll();

    RestResponseVO<PageInfo> listBlogCategory2Page(Integer pageNum, Integer pageSize, String keyword);
}
