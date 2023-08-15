package com.zxwcbj.ccyx.vo.product;

import com.zxwcbj.ccyx.model.product.SkuAttrValue;
import com.zxwcbj.ccyx.model.product.SkuImage;
import com.zxwcbj.ccyx.model.product.SkuInfo;
import com.zxwcbj.ccyx.model.product.SkuPoster;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SkuInfoVo extends SkuInfo {

    @ApiModelProperty(value = "海报列表")
    private List<SkuPoster> skuPosterList;

    @ApiModelProperty(value = "属性值")
    private List<SkuAttrValue> skuAttrValueList;

    @ApiModelProperty(value = "图片")
    private List<SkuImage> skuImagesList;

}

