package com.zxwcbj.ccyx.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxwcbj.ccyx.model.product.Category;
import com.zxwcbj.ccyx.vo.product.CategoryQueryVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品三级分类 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-08-15
 */

@Service
public interface CategoryService extends IService<Category> {


    IPage<Category> selectPageCategory(Page<Category> ipage, CategoryQueryVo categoryQueryVo);


}
