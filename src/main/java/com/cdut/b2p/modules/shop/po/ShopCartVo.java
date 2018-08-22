package com.cdut.b2p.modules.shop.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class ShopCartVo implements Serializable{

	/**
     * 商品品牌
     */
  
    private String goodsBrand;

    /**
     * 商品型号
     */
    private String goodsBrandModel;

    /**
     * 商品标题
     */
    private String goodsTitle;

    /**
     * 商品原价
     */
    private BigDecimal goodsOriginalPrice;

    /**
     * 商品现价
     */
    private BigDecimal goodsPresentPrice;

    /**
     * 商品新旧程度
     */
    private String goodsOldLevel;

    /**
     * 商品数量
     */
    private Integer goodsNums;

    /**
     * 商品图片
     */
    private String goodsPics;
    
    /**
     * 商品状态
     */
    private String goodsStatus;
    
    public String getGoodsBrand() {
		return goodsBrand;
	}

	public void setGoodsBrand(String goodsBrand) {
		this.goodsBrand = goodsBrand;
	}

	public String getGoodsBrandModel() {
		return goodsBrandModel;
	}

	public void setGoodsBrandModel(String goodsBrandModel) {
		this.goodsBrandModel = goodsBrandModel;
	}

	public String getGoodsTitle() {
		return goodsTitle;
	}

	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}

	public BigDecimal getGoodsOriginalPrice() {
		return goodsOriginalPrice;
	}

	public void setGoodsOriginalPrice(BigDecimal goodsOriginalPrice) {
		this.goodsOriginalPrice = goodsOriginalPrice;
	}

	public BigDecimal getGoodsPresentPrice() {
		return goodsPresentPrice;
	}

	public void setGoodsPresentPrice(BigDecimal goodsPresentPrice) {
		this.goodsPresentPrice = goodsPresentPrice;
	}

	public String getGoodsOldLevel() {
		return goodsOldLevel;
	}

	public void setGoodsOldLevel(String goodsOldLevel) {
		this.goodsOldLevel = goodsOldLevel;
	}

	public Integer getGoodsNums() {
		return goodsNums;
	}

	public void setGoodsNums(Integer goodsNums) {
		this.goodsNums = goodsNums;
	}
	public String getGoodsPics() {
		return goodsPics;
	}

	public void setGoodsPics(String goodsPics) {
		this.goodsPics = goodsPics;
	}
	public String getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}
}
