package com.zxwcbj.ccyx.product.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxwcbj.ccyx.model.product.SkuAttrValue;
import com.zxwcbj.ccyx.model.product.SkuImage;
import com.zxwcbj.ccyx.model.product.SkuInfo;
import com.zxwcbj.ccyx.model.product.SkuPoster;
import com.zxwcbj.ccyx.product.mapper.SkuInfoMapper;
import com.zxwcbj.ccyx.product.service.SkuAttrValueService;
import com.zxwcbj.ccyx.product.service.SkuImageService;
import com.zxwcbj.ccyx.product.service.SkuInfoService;
import com.zxwcbj.ccyx.product.service.SkuPosterService;
import com.zxwcbj.ccyx.vo.product.SkuInfoQueryVo;
import com.zxwcbj.ccyx.vo.product.SkuInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * sku信息 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-08-15
 */
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo> implements SkuInfoService {

    //sku图片
    @Autowired
    private SkuImageService skuImageService;
    //sku平台属性
    @Autowired
    private SkuAttrValueService skuAttrValueService;
    //sku海报
    @Autowired
    private SkuPosterService skuPosterService;

    @Override
    public IPage<SkuInfo> selectPageSkuinfo(Page<SkuInfo> pagelist, SkuInfoQueryVo skuInfoQueryVo) {
        String name = skuInfoQueryVo.getKeyword();
        Long categoryId = skuInfoQueryVo.getCategoryId();
        String skuType = skuInfoQueryVo.getSkuType();
        LambdaQueryWrapper<SkuInfo> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like(SkuInfo::getSkuName, name);
        }
        if (!StringUtils.isEmpty(skuType)) {
            wrapper.eq(SkuInfo::getSkuType, skuType);
        }
        if (categoryId != null) {
            wrapper.like(SkuInfo::getCategoryId, categoryId);
        }
        IPage<SkuInfo> iPage = baseMapper.selectPage(pagelist, wrapper);
        return iPage;
    }

    //添加商品sku信息
    @Override
    public void saveSkuInfo(SkuInfoVo skuInfoVo) {
        //1 添加sku基本信息
        //SkuInfoVo复制--SkuInfo
        SkuInfo skuInfo = new SkuInfo();
        BeanUtils.copyProperties(skuInfoVo, skuInfo);
        this.save(skuInfo);
        //2保存sku海报
        //遍历，,向每个海报对象添加商品skuid
        //因为sku info是刚刚生成的,所以不能使用 **saveBatch(list)** 方法
        //因为skuInfoVo 中没有海报的id
        List<SkuPoster> skukuPosterList = skuInfoVo.getSkuPosterList();
        if (!CollectionUtils.isEmpty(skukuPosterList)) {
            int sort = 1;
            for (SkuPoster skuPoster : skukuPosterList) {
                skuPoster.setSkuId(skuInfo.getId());
                sort++;
            }
            skuPosterService.saveBatch(skukuPosterList);
        }
        //33保存sku图片
        //遍历，,向每个图片对象添加商品skuid
        List<SkuImage> skuImageList = skuInfoVo.getSkuImagesList();
        if (!CollectionUtils.isEmpty(skuImageList)) {
            int sort = 1;
            for (SkuImage skuImage : skuImageList) {
                skuImage.setSkuId(skuInfo.getId());
                skuImage.setSort(sort);
                sort++;
            }
            skuImageService.saveBatch(skuImageList);
        }
        //4保存sku平台属性
        //遍历，,向每个平台属性对象添加商品skuid
        List<SkuAttrValue> skuAttrValueList = skuInfoVo.getSkuAttrValueList();
        if (!CollectionUtils.isEmpty(skuAttrValueList)) {
            int sort = 1;
            for (SkuAttrValue skuAttrValue : skuAttrValueList) {
                skuAttrValue.setSkuId(skuInfo.getId());
                skuAttrValue.setSort(sort);
                sort++;
            }
            skuAttrValueService.saveBatch(skuAttrValueList);
        }
    }
//根据skuId获取sku基本信息
    @Override
    public SkuInfoVo getSkuInfo(Long id) {
        SkuInfoVo skuInfoVo = new SkuInfoVo();
        //根据id查询sku基本信息
        SkuInfo skuInfo=baseMapper.selectById(id);
        //根据d查询商品图片列表
        List<SkuImage> skuImageList=skuImageService.getImageListBySkuId(id);
//根据d查询商品海报列表
        List<SkuPoster> skuPosterList=skuPosterService.getPosterListBySkuId(id);
//根据d查询商品属性信息
        List<SkuAttrValue> skuAttrValueList=skuAttrValueService.getAttrValueListBySkuId(id);
        //封装所有数据，返回
        BeanUtils.copyProperties(skuInfo,skuInfoVo);
        skuInfoVo.setSkuImagesList(skuImageList);
        skuInfoVo.setSkuPosterList(skuPosterList);
        skuInfoVo.setSkuAttrValueList(skuAttrValueList);
        return skuInfoVo;
    }

    @Override
    public void updateSkuInfo(SkuInfoVo skuInfoVo) {
        //修改sku基本信息
        SkuInfo skuInfo=new SkuInfo();
        BeanUtils.copyProperties(skuInfoVo,skuInfo);
        baseMapper.updateById(skuInfo);
        skuImageService.removeById(skuInfoVo.getId());
    }


}
