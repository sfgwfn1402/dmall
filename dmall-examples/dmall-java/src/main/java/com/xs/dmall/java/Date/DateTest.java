package com.xs.dmall.java.Date;

import com.xs.dmall.java.obj.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class DateTest {

    public static void main(String[] args){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE,-5);
        System.out.println(c.getTime());
    }
}
