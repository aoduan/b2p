package com.cdut.b2p.modules.shop.mapper;

import com.cdut.b2p.common.po.annotation.MyBatisMapper;
import com.cdut.b2p.modules.shop.po.ShopChat;
import com.cdut.b2p.modules.shop.po.ShopChatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisMapper
public interface ShopChatMapper {
    long countByExample(ShopChatExample example);

    int deleteByExample(ShopChatExample example);

    int deleteByPrimaryKey(String id);

    int insert(ShopChat record);

    int insertSelective(ShopChat record);

    List<ShopChat> selectByExampleWithBLOBs(ShopChatExample example);

    List<ShopChat> selectByExample(ShopChatExample example);

    ShopChat selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ShopChat record, @Param("example") ShopChatExample example);

    int updateByExampleWithBLOBs(@Param("record") ShopChat record, @Param("example") ShopChatExample example);

    int updateByExample(@Param("record") ShopChat record, @Param("example") ShopChatExample example);

    int updateByPrimaryKeySelective(ShopChat record);

    int updateByPrimaryKeyWithBLOBs(ShopChat record);

    int updateByPrimaryKey(ShopChat record);
}