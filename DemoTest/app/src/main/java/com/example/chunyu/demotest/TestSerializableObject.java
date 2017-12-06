package com.example.chunyu.demotest;

import java.io.Serializable;

/**
 * Created by chunyu on 2017/12/6.
 */

public class TestSerializableObject implements Serializable {

    int mInt;
    String name ;

    public TestSerializableObject(int anInt, String name) {
        mInt = anInt;
        this.name = name;
    }
}
