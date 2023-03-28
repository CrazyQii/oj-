package org.example.oj.controller.portal;

import com.github.pagehelper.PageInfo;
import org.example.oj.common.ResponseEnum;
import org.example.oj.entity.User;
import org.example.oj.response.RestResponseVO;
import org.example.oj.response.UpVO;
import org.example.oj.service.BlogCommentService;
import org.example.oj.service.UpService;
import javax.annotation.Resource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/blogComment")
@Controller
public class BlogCommentController {

    @Resource
    private BlogCommentService blogCommentService;

    @Resource
    private UpService upService;

    /**
     * 博客评论List
     *
     * @param userDetails
     * @param pageNum
     * @param pageSize
     * @param sort
     * @param blogId
     * @return
     */
    @RequestMapping("/listBlogComment2Page")
    @ResponseBody
    public RestResponseVO<PageInfo> listBlogComment2Page(@AuthenticationPrincipal UserDetails userDetails,
                                                         @RequestParam(defaultValue = "1") Integer pageNum,
                                                         @RequestParam(defaultValue = "10") Integer pageSize,
                                                         @RequestParam(defaultValue = "-1") Integer sort,
                                                         Integer blogId) {
        Integer userId = null;
        if (userDetails != null) {
            userId = ((User) userDetails).getId();
        }
        return blogCommentService.listByBlogId2Page(userId, sort, pageNum, pageSize, blogId);
    }


    /**
     * 博客评论点赞
     *
     * @param blogCommentId
     * @param userDetails
     * @return
     */
    @RequestMapping("/blogCommentUp")
    @ResponseBody
    public RestResponseVO<UpVO> blogUp(Integer blogCommentId, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.UNAUTHORIZED);
        }
        User user = (User) userDetails;
        return upService.blogCommentUp(blogCommentId, user.getId());
    }


}
