package org.example.oj.service;

import org.example.oj.response.RestResponseVO;

public interface TestcaseService {

     RestResponseVO listTestcaseByProblemId(Integer problemId);


}
