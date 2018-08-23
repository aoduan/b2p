package com.cdut.b2p.modules.shop.mapper;

import com.cdut.b2p.common.po.annotation.MyBatisMapper;
import com.cdut.b2p.modules.shop.po.ShopGoods;
import com.cdut.b2p.modules.shop.po.ShopGoodsExample;
import com.cdut.b2p.modules.shop.po.ShopGoodsInfo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisMapper
public interface ShopGoodsMapper {
    long countByExample(ShopGoodsExample example);

    int deleteByExample(ShopGoodsExample example);

    int deleteByPrimaryKey(String id);

    int insert(ShopGoods record);

    int insertSelective(ShopGoods record);

    List<ShopGoods> selectByExampleWithBLOBs(ShopGoodsExample example);

    List<ShopGoods> selectByExample(ShopGoodsExample example);

    ShopGoods selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ShopGoods record, @Param("example") ShopGoodsExample example);

    int updateByExampleWithBLOBs(@Param("record") ShopGoods record, @Param("example") ShopGoodsExample example);

    int updateByExample(@Param("record") ShopGoods record, @Param("example") ShopGoodsExample example);

    int updateByPrimaryKeySelective(ShopGoods record);

    int updateByPrimaryKeyWithBLOBs(ShopGoods record);

    int updateByPrimaryKey(ShopGoods record);
    /**
     * @desc 对比商品
     * @param shopGoodsInfo
     * @return
     */
    List<ShopGoods> compareGoods(ShopGoodsInfo shopGoodsInfo);
}