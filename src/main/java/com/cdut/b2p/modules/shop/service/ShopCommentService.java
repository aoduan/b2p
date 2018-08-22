package com.cdut.b2p.modules.shop.service;
/**
 * @title ShopCommentService
 * @desc   用户评论接口
 * @author zsb
 *
 */

import java.util.Date;
import java.util.List;

import com.cdut.b2p.modules.shop.po.ShopComment;


public interface ShopCommentService {
	/**
	 * @desc  添加一条评论记录
	 * @param shopComment
	 * @return boolean
	 */
	public boolean addComment(ShopComment shopComment);
	/**
	 * @desc 根据id，删除相应的评论记录
	 * @param id
	 * @return Boolean
	 */
	public boolean deleteComment(String id);
	/**
	 * @desc 根据id，更新相应的评论
	 * @param shopComment
	 * @return
	 */
	public boolean updateComment(ShopComment shopComment);
	/**
	 * @desc 根据id，查询相应的评论
	 * @param id
	 * @return
	 */
	public ShopComment findCommentById(String id);
	/**
	 * @desc 根据用户id，查询其所发表的评论
	 * @param id
	 * @return
	 */
	public List<ShopComment> findCommentByUser(String uid);
	/**
	 * @desc 根据商品id，查询其被发表的评论
	 * @param gid
	 * @return
	 */
	public List<ShopComment> findCommentByGoods(String gid);
	/**
	 * @desc 查询所有的评论
	 * @return
	 */
	public List<ShopComment> findAllComment();
	
	
	
	/**
	 * @desc 查询某一段时间内，发表的评论
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<ShopComment> findCommentByDate(Date startDate,Date endDate);
	
}
