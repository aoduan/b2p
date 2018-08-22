package com.cdut.b2p.modules.shop.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * shop_goods
 * @author 
 */
public class ShopGoods implements Serializable {
    /**
     * 编号
     */
    private String id;

    /**
     * 商品种类id
     */
 
    private String goodsCategoryId;

    /**
     * 卖方id
     */
  
    private String goodsSellerId;

    /**
     * 商品区域id
     */
  
    private String goodsAreaId;

    /**
     * 商品品牌id
     */
  
    private String goodsBrandId;

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
     * 商品状态
     */
    private String goodsStatus;

    /**
     * 商品点击次数
     */
    private Integer goodsClickTimes;

    /**
     * 商品图片
     */
    private String goodsPics;

    /**
     * 商品上架时间
     */
    private Date goodsUpTime;

    /**
     * 商品下架时间
     */
    private Date goodsEndTime;

    /**
     * 创建者
     */
  
    private String createBy;

    /**
     * 创建时间
     */
  
    private Date createDate;

    /**
     * 更新者
     */
  
    private String updateBy;

    /**
     * 更新时间
     */
  
    private Date updateDate;

    /**
     * 备注信息
     */
  
    private String remarks;

    /**
     * 删除标记
     */
  
    private String delFlag;

    /**
     * 商品描述
     */
    private String goodsDiscrible;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsCategoryId() {
        return goodsCategoryId;
    }

    public void setGoodsCategoryId(String goodsCategoryId) {
        this.goodsCategoryId = goodsCategoryId;
    }

    public String getGoodsSellerId() {
        return goodsSellerId;
    }

    public void setGoodsSellerId(String goodsSellerId) {
        this.goodsSellerId = goodsSellerId;
    }

    public String getGoodsAreaId() {
        return goodsAreaId;
    }

    public void setGoodsAreaId(String goodsAreaId) {
        this.goodsAreaId = goodsAreaId;
    }

    public String getGoodsBrandId() {
        return goodsBrandId;
    }

