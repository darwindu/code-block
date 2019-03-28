package com.du.block.proxy.dynamic;

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
        try {
            //假设紧张花了一秒时间
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + ": hello");
    }
}
