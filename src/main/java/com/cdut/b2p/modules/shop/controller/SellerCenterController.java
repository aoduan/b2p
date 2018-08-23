package com.cdut.b2p.modules.shop.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cdut.b2p.common.config.Global;
import com.cdut.b2p.common.controller.BaseController;
import com.cdut.b2p.common.utils.DateUtils;
import com.cdut.b2p.common.utils.IdUtils;
import com.cdut.b2p.common.utils.ValidateUtils;
import com.cdut.b2p.modules.shop.po.ShopGoods;
import com.cdut.b2p.modules.shop.po.ShopGoodsInfo;
import com.cdut.b2p.modules.shop.security.annotation.ShopAuth;
import com.cdut.b2p.modules.shop.service.ShopGoodsService;
import com.cdut.b2p.modules.sys.service.SysDictService;

/**
 * @title SellerCenterController
 * @desc SellerCenterController为卖家中心处理类
 * @author zsb
 * @Date 2018/8/18
 *
 */
@Controller
@RequestMapping("${shopPath}/sellerCenter")
public class SellerCenterController extends BaseController {
	
	@Autowired
	private ShopGoodsService shopGoodsService;
	@Autowired
	private SysDictService sysDictService;
	
	@ResponseBody
	@RequestMapping(value = ("/upload"), method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException, IOException {

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
		// 获取文件上传的真实路径
		String uploadPath = request.getSession().getServletContext().getRealPath("/");
		// 上传文件过程

		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

		String destDir = Global.getGoodsfilesBaseDir();
		File destFile = new File(uploadPath + destDir);
		if (!destFile.exists()) {
			destFile.mkdirs();
		}
		String fileNameNew = String.valueOf(System.currentTimeMillis()) + RandomStringUtils.randomAlphanumeric(5) + "." + suffix;
		File f = new File(destFile.getAbsoluteFile() + File.separator + fileNameNew);
		// 如果当前文件已经存在了，就跳过。
		if (f.exists()) {
			return "";
		}
		file.transferTo(f);
		f.createNewFile();

		return (destDir + fileNameNew);
	}

	@ShopAuth
	@RequestMapping(value = ("/goods/onshelf"), method = RequestMethod.POST)
	public String upload(HttpServletRequest request, HttpServletResponse response, String brand_id, String brand_model,
			String oldLevel, String pre_price, String now_price, String area_id, String title, String descibe,
			String pics) {

		String uid=(String) request.getAttribute("uid");
		if (ValidateUtils.validateText(brand_id, 32, 32)
				&& ValidateUtils.validateText(brand_model, 1, 20)
				&& ValidateUtils.validateText(oldLevel, 1, 2)
				&& ValidateUtils.validateText(pre_price, 1, 6)
				&& ValidateUtils.validateText(now_price, 1, 6)
				&& ValidateUtils.validateText(area_id, 32, 32)
				&& ValidateUtils.validateText(title, 10, 100)
				&& ValidateUtils.validateText(descibe, 10, 200)
				&& ValidateUtils.validateText(pics, 1, 255)) {
			
			ShopGoods shopGoods = new ShopGoods();
			shopGoods.setId(IdUtils.uuid());
			shopGoods.setGoodsAreaId(area_id);
			shopGoods.setGoodsBrandId(brand_id);
			shopGoods.setGoodsBrandModel(brand_model);
			String goodsCategoryId = sysDictService.findIdByType("phone");
			shopGoods.setGoodsCategoryId(goodsCategoryId);
			shopGoods.setGoodsClickTimes(0);
			shopGoods.setGoodsDiscrible(descibe);
			shopGoods.setGoodsOriginalPrice(new BigDecimal(Integer.parseInt(pre_price)));
			shopGoods.setGoodsPresentPrice(new BigDecimal(Integer.parseInt(now_price)));
			shopGoods.setGoodsSellerId(uid);
			shopGoods.setGoodsTitle(title);
			shopGoods.setGoodsOldLevel(oldLevel);
			shopGoods.setGoodsStatus("0");
			Date d = new Date();
			shopGoods.setGoodsUpTime(d);
			shopGoods.setGoodsEndTime(DateUtils.getDateAfter(d, 30));
			
			String[] splited = pics.split(",");
			if(splited.length > 0) {
				shopGoods.setGoodsPics(splited[0]);
			}
			shopGoods.setRemarks(pics);
			shopGoodsService.saveGoods(shopGoods);
			return renderSuccessString(response, "上架成功", shopGoods.getId());

		}else {
			return renderErrorString(response, "上架失败");
		}
	}
	
	@ShopAuth
	@RequestMapping(value = ("/goods/goods_list"), method = RequestMethod.POST)
	public String goods_list(HttpServletRequest request, HttpServletResponse response) {
		String uid=(String) request.getAttribute("uid");
		List<ShopGoodsInfo> list = shopGoodsService.findGoodsBySellerId(uid);
		return renderSuccessString(response, "获取商品", list);	
	}
	
	@ShopAuth
	@RequestMapping(value = ("/goods/goods_del"), method = RequestMethod.POST)
	public String goods_del(HttpServletRequest request, HttpServletResponse response, String goods_id) {
		String uid=(String) request.getAttribute("uid");
		ShopGoods goods = shopGoodsService.findGoodsById(goods_id);
		if(goods == null || !goods.getGoodsSellerId().equals(uid)) {
			return renderSuccessString(response, "删除失败");	
		}
		boolean rs = shopGoodsService.deleteGoods(goods_id);
		if(rs) {
			return renderSuccessString(response, "删除成功");	
		}else {
			return renderSuccessString(response, "删除失败");	
		}
		
	}
	
	@ShopAuth
	@RequestMapping(value = ("/goods/edit"), method = RequestMethod.POST)
	public String upload(HttpServletRequest request, HttpServletResponse response, String goods_id, String brand_id, String brand_model,
			String oldLevel, String pre_price, String now_price, String area_id, String title, String descibe,
			String pics) {
		String uid=(String) request.getAttribute("uid");
		if(goods_id == null) {
			return renderErrorString(response, "修改失败");
		}
		ShopGoods goods = shopGoodsService.findGoodsById(goods_id);
		if(goods == null || !goods.getGoodsSellerId().equals(uid)) {
			return renderErrorString(response, "修改失败");
		}
		if (ValidateUtils.validateText(brand_id, 32, 32)
				&& ValidateUtils.validateText(brand_model, 1, 20)
				&& ValidateUtils.validateText(oldLevel, 1, 2)
				&& ValidateUtils.validateText(pre_price, 1, 6)
				&& ValidateUtils.validateText(now_price, 1, 6)
				&& ValidateUtils.validateText(area_id, 32, 32)
				&& ValidateUtils.validateText(title, 10, 100)
				&& ValidateUtils.validateText(descibe, 10, 200)
				&& ValidateUtils.validateText(pics, 1, 255)) {

			goods.setGoodsAreaId(area_id);
			goods.setGoodsBrandId(brand_id);
			goods.setGoodsBrandModel(brand_model);
			goods.setGoodsDiscrible(descibe);
			goods.setGoodsOriginalPrice(new BigDecimal(Integer.parseInt(pre_price)));
			goods.setGoodsPresentPrice(new BigDecimal(Integer.parseInt(now_price)));
			goods.setGoodsSellerId(uid);
			goods.setGoodsTitle(title);
			goods.setGoodsOldLevel(oldLevel);

			String[] splited = pics.split(",");
			if(splited.length > 0) {
				goods.setGoodsPics(splited[0]);
			}
			goods.setRemarks(pics);
			shopGoodsService.updateGoods(goods);
			return renderSuccessString(response, "修改成功");

		}else {
			return renderErrorString(response, "修改失败");
		}
	}

}
