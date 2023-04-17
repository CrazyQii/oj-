package com.oj.judge.service;

import com.oj.judge.response.ServerResponse;

import java.util.List;


public interface CompetitionProblemService {

    Integer getScoreByCompIdProblemId(Integer compId,Integer problemId);

    ServerResponse addAcCountByCompIdProblemId(Integer compId,Integer problemId);

    ServerResponse addSubmitCountByCompIdProblemId(Integer compId,Integer problemId);

}
