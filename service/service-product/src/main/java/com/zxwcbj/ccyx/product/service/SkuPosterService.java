package com.zxwcbj.ccyx.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zxwcbj.ccyx.model.product.SkuPoster;

import java.util.List;

/**
 * <p>
 * 商品海报表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-08-15
 */
public interface SkuPosterService extends IService<SkuPoster> {
//根据d查询商品海报列表
    List<SkuPoster> getPosterListBySkuId(Long id);
}
