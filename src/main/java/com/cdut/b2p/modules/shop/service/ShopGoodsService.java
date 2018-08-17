package com.cdut.b2p.modules.shop.service;

import java.util.Date;
import java.util.List;
import com.cdut.b2p.common.po.Page;
import com.cdut.b2p.modules.shop.po.ShopGoods;
import com.cdut.b2p.modules.shop.po.ShopGoodsInfo;
import com.cdut.b2p.modules.sys.po.SysDict;

public interface ShopGoodsService {
	/**
	 * @desc 查询上一月增加的商品
	 * @author zsb
	 * @return Integer
	 */
	public Integer addGoodsCountByMonth();

	public void save(ShopGoods shopGoods);

	/**
	 * @desc 查询所有的商品
	 * @author zsb
	 * @return List<ShopGoods>
	 */
	public List<ShopGoods> findAllGoods();
	/**
	 * @desc 查询某一时间段内，上架的商品
	 * @author zsb
	 * @param startDate
	 * @param endDate
	 * @return List<ShopGoods>
	 */
	public List<ShopGoods> findGoodsByDate(Date startDate,Date endDate);
	/**
	 * @desc 根据id，更新某一件商品
	 * @param shopGoods
	 * @return boolean
	 */
	public boolean updateGoods(ShopGoods shopGoods);
	/**
	 * @desc 根据商品id，删除某一件商品
	 * @param id
	 * @return
	 */
	public boolean deleteGoods(String id);
	
	
	public List<SysDict> findAllBrandByGoodsType(String goods_type);

	public List<SysDict> findAllDict();
	
	public List<ShopGoods> findAllGoods();
	
	public Page<ShopGoodsInfo> findGoodsofOnePage(String type,String brand,
			Integer min_price,Integer max_price,
			String area,Integer pageNum,Integer pageSize);

}
