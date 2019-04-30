package com.du.block.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.du.block.protocol.base.Menu;
import com.du.block.protocol.base.TreeNode;
import com.du.block.tree.MenuServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author darwindu
 * @date 2019/4/1
 **/
@Slf4j
public class TreeUtilTest {

    @Test
    public void getMap() {
        List<Menu> list = buildListMenu();
        ListUtil.sort(list, true, "id");
        ListUtil.sort(list, true, "typeLevel");
        Map<String, List<Integer>> map = TreeUtil.getMap(list);
        System.out.println("map:" + JsonUtil.objToJsonStr(map));
    }

    @Test
    public void testJsonStrToObj1() {

        List<Menu> list = this.buildListMenu();
        List<TreeNode> listTreeNode = TreeUtil.buildTrees(list, "0");
        log.info("listTreeNode: {}", JsonUtil.objToJsonStr(listTreeNode));
        Assert.assertNotNull(listTreeNode);
    }

    @Test
    public void testJsonStrToObj2() {

        List<Menu> list = this.buildListMenu();
        List<TreeNode> listTreeNode = TreeUtil.buildTrees(list);
        log.info("listTreeNode: {}", JsonUtil.objToJsonStr(listTreeNode));
        Assert.assertNotNull(listTreeNode);
    }

    @Test
    public void testJsonStrToObj3() {

        List<Menu> list = this.buildListMenu();
        TreeNode treeNode = TreeUtil.buildTree(list);
        log.info("treeNode: {}", JsonUtil.objToJsonStr(treeNode));
        Assert.assertNotNull(treeNode);
    }

    @Test
    public void testJsonStrToObj4() {

        List<Menu> list = this.buildListMenu();
        TreeNode treeNode = TreeUtil.buildTreeByRootId(list, "0");
        log.info("treeNode: {}", JsonUtil.objToJsonStr(treeNode));
        Assert.assertNotNull(treeNode);
    }

    public static List<Menu> buildListMenu() {

        List<Menu> list = new ArrayList<>();
        Menu menu = new Menu();
        menu.setId(1010);
        menu.setMenuName("一级菜单1");
        menu.setParentId(0);
        menu.setSort(1010);
        menu.setTypeLevel(1);
        list.add(menu);

        menu = new Menu();
        menu.setId(1011);
        menu.setMenuName("二级菜单1");
        menu.setParentId(1010);
        menu.setSort(1011);
        menu.setTypeLevel(2);
        list.add(menu);

        menu = new Menu();
        menu.setId(1012);
        menu.setMenuName("二级菜单2");
        menu.setParentId(1010);
        menu.setSort(1012);
        menu.setTypeLevel(2);
        list.add(menu);

        menu = new Menu();
        menu.setId(1013);
        menu.setMenuName("二级菜单3");
        menu.setParentId(1010);
        menu.setSort(1013);
        menu.setTypeLevel(2);
        list.add(menu);


        menu = new Menu();
        menu.setId(1110);
        menu.setMenuName("一级菜单2");
        menu.setParentId(0);
        menu.setSort(1010);
        menu.setTypeLevel(1);
        list.add(menu);

        menu = new Menu();
        menu.setId(1111);
        menu.setMenuName("二级菜单1");
        menu.setParentId(1110);
        menu.setSort(1111);
        menu.setTypeLevel(2);
        list.add(menu);

        menu = new Menu();
        menu.setId(1112);
        menu.setMenuName("二级菜单2");
        menu.setParentId(1110);
        menu.setSort(1112);
        menu.setTypeLevel(2);
        list.add(menu);

        menu = new Menu();
        menu.setId(1210);
        menu.setMenuName("一级菜单3");
        menu.setParentId(0);
        menu.setSort(1010);
        menu.setTypeLevel(1);
        list.add(menu);

        menu = new Menu();
        menu.setId(1211);
        menu.setMenuName("二级菜单1");
        menu.setParentId(1210);
        menu.setSort(1211);
        menu.setTypeLevel(2);
        list.add(menu);
        return list;
    }

    public static List<Menu> buildListMenu1() {

        List<Menu> list = new ArrayList<>();
        Menu menu = new Menu();
        menu.setId(101010);
        menu.setMenuName("一级菜单1");
        menu.setParentId(0);
        menu.setSort(101010);
        menu.setTypeLevel(1);
        list.add(menu);

        menu = new Menu();
        menu.setId(101110);
        menu.setMenuName("二级菜单1");
        menu.setParentId(101010);
        menu.setSort(101110);
        menu.setTypeLevel(2);
        list.add(menu);

        menu = new Menu();
        menu.setId(101111);
        menu.setMenuName("三级菜单1");
        menu.setParentId(101110);
        menu.setSort(101111);
        menu.setTypeLevel(3);
        list.add(menu);

        menu = new Menu();
        menu.setId(101112);
        menu.setMenuName("三级菜单2");
        menu.setParentId(101110);
        menu.setSort(101112);
        menu.setTypeLevel(3);
        list.add(menu);

        menu = new Menu();
        menu.setId(101210);
        menu.setMenuName("二级菜单2");
        menu.setParentId(101010);
        menu.setSort(101210);
        menu.setTypeLevel(2);
        list.add(menu);

        menu = new Menu();
        menu.setId(101310);
        menu.setMenuName("二级菜单3");
        menu.setParentId(101010);
        menu.setSort(101310);
        menu.setTypeLevel(2);
        list.add(menu);


        menu = new Menu();
        menu.setId(111010);
        menu.setMenuName("一级菜单2");
        menu.setParentId(0);
        menu.setSort(111010);
        menu.setTypeLevel(1);
        list.add(menu);

        menu = new Menu();
        menu.setId(111110);
        menu.setMenuName("二级菜单1");
        menu.setParentId(111010);
        menu.setSort(111110);
        menu.setTypeLevel(2);
        list.add(menu);

        menu = new Menu();
        menu.setId(111210);
        menu.setMenuName("二级菜单2");
        menu.setParentId(111010);
        menu.setSort(111210);
        menu.setTypeLevel(2);
        list.add(menu);

        menu = new Menu();
        menu.setId(121010);
        menu.setMenuName("一级菜单3");
        menu.setParentId(0);
        menu.setSort(121010);
        menu.setTypeLevel(1);
        list.add(menu);

        menu = new Menu();
        menu.setId(121110);
        menu.setMenuName("二级菜单1");
        menu.setParentId(121010);
        menu.setSort(121110);
        menu.setTypeLevel(2);
        list.add(menu);
        return list;
    }
}
