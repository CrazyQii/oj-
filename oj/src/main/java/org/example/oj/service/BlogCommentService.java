package org.example.oj.service;

import com.github.pagehelper.PageInfo;
import org.example.oj.response.RestResponseVO;
import org.example.oj.entity.BlogComment;

public interface BlogCommentService {
    RestResponseVO getById(Integer blogCommentId);

    RestResponseVO insert(BlogComment blogComment);

    RestResponseVO delById(Integer id);

    RestResponseVO updateById(BlogComment blogComment);

    RestResponseVO<PageInfo> listByBlogId2Page(Integer userId,Integer sort, Integer pageNum, Integer pageSize, Integer blogId);
}
