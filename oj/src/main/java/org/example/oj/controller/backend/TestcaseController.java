package org.example.oj.controller.backend;

import org.example.oj.response.RestResponseVO;
import org.example.oj.service.FileService;
import org.example.oj.service.TestcaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller("backendTestcase")
@RequestMapping("/backend/testcase")
public class TestcaseController {

    @Resource
    private TestcaseService testcaseService;

    @Resource
    private FileService fileService;

    @RequestMapping("/list2PageByProblemId")
    @ResponseBody
    public RestResponseVO list2PageByProblemId(Integer problemId){
        return testcaseService.listTestcaseByProblemId(problemId);
    }



    /**
     * 跳转到测试用例列表页面
     *
     * @return
     */
    @RequestMapping("/testcaseListPage")
    public String userListPage(HttpServletRequest request) {
        request.setAttribute("questionActive",true);
        request.setAttribute("testcaseActive",true);
        return "backend/testcase/testcase-list";
    }

    /**
     * 添加－更新测试用例
     *
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public RestResponseVO save(Integer problemId,Integer num,String testcaseInput,String testcaseOutput) {
        return fileService.saveTestcase(problemId,num,testcaseInput,testcaseOutput);
    }

    /**
     * 删除测试用例
     *
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public RestResponseVO delete(Integer problemId,Integer num) {
        return fileService.deleteTestcase(problemId,num);
    }


    /**
     * 获取testcaseList
     *
     * @return
     */
    @RequestMapping("/listTestcase")
    @ResponseBody
    public RestResponseVO listTestcase(Integer problemId) {
        return fileService.listTestcaseVO(problemId);
    }



}
