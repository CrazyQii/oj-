package com.oj.judge.service;

import com.oj.judge.response.ServerResponse;

public interface RegisterService {

    ServerResponse addSolutionCountByProblemIdCompIdUserId(Integer problemId,Integer compId, Integer userId);

    ServerResponse addSubmitCountByCompIdUserId(Integer compId, Integer userId);

    ServerResponse addAcCountByCompIdUserId(Integer compId, Integer userId);

    ServerResponse updateScore(Integer score,Integer compId, Integer userId);

}
