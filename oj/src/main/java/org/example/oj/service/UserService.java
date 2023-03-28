package org.example.oj.service;


import com.github.pagehelper.PageInfo;
import org.example.oj.entity.Blog;
import org.example.oj.entity.Problem;
import org.example.oj.request.UserRequest;
import org.example.oj.response.ProblemResultRecentVO;
import org.example.oj.response.RestResponseVO;
import org.example.oj.response.UserDetailVO;

import java.util.List;

public interface UserService {

    RestResponseVO addSignCount(Integer userId);

    RestResponseVO<UserDetailVO> getById(Integer userId);

    RestResponseVO insert(UserRequest request);

    RestResponseVO delById(Integer id);

    RestResponseVO lockById(Integer id);

    RestResponseVO activeById(Integer id);

    RestResponseVO updateById(UserRequest request);

//    RestResponseVO sendRegisterEmail(String email);
//
//    RestResponseVO sendForgetEmail(String email);

    RestResponseVO forgetRestPassword(String token,String email,String password);

    RestResponseVO register(UserRequest request);

    RestResponseVO<PageInfo> listRankUser2Page(Integer pageNum, Integer pageSize, String keyword);

    RestResponseVO checkLoginByAdmin(String username,String password);

    RestResponseVO<PageInfo> listUser2Page(Integer pageNum,Integer pageSize,String keyword);

    RestResponseVO<List<Problem>> listAllSolveProblemByUserId(Integer userId);

    RestResponseVO<List<Blog>> listRecentBlog(Integer userId, Integer recentSize);

    RestResponseVO<List<ProblemResultRecentVO>> listRecentProblem(Integer userId, Integer recentSize);

    RestResponseVO listProblemRecord(Integer userId, Integer flag);

    RestResponseVO updateSecurity(Integer id, String email, String oldPassword, String password);
}
