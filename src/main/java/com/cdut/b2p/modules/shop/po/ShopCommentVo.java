package com.cdut.b2p.modules.shop.po;

/**
 * @desc ShopCommentVo是ShopComment的扩展类
 * @author zsb
 *
 */
public class ShopCommentVo extends ShopComment{
	private static final long serialVersionUID = 1L;
	private String userNickname;//评论人的昵称
	private String userImage;//评论人的头像
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	
}
