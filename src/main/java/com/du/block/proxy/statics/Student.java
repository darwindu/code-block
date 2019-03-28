package com.du.block.proxy.statics;

/**
 * @author darwindu
 * @date 2019/3/26
 **/
public class Student implements IPerson {

    private String name;
    public Student(String name) {
        this.name = name;
    }

    @Override
    public void say() {
        System.out.println(name + ": hello world");
    }
}
