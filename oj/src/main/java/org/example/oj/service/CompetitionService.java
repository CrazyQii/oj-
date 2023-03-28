package org.example.oj.service;

import com.github.pagehelper.PageInfo;
import org.example.oj.response.CompetitionDetailVO;
import org.example.oj.response.RestResponseVO;
import org.example.oj.entity.Competition;

import java.util.List;

public interface CompetitionService {

    RestResponseVO<Competition> getById(Integer competitionId);

    RestResponseVO insert(Competition competition);

    RestResponseVO delById(Integer competitionId);

    RestResponseVO updateById(Competition competition);

    RestResponseVO<CompetitionDetailVO> getCompetitionDetailVOById(Integer userId, Integer compId);

    RestResponseVO<PageInfo> listCompetitionVO2Page(Integer pageSize,Integer pageNum,String keyword);

    RestResponseVO<List<CompetitionDetailVO>> listLastCompetitionDetailVO(Integer pageSize);

}
