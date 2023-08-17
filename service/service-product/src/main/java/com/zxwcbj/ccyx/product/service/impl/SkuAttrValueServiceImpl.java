package com.zxwcbj.ccyx.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxwcbj.ccyx.model.product.SkuAttrValue;
import com.zxwcbj.ccyx.product.mapper.SkuAttrValueMapper;
import com.zxwcbj.ccyx.product.service.SkuAttrValueService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * spu属性值 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-08-15
 */
@Service
public class SkuAttrValueServiceImpl extends ServiceImpl<SkuAttrValueMapper, SkuAttrValue> implements SkuAttrValueService {

    @Override
    public List<SkuAttrValue> getAttrValueListBySkuId(Long id) {
        LambdaQueryWrapper<SkuAttrValue> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(SkuAttrValue::getAttrId, id);
        List<SkuAttrValue> skuAttrValueList = baseMapper.selectList(wrapper);
        return skuAttrValueList;
    }
}
