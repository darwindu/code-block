package com.du.block.protocol.base;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 树及节点结构
 * @author darwindu
 * @date 2019/3/4
 *
 */
@Data
public class TreeNode {

	private String id;
	private String name;
	private String parentId;
	private String parentName;
	private String leaderId;
	private String leaderName;
	private Integer typeLevel;
	private boolean isLeaf;
	private Menu data;
	private List<TreeNode> childNodes = new ArrayList<TreeNode>();
}

