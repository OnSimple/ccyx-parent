package com.zxwcbj.ccyx.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zxwcbj.ccyx.model.sys.Region;

import java.util.List;

/**
 * <p>
 * 地区表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-08-13
 */
public interface RegionService extends IService<Region> {

    List<Region> getReginByKeyword(String keyword);
}
