package com.cdut.b2p.modules.shop.service;



import java.util.List;

import com.cdut.b2p.modules.shop.po.ShopChat;

public interface ShopChatService {
	//发表留言
	public void addMessage(ShopChat shopchat);
	//查询留言(根据商品id)
	public List findMessagebyId(String goodsid);
	//删除留言(根据用户id)
	public void deleteMessagebyId(String userid);
}
