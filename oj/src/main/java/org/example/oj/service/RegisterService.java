package org.example.oj.service;

import com.github.pagehelper.PageInfo;
import org.example.oj.response.RestResponseVO;
import org.example.oj.entity.Register;

public interface RegisterService {

    RestResponseVO insert(Register register);

    RestResponseVO delById(Integer id);

    RestResponseVO updateById(Register register);

    RestResponseVO registerCompetition(Integer userId,Integer compId,String password);

    RestResponseVO isRegisterCompetition(Integer userId, Integer compId);


    RestResponseVO<PageInfo> listRegisterByCompId2Page(Integer compId, Integer pageNum, Integer pageSize);



}
