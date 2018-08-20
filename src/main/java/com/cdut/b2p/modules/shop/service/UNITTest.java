package com.cdut.b2p.modules.shop.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cdut.b2p.modules.shop.mapper.ShopChatMapper;
import com.cdut.b2p.modules.shop.po.ShopChat;
import com.cdut.b2p.modules.shop.po.ShopGoods;
import com.cdut.b2p.modules.shop.service.impl.ShopChatServiceImpl;
import com.cdut.b2p.modules.shop.service.impl.ShopGoodsServiceImpl;
import com.mysql.fabric.xmlrpc.base.Array;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;




@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件位置
@ContextConfiguration("classpath*:/spring-context*.xml")
public class UNITTest{
	//声明日志管理，日志记录
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
   // private ShopChatServiceImpl  scsi;
    private ShopGoodsServiceImpl  sgsi;
    @Test
    public void test() {
    	//shopgoodsservice测试
    	ShopGoods sg=new ShopGoods();
    	//查询
    	sg=sgsi.findGoodsbyid("13");
    	System.out.println(sg.getGoodsSellerId()+"  "+sg.getGoodsClickTimes()+"  "+sg.getGoodsPics()+"  "+sg.getGoodsPresentPrice());
    	//添加
//    	sg.setGoodsAreaId("123");
//    	sg.setGoodsBrandId("111");
//    	sg.setGoodsCategoryId("111");
//    	sg.setGoodsClickTimes(111);
//    	sg.setGoodsBrandModel("111");
//    	sg.setId("21");
//    	sgsi.saveGoods(sg);
    	//删除
//    	sgsi.deleteGoods("21");
//    	System.out.println("完成");
    	
    	
        //shopchatservice 测试
    	
           //ShopChat sc = new ShopChat();
         
//            sc.setChatMessage("ceshi ");
//           sc.setId("5");
//           sc.setChatGoodsId("2");
//           sc.setChatUserId("123");
//           List<String> lis=new ArrayList<String>();
//           lis=scsi.findMessagebyId("2");
           //scsi.deleteMessagebyId("22");
          // scsi.addMessage(sc);
//       for(int i=0;i<lis.size();i++)
//       {
//        System.out.println(lis.get(i));
//       }
        
        
        
    }

    
}