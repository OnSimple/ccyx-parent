package com.zxwcbj.ccyx.acl.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxwcbj.ccyx.acl.service.AdminService;
import com.zxwcbj.ccyx.acl.service.RoleService;
import com.zxwcbj.ccyx.commom.utils.MD5;
import com.zxwcbj.ccyx.common.result.Result;
import com.zxwcbj.ccyx.model.acl.Admin;
import com.zxwcbj.ccyx.vo.acl.AdminQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


//@Api("用户接口") 直接将描述信息作为注解的参数 .适合于单个接口的描述
@Api(tags = "用户接口")//tags 属性通常用于对多个接口进行分类和组织
@CrossOrigin
@RestController
@RequestMapping("/admin/acl/user")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;

    /**
     * 为用户进行分配
     *
     * @param adminId
     * @param roleId
     * @return com.zxwcbj.ccyx.common.result.Result
     **/

    @ApiOperation("为用户进行角色分配")
    @PostMapping("doAssign")
    //参数有用户id和多个角色id
        public Result doAssign(@RequestParam Long adminId,
                           @RequestParam Long[] roleId) {
        roleService.saveAdminRole(adminId,roleId);
        return Result.ok(null);
    }
    //获取所有角色，和根据用户id查询用户分配角色列表
    @ApiOperation("获取用户角色")
    @GetMapping("toAssign/{adminId}")

    public Result toAssign(@PathVariable Long adminId) {
        //返回map集合包含两部分数据：所有角色 和 为用户分配角色列表
        Map<String, Object> map = roleService.getRoleByAdminId(adminId);
        return Result.ok(map);
    }

    //用户列表
    @ApiOperation("角色分页列表")
    @GetMapping("{page}/{limit}")
    public Result pageParam(@PathVariable Long page,
                            @PathVariable Long limit,
                            AdminQueryVo adminQueryVo) {
        Page<Admin> pageParam = new Page<Admin>(page, limit);
        IPage<Admin> pageModel = adminService.selectUserPage(pageParam, adminQueryVo);
        return Result.ok(pageModel);
    }

    //2 id查询用户
    @ApiOperation("id查询角色")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Admin admin = adminService.getById(id);
        return Result.ok(admin);
    }

    //3 添加用户
    @ApiOperation("添加用户")
    @PostMapping("save")
    public Result save(@RequestBody Admin admin) {
        String password = admin.getPassword();
        String passqordMD5 = MD5.encrypt(password);
        admin.setPassword(passqordMD5);
        adminService.save(admin);
        return Result.ok(null);
    }

    //4修改删除
    @ApiOperation("修改用户")
    @PutMapping("update")
    public Result update(@RequestBody Admin admin) {
        adminService.updateById(admin);
        return Result.ok(null);
    }

    //删除
    @ApiOperation("删除用户")
    @DeleteMapping("remove/{id}")
    public Result delete(@PathVariable Long id) {
        adminService.removeById(id);
        return Result.ok(null);
    }

    //6批量删除
    @ApiOperation("批量删除")
    @DeleteMapping("batchRemove")

    public Result batchRemove(@RequestBody List<Long> list) {
        adminService.removeByIds(list);
        return Result.ok(null);

    }
}

