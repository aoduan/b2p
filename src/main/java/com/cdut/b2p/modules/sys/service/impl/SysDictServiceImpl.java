package com.cdut.b2p.modules.sys.service.impl;

/**
 * @title  SysDictServiceImpl
 * @desc   SysDictServiceImpl是SysDictService服务接口的实现类
 * @author zsb
 * @Date   2018/8/14
 *
 */
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdut.b2p.common.utils.CacheUtils;
import com.cdut.b2p.common.utils.IdUtils;
import com.cdut.b2p.common.utils.StringUtils;
import com.cdut.b2p.modules.shop.po.ShopUser;
import com.cdut.b2p.modules.sys.mapper.SysDictMapper;
import com.cdut.b2p.modules.sys.po.SysArea;
import com.cdut.b2p.modules.sys.po.SysDict;
import com.cdut.b2p.modules.sys.po.SysDictExample;
import com.cdut.b2p.modules.sys.po.SysUser;
import com.cdut.b2p.modules.sys.service.SysDictService;

@Service
@Transactional
public class SysDictServiceImpl implements SysDictService{

	@Autowired
	private SysDictMapper sysDictMapper;
	
	private void preInsert(SysDict sysDict) {
		sysDict.setUpdateDate(new Date());
		sysDict.setCreateDate(sysDict.getUpdateDate());
	}
	
	@Transactional(readOnly = false)
	@Override
	public void save(SysDict sysDict) {
		
		preInsert(sysDict);
		sysDictMapper.insertSelective(sysDict);
		CacheUtils.remove("dictList");
		CacheUtils.remove("sysdict_type_" + sysDict.getDictType());
		CacheUtils.remove("sysdict_label_" + sysDict.getDictLabel());
		CacheUtils.remove("sysdict_" + sysDict.getId());
		CacheUtils.remove(sysDict.getDictType() + "_dictList");
	}

	@Transactional(readOnly = true)
	@Override
	public List<SysDict> findAllDictLabelByDictType(String type) {
		List<SysDict> dictList = (List<SysDict>) CacheUtils.get(type + "_dictList");
		if (dictList == null) {
			SysDictExample sde = new SysDictExample();
			sde.or().andDictTypeEqualTo(type);
			dictList = sysDictMapper.selectByExample(sde);
			CacheUtils.put(type + "_dictList", dictList);

		}
		return dictList;

	}

	@Transactional(readOnly = true)
	@Override
	public List<SysDict> findAllDict() {
		List<SysDict> dictList = (List<SysDict>) CacheUtils.get("dictList");
		if (dictList == null) {
			SysDictExample sde = new SysDictExample();
			sde.or();
			dictList = sysDictMapper.selectByExample(sde);
			CacheUtils.put("dictList", dictList);
		}
		return dictList;
	}
	
	@Transactional(readOnly = true)
	@Override
	public SysDict findDictByLabel(String label,String type) {
		
		List<SysDict> list = (List<SysDict>) CacheUtils.get("sysdict_type_" + type);
		if(list == null) {
			SysDictExample sde = new SysDictExample();
			sde.or().andDictLabelEqualTo(label).andDictTypeEqualTo(type);
			list = sysDictMapper.selectByExample(sde);
			CacheUtils.put("sysdict_type_" + type, list);
		}
		return (list == null || list.isEmpty()) ? null : list.get(0);
	}
	
	@Transactional(readOnly = true)
	@Override
	public String findIdByType(String type) {
		List<SysDict> list = (List<SysDict>) CacheUtils.get("sysdict_label_" + type);
		if(list == null) {
			SysDictExample sde = new SysDictExample();
			sde.or().andDictTypeEqualTo(type);
			list = sysDictMapper.selectByExample(sde);
			CacheUtils.put("sysdict_label_" + type, list);
		}
		return (list == null || list.isEmpty()) ? null : list.get(0).getId();
	}

	@Transactional(readOnly = true)
	@Override
	public SysDict findDictById(String id) {
		SysDict dict = (SysDict) CacheUtils.get("sysdict_" + id);
		if(dict == null) {
			dict = sysDictMapper.selectByPrimaryKey(id);
			CacheUtils.put("sysdict_" + id, dict);
		}
		return dict;
	}
}
