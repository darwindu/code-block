package com.du.block.cglib;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.beans.BeanMap;

/**
 * 通过cglib生成POJO
 * @author darwindu
 * @date 2019/3/27
 **/
public class CglibBean {

    /**
     * 实体Object
     */
    public Object object;

    /**
     * 属性map
     */
    public BeanMap beanMap;

    public CglibBean() {
        super();
    }


    public CglibBean(Map propertyMap) {
        this.object = generateBean(propertyMap);
        this.beanMap = BeanMap.create(this.object);
    }

    public CglibBean(Map propertyMap, Class clazz) {
        this.object = generateBean(propertyMap, clazz);
        this.beanMap = BeanMap.create(this.object);
    }

    /**
     * 给bean属性赋值
     *
     * @param property 属性名
     * @param value 值
     */
    public void setValue(String property, Object value) {
        beanMap.put(property, value);
    }

    /**
     * 通过属性名得到属性值
     *
     * @param property 属性名
     * @return 值
     */
    public Object getValue(String property) {
        return beanMap.get(property);
    }

    /**
     * 得到该实体bean对象
     */
    public Object getObject() {
        return this.object;
    }

    private Object generateBean(Map propertyMap) {
        return generateBean(propertyMap, null);
    }

    private Object generateBean(Map propertyMap, Class clazz) {
        BeanGenerator generator = new BeanGenerator();
        Set keySet = propertyMap.keySet();
        for (Iterator i = keySet.iterator(); i.hasNext(); ) {
            String key = (String) i.next();
            generator.addProperty(key, (Class) propertyMap.get(key));
        }
        if (clazz != null) {
            generator.setSuperclass(clazz);
        }
        return generator.create();
    }
}
