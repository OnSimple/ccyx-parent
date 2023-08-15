package com.zxwcbj.ccyx.acl.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxwcbj.ccyx.acl.mapper.RoleMapper;
import com.zxwcbj.ccyx.acl.service.AdminRoleService;
import com.zxwcbj.ccyx.acl.service.RoleService;
import com.zxwcbj.ccyx.model.acl.AdminRole;
import com.zxwcbj.ccyx.model.acl.Role;
import com.zxwcbj.ccyx.vo.acl.RoleQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    //用户角色关系
    @Autowired
    private AdminRoleService adminRoleService;

    //1 角色列表（条件分页查询）
    @Override
    public IPage<Role> selectRolePage(Page<Role> pageParam, RoleQueryVo roleQueryVo) {
        //获取条件值
        String roleName = roleQueryVo.getRoleName();

        //创建map条件对象
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();

        //判断条件值是否为空，不为封装查询条件
        // rolename like ?
        if (!StringUtils.isEmpty(roleName)) {
            wrapper.like(Role::getRoleName, roleName);
        }

        //调用方法实现条件分页查询
        IPage<Role> rolePage = baseMapper.selectPage(pageParam, wrapper);

        //返回分页对象
        return rolePage;
    }


    //获取所有角色，和根据用户id查询用户分配角色列表
    @Override
    public Map<String, Object> getRoleByAdminId(Long adminId) {
        //1 查询所有角色
        List<Role> allRolesList = baseMapper.selectList(null);
        //2 根据用户id查询用户分配角色列表
        //2.1 根据用户id查询 用户角色关系表 admin_role 查询用户分配角色id列表
        LambdaQueryWrapper<AdminRole> wrapper = new LambdaQueryWrapper<>();

        //Map<String,Object> map = new HashMap<>();
        //设置查询条件，根据用户id AdminRoleService
        wrapper.eq(AdminRole::getAdminId, adminId);
        List<AdminRole> adminRoleList = adminRoleService.list(wrapper);
        //2.2 通过第一步返回集合，获取所有角色i的列表List<AdminRole>--List<Long>
        List<Long> roleIdsList =
                adminRoleList.stream()
                        .map(item -> item.getRoleId())
                        .collect(Collectors.toList());
        //2.3创建新的ist集合，用于存储用户配置角色
        List<Role> assignRoleList = new ArrayList<>();
        ///判断所有角色单面是否包含已经分配角色id，封装到2.3单面新的ist集合
        for (Role role : allRolesList) {
            //判断
            if (roleIdsList.contains(role.getId())) {
                assignRoleList.add(role);
            }
        }
        //封装到map，返回
        Map<String, Object> result = new HashMap<>();
        //所有角色列表
        result.put("allRolesList", allRolesList);
        result.put("assignRoles", assignRoleList);
        //用户分配角色列表
        return result;
    }

    //为用户进行分配
    @Override
    public void saveAdminRole(Long adminId, Long[] roleId) {
        //1 删除用户已经分配过的角色数据
        //根据用户id删除admin_role表里面对应数据
        LambdaQueryWrapper<AdminRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminRole::getAdminId, adminId);
        adminRoleService.remove(wrapper);
        //2 重新分配
//遍历多个角色id，得到每个角色id，拿着每个角色id + 用户id添加用户角色关系表
        //adminId: 1 roleId: 2 3
       /*     第一种写法
   for (Long roleId :roleIds){
            AdminRole adminRole=new AdminRole();
            adminRole.setAdminId(adminId);
            adminRole.setRoleId(roleId);
            adminRoleService.save(adminRole);
        }*/
        List<AdminRole> list = new ArrayList<>();
        for (Long roled : roleId) {
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId(adminId);
            adminRole.setRoleId(roled);
            //放到List集合
            list.add(adminRole);
        }
        //调用方法添加
        adminRoleService.saveBatch(list);
    }
}
