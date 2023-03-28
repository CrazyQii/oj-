package org.example.oj.controller.portal;

import org.example.oj.response.CompetitionProblemVO;
import org.example.oj.response.RestResponseVO;
import org.example.oj.service.CompetitionProblemService;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/competitionProblem")
@Controller
public class CompetitionProblemController {

    @Resource
    private CompetitionProblemService competitionProblemService;

    @RequestMapping("/listVOByCompId")
    @ResponseBody
    public RestResponseVO<List<CompetitionProblemVO>> listVOByCompId(Integer compId){
        return competitionProblemService.listVOByCompetitionId(compId);
    }


}
