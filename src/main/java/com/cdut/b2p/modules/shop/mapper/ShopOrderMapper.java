package com.cdut.b2p.modules.shop.mapper;

import com.cdut.b2p.common.po.annotation.MyBatisMapper;
import com.cdut.b2p.modules.shop.po.ShopOrder;
import com.cdut.b2p.modules.shop.po.ShopOrderExample;
import com.cdut.b2p.modules.shop.po.ShopOrderVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisMapper
public interface ShopOrderMapper {
    long countByExample(ShopOrderExample example);

    int deleteByExample(ShopOrderExample example);

    int deleteByPrimaryKey(String id);

    int insert(ShopOrder record);

    int insertSelective(ShopOrder record);

    List<ShopOrder> selectByExample(ShopOrderExample example);

    ShopOrder selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ShopOrder record, @Param("example") ShopOrderExample example);

    int updateByExample(@Param("record") ShopOrder record, @Param("example") ShopOrderExample example);

    int updateByPrimaryKeySelective(ShopOrder record);

    int updateByPrimaryKey(ShopOrder record);
    /**
     * @desc 展示我的订单信息
     */
    public List<ShopOrderVo> myOrders(String uid);
}