package org.example.oj.service;

import org.example.oj.entity.CompetitionProblem;
import org.example.oj.response.CompetitionProblemVO;
import org.example.oj.response.RestResponseVO;

import java.util.List;

public interface CompetitionProblemService {

    RestResponseVO getById(Integer id);

    RestResponseVO insert(CompetitionProblem competitionProblem);

    RestResponseVO delById(Integer id);

    RestResponseVO updateById(CompetitionProblem competitionProblem);

    RestResponseVO<List<CompetitionProblemVO>> listVOByCompetitionId(Integer competitionId);

    RestResponseVO<Integer> getScoreByCompIdProblemId(Integer compId,Integer problemId);

}
