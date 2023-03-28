package org.example.oj.request;

import org.example.oj.response.TestcaseVO;
import lombok.Data;

import java.util.List;

/**
 * 题目请求
 */
@Data
public class ProblemRequest {

    private Integer id;

    private String name;

    private String content;

    private String htmlContent;

    private String inputDesc;

    private String outputDesc;

    private String testcaseInput;

    private String testcaseOutput;

    private Integer level;

    private Long time;

    private Long memory;

    private Integer flag;

    private String tags;

    private List<TestcaseVO> testcaseList;

    private boolean settingUpdated = false;


}
