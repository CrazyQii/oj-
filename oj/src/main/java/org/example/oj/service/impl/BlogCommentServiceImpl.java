package org.example.oj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.oj.common.ResponseEnum;
import org.example.oj.dao.UpMapper;
import org.example.oj.entity.Up;
import org.example.oj.response.BlogCommentVO;
import org.example.oj.response.RestResponseVO;
import org.example.oj.common.StringConst;
import org.example.oj.dao.BlogCommentMapper;
import org.example.oj.entity.BlogComment;
import org.example.oj.service.BlogCommentService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogCommentServiceImpl implements BlogCommentService {
    @Resource
    private BlogCommentMapper blogCommentMapper;

    @Resource
    private UpMapper upMapper;

    @Override
    public RestResponseVO getById(Integer blogCommentId) {
        if (blogCommentId == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        BlogComment blogComment = blogCommentMapper.selectByPrimaryKey(blogCommentId);
        return RestResponseVO.createBySuccess(blogComment);
    }

    @Override
    public RestResponseVO insert(BlogComment blogComment) {
        if (blogComment == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        int effect = blogCommentMapper.insertSelective(blogComment);
        return effect > 0 ? RestResponseVO.createBySuccessMessage(StringConst.ADD_SUCCESS)
                : RestResponseVO.createByErrorMessage(StringConst.ADD_FAIL);
    }

    @Override
    public RestResponseVO delById(Integer id) {
        if (id == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        int effect = blogCommentMapper.deleteByPrimaryKey(id);
        return effect > 0 ? RestResponseVO.createBySuccessMessage(StringConst.DEL_SUCCESS)
                : RestResponseVO.createByErrorMessage(StringConst.DEL_FAIL);
    }

    @Override
    public RestResponseVO updateById(BlogComment blogComment) {
        if (blogComment == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        int effect = blogCommentMapper.updateByPrimaryKeySelective(blogComment);
        return effect > 0 ? RestResponseVO.createBySuccessMessage(StringConst.UPDATE_SUCCESS)
                : RestResponseVO.createByErrorMessage(StringConst.UPDATE_FAIL);
    }

    @Override
    public RestResponseVO<PageInfo> listByBlogId2Page(Integer userId, Integer sort, Integer pageNum, Integer pageSize, Integer blogId) {
        PageHelper.startPage(pageNum, pageSize);
        List<BlogCommentVO> blogCommentVOList = blogCommentMapper.listBlogCommentVO(sort, blogId);
        if (userId != null) {
            for (BlogCommentVO blogCommentVO : blogCommentVOList) {
                Up up = upMapper.getByBlogCommentIdUserId(blogCommentVO.getId(), userId);
                if (up != null) {
                    blogCommentVO.setUserUpStatus(up.getStatus());
                }
            }
        }
        PageInfo<BlogCommentVO> pageInfo = new PageInfo<>(blogCommentVOList);
        return RestResponseVO.createBySuccess(pageInfo);
    }
}
