package com.zxwcbj.ccyx.acl.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxwcbj.ccyx.acl.mapper.RolePermissionMapper;
import com.zxwcbj.ccyx.acl.service.RolePermissionService;
import com.zxwcbj.ccyx.model.acl.RolePermission;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {
}
