package com.zxwcbj.ccyx.product.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxwcbj.ccyx.model.product.SkuInfo;
import com.zxwcbj.ccyx.vo.product.SkuInfoQueryVo;
import com.zxwcbj.ccyx.vo.product.SkuInfoVo;

/**
 * <p>
 * sku信息 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-08-15
 */
public interface SkuInfoService extends IService<SkuInfo> {

    IPage<SkuInfo> selectPageSkuinfo(Page<SkuInfo> pagelist, SkuInfoQueryVo skuInfoQueryVo);

    void saveSkuInfo(SkuInfoVo skuInfoVo);

    SkuInfoVo getSkuInfo(Long id);

    void updateSkuInfo(SkuInfoVo skuInfoVo);
}
