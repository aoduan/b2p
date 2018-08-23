package com.cdut.b2p.modules.shop.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdut.b2p.modules.shop.mapper.ShopAddressMapper;
import com.cdut.b2p.modules.shop.po.ShopAddress;
import com.cdut.b2p.modules.shop.po.ShopAddressExample;
import com.cdut.b2p.modules.shop.service.ShopAddressService;
@Service
@Transactional
public class ShopAddressServiceImpl implements ShopAddressService{
	@Autowired
	private ShopAddressMapper shopAddressMapper;
	/**
	 * @desc 添加新地址
	 * @param shopAddress
	 * @return
	 */
	@Transactional(readOnly=false)
	@Override
	public boolean addAddress(ShopAddress shopAddress) {
		shopAddress.setCreateDate(new Date());
		shopAddress.setDelFlag("0");
		shopAddress.setAddressZipcode("0");
		shopAddress.setUpdateDate(new Date());
		System.out.println(shopAddress);
		int count=shopAddressMapper.insertSelective(shopAddress);
		System.out.println("count："+count);
		if(count>0) {
			return true;
		}
		return false;
	}
	/**
	 * @desc 根据用户id，查询其收获地址表;只展示最新的4个
	 * @param uid
	 * @return
	 */
	@Transactional(readOnly=true)
	@Override
	public List<ShopAddress> findAddressByUser(String uid) {
		ShopAddressExample example=new ShopAddressExample();
		example.or().andAddressUserIdEqualTo(uid);
		List<ShopAddress> list=shopAddressMapper.selectByExample(example);
		int size=list.size();
		if(size>4) {
			return list.subList(size-5, size-1);
		}
		return list;
	}

}
