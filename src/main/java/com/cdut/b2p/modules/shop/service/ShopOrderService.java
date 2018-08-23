package com.cdut.b2p.modules.shop.service;


import java.util.Date;
import java.util.List;


import org.quartz.SimpleTrigger;

import com.cdut.b2p.modules.shop.po.ShopOrder;
import com.cdut.b2p.modules.shop.po.ShopOrderVo;

public interface ShopOrderService {
	/**
	 * @desc 上一个月成交的订单数量
	 * @author zsb
	 */
	public Integer addOrdersCountByMonth();
	/**
	 * @desc 添加一个订单
	 * @param uid
	 * @param gid
	 * @return
	 */
	public ShopOrder addOrder(String uid,String gid);
	/**
	 * @desc 查询某一时间段内的用户订单
	 * @author zsb
	 * @param startDate
	 * @param enDate
	 * @return
	 */
	public List<ShopOrder> findOrderByDate(Date startDate,Date enDate);
	/**
	 * @desc 查询生成的所有订单
	 * @author zsb
	 * @return
	 */
	public List<ShopOrder> findAllOrder();
	/**
	 * @desc 通过买方id，查询订单
	 * @param id
	 * @return
	 */
	public List<ShopOrder> findOrderByCustomer(String id);
	/**
	 * @desc 展示用户的 订单信息
	 * @param uid
	 * @return
	 */
	public List<ShopOrderVo> myOrders(String uid);
	/**
	 * @desc 通过卖方id，查询订单
	 * @param id
	 * @return
	 */
	public List<ShopOrder> findOrderBySeller(String id);
	/**
	 * @desc 用户催单
	 * @param id
	 * @return
	 */
	public boolean reminder(String id);
	/**
	 * @desc 确认id对应的商品已经达到
	 * @param id
	 * @return
	 */
	public boolean suerOrder(String id);
	/**
	 * @desc 根据id，更新相应的订单信息
	 * @author zsb
	 * @param shopOrder
	 * @return
	 */
	public boolean updateOrder(ShopOrder shopOrder);
	/**
	 * @desc 根据id，删除相应的订单
	 * @author zsb
	 * @param id
	 * @return
	 */
	public boolean deleteOrder(String id);
	/**
	 * @desc 根据ids集，,删除相应的订单
	 * @param ids
	 * @return
	 */
	public boolean deleteOrder(String[]ids);

}
