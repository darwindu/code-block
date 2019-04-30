package com.du.block.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.du.block.exception.SdkException;
import com.du.block.protocol.base.Menu;
import com.du.block.protocol.base.TreeNode;
import org.springframework.util.StringUtils;

public class TreeUtil {

    /**
     * 对以排序的树list进行map封装，返回如下：
     * map:{
     *   "1-0" : [ 1010, 1110, 1210 ],
     *   "2-1010" : [ 1011, 1012, 1013 ],
     *   "2-1110" : [ 1111, 1112 ],
     *   "2-1210" : [ 1211 ]
     * }
     *
     * 已进行排序的list(id, typeLevel)
     * @param list
     * @return {
     */
    public static Map<String, List<Integer>> getMap(List<Menu> list) {

        if(list == null || list.isEmpty()) {
            throw new SdkException("参数不能为null");
        }
        Map<String, List<Integer>> map = new HashMap<>();
        List<Integer> idList = new ArrayList<>();

        for(int i = 0; i < list.size(); i ++) {

            Integer typeLevel = list.get(i).getTypeLevel();
            Integer parentId = list.get(i).getParentId();
            map.put(typeLevel + "-" + parentId, idList);
            idList.add(list.get(i).getId());
            if(i == list.size() - 1 ) {
                break;
            }
            if(!list.get(i).getParentId().equals(list.get(i + 1).getParentId())) {
                idList = new ArrayList<>();
            }
        }
        return map;
    }

    /**
     * 构建父节点ID为0的树(只有一个根节点)
     * @param list 已进行排序的list
     * @return
     */
    public static TreeNode buildTree(List<Menu> list) {
        return buildTrees(list).get(0);
    }

    /**
     * 构建多个根节点的树，根据根节点0构建
     * @param list 已进行排序的list
     * @return
     */
    public static List<TreeNode> buildTrees(List<Menu> list) {
        return buildTrees(list, "0");
    }


    /**
     * 根据根节点父ID构建树
     * @param list 已进行排序的list
     * @param rootParentId 根节点的父ID
     */
    public static List<TreeNode> buildTrees(List<Menu> list, String rootParentId) {
        return buildTreeByColumn(list, rootParentId, true);
    }

    /**
     * 根据根节点ID构建树
     * @param list 已进行排序的list
     * @param rootId 根节点的ID
     */
    public static TreeNode buildTreeByRootId(List<Menu> list, String rootId) {

        List<TreeNode> nodes = buildTreeByColumn(list, rootId, false);
        if(nodes == null || nodes.isEmpty()){
            return null;
        }
        return nodes.get(0);
    }

    /**
     * 将树装换成list
     * @param treeNode 根节点的ID
     * @param list 参数为(new ArrayList<TreeNode>())
     */
    public static List<TreeNode> treeList(TreeNode treeNode, List<TreeNode> list) {

        if (treeNode.getChildNodes() == null || treeNode.getChildNodes().isEmpty()) {
            return list;
        } else {
            List<TreeNode> childNodes = treeNode.getChildNodes();
            for (TreeNode child : childNodes) {
                list.add(child);
                treeList(child, list);
            }
        }
        return list;
    }

    /**
     * 根据节点ID构建树
     * @param list 已进行排序的list
     * @param nodeId 节点id
     * @param isParent 父nodeId，根据父nodeId生成树
     */
    private static List<TreeNode> buildTreeByColumn(List<Menu> list, String nodeId, Boolean isParent) {

        if (list == null || list.isEmpty()) {
            throw new SdkException("资源列表不能为null");
        }
        if (StringUtils.isEmpty(nodeId)) {
            throw new SdkException("nodeId不能为null");
        }

        //定义一个map,保存PARENT_ID和子节点list键值对
        Map<String, List<TreeNode>> map = new HashMap<>();
        List<TreeNode> roots = new ArrayList<>();
        String parentId;
        for (Menu menu : list) {

            parentId = menu.getParentId().toString();
            String equalNodeId = parentId;
            if (!isParent) {
                equalNodeId = menu.getId().toString();
            }
            if (nodeId.equals(equalNodeId)) {
                roots.add(parseTreeNode(menu));
                continue;
            }
            if (map.get(parentId) == null) {
                map.put(parentId, new ArrayList<>());
            }
            map.get(parentId).add(parseTreeNode(menu));
        }
        for (TreeNode root : roots) {
            setChildNodes(root, map);
        }
        return roots;
    }

    /**
     * 设置子节点
     */
    private static void setChildNodes(TreeNode parentNode, Map<String, List<TreeNode>> map) {
        List<TreeNode> childNodes = map.get(parentNode.getId());
        if (childNodes == null || childNodes.isEmpty()) {
            return;
        } else {
            parentNode.setLeaf(false);
            for (TreeNode childNode : childNodes) {
                childNode.setParentName(parentNode.getName());
                setChildNodes(childNode, map);
            }
            parentNode.setChildNodes(childNodes);
        }

    }

    /**
     * 将PageData解析为树节点对象
     */
    private static TreeNode parseTreeNode(Menu menu) {
        TreeNode node = new TreeNode();
        node.setId(menu.getId().toString());
        node.setName(menu.getMenuName());
        node.setParentId(menu.getParentId().toString());
        node.setLeaf(true);
        node.setChildNodes(new ArrayList<TreeNode>());
        node.setData(menu);
        return node;
    }

}
