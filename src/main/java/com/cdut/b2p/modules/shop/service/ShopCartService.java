package com.cdut.b2p.modules.shop.service;

import java.util.List;

import com.cdut.b2p.modules.shop.po.ShopCart;

public interface ShopCartService {
	/**
	 * @desc 在购物车中添加一件物品
	 * @param shopCart
	 * @return
	 */
	public boolean addCart(ShopCart shopCart);
	/**
	 * @desc 在购物车中添加一件物品
	 * @param uid 用户id
	 * @param gid 商品id
	 * @return
	 */
	public boolean addCart(String uid,String gid);
	/**
	 * @desc 根据用户id,查询购物车 
	 * @param uid
	 * @return
	 */
	public List<ShopCart> findCartByUser(String uid);
	/**
	 * @desc 根据ids集，删除对应的购物车记录
	 * @param ids
	 * @return
	 */
	public boolean delCart(String[]ids);
}
