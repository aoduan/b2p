package com.cdut.b2p.modules.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdut.b2p.common.utils.IdUtils;
import com.cdut.b2p.common.utils.StringUtils;
import com.cdut.b2p.modules.shop.po.ShopUser;
import com.cdut.b2p.modules.sys.mapper.SysDictMapper;
import com.cdut.b2p.modules.sys.po.SysDict;
import com.cdut.b2p.modules.sys.po.SysDictExample;
import com.cdut.b2p.modules.sys.po.SysUser;
import com.cdut.b2p.modules.sys.service.SysDictService;
import com.cdut.b2p.modules.sys.utils.SysUserUtils;

@Service
@Transactional
public class SysDictServiceImpl implements SysDictService{

	@Autowired
	private SysDictMapper sysDictMapper;
	
	private void preInsert(SysDict sysDict) {
		if (sysDict.getId() == null || StringUtils.isBlank(sysDict.getId())) {
			sysDict.setId(IdUtils.uuid());
		}
		SysUser user = SysUserUtils.getUser();
		if(user == null) {
			user = new SysUser();
		}
		if (StringUtils.isNotBlank(user.getId())) {
			sysDict.setUpdateBy(user.getId());
			sysDict.setCreateBy(user.getId());
		}
		sysDict.setUpdateDate(new Date());
		sysDict.setCreateDate(sysDict.getUpdateDate());
	}
	
	@Transactional(readOnly = false)
	@Override
	public void save(SysDict sysDict) {
		
		preInsert(sysDict);
		sysDictMapper.insertSelective(sysDict);
	}

	@Transactional(readOnly = true)
	@Override
	public SysDict findByLabel(String label,String type) {
		SysDictExample sde = new SysDictExample();
		sde.or().andDictLabelEqualTo(label).andDictTypeEqualTo(type);
		return sysDictMapper.selectByExample(sde).get(0);
	}
	
	@Transactional(readOnly = true)
	@Override
	public String findIdByType(String type) {
		SysDictExample sde = new SysDictExample();
		sde.or().andDictTypeEqualTo(type);
		return sysDictMapper.selectByExample(sde).get(0).getId();
	}

}