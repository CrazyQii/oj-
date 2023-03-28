package org.example.oj.service.impl;

import org.example.oj.common.ResponseEnum;
import org.example.oj.response.RestResponseVO;
import org.example.oj.service.TestcaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestcaseServiceImpl implements TestcaseService {


    /**
     * todo 超链接
     * @param problemId
     * @return
     */
    @Override
    public RestResponseVO listTestcaseByProblemId(Integer problemId) {
        if (problemId == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        return null;
    }



}
