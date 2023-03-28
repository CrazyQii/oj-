package org.example.oj.service.impl;

import org.example.oj.common.ResponseEnum;
import org.example.oj.response.RestResponseVO;
import org.example.oj.common.StringConst;
import org.example.oj.dao.SignMapper;
import org.example.oj.entity.Sign;
import org.example.oj.service.SignService;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class SignServiceImpl implements SignService {

    @Resource
    private SignMapper signMapper;

    @Override
    public RestResponseVO insert(Sign sign) {
        if (sign == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        int effect = signMapper.insertSelective(sign);
        return effect > 0 ? RestResponseVO.createBySuccessMessage(StringConst.ADD_SUCCESS)
                : RestResponseVO.createByErrorMessage(StringConst.ADD_FAIL);
    }

    @Override
    public RestResponseVO delById(Integer id) {
        if (id == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        int effect = signMapper.deleteByPrimaryKey(id);
        return effect > 0 ? RestResponseVO.createBySuccessMessage(StringConst.DEL_SUCCESS)
                : RestResponseVO.createByErrorMessage(StringConst.DEL_FAIL);
    }

    @Override
    public RestResponseVO updateById(Sign sign) {
        if (sign == null) {
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        int effect = signMapper.updateByPrimaryKeySelective(sign);
        return effect > 0 ? RestResponseVO.createBySuccessMessage(StringConst.UPDATE_SUCCESS)
                : RestResponseVO.createByErrorMessage(StringConst.UPDATE_FAIL);
    }
}
