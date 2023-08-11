package com.zxwcbj.ccyx.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zxwcbj.ccyx.model.acl.Permission;
import org.springframework.stereotype.Service;

@Service
public interface PermissionService extends IService<Permission> {
    //获取所有菜单列表
    void getPermissionList();
    //递归删除
    boolean removeChildById(Long id);
}
