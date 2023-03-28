package org.example.oj.service;

import com.github.pagehelper.PageInfo;
import org.example.oj.response.BlogDetailVO;
import org.example.oj.response.BlogVO;
import org.example.oj.response.RestResponseVO;
import org.example.oj.entity.Blog;

import java.util.List;

public interface BlogService {

    RestResponseVO<Blog> getById(Integer blogId);

    RestResponseVO insert(Blog blog);

    RestResponseVO delById(Integer id);

    RestResponseVO updateById(Blog blog);

    RestResponseVO<PageInfo> listBlogVO2Page(Integer pageNum, Integer pageSize,Integer sort, String keyword, Integer bcId);

    RestResponseVO<BlogDetailVO> getBlogDetailVOById(Integer blogId, Integer userId);

    RestResponseVO updateViewCount(Integer blogId);

    RestResponseVO<List<BlogVO>> listHotBlogVO(Integer pageSize);

    RestResponseVO<List<BlogVO>> listLastCommentBlogVO(Integer pageSize);

    RestResponseVO<List<BlogVO>> listNoticeBlogVO(Integer pageSize);

}
