package com.du.block.proxy.statics;

import org.junit.Test;

/**
 * 静态代理
 * @author darwindu
 * @date 2019/3/26
 **/
public class StudentsProxyTest {

    @Test
    public void say() {

        //被代理的学生张三，他的的发言由代理对象monitor（班长）完成
        IPerson zhangsan = new Student("张三");

        //生成代理对象，并将张三传给代理对象
        IPerson monitor = new StudentsProxy(zhangsan);

        //班长代理其发言
        monitor.say();
    }
}
