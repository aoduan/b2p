package com.cdut.b2p.modules.sys.service;

import java.util.List;

import com.cdut.b2p.modules.sys.po.SysDict;
/**
 * @title  SysDictService
 * @desc   SysDictService是用于实现操作数据字典的服务接口
 * @author zsb
 * @Date   2018/8/14
 *
 */

public interface SysDictService {
	public void save(SysDict sysDict);
	
	public SysDict findDictByLabel(String label,String type);
	
	public String findIdByType(String type);
	
	public SysDict findDictById(String type);
	
	public List<SysDict> findAllDictLabelByDictType(String goods_type);
	
	public List<SysDict> findAllDict();

}
