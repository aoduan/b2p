package com.cdut.b2p.modules.shop.service.impl;


import static org.junit.jupiter.api.Assumptions.assumingThat;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.print.DocFlavor.STRING;

import org.apache.commons.io.filefilter.FalseFileFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import com.cdut.b2p.common.utils.IdUtils;

import com.cdut.b2p.modules.shop.mapper.ShopGoodsMapper;
import com.cdut.b2p.modules.shop.mapper.ShopOrderMapper;
import com.cdut.b2p.modules.shop.po.ShopGoods;
import com.cdut.b2p.modules.shop.po.ShopGoodsExample;

import com.cdut.b2p.modules.shop.po.ShopOrder;
import com.cdut.b2p.modules.shop.po.ShopOrderExample;
import com.cdut.b2p.modules.shop.po.ShopOrderVo;
import com.cdut.b2p.modules.shop.service.ShopOrderService;

@Service
@Transactional
public class ShopOrderServiceImpl implements ShopOrderService{
	@Autowired

	private ShopOrderMapper shopOrderMapper;
	@Autowired
	private ShopGoodsMapper shopGoodsMapper;
	/**
	 * @desc 上一个月成交的订单数量
	 * @author zsb
	 */
	@SuppressWarnings("deprecation")
	@Transactional(readOnly=true)
	@Override
	public Integer addOrdersCountByMonth() {
		Date date=new Date();
		int year=date.getYear();
		int month=date.getMonth();
		//上一月的开始时间[默认为每月的时间为31天]
		Date startDate=new Date(year,month,1);
		//上一月的结束时间
		Date endDate=new Date(year,month,31);
		//开始查询
		ShopOrderExample shopOrderExample=new ShopOrderExample();
		shopOrderExample.or().andCreateDateBetween(startDate, endDate);
		List<ShopOrder> list=shopOrderMapper.selectByExample(shopOrderExample);
		return list.size();
	}
	/**
	 * @desc 查询某一时间段内的用户订单
	 * @author zsb
	 * @param startDate
	 * @param enDate
	 * @return
	 */
	@Transactional(readOnly=true)
	@Override
	public List<ShopOrder> findOrderByDate(Date startDate, Date enDate) {
		ShopOrderExample example=new ShopOrderExample();
		example.or().andCreateDateBetween(startDate, enDate).andDelFlagEqualTo("0");
		List<ShopOrder> list=shopOrderMapper.selectByExample(example);
		return list;
	}
	/**
	 * @desc 查询生成的所有订单
	 * @author zsb
	 * @return
	 */
	@Transactional(readOnly=true)
	@Override
	public List<ShopOrder> findAllOrder() {
		ShopOrderExample example=new ShopOrderExample();
		example.or().andDelFlagEqualTo("0");
		List<ShopOrder> list=shopOrderMapper.selectByExample(example);
		return list;
	}
	/**
	 * @desc 根据id，更新相应的订单信息
	 * @author zsb
	 * @param shopOrder
	 * @return
	 */
	@Transactional(readOnly=false)
	@Override
	public boolean updateOrder(ShopOrder shopOrder) {
		int count=shopOrderMapper.updateByPrimaryKey(shopOrder);
		if(count>0) {
			return true;
		}
		return false;
	}
	/**
	 * @desc 根据id，删除相应的订单
	 * @author zsb
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=false)
	@Override
	public boolean deleteOrder(String id) {
		ShopOrder shopOrder=new ShopOrder();
		shopOrder.setId(id);
		shopOrder.setDelFlag("1");
		int count=shopOrderMapper.updateByPrimaryKeySelective(shopOrder);
		if(count>0) {
			return true;
		}
		return false;
	}
	/**
	 * @desc 通过买方id，查询订单
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	@Override
	public List<ShopOrder> findOrderByCustomer(String id) {
		ShopOrderExample example=new ShopOrderExample();
		example.or().andOrderBuyerIdEqualTo(id).andDelFlagEqualTo("0").andOrderStatusEqualTo("1");
		List<ShopOrder> list=shopOrderMapper.selectByExample(example);
		return list;
	}
	/**
	 * @desc 通过卖方id，查询订单
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	@Override
	public List<ShopOrder> findOrderBySeller(String id) {
		//通过卖家id，查询商品id
		ShopGoodsExample shopGoodsExample=new ShopGoodsExample();
		shopGoodsExample.or().andGoodsSellerIdEqualTo(id).andDelFlagEqualTo("0");
		List<ShopGoods> goodsList=shopGoodsMapper.selectByExample(shopGoodsExample);
		List<String> idList=new ArrayList<>();
		for(int i=0;i<goodsList.size();i++) {
			idList.add(goodsList.get(i).getId());
		}
		ShopOrderExample example=new ShopOrderExample();
		example.or().andOrderGoodsIdIn(idList).andDelFlagEqualTo("0");
		List<ShopOrder> list=shopOrderMapper.selectByExample(example);
		return list;
	}
	/**
	 * @desc 根据ids集，删除相应的订单
	 * @param ids
	 * @return
	 */
	@Override
	public boolean deleteOrder(String[] ids) {
		List<String> idList=new ArrayList<>();
		for(int i=0;i<ids.length;i++) {
			idList.add(ids[i]);
		}
		ShopOrder shopOrder=new ShopOrder();
		shopOrder.setDelFlag("1");
		ShopOrderExample example=new ShopOrderExample();
		example.or().andIdIn(idList);
		int count=shopOrderMapper.updateByExampleSelective(shopOrder, example);
		if(count>=0) {
			return true;
		}
		return false;
	}
	/**
	 * @desc 确认id对应的商品已经达到
	 * @param id
	 * @return
	 */
	@Override
	public boolean suerOrder(String id) {
		ShopOrder shopOrder=new ShopOrder();
		shopOrder.setOrderStatus("3");
		shopOrder.setUpdateDate(new Date());
		ShopOrderExample example=new ShopOrderExample();
		example.or().andIdEqualTo(id);
		int count=shopOrderMapper.updateByExampleSelective(shopOrder, example);
		if(count>0) {
			return true;
		}
		return false;
	}
	/**
	 * @desc 用户催单
	 * @param id
	 * @return
	 */
	@Override
	public boolean reminder(String id) {
		ShopOrder shopOrder=new ShopOrder();
		shopOrder.setOrderStatus("2");
		shopOrder.setUpdateDate(new Date());
		ShopOrderExample example=new ShopOrderExample();
		example.or().andIdEqualTo(id);
		int count=shopOrderMapper.updateByExampleSelective(shopOrder, example);
		if(count>0) {
			return true;
		}
		return false;

	}
	/**
	 * @desc 展示用户的 订单信息
	 * @param uid
	 * @return
	 */
	@Override
	public List<ShopOrderVo> myOrders(String uid) {
		List<ShopOrderVo> list=shopOrderMapper.myOrders(uid);
		return list;
	}
	/**
	 * @desc 添加一个订单
	 * @param uid
	 * @param gid
	 * @return
	 */
	@Override
	public ShopOrder addOrder(String uid, String gid) {
		//根据商品id，查询商品价格
		ShopGoods goods=shopGoodsMapper.selectByPrimaryKey(gid);
		ShopOrder order=new ShopOrder();
		order.setId(IdUtils.uuid());
		order.setOrderBuyerId(uid);
		order.setOrderGoodsId(gid);
		order.setCreateDate(new Date());
		order.setOrderStatus("0");
		order.setDelFlag("0");
		order.setOrderPrice(goods.getGoodsPresentPrice());
	
		int count=shopOrderMapper.insertSelective(order);
		
		return order;
	}

}
