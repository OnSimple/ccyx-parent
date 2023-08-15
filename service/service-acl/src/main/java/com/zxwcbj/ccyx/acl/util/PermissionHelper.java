package com.zxwcbj.ccyx.acl.util;

import com.zxwcbj.ccyx.model.acl.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据权限数据构建菜单数据
 **/

public class PermissionHelper {
    /**
     * 使用递归方法建菜单
     *
     * @return java.util.List<com.zxwcbj.ccyx.model.acl.Permission>
     **/

    public static List<Permission> buildPermission(List<Permission> list) {
        List<Permission> list2 = new ArrayList<>();
        for (Permission permission : list) {
            if (permission.getPid() == 0) {
                permission.setLevel(1);
                list2.add(findChildren(permission, list));
            }
        }
        return list2;
    }

    /**
     * 递归查找子节点
     *
     * @param permission
     * @param list
     * @return com.zxwcbj.ccyx.model.acl.Permission
     **/

    private static Permission findChildren(Permission permission, List<Permission> list) {
        permission.setChildren(new ArrayList<>());
        //遍历List所有菜单数据
//判断：当前节点id = pid是否一样，封装，递归往下找
        for (Permission per : list) {
            if (per.getPid().longValue() == permission.getId().longValue()) {
                int i = permission.getLevel() + 1;
                per.setLevel(i);
                if (permission.getChildren() == null) {
                    permission.setChildren(new ArrayList<>());
                }
                //封装下一层数据
                permission.getChildren().add(findChildren(per, list));
            }
        }
        return permission;
    }
}
