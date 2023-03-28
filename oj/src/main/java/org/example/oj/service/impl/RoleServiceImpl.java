package org.example.oj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.oj.common.ResponseEnum;
import org.example.oj.common.StringConst;
import org.example.oj.dao.RoleMapper;
import org.example.oj.entity.Role;
import org.example.oj.response.RestResponseVO;
import org.example.oj.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;


    @Override
    public RestResponseVO getById(Integer roleId) {
        Role role = roleMapper.selectByPrimaryKey(roleId);
        return RestResponseVO.createBySuccess(role);
    }

    @Override
    public RestResponseVO insert(Role role) {
        if(role == null){
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        int effect = roleMapper.insertSelective(role);
        return effect > 0 ? RestResponseVO.createBySuccessMessage(StringConst.ADD_SUCCESS)
                : RestResponseVO.createByErrorMessage(StringConst.ADD_FAIL);
    }

    @Override
    public RestResponseVO delById(Integer id) {
        if(id == null){
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        int effect = roleMapper.deleteByPrimaryKey(id);
        return effect > 0 ? RestResponseVO.createBySuccessMessage(StringConst.DEL_SUCCESS)
                : RestResponseVO.createByErrorMessage(StringConst.DEL_FAIL);
    }

    @Override
    public RestResponseVO updateById(Role role) {
        if(role == null){
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        int effect = roleMapper.updateByPrimaryKeySelective(role);
        return effect > 0 ? RestResponseVO.createBySuccessMessage(StringConst.UPDATE_SUCCESS)
                : RestResponseVO.createByErrorMessage(StringConst.UPDATE_FAIL);
    }

    @Override
    public RestResponseVO<PageInfo> listRole2Page(Integer pageNum, Integer pageSize,String keyword) {
        if(pageNum == null || pageSize == null){
            return RestResponseVO.createByErrorEnum(ResponseEnum.INVALID_REQUEST);
        }
        PageHelper.startPage(pageNum,pageSize,true);
        List<Role> roleList = roleMapper.listRoleByKeyWord(keyword);
        PageInfo<Role> pageInfo = new PageInfo<Role>(roleList);
        return RestResponseVO.createBySuccess(pageInfo);
    }

    @Override
    public RestResponseVO listRole() {
     return RestResponseVO.createBySuccess(roleMapper.listRole());
    }

}
