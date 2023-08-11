package com.zxwcbj.ccyx.acl.controller;

import com.zxwcbj.ccyx.acl.service.PermissionService;
import com.zxwcbj.ccyx.common.result.Result;
import com.zxwcbj.ccyx.model.acl.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/acl/permission")
@Api(tags = "菜单管理")
@CrossOrigin
public class PermissionController {
    @Autowired
    PermissionService permissionService;
    @ApiOperation(value = "获取菜单")
    @GetMapping
    public Result getPermissionList() {
        permissionService.getPermissionList();
        return Result.ok(null);
    }
    @ApiOperation("新增菜单")
@PostMapping("save")
 public Result addPermission(@RequestBody Permission permission){
        permissionService.save(permission);
        return Result.ok(null);
    }
     @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public Result updateById(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return Result.ok();
    }

    @ApiOperation(value = "递归删除菜单")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        permissionService.removeChildById(id);
        return Result.ok();
    }
}
