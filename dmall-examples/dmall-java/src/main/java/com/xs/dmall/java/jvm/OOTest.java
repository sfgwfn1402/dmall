package com.xs.dmall.java.jvm;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.xs.dmall.java.obj.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OOTest {

    /**
     * 无引的的对象，将年轻代满时会被回收
     * @param args
     */
    public static void main(String[] args){
        List<Object> list = new ArrayList<>();
        int i =0;
        int j=0;
        while (true){
            list.add(new User(i++, UUID.randomUUID().toString()));
            new User(j--, UUID.randomUUID().toString());
        }
    }
}
