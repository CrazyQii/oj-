package org.example.oj.service;

import com.github.pagehelper.PageInfo;
import org.example.oj.response.ProblemResultDetailVO;
import org.example.oj.response.ProblemResultSubmitVO;
import org.example.oj.response.RestResponseVO;
import org.example.oj.entity.ProblemResult;

public interface ProblemResultService {

    RestResponseVO<ProblemResult> getById(Integer problemResultId);

    RestResponseVO delById(Integer problemResultId);

    RestResponseVO insert(ProblemResult problemResult);


    RestResponseVO listProblemResult2Page(Integer problemId, String name, String type, Integer status, Integer pageNum,Integer pageSize);

    RestResponseVO<ProblemResultSubmitVO> getByRunNum2SubmitVO(String runNum);

    RestResponseVO<ProblemResultDetailVO> getById2DetailVO(Integer problemResultId);

    RestResponseVO<PageInfo> listProblemResultCompetitionVO2Page(Integer pageNum, Integer pageSize, Integer userId, Integer compId);
}
