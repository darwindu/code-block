package com.du.block.proxy.statics;

/**
 * @author darwindu
 * @date 2019/3/26
 **/
public class StudentsProxy implements IPerson {

    //被代理的学生
    Student student;

    public StudentsProxy(IPerson person) {
        // 只代理学生对象
        if(person.getClass() == Student.class) {
            this.student = (Student)person;
        }
    }

    @Override
    public void say() {
        System.out.println("today is holiday");
        student.say();
    }
}
