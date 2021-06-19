package com.other;

public class A {
    static {
        System.out.println("a static;");
    }
    {
        System.out.println("a {}");
    }
    public A(){
        System.out.println("a constructor");
    }
}