    public void setGoodsBrandId(String goodsBrandId) {
        this.goodsBrandId = goodsBrandId;
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

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
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

    public Date getGoodsUpTime() {
        return goodsUpTime;
    }

    public void setGoodsUpTime(Date goodsUpTime) {
        this.goodsUpTime = goodsUpTime;
    }

    public Date getGoodsEndTime() {
        return goodsEndTime;
    }

    public void setGoodsEndTime(Date goodsEndTime) {
        this.goodsEndTime = goodsEndTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getGoodsDiscrible() {
        return goodsDiscrible;
    }

    public void setGoodsDiscrible(String goodsDiscrible) {
        this.goodsDiscrible = goodsDiscrible;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ShopGoods other = (ShopGoods) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGoodsCategoryId() == null ? other.getGoodsCategoryId() == null : this.getGoodsCategoryId().equals(other.getGoodsCategoryId()))
            && (this.getGoodsSellerId() == null ? other.getGoodsSellerId() == null : this.getGoodsSellerId().equals(other.getGoodsSellerId()))
            && (this.getGoodsAreaId() == null ? other.getGoodsAreaId() == null : this.getGoodsAreaId().equals(other.getGoodsAreaId()))
            && (this.getGoodsBrandId() == null ? other.getGoodsBrandId() == null : this.getGoodsBrandId().equals(other.getGoodsBrandId()))
            && (this.getGoodsBrandModel() == null ? other.getGoodsBrandModel() == null : this.getGoodsBrandModel().equals(other.getGoodsBrandModel()))
            && (this.getGoodsTitle() == null ? other.getGoodsTitle() == null : this.getGoodsTitle().equals(other.getGoodsTitle()))
            && (this.getGoodsOriginalPrice() == null ? other.getGoodsOriginalPrice() == null : this.getGoodsOriginalPrice().equals(other.getGoodsOriginalPrice()))
            && (this.getGoodsPresentPrice() == null ? other.getGoodsPresentPrice() == null : this.getGoodsPresentPrice().equals(other.getGoodsPresentPrice()))
            && (this.getGoodsOldLevel() == null ? other.getGoodsOldLevel() == null : this.getGoodsOldLevel().equals(other.getGoodsOldLevel()))
            && (this.getGoodsStatus() == null ? other.getGoodsStatus() == null : this.getGoodsStatus().equals(other.getGoodsStatus()))
            && (this.getGoodsClickTimes() == null ? other.getGoodsClickTimes() == null : this.getGoodsClickTimes().equals(other.getGoodsClickTimes()))
            && (this.getGoodsPics() == null ? other.getGoodsPics() == null : this.getGoodsPics().equals(other.getGoodsPics()))
            && (this.getGoodsUpTime() == null ? other.getGoodsUpTime() == null : this.getGoodsUpTime().equals(other.getGoodsUpTime()))
            && (this.getGoodsEndTime() == null ? other.getGoodsEndTime() == null : this.getGoodsEndTime().equals(other.getGoodsEndTime()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getGoodsDiscrible() == null ? other.getGoodsDiscrible() == null : this.getGoodsDiscrible().equals(other.getGoodsDiscrible()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGoodsCategoryId() == null) ? 0 : getGoodsCategoryId().hashCode());
        result = prime * result + ((getGoodsSellerId() == null) ? 0 : getGoodsSellerId().hashCode());
        result = prime * result + ((getGoodsAreaId() == null) ? 0 : getGoodsAreaId().hashCode());
        result = prime * result + ((getGoodsBrandId() == null) ? 0 : getGoodsBrandId().hashCode());
        result = prime * result + ((getGoodsBrandModel() == null) ? 0 : getGoodsBrandModel().hashCode());
        result = prime * result + ((getGoodsTitle() == null) ? 0 : getGoodsTitle().hashCode());
        result = prime * result + ((getGoodsOriginalPrice() == null) ? 0 : getGoodsOriginalPrice().hashCode());
        result = prime * result + ((getGoodsPresentPrice() == null) ? 0 : getGoodsPresentPrice().hashCode());
        result = prime * result + ((getGoodsOldLevel() == null) ? 0 : getGoodsOldLevel().hashCode());
        result = prime * result + ((getGoodsStatus() == null) ? 0 : getGoodsStatus().hashCode());
        result = prime * result + ((getGoodsClickTimes() == null) ? 0 : getGoodsClickTimes().hashCode());
        result = prime * result + ((getGoodsPics() == null) ? 0 : getGoodsPics().hashCode());
        result = prime * result + ((getGoodsUpTime() == null) ? 0 : getGoodsUpTime().hashCode());
        result = prime * result + ((getGoodsEndTime() == null) ? 0 : getGoodsEndTime().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getGoodsDiscrible() == null) ? 0 : getGoodsDiscrible().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", goodsCategoryId=").append(goodsCategoryId);
        sb.append(", goodsSellerId=").append(goodsSellerId);
        sb.append(", goodsAreaId=").append(goodsAreaId);
        sb.append(", goodsBrandId=").append(goodsBrandId);
        sb.append(", goodsBrandModel=").append(goodsBrandModel);
        sb.append(", goodsTitle=").append(goodsTitle);
        sb.append(", goodsOriginalPrice=").append(goodsOriginalPrice);
        sb.append(", goodsPresentPrice=").append(goodsPresentPrice);
        sb.append(", goodsOldLevel=").append(goodsOldLevel);
        sb.append(", goodsStatus=").append(goodsStatus);
        sb.append(", goodsClickTimes=").append(goodsClickTimes);
        sb.append(", goodsPics=").append(goodsPics);
        sb.append(", goodsUpTime=").append(goodsUpTime);
        sb.append(", goodsEndTime=").append(goodsEndTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", remarks=").append(remarks);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", goodsDiscrible=").append(goodsDiscrible);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}