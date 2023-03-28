package org.example.oj.controller.portal;

import com.github.pagehelper.PageInfo;
import org.example.oj.response.RestResponseVO;
import org.example.oj.service.RegisterService;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Resource
    private RegisterService registerService;

    /**
     * 用户比赛排名
     * @param pageNum
     * @param pageSize
     * @param compId
     * @return
     */
    @RequestMapping("/listRegisterByCompId2Page")
    @ResponseBody
    public RestResponseVO<PageInfo> listRegisterByCompId2Page(@RequestParam(defaultValue = "1") Integer pageNum,
                                                              @RequestParam(defaultValue = "10") Integer pageSize,
                                                              Integer compId) {
        return registerService.listRegisterByCompId2Page(compId,pageNum,pageSize);
    }

}
