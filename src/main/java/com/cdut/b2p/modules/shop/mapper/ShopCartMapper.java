package com.cdut.b2p.modules.shop.mapper;

import com.cdut.b2p.common.po.annotation.MyBatisMapper;
import com.cdut.b2p.modules.shop.po.ShopCart;
import com.cdut.b2p.modules.shop.po.ShopCartExample;
import com.cdut.b2p.modules.shop.po.ShopCartVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisMapper
public interface ShopCartMapper {
    long countByExample(ShopCartExample example);

    int deleteByExample(ShopCartExample example);

    int deleteByPrimaryKey(String id);

    int insert(ShopCart record);

    int insertSelective(ShopCart record);

    List<ShopCart> selectByExample(ShopCartExample example);

    ShopCart selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ShopCart record, @Param("example") ShopCartExample example);

    int updateByExample(@Param("record") ShopCart record, @Param("example") ShopCartExample example);

    int updateByPrimaryKeySelective(ShopCart record);

    int updateByPrimaryKey(ShopCart record);
    List<ShopCartVo> selectMyCart(String uid);
}