package com.zxwcbj.ccyx.acl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxwcbj.ccyx.acl.service.RoleService;
import com.zxwcbj.ccyx.common.result.Result;
import com.zxwcbj.ccyx.model.acl.Role;
import com.zxwcbj.ccyx.vo.acl.RoleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色接口")
@RestController
@RequestMapping("/admin/acl/role")
@CrossOrigin
public class RoleController {
    @Autowired
    private RoleService roleService;
    //1.查询角色列表
    @ApiOperation("角色列表查询")
    @GetMapping("{current}/{limit}")
    public Result PageList(@PathVariable Long current,
                           @PathVariable Long limit,
                           RoleQueryVo roleQueryVo){
        Page<Role> pageParms=new Page<>(current,limit);
        IPage<Role> pageModel=roleService.selectRolePage(pageParms,roleQueryVo);
        return Result.ok(pageModel);
    }
    //2 根据id查询角色
    @ApiOperation("根据id查询角色")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable Long id){
        Role role=roleService.getById(id);
        return Result.ok(role);
    }
    //3添加角色
    @ApiOperation("添加角色")
    @PostMapping("save")
    public Result save(@RequestBody Role role){
        roleService.save(role);
        return  Result.ok(null);
    }
    //4 修改角色
    @ApiOperation("更新角色")
    @PutMapping("update")
    public Result update(@RequestBody Role role){
        roleService.updateById(role);
        return Result.ok(null);
    }
    //5 根据id删除角色
    @ApiOperation("删除角色")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id){
        roleService.removeById(id);
        return Result.ok(null);
    }
    //6 批量删除角色
    @ApiOperation("批量删除角色")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> roleList){
        roleService.removeByIds(roleList);
        return Result.ok(null);
    }
}
