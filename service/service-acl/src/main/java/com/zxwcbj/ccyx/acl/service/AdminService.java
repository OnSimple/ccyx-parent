package com.zxwcbj.ccyx.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxwcbj.ccyx.model.acl.Admin;
import com.zxwcbj.ccyx.vo.acl.AdminQueryVo;
import org.springframework.stereotype.Service;

@Service
public interface AdminService extends IService<Admin> {

    IPage<Admin> selectUserPage(Page<Admin> pageParam, AdminQueryVo adminQueryVo);
}
