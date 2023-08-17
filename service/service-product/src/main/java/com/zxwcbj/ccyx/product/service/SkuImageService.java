package com.zxwcbj.ccyx.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zxwcbj.ccyx.model.product.SkuImage;

import java.util.List;

/**
 * <p>
 * 商品图片 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-08-15
 */
public interface SkuImageService extends IService<SkuImage> {
//根据id香询商品图片列表
    List<SkuImage> getImageListBySkuId(Long id);
}
