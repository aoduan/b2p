package com.cdut.b2p.modules.shop.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class ShopGoodsInfo implements Serializable{
	  /**
     * 编号
     */
    private String id;

    /**
     * 卖方nickname
     */
  
    private String goodsSellerNickname;
    
    /**
     * 卖方头像
     */
  
    private String goodsSellerImg;
    
    /**
     * 卖方id
     */
  
    private String goodsSellerId;
    
    /**
     * 卖方签名
     */
  
    private String goodsSellerAutograph;

    /**
     * 商品省
     */

    private String goodsProvince;
    
    /**
     * 商品市
     */

    private String goodsCity;
    
    /**
     * 商品区
     */

    private String goodsArea;

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
     * 商品点击次数
     */
    private Integer goodsClickTimes;
    
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
    
    /**
     * 商品描述
     */
    private String goodsDesc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGoodsSellerNickname() {
		return goodsSellerNickname;
	}

	public void setGoodsSellerNickname(String goodsSellerNickname) {
		this.goodsSellerNickname = goodsSellerNickname;
	}

	public String getGoodsProvince() {
		return goodsProvince;
	}

	public void setGoodsProvince(String goodsProvince) {
		this.goodsProvince = goodsProvince;
	}

	public String getGoodsCity() {
		return goodsCity;
	}

	public void setGoodsCity(String goodsCity) {
		this.goodsCity = goodsCity;
	}

	public String getGoodsArea() {
		return goodsArea;
	}

	public void setGoodsArea(String goodsArea) {
		this.goodsArea = goodsArea;
	}

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

	public Integer getGoodsClickTimes() {
		return goodsClickTimes;
	}

	public void setGoodsClickTimes(Integer goodsClickTimes) {
		this.goodsClickTimes = goodsClickTimes;
	}

	public String getGoodsPics() {
		return goodsPics;
	}

	public void setGoodsPics(String goodsPics) {
		this.goodsPics = goodsPics;
	}

	public Integer getGoodsNums() {
		return goodsNums;
	}

	public void setGoodsNums(Integer goodsNums) {
		this.goodsNums = goodsNums;
	}

	public String getGoodsSellerImg() {
		return goodsSellerImg;
	}

	public void setGoodsSellerImg(String goodsSellerImg) {
		this.goodsSellerImg = goodsSellerImg;
	}

	public String getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public String getGoodsSellerAutograph() {
		return goodsSellerAutograph;
	}

	public void setGoodsSellerAutograph(String goodsSellerAutograph) {
		this.goodsSellerAutograph = goodsSellerAutograph;
	}

	public String getGoodsSellerId() {
		return goodsSellerId;
	}

	public void setGoodsSellerId(String goodsSellerId) {
		this.goodsSellerId = goodsSellerId;
	}
    
    

}
