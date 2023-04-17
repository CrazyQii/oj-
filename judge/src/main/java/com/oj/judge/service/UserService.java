package com.oj.judge.service;

import com.oj.judge.response.ServerResponse;
import com.oj.judge.common.JudgeStatusEnum;

public interface UserService {

    ServerResponse addCount(Integer userId, JudgeStatusEnum statusConst);

    ServerResponse addSolutionCountAndGoldCountAndRating(Integer userId,Integer problemId,Integer goldCount,Integer ratingCount);

}
