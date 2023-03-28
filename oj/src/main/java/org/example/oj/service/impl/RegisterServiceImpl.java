package org.example.oj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.oj.common.ResponseEnum;
import org.example.oj.dao.CompetitionMapper;
import org.example.oj.dao.ProblemResultMapper;
import org.example.oj.entity.Competition;
import org.example.oj.response.RegisterVO;
import org.example.oj.response.RestResponseVO;
import org.example.oj.common.StringConst;
import org.example.oj.dao.RegisterMapper;
import org.example.oj.entity.Register;
import org.example.oj.service.RegisterService;
import org.apache.commons.lang3.StringUtils;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Resource
    private RegisterMapper registerMapper;

    @Resource
    private CompetitionMapper competitionMapper;

    @Resource
    private ProblemResultMapper problemResultMapper;

    @Override
    public RestResponseVO insert(Register register) {
        if (register == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        int effect = registerMapper.insertSelective(register);
        return effect > 0 ? RestResponseVO.createBySuccessMessage(StringConst.ADD_SUCCESS)
                : RestResponseVO.createByErrorMessage(StringConst.ADD_FAIL);
    }

    @Override
    public RestResponseVO delById(Integer id) {
        if (id == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        int effect = registerMapper.deleteByPrimaryKey(id);
        return effect > 0 ? RestResponseVO.createBySuccessMessage(StringConst.DEL_SUCCESS)
                : RestResponseVO.createByErrorMessage(StringConst.DEL_FAIL);
    }

    @Override
    public RestResponseVO updateById(Register register) {
        if (register == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        int effect = registerMapper.updateByPrimaryKeySelective(register);
        return effect > 0 ? RestResponseVO.createBySuccessMessage(StringConst.UPDATE_SUCCESS)
                : RestResponseVO.createByErrorMessage(StringConst.UPDATE_FAIL);
    }

    @Override
    public RestResponseVO registerCompetition(Integer userId, Integer compId, String password) {
        if (userId == null || compId == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        int rows = registerMapper.countByUserIdAndCompId(userId, compId);
        if (rows > 0) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.COMPETITION_REPEATED_REGISTER_ERROR);
        }
        Competition competition = competitionMapper.selectByPrimaryKey(compId);
        if (competition == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        if (!StringUtils.equals(competition.getPassword(), password)) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.COMPETITION_PASSWORD_ERROR);
        }
        long startTime = competition.getStartTime().getTime();
        long nowTime = Instant.now().toEpochMilli();
        if (nowTime > startTime) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.COMPETITION_STARTED_ERROR);
        }
        Register register = new Register();
        register.setCompId(compId);
        register.setUserId(userId);
        int effect = registerMapper.insertSelective(register);

        return effect > 0 ? RestResponseVO.createBySuccessMessage(StringConst.ADD_SUCCESS)
                : RestResponseVO.createByErrorMessage(StringConst.ADD_FAIL);
    }


    @Override
    public RestResponseVO isRegisterCompetition(Integer userId, Integer compId) {
        if (userId == null || compId == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        int rows = registerMapper.countByUserIdAndCompId(userId, compId);
        return rows > 0 ? RestResponseVO.createBySuccess()
                : RestResponseVO.createByError();
    }

    @Override
    public RestResponseVO<PageInfo> listRegisterByCompId2Page(Integer compId, Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageSize == null || compId == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        PageHelper.startPage(pageNum, pageSize, true);
        List<RegisterVO> registerVOList = registerMapper.listRegisterByCompId2Page(compId);
        PageInfo<RegisterVO> pageInfo = new PageInfo<>(registerVOList);
        return RestResponseVO.createBySuccess(pageInfo);
    }

}
