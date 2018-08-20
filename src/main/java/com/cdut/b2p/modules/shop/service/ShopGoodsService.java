package com.cdut.b2p.modules.shop.service;

import com.cdut.b2p.modules.shop.po.ShopGoods;

public interface ShopGoodsService {
	//增加商品
	public void saveGoods(ShopGoods shopGoods);
	//修改商品信息(根据商品id)
	public void changeGoods(ShopGoods shopgoods);
	//删除商品(根据商品id)
	public void deleteGoods(String goodsId);
	//查询商品(根据商品id)
	public ShopGoods findGoodsbyid(String goodsId);

}
