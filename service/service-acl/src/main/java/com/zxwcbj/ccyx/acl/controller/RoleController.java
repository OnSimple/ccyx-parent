package com.zxwcbj.ccyx.acl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
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
    //注入service
    @Autowired
    private RoleService roleService;


    /**
     * 查询角色列表
     *
     * @param current     当前页
     * @param limit       每页显示记录数
     * @param roleQueryVo
     * @return com.zxwcbj.ccyx.common.result.Result
     **/

    @ApiOperation("角色列表查询")
    @GetMapping("{current}/{limit}")
    public Result PageList(@PathVariable Long current,
                           @PathVariable Long limit,
                           RoleQueryVo roleQueryVo) {
        //创建page对象，传递当前页和每页记录数
        Page<Role> pageParms = new Page<>(current, limit);
        //2 调用service方法实现条件分页查询，返回分页对象
        IPage<Role> pageModel = roleService.selectRolePage(pageParms, roleQueryVo);
        return Result.ok(pageModel);
    }

    //2 根据id查询角色
    @ApiOperation("根据id查询角色")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable Long id) {
        Role role = roleService.getById(id);
        return Result.ok(role);
    }

    //3添加角色
    @ApiOperation("添加角色")
    @PostMapping("save")
    public Result save(@RequestBody Role role) {
        roleService.save(role);
        return Result.ok(null);
    }

    //4 修改角色
    @ApiOperation("更新角色")
    @PutMapping("update")
    public Result update(@RequestBody Role role) {
        roleService.updateById(role);
        return Result.ok(null);
    }

    //5 根据id删除角色
    @ApiOperation("删除角色")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        roleService.removeById(id);
        return Result.ok(null);
    }

    //6 批量删除角色
    @ApiOperation("批量删除角色")
    @DeleteMapping("batchRemove")
    //json  中的数组格式对应java中的List 集合
    public Result batchRemove(@RequestBody List<Long> roleList) {
        roleService.removeByIds(roleList);
        return Result.ok(null);
    }
}
