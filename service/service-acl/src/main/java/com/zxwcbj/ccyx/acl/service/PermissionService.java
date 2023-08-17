package com.zxwcbj.ccyx.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zxwcbj.ccyx.model.acl.Permission;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PermissionService extends IService<Permission> {


    List<Permission> getPermissionByRoleId(Long roleId);

    //获取所有菜单列表
    List<Permission> getPermissionList();

    //给某个角色授权
    void saveRolePermission(Long roleId, Long[] permissionId);

    //递归删除
    boolean removeChildById(Long id);


}
