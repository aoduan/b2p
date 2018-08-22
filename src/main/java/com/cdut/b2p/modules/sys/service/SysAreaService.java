package com.cdut.b2p.modules.sys.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cdut.b2p.modules.sys.po.SysArea;
/**
 * @title  SysAreaService
 * @desc   SysAreaService是用于实现操作区域表的服务接口
 * @author zsb
 * @Date   2018/8/14
 *
 */

public interface SysAreaService {
	
	public void save(SysArea sysArea);

	public List<SysArea> findAllChildByParentId(String pid);
	
	public List<SysArea> findAllChildrenByParentId(String pid);
	
	public List<SysArea> findAllArea();
	
	public SysArea findAreaById(String id);

}
