package org.example.oj.service;

import org.example.oj.response.RestResponseVO;
import org.example.oj.entity.Up;
import org.example.oj.response.UpVO;

public interface UpService {
    RestResponseVO insert(Up up);

    RestResponseVO delById(Integer id);

    RestResponseVO updateById(Up up);

    RestResponseVO<UpVO> blogUp(Integer blogId, Integer userId);

    RestResponseVO<UpVO> blogCommentUp(Integer blogCommentId,Integer userId);


}
