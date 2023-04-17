package com.oj.judge.service.impl;

import com.oj.judge.dao.RegisterMapper;
import com.oj.judge.response.ServerResponse;
import com.oj.judge.service.RegisterService;
import org.apache.ibatis.annotations.Param;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Resource
    private RegisterMapper registerMapper;


    @Override
    public ServerResponse addSolutionCountByProblemIdCompIdUserId(Integer problemId,Integer compId, Integer userId) {
        int effect = registerMapper.addSolutionCountByProblemIdCompIdUserId(problemId,compId, userId);
        return effect > 0 ? ServerResponse.success() : ServerResponse.fail();
    }



    @Override
    public ServerResponse addSubmitCountByCompIdUserId(Integer compId, Integer userId) {
        int effect = registerMapper.addSubmitCountByCompIdUserId(compId, userId);
        return effect > 0 ? ServerResponse.success() : ServerResponse.fail();
    }

    @Override
    public ServerResponse addAcCountByCompIdUserId(Integer compId, Integer userId) {
        int effect = registerMapper.addAcCountByCompIdUserId(compId, userId);
        return effect > 0 ? ServerResponse.success() : ServerResponse.fail();
    }

    @Override
    public ServerResponse updateScore(Integer score, Integer compId, Integer userId) {
        int effect = registerMapper.updateScoreByCompIdUserId(score,compId,userId);
        return effect > 0 ? ServerResponse.success() : ServerResponse.fail();
    }


}
