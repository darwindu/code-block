package com.du.block.cglib;

import java.lang.reflect.Method;
import java.util.HashMap;

import com.du.block.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * cglib生成pojo测试
 * @author darwindu
 * @date 2019/3/27
 **/
@Slf4j
public class CglibBeanTest {

    @Test
    public void cglibBean() throws ClassNotFoundException {

        // 设置类成员属性
        HashMap propertyMap = new HashMap();
        propertyMap.put("id", Class.forName("java.lang.Integer"));
        propertyMap.put("name", Class.forName("java.lang.String"));
        propertyMap.put("address", Class.forName("java.lang.String"));

        // 生成动态 Bean
        // CglibBean bean = new CglibBean(propertyMap);
        // 生成指定Bean
        CglibBean bean = new CglibBean(propertyMap, Cpt.class);

        // 给 Bean 设置值
        bean.setValue("id", new Integer(123));
        bean.setValue("name", "454");
        bean.setValue("address", "789");

        // 从 Bean 中获取值，当然了获得值的类型是 Object
        System.out.println("id:" + bean.getValue("id"));
        System.out.println("name:" + bean.getValue("name"));
        System.out.println("address:" + bean.getValue("address"));

        // 获得bean的实体
        Object object = bean.getObject();
        System.out.println("object:" + JsonUtil.objToJsonStr(object));

        // 通过反射查看所有方法名
        Class clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println("method:" + methods[i].getName());
        }

    }
}