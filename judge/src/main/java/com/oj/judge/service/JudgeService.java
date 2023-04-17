package com.oj.judge.service;

import com.oj.judge.entity.ProblemResult;

import java.util.List;

public interface JudgeService {


    String compile(ProblemResult problemResult);

    void execute(ProblemResult problemResult,String userDirPath);

}
