package com.zxwcbj.ccyx.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxwcbj.ccyx.acl.mapper.PermissionMapper;
import com.zxwcbj.ccyx.acl.service.PermissionService;
import com.zxwcbj.ccyx.model.acl.Permission;
import org.springframework.stereotype.Service;
import  com.zxwcbj.ccyx.acl.util.PermissionHelper;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 权限服务实现类
 * </p>
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    //获取所有菜单
    @Override
    public void getPermissionList() {
        //1 查询所有菜单
        List<Permission> list1 = baseMapper.selectList(null);
        //2 转换要求数据格式
        List<Permission> list= PermissionHelper.buildPermission(list1);
    }
    //递归删除菜单
    @Override
    public boolean removeChildById(Long id) {
        //1 创建List集合idList，封装所有要删除菜单id
            List<Long> list=new ArrayList<>();
            //根据当前菜单id，获取当前菜单下面所有子菜单，
//如果子菜单下面还有子菜单，都要获取到
//重点：递归找当前菜单子菜单
            this.selectChildListById(id, list);
            list.add(id);
            //调用方法根据多个菜单id删除
            baseMapper.deleteBatchIds(list);
            return true;

    }
//重点：递归找当前菜单下面的所有子菜单
//第一个参数是当前菜单id
//第二个参数最终封装List集合，包含所有菜单id
    private void selectChildListById(Long id, List<Long> list) {
        //根据当前菜单认d查询下面子菜单
//select*from permission where pid=2
        LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Permission::getPid,id);
        List<Permission> list1 = baseMapper.selectList(wrapper);
        //递归查询是否还有子菜单，有继续递归查询
        list1.stream().forEach(item->{
            //封装菜单id到idList集合里面
            list.add(item.getId());
            //递归
            this.selectChildListById(item.getPid(),list);
        });
    }
}
