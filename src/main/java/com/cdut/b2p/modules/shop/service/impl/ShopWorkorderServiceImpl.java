package com.cdut.b2p.modules.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.RemoteTimeoutException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdut.b2p.common.utils.CacheUtils;
import com.cdut.b2p.modules.shop.mapper.ShopWorkorderMapper;
import com.cdut.b2p.modules.shop.po.ShopWorkorder;
import com.cdut.b2p.modules.shop.po.ShopWorkorderExample;
import com.cdut.b2p.modules.shop.service.ShopWorkorderService;


@Service
@Transactional
public class ShopWorkorderServiceImpl implements ShopWorkorderService{
	@Autowired
	private ShopWorkorderMapper shopWorkorderMapper;
	/**
	 * @desc 增加一条投诉信息
	 * @param shopWorkorder
	 * @return
	 */
	@Transactional(readOnly=false)
	@Override
	public boolean addShopWorkOrder(ShopWorkorder shopWorkorder) {
		int count=shopWorkorderMapper.insertSelective(shopWorkorder);
		if(count>0) {
			return true;
		}
		return false;
	}
	/**
	 * @desc 根据接受者的id，查询相应的消息【该消息类型为1，表示订单状态】
	 * @param uid
	 * @return
	 */
	@Override
	public List<ShopWorkorder> findWorkOrderByUser(String uid) {
		ShopWorkorderExample example=new ShopWorkorderExample();
		example.or().andWorkorderReceiverIdEqualTo(uid).andWorkorderTypeIdEqualTo("1").andDelFlagEqualTo("0");
		List<ShopWorkorder> list=shopWorkorderMapper.selectByExample(example);
		return list;
	}
	/**
	 * @desc 根据id，删除相应的记录
	 * @param id
	 * @return
	 */
	@Override
	public boolean delWorkOrderById(String id) {
		ShopWorkorder shopWorkorder=new ShopWorkorder();
	    shopWorkorder.setId(id);
		shopWorkorder.setDelFlag("1");
		ShopWorkorderExample example=new ShopWorkorderExample();
		example.or().andIdEqualTo(id);
		int count=shopWorkorderMapper.updateByExampleSelective(shopWorkorder, example);
		if(count>0) {
			return true;
		}
		return false;
	}

}
