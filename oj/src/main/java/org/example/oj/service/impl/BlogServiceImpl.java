package org.example.oj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.oj.common.ResponseEnum;
import org.example.oj.dao.BlogCommentMapper;
import org.example.oj.dao.UpMapper;
import org.example.oj.entity.Up;
import org.example.oj.response.BlogDetailVO;
import org.example.oj.response.BlogVO;
import org.example.oj.response.RestResponseVO;
import org.example.oj.common.StringConst;
import org.example.oj.dao.BlogMapper;
import org.example.oj.entity.Blog;
import org.example.oj.service.BlogService;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Resource
    private BlogMapper blogMapper;

    @Resource
    private UpMapper upMapper;

    @Resource
    private BlogCommentMapper blogCommentMapper;



    @Override
    public RestResponseVO<Blog> getById(Integer blogId) {
        if (blogId == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        Blog blog = blogMapper.selectByPrimaryKey(blogId);
        return RestResponseVO.createBySuccess(blog);
    }

    @Override
    public RestResponseVO insert(Blog blog) {
        if (blog == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        int effect = blogMapper.insertSelective(blog);
        return effect > 0 ? RestResponseVO.createBySuccessMessage(StringConst.ADD_SUCCESS)
                : RestResponseVO.createByErrorMessage(StringConst.ADD_FAIL);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RestResponseVO delById(Integer id) {
        if (id == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        int effect = blogMapper.deleteByPrimaryKey(id);
        if (effect > 0) {
            upMapper.deleteByBlogId(id);
            blogCommentMapper.deleteByBlogId(id);
            return RestResponseVO.createBySuccessMessage(StringConst.DEL_SUCCESS);
        }
        return RestResponseVO.createByErrorMessage(StringConst.DEL_FAIL);
    }

    @Override
    public RestResponseVO updateById(Blog blog) {
        if (blog == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        int effect = blogMapper.updateByPrimaryKeySelective(blog);
        return effect > 0 ? RestResponseVO.createBySuccessMessage(StringConst.UPDATE_SUCCESS)
                : RestResponseVO.createByErrorMessage(StringConst.UPDATE_FAIL);
    }

    @Override
    public RestResponseVO<PageInfo> listBlogVO2Page(Integer pageNum, Integer pageSize,Integer sort, String keyword, Integer bcId) {
        PageHelper.startPage(pageNum, pageSize, true);
        List<BlogVO> list = blogMapper.list2BlogVO(sort,keyword, bcId);
        PageInfo<BlogVO> pageInfo = new PageInfo<>(list);
        return RestResponseVO.createBySuccess(pageInfo);
    }

    @Override
    public RestResponseVO<BlogDetailVO> getBlogDetailVOById(Integer blogId, Integer userId) {
        if (blogId == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        BlogDetailVO blogDetailVO = blogMapper.getBlogDetailVO(blogId);
        if (userId != null) {
            Up up = upMapper.getByBlogIdUserId(blogId, userId);
            if (up != null) {
                blogDetailVO.setUserUpStatus(up.getStatus());
            }
        }
        return RestResponseVO.createBySuccess(blogDetailVO);
    }

    @Override
    public RestResponseVO updateViewCount(Integer blogId) {
        if (blogId == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        int effect = blogMapper.updateViewCountIncrease(blogId);
        return effect > 0 ? RestResponseVO.createBySuccessMessage(StringConst.UPDATE_SUCCESS)
                : RestResponseVO.createByErrorMessage(StringConst.UPDATE_FAIL);
    }

    @Override
    public RestResponseVO listHotBlogVO(Integer pageSize) {
        List<BlogVO> blogVOList = blogMapper.listHotBlogVO(pageSize);
        return RestResponseVO.createBySuccess(blogVOList);
    }

    @Override
    public RestResponseVO<List<BlogVO>> listLastCommentBlogVO(Integer pageSize) {
        List<BlogVO> blogVOList = blogMapper.listLastCommentBlogVO(pageSize);
        return RestResponseVO.createBySuccess(blogVOList);
    }

    @Override
    public RestResponseVO<List<BlogVO>> listNoticeBlogVO(Integer pageSize) {
        List<BlogVO> blogVOList = blogMapper.listNoticeBlogVO(pageSize);
        return RestResponseVO.createBySuccess(blogVOList);
    }
}
