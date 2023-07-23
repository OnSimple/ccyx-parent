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
        if(!StringUtils.isEmpty(roleName)) {
            wrapper.like(Role::getRoleName,roleName);
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
            List<Role> allRoleList= baseMapper.selectList(null);
            //2 根据用户id查询用户分配角色列表
                //2.1 根据用户id查询 用户角色关系表 admin_role 查询用户分配角色id列表
                LambdaQueryWrapper<AdminRole> waqWrapper = new LambdaQueryWrapper<>();

        //Map<String,Object> map = new HashMap<>();
        //设置查询条件，根据用户id AdminRoleService
        waqWrapper.eq(AdminRole::getAdminId,adminId);
            List<AdminRole> adminRoleList = adminRoleService.list(waqWrapper);
            //2.2 通过第一步返回集合，获取所有角色i的列表List<AdminRole>--List<Long>
            List<Long> RoleIdList = adminRoleList.stream().map(item -> item.getRoleId()).collect(Collectors.toList());
            //2.3创建新的ist集合，用于存储用户配置角色
            List<Role> assignedRoleList = new ArrayList<>();
            ///判断所有角色单面是否包含已经分配角色id，封装到2.3单面新的ist集合
            for(Role role:allRoleList){
                //判断
                if(RoleIdList.contains(role.getId())){
                    assignedRoleList.add(role);
                }
            }
            //封装到map，返回
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put(" allRoleList", allRoleList);
            resultMap.put("assignRoles", assignedRoleList);
        return resultMap;
    }
}
