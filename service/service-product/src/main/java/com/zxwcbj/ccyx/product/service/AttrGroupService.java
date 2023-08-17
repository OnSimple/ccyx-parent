package com.zxwcbj.ccyx.product.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxwcbj.ccyx.model.product.AttrGroup;
import com.zxwcbj.ccyx.vo.product.AttrGroupQueryVo;

import java.util.List;

/**
 * <p>
 * 属性分组 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-08-15
 */
public interface AttrGroupService extends IService<AttrGroup> {


    IPage<AttrGroup> selectPageAttrGroup(Page<AttrGroup> pageParam, AttrGroupQueryVo attrGroupQueryVo);

    List<AttrGroup> findAllListAttrGroup();
}
