package com.cdut.b2p.modules.shop.po;

import java.math.BigDecimal;

/**
 * @desc ShopCartVo是ShopCart类的扩展类
 * @author zsb
 *
 */
public class ShopCartVo extends  ShopCart{

	private static final long serialVersionUID = 1L;
	private String url;//图片路径
	private String describe;//商品描述
	private BigDecimal oldPrice;//原价
	private BigDecimal nowPrice;//现价
	private String level;//新旧程度
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public BigDecimal getOldPrice() {
		return oldPrice;
	}
	public void setOldPrice(BigDecimal oldPrice) {
		this.oldPrice = oldPrice;
	}
	public BigDecimal getNowPrice() {
		return nowPrice;
	}
	public void setNowPrice(BigDecimal nowPrice) {
		this.nowPrice = nowPrice;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	
}
