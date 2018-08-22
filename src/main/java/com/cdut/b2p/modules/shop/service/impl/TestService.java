package com.cdut.b2p.modules.shop.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdut.b2p.common.utils.DateUtils;
import com.cdut.b2p.common.utils.IdUtils;
import com.cdut.b2p.common.utils.PropertiesLoaderUtils;
import com.cdut.b2p.common.utils.SecurityUtils;
import com.cdut.b2p.common.utils.StringUtils;
import com.cdut.b2p.modules.shop.mapper.AdvertisementMapper;
import com.cdut.b2p.modules.shop.mapper.ShopGoodsMapper;
import com.cdut.b2p.modules.shop.mapper.ShopUserMapper;
import com.cdut.b2p.modules.shop.mapper.ShopWalletMapper;
import com.cdut.b2p.modules.shop.po.Advertisement;
import com.cdut.b2p.modules.shop.po.AdvertisementExample;
import com.cdut.b2p.modules.shop.po.ShopGoods;
import com.cdut.b2p.modules.shop.po.ShopUser;
import com.cdut.b2p.modules.shop.po.ShopUserExample;
import com.cdut.b2p.modules.shop.po.ShopWallet;
import com.cdut.b2p.modules.shop.po.ShopWalletExample;
import com.cdut.b2p.modules.shop.service.ShopGoodsService;
import com.cdut.b2p.modules.shop.service.ShopUserService;
import com.cdut.b2p.modules.sys.mapper.SysAreaMapper;
import com.cdut.b2p.modules.sys.mapper.SysDictMapper;
import com.cdut.b2p.modules.sys.po.SysArea;
import com.cdut.b2p.modules.sys.po.SysDict;
import com.cdut.b2p.modules.sys.service.SysAreaService;
import com.cdut.b2p.modules.sys.service.SysDictService;

@Service
@Transactional
public class TestService {
	private static Logger logger = LoggerFactory.getLogger(TestService.class);

	@Autowired
	private AdvertisementMapper advertisementMapper;
	@Autowired
	private ShopUserMapper shopUserMapper;
	@Autowired
	private ShopWalletMapper shopWalletMapper;
	@Autowired
	private SysAreaService sysAreaService;
	@Autowired
	private SysDictService sysDictService;
	@Autowired
	private ShopUserService shopUserService;
	@Autowired
	private ShopGoodsService shopGoodsService;

	private List<Advertisement> list;

	private HashMap<Object, Object> map = new HashMap<Object, Object>();
	private HashMap<Object, Object> map1 = new HashMap<Object, Object>();
	private HashMap<Object, Object> map2 = new HashMap<Object, Object>();
	private String phoneid;

	private void load() {
		String[] brandArray = new String[] { "苹果", "三星", "小米", "华为", "OPPO", "vivo", "魅族", "诺基亚", "HTC", "索尼爱立信", "努比亚",
				"夏普", "摩托罗拉", "联想", "LG", "中兴", "酷派", "金立", "锤子", "乐视", "一加" };
		AdvertisementExample ae = new AdvertisementExample();
		ae.or();
		list = advertisementMapper.selectByExample(ae);
		// list.forEach(x -> logger.debug(x.toString()));
//		List<String> sList=Arrays.asList(brandArray);
//		for (Advertisement ad : list) {
//			String[] splited = ad.getTitle().split("\\s+");
//			if(StringUtils.isBlank(ad.getNickname()) || splited.length < 3){
//				list.remove(ad);
//				continue;
//			}
//			if(!sList.contains(splited[0])) {
//				list.remove(ad);
//				continue;
//			}
//		}
	}

