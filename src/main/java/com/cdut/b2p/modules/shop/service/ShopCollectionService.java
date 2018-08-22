package com.cdut.b2p.modules.shop.service;

import java.util.List;

import com.cdut.b2p.modules.shop.po.ShopCollection;

/**
 * @title ShopCollectionService
 * @desc  ShopCollectionService有关收藏物的服务接口
 * @author zsb
 * @Date   2018/8/18
 */
public interface ShopCollectionService {
	/**
	 * @desc 添加一件收藏物
	 * @param shopCollection
	 * @return
	 */
	public boolean addCollection(ShopCollection shopCollection);
	/**
	 * @desc 添加一件收藏物
	 * @param uid 用户id
	 * @param gid 商品id
	 * @return
	 */
	public boolean addCollection(String uid,String gid);
	/**
	 * @desc 根据用户id，查询收藏物
	 * @param uid
	 * @return
	 */
	public List<ShopCollection> findCollectionByUser(String uid);
	/**
	 * @desc 删除id集，对应的收藏物
	 * @param id
	 * @return
	 */
	public boolean delCollection(String[]id);
	
}
