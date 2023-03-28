package org.example.oj.service;


import com.github.pagehelper.PageInfo;
import org.example.oj.entity.Role;
import org.example.oj.response.RestResponseVO;

public interface RoleService {

    RestResponseVO getById(Integer roleId);

    RestResponseVO insert(Role role);

    RestResponseVO delById(Integer id);

    RestResponseVO updateById(Role role);

    RestResponseVO<PageInfo> listRole2Page(Integer pageNum, Integer pageSize,String keyword);

    RestResponseVO listRole();

}