	private void initArea() {
		String[] areaArray = new String[] { "武侯区", "金牛区", "青羊区", "成华区", "高新区", "锦江区", "郫都区", "双流区", "高新西区", "天府新区",
				"简阳市", "龙泉驿区", "新都区", "温江区", "都江堰市", "彭州市", "青白江区", "崇州市", "金堂县", "新津县", "邛崃市", "大邑县", "蒲江县" };
		SysArea sysArea = new SysArea();
		sysArea.setAreaName("中国");
		sysArea.setAreaType("1");
		sysArea.setAreaCode("100000");
		sysArea.setAreaSort(new BigDecimal(10));
		String uid = IdUtils.uuid();
		sysArea.setId(uid);
		sysAreaService.save(sysArea);

		SysArea sysArea1 = new SysArea();
		sysArea1.setAreaParentId(uid);
		sysArea1.setAreaName("四川");
		sysArea1.setAreaType("2");
		sysArea1.setAreaCode("110000");
		sysArea1.setAreaSort(new BigDecimal(20));
		String uid1 = IdUtils.uuid();
		sysArea1.setId(uid1);
		sysAreaService.save(sysArea1);

		SysArea sysArea2 = new SysArea();
		sysArea2.setAreaParentId(uid1);
		sysArea2.setAreaName("成都");
		sysArea2.setAreaType("3");
		sysArea2.setAreaCode("110100");
		sysArea2.setAreaSort(new BigDecimal(30));
		String uid2 = IdUtils.uuid();
		sysArea2.setId(uid2);
		sysAreaService.save(sysArea2);
		int i = 1;
		for (String s : areaArray) {
			SysArea sysArea3 = new SysArea();
			sysArea3.setAreaParentId(uid2);
			sysArea3.setAreaName(s);
			sysArea3.setAreaType("4");
			sysArea3.setAreaCode("1101" + String.format("%02d", i));
			sysArea3.setAreaSort(new BigDecimal(40));
			sysAreaService.save(sysArea3);
			i++;
			map2.put(sysArea3.getAreaName(), sysArea3.getId());
		}

	}

	private void initDict() {
		SysDict dict1 = new SysDict();
		dict1.setId(IdUtils.uuid());
		dict1.setDictValue("10");
		dict1.setDictLabel("手机");
		dict1.setDictType("phone");
		dict1.setDictDescription("手机");
		dict1.setDictSort(new BigDecimal(10));
		dict1.setDictParentId("0");
		dict1.setDictDescription("手机");
		sysDictService.save(dict1);
		phoneid = dict1.getId();

		String[] brandArray = new String[] { "苹果", "三星", "小米", "华为", "OPPO", "vivo", "魅族", "诺基亚", "HTC", "索尼爱立信", "努比亚",
				"夏普", "摩托罗拉", "联想", "LG", "中兴", "酷派", "金立", "锤子", "乐视", "一加" };
		int i = 0;
		for (String s : brandArray) {
			SysDict dict = new SysDict();
			dict.setId(IdUtils.uuid());
			dict.setDictValue(String.format("%03d", i));
			dict.setDictLabel(s);
			dict.setDictType("phone_brand");
			dict.setDictDescription("手机品牌");
			dict.setDictSort(new BigDecimal(i * 10));
			dict.setDictParentId("0");
			dict1.setDictDescription(s);
			sysDictService.save(dict);
			i++;
			map.put(s, dict.getId());

		}

	}

	private void initUser() {
		int i = 0;
		for (Advertisement ad : list) {
			ShopUser user = new ShopUser();
			ShopWallet wallet = new ShopWallet();
			user.setId(IdUtils.uuid());
			user.setUserNickname(ad.getNickname());
			user.setUserName(String.format("%06d", i));
			user.setUserPassword(SecurityUtils.getMD5(String.format("%06d", i)));
			user.setUserEmail("cdutb2p@outlook.com");
			String path = user.getUserName() + String.valueOf(System.currentTimeMillis());
			saveUrlAs(ad.getIcon(),path);
			user.setUserImage("/userfiles/user/" + path + ".jpg");
			user.setUserScore(0);
			user.setUserStatus("0");
			user.setUserAutograph(ad.getAutograph().replace("转转", "平安二手"));
			user.setUserAddress(ad.getAddress());

			String uid = IdUtils.uuid();
			wallet.setAccount(IdUtils.uuid());
			wallet.setId(uid);
			wallet.setBalance(new BigDecimal(10000));
			shopUserService.saveWallet(wallet);

			user.setUserWalletId(uid);
			shopUserService.saveUser(user);
			i++;
			map1.put(user.getUserName(), user.getId());
		}

	}

