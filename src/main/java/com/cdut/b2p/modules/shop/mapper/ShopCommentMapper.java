package com.cdut.b2p.modules.shop.mapper;

import com.cdut.b2p.common.po.annotation.MyBatisMapper;
import com.cdut.b2p.modules.shop.po.ShopComment;
import com.cdut.b2p.modules.shop.po.ShopCommentExample;
import com.cdut.b2p.modules.shop.po.ShopCommentVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisMapper
public interface ShopCommentMapper {
    long countByExample(ShopCommentExample example);

    int deleteByExample(ShopCommentExample example);

    int deleteByPrimaryKey(String id);

    int insert(ShopComment record);

    int insertSelective(ShopComment record);

    List<ShopComment> selectByExample(ShopCommentExample example);

    ShopComment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ShopComment record, @Param("example") ShopCommentExample example);

    int updateByExample(@Param("record") ShopComment record, @Param("example") ShopCommentExample example);

    int updateByPrimaryKeySelective(ShopComment record);

    int updateByPrimaryKey(ShopComment record);
    /**
     * @desc 展示用户对某件商品的评论
     */
    public List<ShopCommentVo> selectCommentByGId(String gid);
}