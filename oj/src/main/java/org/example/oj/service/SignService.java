package org.example.oj.service;

import org.example.oj.response.RestResponseVO;
import org.example.oj.entity.Sign;

public interface SignService {

    RestResponseVO insert(Sign sign);

    RestResponseVO delById(Integer id);

    RestResponseVO updateById(Sign sign);
}
