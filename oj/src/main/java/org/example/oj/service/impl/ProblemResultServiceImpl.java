package org.example.oj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.oj.common.JudgeStatusEnum;
import org.example.oj.common.ResponseEnum;
import org.example.oj.dao.TestcaseResultMapper;
import org.example.oj.entity.TestcaseResult;
import org.example.oj.response.*;
import org.example.oj.common.StringConst;
import org.example.oj.dao.ProblemResultMapper;
import org.example.oj.entity.ProblemResult;
import org.example.oj.service.CompetitionProblemService;
import org.example.oj.service.ProblemResultService;
import org.apache.commons.lang3.StringUtils;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemResultServiceImpl implements ProblemResultService {
    @Resource
    private ProblemResultMapper problemResultMapper;

    @Resource
    private TestcaseResultMapper testcaseResultMapper;

    @Resource
    private CompetitionProblemService competitionProblemService;


    @Override
    public RestResponseVO<ProblemResult> getById(Integer problemResultId) {
        if (problemResultId == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        ProblemResult problemResult = problemResultMapper.selectByPrimaryKey(problemResultId);
        return problemResult != null ? RestResponseVO.createBySuccess(problemResult)
                : RestResponseVO.createByError();
    }

    @Override
    public RestResponseVO delById(Integer problemResultId) {
        if (problemResultId == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        int effect = problemResultMapper.deleteByPrimaryKey(problemResultId);
        return effect > 0 ? RestResponseVO.createBySuccessMessage(StringConst.DEL_SUCCESS)
                : RestResponseVO.createByErrorMessage(StringConst.DEL_FAIL);
    }

    @Override
    public RestResponseVO insert(ProblemResult problemResult) {
        if (problemResult == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        int effect = problemResultMapper.insertSelective(problemResult);
        return effect > 0 ? RestResponseVO.createBySuccessMessage(StringConst.DEL_SUCCESS)
                : RestResponseVO.createByErrorMessage(StringConst.DEL_FAIL);
    }

    @Override
    public RestResponseVO<PageInfo> listProblemResult2Page(Integer problemId, String name, String type, Integer status, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, true);
        List<ProblemResultVO> problemResultList = problemResultMapper.listProblemResult(problemId, name, type, status);
        for (ProblemResultVO problemResultVO : problemResultList) {
            List<TestcaseResult> testcaseResultList = testcaseResultMapper.listByProblemResultId(problemResultVO.getId());
            if (testcaseResultList != null) {
                int acCount = 0;
                for (TestcaseResult testcaseResult : testcaseResultList) {
                    if (JudgeStatusEnum.ACCEPTED.getStatus().equals(testcaseResult.getStatus())) {
                        acCount++;
                    }
                }
                int score = (int) ((acCount * 1.0 / testcaseResultList.size()) * 100);
                problemResultVO.setScore(score);
            }

        }
        PageInfo<ProblemResultVO> pageInfo = new PageInfo<ProblemResultVO>(problemResultList);
        return RestResponseVO.createBySuccess(pageInfo);
    }

    @Override
    public RestResponseVO<ProblemResultSubmitVO> getByRunNum2SubmitVO(String runNum) {
        if (StringUtils.isBlank(runNum)) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        ProblemResultSubmitVO resultSubmitVO = problemResultMapper.getByRunNum2SubmitVO(runNum);
        return RestResponseVO.createBySuccess(resultSubmitVO);
    }

    @Override
    public RestResponseVO<ProblemResultDetailVO> getById2DetailVO(Integer problemResultId) {
        if (problemResultId == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        ProblemResultDetailVO detailVO = problemResultMapper.getById2DetailVO(problemResultId);
        if (detailVO != null) {
            List<TestcaseResult> testcaseResultList = detailVO.getTestcaseResultList();
            if (testcaseResultList != null) {
                for (TestcaseResult testcaseResult : testcaseResultList) {
                    if (JudgeStatusEnum.ACCEPTED.getStatus().equals(testcaseResult.getStatus())) {
                        detailVO.setAcCount(detailVO.getAcCount() + 1);
                    }
                }
                int score = (int) ((detailVO.getAcCount() * 1.0 / testcaseResultList.size()) * 100);
                detailVO.setScore(score);
            }
        }

        return RestResponseVO.createBySuccess(detailVO);
    }

    @Override
    public RestResponseVO<PageInfo> listProblemResultCompetitionVO2Page(Integer pageNum, Integer pageSize, Integer userId, Integer compId) {
        if (pageNum == null || pageSize == null || userId == null || compId == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        PageHelper.startPage(pageNum, pageSize, true);

        List<ProblemResultCompetitionVO> list = problemResultMapper.listProblemResultCompetitionVO2Page(compId, userId);
        for (ProblemResultCompetitionVO dataVO : list) {
            List<TestcaseResult> testcaseResultList = testcaseResultMapper.listByProblemResultId(dataVO.getId());
            for (TestcaseResult testcaseResult : testcaseResultList) {
                if (JudgeStatusEnum.ACCEPTED.getStatus().equals(testcaseResult.getStatus())) {
                    dataVO.setAcCount(dataVO.getAcCount() + 1);
                }
            }
            Integer score = competitionProblemService.getScoreByCompIdProblemId(compId, dataVO.getProblemId()).getData();
            if (score != null) {
                score = (int) ((dataVO.getAcCount() * 1.0 / testcaseResultList.size()) * score);
                dataVO.setScore(dataVO.getScore() + score);
            }

        }

        PageInfo<ProblemResultCompetitionVO> pageInfo = new PageInfo<>(list);

        return RestResponseVO.createBySuccess(pageInfo);
    }


    /**
     * todo
     * @param testcaseResultList
     * @return
     */
    private int getProblemResultBaseScore(List<TestcaseResult> testcaseResultList) {
        return testcaseResultList.size() == 5 ? 20 : testcaseResultList.size() == 10 ? 10 : 0;
    }

}
