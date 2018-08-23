package com.cdut.b2p.modules.shop.service.impl;
/**
 * @title ShopCommentServiceImpl
 * @desc  ShopCommentServiceImpl是ShopCommentService的实现类
 * @author zsb 
 * @Date   2018/08/17   
 */
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdut.b2p.common.utils.IdUtils;
import com.cdut.b2p.modules.shop.mapper.ShopCommentMapper;
import com.cdut.b2p.modules.shop.po.ShopComment;
import com.cdut.b2p.modules.shop.po.ShopCommentExample;
import com.cdut.b2p.modules.shop.service.ShopCommentService;
@Service
@Transactional
public class ShopCommentServiceImpl implements ShopCommentService {
	@Autowired
	private ShopCommentMapper shopCommentMapper;
	/**
	 * @desc  添加一条评论记录
	 * @param shopComment
	 * @return boolean
	 */
	@Transactional(readOnly=false)
	@Override
	public boolean addComment(ShopComment shopComment) {
		shopComment.setId(IdUtils.uuid());
		shopComment.setCreateDate(new Date());
		shopComment.setUpdateDate(new Date());
		shopComment.setDelFlag("0");
		int count=shopCommentMapper.insertSelective(shopComment);
		if(count>0) {
			return true;
		}
		return false;
	}
	/**
	 * @desc 根据id，删除相应的评论记录
	 * @param id
	 * @return Boolean
	 */
	@Transactional(readOnly=false)
	@Override
	public boolean deleteComment(String id) {
		ShopCommentExample example=new ShopCommentExample();
		example.or().andIdEqualTo(id);
		ShopComment comment=new ShopComment();
		comment.setUpdateDate(new Date());
		comment.setDelFlag("1");
		int count=shopCommentMapper.updateByExampleSelective(comment, example);
		if(count>=0) {
			return true;
		}
		return false;
	}
	/**
	 * @desc 根据id，更新相应的评论
	 * @param shopComment
	 * @return
	 */
	@Transactional(readOnly=false)
	@Override
	public boolean updateComment(ShopComment shopComment) {
		int count=shopCommentMapper.updateByPrimaryKeySelective(shopComment);
		if(count>0) {
			return true;
		}
		return false;
	}
	/**
	 * @desc 根据id，查询相应的评论
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	@Override
	public ShopComment findCommentById(String id) {
		ShopCommentExample example=new ShopCommentExample();
		example.or().andIdEqualTo(id);
		List<ShopComment> list=shopCommentMapper.selectByExample(example);
		if(list!=null) {
			return list.get(0);
		}
		return null;
	}
	/**
	 * @desc 根据用户id，查询其所发表的评论
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	@Override
	public List<ShopComment> findCommentByUser(String uid) {
		ShopCommentExample example=new ShopCommentExample();
		example.or().andCommentUserIdEqualTo(uid);
		List<ShopComment> list=shopCommentMapper.selectByExample(example);
		return list;
	}
	/**
	 * @desc 根据商品id，查询其被发表的评论
	 * @param gid
	 * @return
	 */
	@Transactional(readOnly=true)
	@Override
	public List<ShopComment> findCommentByGoods(String gid) {
		ShopCommentExample example=new ShopCommentExample();
		example.or().andCommentGoodsIdEqualTo(gid).andDelFlagEqualTo("0");
		List<ShopComment> list=shopCommentMapper.selectByExample(example);
		return list;
	}
	/**
	 * @desc 查询所有的评论
	 * @return
	 */
	@Transactional(readOnly=true)
	@Override
	public List<ShopComment> findAllComment() {
		ShopCommentExample example=new ShopCommentExample();
		example.or().andDelFlagEqualTo("0");
		List<ShopComment> list=shopCommentMapper.selectByExample(example);
		return list;
	}
	/**
	 * @desc 查询某一段时间内，发表的评论
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Transactional(readOnly=true)
	@Override
	public List<ShopComment> findCommentByDate(Date startDate, Date endDate) {
		ShopCommentExample example=new ShopCommentExample();
		example.or().andCreateDateBetween(startDate, endDate);
		List<ShopComment> list=shopCommentMapper.selectByExample(example);
		return list;
	}

}
