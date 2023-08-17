package com.zxwcbj.ccyx.product.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxwcbj.ccyx.model.product.SkuImage;
import com.zxwcbj.ccyx.product.mapper.SkuImageMapper;
import com.zxwcbj.ccyx.product.service.SkuImageService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品图片 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-08-15
 */
@Service
public class SkuImageServiceImpl extends ServiceImpl<SkuImageMapper, SkuImage> implements SkuImageService {
    ///根据id香询商品图片列表
    @Override
    public List<SkuImage> getImageListBySkuId(Long id) {
        LambdaQueryWrapper<SkuImage> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SkuImage::getSkuId, id);
        List<SkuImage> skuImageList = baseMapper.selectList(wrapper);
        return skuImageList;
    }
}
