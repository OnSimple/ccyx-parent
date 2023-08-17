package com.zxwcbj.ccyx.sys.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxwcbj.ccyx.model.sys.RegionWare;
import com.zxwcbj.ccyx.vo.sys.RegionWareQueryVo;

/**
 * <p>
 * 城市仓库关联表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-08-13
 */
public interface RegionWareService extends IService<RegionWare> {

    IPage<RegionWare> getPageList(Page<RegionWare> pagelist, RegionWareQueryVo regionWareQueryVo);

    void saveReginWare(RegionWare regionWare);

    void updateStatus(Long id, Integer status);
}
