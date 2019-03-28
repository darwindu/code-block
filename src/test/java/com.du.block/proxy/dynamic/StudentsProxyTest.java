package com.du.block.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.junit.Test;

/**
 * 动态代理测试
 * @author darwindu
 * @date 2019/3/26
 **/
public class StudentsProxyTest {

    @Test
    public void say() {

        //创建一个实例对象，这个对象是被代理的对象
        IPerson zhangsan = new Student("张三");

        //创建一个与代理对象相关联的InvocationHandler
        InvocationHandler stuHandler = new StudentsInvocationHandler<IPerson>(zhangsan);

        //创建一个代理对象stuProxy来代理zhangsan，代理对象的每个执行方法都会替换执行Invocation中的invoke方法
        IPerson stuProxy = (IPerson) Proxy.newProxyInstance(IPerson.class.getClassLoader(), new Class<?>[]{IPerson.class}, stuHandler);

        //代理执行上交班费的方法
        stuProxy.say();
    }
}
