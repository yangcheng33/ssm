package com.github.vita.ssm.web.controller;

/**
 * a
 *
 * @author Yang Cheng
 * @date 2016-09-07
 */
public class A{
    private B b;

    public void setB(B b) {
        this.b = b;
    }

    public B getB() {
        return b;
    }
    public void out(){
        System.out.println(ClassLoader.getSystemClassLoader());
    }
}