	private void initGoods() {
		int i = 0;
		for (Advertisement ad : list) {
			ShopGoods goods = new ShopGoods();
			goods.setId(IdUtils.uuid());
			String[] splited = ad.getTitle().split("\\s+");

			String id = (String) map.get(splited[0]);
			if (id == null)
				continue;
			String addr = ad.getAddress();
			String[] addrl = addr.split("-");
			if (addrl.length <= 1) {
				continue;
			}
			String aid = (String) map2.get(addrl[1]);
			String aid1 = (String) map2.get(addrl[1] + "区");
			String aid2 = (String) map2.get(addrl[1] + "市");
			String aid3 = (String) map2.get(addrl[1] + "县");
			if (aid == null) {
				if (aid1 == null) {
					if (aid2 == null) {
						if (aid3 == null) {
							continue;
						} else {
							goods.setGoodsAreaId(aid3);
						}
					} else {
						goods.setGoodsAreaId(aid2);
					}
				} else {
					goods.setGoodsAreaId(aid1);
				}
			} else {
				goods.setGoodsAreaId(aid);
			}

			goods.setGoodsBrandId(id);
			goods.setGoodsBrandModel(splited[1]);
			// ShopUser user = shopUserService.findUserByUsername(String.format("%06d", i));
			String userid = (String) map1.get(String.format("%06d", i));
			goods.setGoodsSellerId(userid);

			// String goodsCategoryId = sysDictService.findIdByType("phone");

			goods.setGoodsCategoryId(phoneid);

			// String areaId = sysAreaService.findIdbyAreaName(ad.getAddress());
			// goods.setGoodsAreaId(goodsAreaId);
			goods.setGoodsClickTimes((int) (1 + Math.random() * (1000)));
			goods.setGoodsDiscrible(ad.getDes());
			goods.setGoodsOldLevel("9");
			goods.setGoodsOriginalPrice(new BigDecimal(Integer.valueOf(ad.getPrice()) + 1000));
			Date d = new Date();
			goods.setGoodsUpTime(d);
			goods.setGoodsEndTime(DateUtils.getDateAfter(d, 30));

			String path = String.valueOf(System.currentTimeMillis());
			saveUrlAs(ad.getImg(),path);
			goods.setGoodsPics("/userfiles/goods/" + path + ".jpg");
			goods.setGoodsPresentPrice(new BigDecimal(ad.getPrice()));
			goods.setGoodsTitle(ad.getTitle());
			goods.setGoodsStatus("0");
			shopGoodsService.saveGoods(goods);
			i++;
		}

	}
	private void fix() {
		ShopUserExample sue = new ShopUserExample();
		sue.or();
		List<ShopUser> l1 = shopUserMapper.selectByExample(sue);
		
		ShopWalletExample swe = new ShopWalletExample();
		sue.or();
		List<ShopWallet> l2 = shopWalletMapper.selectByExample(swe);
		
		for(int i = 0; i < l1.size() ; i++) {
			l1.get(i).setUserWalletId(l2.get(i).getId());
			shopUserMapper.updateByPrimaryKey(l1.get(i));
		}
		
	}

	public void start() {
		//load();
		//initArea();
		//initDict();
		//initUser();
		//initGoods();
		fix();

	}

	private void saveUrlAs(String url, String name) {
		// System.out.println("fileName---->"+filePath);
		// 创建不同的文件夹目录
		String filePath = "C:/output/d/";
		File file = new File(filePath);
		// 判断文件夹是否存在
		if (!file.exists()) {
			// 如果文件夹不存在，则创建新的的文件夹
			file.mkdirs();
		}
		FileOutputStream fileOut = null;
		HttpURLConnection conn = null;
		InputStream inputStream = null;
		try {
			// 建立链接
			URL httpUrl = new URL(url);
			conn = (HttpURLConnection) httpUrl.openConnection();
			// 以Post方式提交表单，默认get方式
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			// post方式不能使用缓存
			conn.setUseCaches(false);
			// 连接指定的资源
			conn.connect();
			// 获取网络输入流
			inputStream = conn.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(inputStream);
			// 判断文件的保存路径后面是否以/结尾

			// 写入到文件（注意文件保存路径的后面一定要加上文件的名称）
			fileOut = new FileOutputStream(filePath + name + ".jpg");
			BufferedOutputStream bos = new BufferedOutputStream(fileOut);

			byte[] buf = new byte[4096];
			int length = bis.read(buf);
			// 保存文件
			while (length != -1) {
				bos.write(buf, 0, length);
				length = bis.read(buf);
			}
			bos.close();
			bis.close();
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("抛出异常！！");
		}

	}

}
