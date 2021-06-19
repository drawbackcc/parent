package com.other;

public class B extends A{
    static {
        System.out.println("b static");
    }

    {
        System.out.println("b {}");
    }

    public B(){
        System.out.println("b constructor");
    }
}
