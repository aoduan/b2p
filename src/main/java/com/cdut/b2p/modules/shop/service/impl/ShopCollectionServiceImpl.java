package com.cdut.b2p.modules.shop.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdut.b2p.common.utils.IdUtils;
import com.cdut.b2p.modules.shop.mapper.ShopCollectionMapper;
import com.cdut.b2p.modules.shop.po.ShopCollection;
import com.cdut.b2p.modules.shop.po.ShopCollectionExample;
import com.cdut.b2p.modules.shop.service.ShopCollectionService;
@Service
@Transactional
public class ShopCollectionServiceImpl implements ShopCollectionService{
    @Autowired
	private ShopCollectionMapper shopCollectionMapper;
	/**
     * @desc 添加一件收藏物
     */
    @Transactional(readOnly=false)
	@Override
	public boolean addCollection(ShopCollection shopCollection) {
		shopCollection.setId(IdUtils.uuid());
		shopCollection.setCreateDate(new Date());
		shopCollection.setUpdateDate(new Date());
    	int count=shopCollectionMapper.insertSelective(shopCollection);
		if(count>0) {
			return true;
		}
		return false;
	}
	/**
	 * @desc 添加一件收藏物
	 * @param uid 用户id
	 * @param gid 商品id
	 * @return
	 */
    @Transactional(readOnly=false)
	@Override
	public boolean addCollection(String uid, String gid) {
    	ShopCollection shopCollection=new ShopCollection();
    	shopCollection.setId(IdUtils.uuid());
    	shopCollection.setCollectionUserId(uid);
    	shopCollection.setCollectionGoodsId(gid);
    	shopCollection.setCreateDate(new Date());
    	shopCollection.setUpdateDate(new Date());
    	shopCollection.setDelFlag("0");
    	int count=shopCollectionMapper.insertSelective(shopCollection);
		if(count>0) {
			return true;
		}
		return false;
	}
	/**
	 * @desc 根据用户id，查询收藏物
	 * @param uid
	 * @return
	 */
    @Transactional(readOnly=true)
	@Override
	public List<ShopCollection> findCollectionByUser(String uid) {
		ShopCollectionExample example=new ShopCollectionExample();
		example.or().andCollectionUserIdEqualTo(uid).andDelFlagEqualTo("0");
		List<ShopCollection> list=shopCollectionMapper.selectByExample(example);
		
		return list;
	}
    /**
	 * @desc 删除id集，对应的收藏物
	 * @param id
	 * @return
	 */
	@Override
	public boolean delCollection(String[] id) {
		List<String> ids =new ArrayList<>();
		for(int i=0;i<id.length;i++){
			ids.add(id[i]);
		}
		ShopCollection shopCollection=new ShopCollection();
		shopCollection.setDelFlag("1");
		ShopCollectionExample example=new ShopCollectionExample();
		example.or().andIdIn(ids).andDelFlagEqualTo("1");
		int count=shopCollectionMapper.updateByExampleSelective(shopCollection, example);
		if(count>0) {
			return true;
		}
	    return false;
	}

}
