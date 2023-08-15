package com.zxwcbj.ccyx.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxwcbj.ccyx.model.acl.Role;
import com.zxwcbj.ccyx.vo.acl.RoleQueryVo;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface RoleService extends IService<Role> {
    //1 角色列表（条件分页查询）
    IPage<Role> selectRolePage(Page<Role> pageParam, RoleQueryVo roleQueryVo);

    //获取所有角色和根据用户id查询用户分配角色列表
    Map<String, Object> getRoleByAdminId(Long adminId);

    void saveAdminRole(Long adminId, Long[] roleIds);
    /*
     *selectRolePage的作用是根据传入的分页参数pageParam和查询条件roleQueryVo，返回一个包含Role实体对象的分页结果。
     * 这个方法使用了MyBatis Plus框架提供的分页插件，将传入的分页参数与查询条件封装成一个IPage对象，并调用数据库访问
     * 层的相应方法执行查询操作。查询结果将以分页的形式返回，其中每页包含多个Role实体对象。
     */
}
