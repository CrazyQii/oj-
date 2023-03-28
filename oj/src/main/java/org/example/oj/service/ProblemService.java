package org.example.oj.service;

import com.github.pagehelper.PageInfo;
import org.example.oj.entity.Problem;
import org.example.oj.request.ProblemRequest;
import org.example.oj.response.ProblemDetailVO;
import org.example.oj.response.RestResponseVO;

import java.util.List;

public interface ProblemService {

    RestResponseVO<Problem> getById(Integer problemId);

    RestResponseVO insert(ProblemRequest problemRequest);

    RestResponseVO delById(Integer id);

    RestResponseVO<Problem> updateById(ProblemRequest problemRequest);

    RestResponseVO<PageInfo> listProblemVOToPage(Integer userId,Integer flag,Integer sort,String keyword, Integer level, String tagIds, Integer pageNum, Integer pageSize);

    RestResponseVO<List<ProblemDetailVO>> listSuggestProblem(Integer problemId, Integer row);

    RestResponseVO<Integer> randomProblemId();

    RestResponseVO<ProblemDetailVO> getDetailVOById(Integer problemId);

}
