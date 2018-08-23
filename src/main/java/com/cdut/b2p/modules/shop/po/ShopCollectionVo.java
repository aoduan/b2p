package com.cdut.b2p.modules.shop.po;
/**
 * @desc   ShopCollectionVo是ShopCollection的扩展类
 * @author Administrator
 *
 */
public class ShopCollectionVo extends ShopCollection{
	private String url;//url为商品的图片路径
	private String describe;//商品描述
	private ShopGoods      shopGoods;
	public ShopCollectionVo() {
		super();
	}
	public ShopCollectionVo( ShopGoods shopGoods) {
		super();
		this.shopGoods = shopGoods;
	}

	public ShopGoods getShopGoods() {
		return shopGoods;
	}
	public void setShopGoods(ShopGoods shopGoods) {
		this.shopGoods = shopGoods;
	}
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
	
	
}
