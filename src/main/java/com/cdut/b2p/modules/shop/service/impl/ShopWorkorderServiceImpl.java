package com.cdut.b2p.modules.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdut.b2p.modules.shop.mapper.ShopWorkorderMapper;
import com.cdut.b2p.modules.shop.po.ShopWorkorder;
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

}
