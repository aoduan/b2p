package com.cdut.b2p.common.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.cdut.b2p.modules.sys.po.SysArea;

public class AreaUtils {

	public static class TreeNode {
		
	    private String id;
	
		private String areaParentId;

	    private String areaName;

	    private BigDecimal areaSort;

	    private String areaCode;

	    private String areaType;
	    
	    private List<TreeNode> children;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public List<TreeNode> getChildren() {
			return children;
		}

		public void setChildren(List<TreeNode> children) {
			this.children = children;
		}

		public String getAreaParentId() {
			return areaParentId;
		}

		public void setAreaParentId(String areaParentId) {
			this.areaParentId = areaParentId;
		}

		public String getAreaName() {
			return areaName;
		}

		public void setAreaName(String areaName) {
			this.areaName = areaName;
		}

		public BigDecimal getAreaSort() {
			return areaSort;
		}

		public void setAreaSort(BigDecimal areaSort) {
			this.areaSort = areaSort;
		}

		public String getAreaCode() {
			return areaCode;
		}

		public void setAreaCode(String areaCode) {
			this.areaCode = areaCode;
		}

		public String getAreaType() {
			return areaType;
		}

		public void setAreaType(String areaType) {
			this.areaType = areaType;
		}

		@Override
		public String toString() {
			return "TreeNode [id=" + id + ", children=" + children + ", areaParentId=" + areaParentId + ", areaName="
					+ areaName + ", areaSort=" + areaSort + ", areaCode=" + areaCode + ", areaType=" + areaType + "]";
		}
	    
	    


	}

	public static List<TreeNode> bulid(List<TreeNode> treeNodes,String topid) {
		 
        List<TreeNode> trees = new ArrayList<TreeNode>();
 
        for (TreeNode treeNode : treeNodes) {
 
            if (topid.equals(treeNode.getAreaParentId())) {
                trees.add(treeNode);
            }
 
            for (TreeNode it : treeNodes) {
                if (it.getAreaParentId().equals(treeNode.getId())) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<TreeNode>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return trees;
	}
	
	public static List<TreeNode> parseArea(List<SysArea> list,String topid) {
		List<TreeNode> nodelist = new ArrayList<TreeNode>();
		for(SysArea area : list) {
			TreeNode node = new TreeNode();
			node.setId(area.getId());
			node.setAreaCode(area.getAreaCode());
			node.setAreaName(area.getAreaName());
			node.setAreaParentId(area.getAreaParentId());
			node.setAreaSort(area.getAreaSort());
			node.setAreaType(area.getAreaType());
			nodelist.add(node);
		}
		return AreaUtils.bulid(nodelist,topid);
		
	}
	
	

}
