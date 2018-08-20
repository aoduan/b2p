package com.cdut.b2p.modules.shop.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdut.b2p.modules.shop.service.ShopChatService;
import com.cdut.b2p.modules.shop.po.ShopChat;
import com.cdut.b2p.modules.shop.mapper.ShopChatMapper;
import com.cdut.b2p.modules.shop.po.ShopChatExample;
@Service
@Transactional
public class ShopChatServiceImpl implements ShopChatService{
	//注解注入CustomerMapper
		@Autowired
		private ShopChatMapper shopchatmapper;
		//发表留言
		public void addMessage(ShopChat shopchat ) {
			
			shopchat.setUpdateDate(new Date());
			shopchat.setCreateDate(shopchat.getUpdateDate());
			shopchatmapper.insert(shopchat);
			}
		//查询留言(根据商品id)
		public List findMessagebyId(String goodsid) {
			ShopChatExample sce=new ShopChatExample();
			sce.or().andChatGoodsIdEqualTo(goodsid);
			
			List<String> twoList = new ArrayList<String>();
			for(int i=0;i<shopchatmapper.selectByExample(sce).size();i++){
				twoList.add(shopchatmapper.selectByExample(sce).get(i).getChatMessage());
			}
			return twoList;
			
		}
		//删除留言(根据用户id)
		@Override
		public void deleteMessagebyId(String userid) {
			// TODO Auto-generated method stub
			ShopChatExample sce=new ShopChatExample();
			sce.or().andChatUserIdEqualTo(userid);
			
			 shopchatmapper.deleteByExample(sce);
		}
			
		
		
}
