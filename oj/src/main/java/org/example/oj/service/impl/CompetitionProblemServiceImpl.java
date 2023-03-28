package org.example.oj.service.impl;

import org.example.oj.common.ResponseEnum;
import org.example.oj.common.StringConst;
import org.example.oj.dao.CompetitionProblemMapper;
import org.example.oj.entity.CompetitionProblem;
import org.example.oj.response.CompetitionProblemVO;
import org.example.oj.response.RestResponseVO;
import org.example.oj.service.CompetitionProblemService;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionProblemServiceImpl implements CompetitionProblemService {

    @Resource
    private CompetitionProblemMapper competitionProblemMapper;
    @Override
    public RestResponseVO getById(Integer id) {
        if (id == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        CompetitionProblem competitionProblem = competitionProblemMapper.selectByPrimaryKey(id);
        return RestResponseVO.createBySuccess(competitionProblem);
    }

    @Override
    public RestResponseVO insert(CompetitionProblem competitionProblem) {
        if (competitionProblem == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }

        CompetitionProblem competitionProblemFromDB = competitionProblemMapper.getByCompIdProblemId(competitionProblem.getCompId(), competitionProblem.getProblemId());
        if (competitionProblemFromDB != null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.COMPETITION_PROBLEM_REPEATED_ERROR);
        }
        int effect = competitionProblemMapper.insertSelective(competitionProblem);
        return effect > 0 ? RestResponseVO.createBySuccessMessage(StringConst.ADD_SUCCESS)
                : RestResponseVO.createByErrorMessage(StringConst.ADD_FAIL);
    }

    @Override
    public RestResponseVO delById(Integer id) {
        if (id == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        int effect = competitionProblemMapper.deleteByPrimaryKey(id);
        return effect > 0 ? RestResponseVO.createBySuccessMessage(StringConst.DEL_SUCCESS)
                : RestResponseVO.createByErrorMessage(StringConst.DEL_FAIL);
    }

    @Override
    public RestResponseVO updateById(CompetitionProblem competitionProblem) {
        if (competitionProblem == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        int effect = competitionProblemMapper.updateByPrimaryKeySelective(competitionProblem);
        return effect > 0 ? RestResponseVO.createBySuccessMessage(StringConst.UPDATE_SUCCESS)
                : RestResponseVO.createByErrorMessage(StringConst.UPDATE_FAIL);
    }

    @Override
    public RestResponseVO<List<CompetitionProblemVO>> listVOByCompetitionId(Integer competitionId) {
        if (competitionId == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }

        List<CompetitionProblemVO> competitionProblemVOList = competitionProblemMapper.listVOByCompetitionId(competitionId);
        return RestResponseVO.createBySuccess(competitionProblemVOList);
    }

    @Override
    public RestResponseVO<Integer> getScoreByCompIdProblemId(Integer compId, Integer problemId) {
        if (compId == null || problemId == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        Integer score = competitionProblemMapper.getScoreByCompIdProblemId(compId, problemId);
        return RestResponseVO.createBySuccess(score);
    }
}
