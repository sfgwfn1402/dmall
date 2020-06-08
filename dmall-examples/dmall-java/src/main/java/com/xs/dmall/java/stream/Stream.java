package com.xs.dmall.java.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Stream {
    /**
     * 1. forEach 循环
     */
    @Test
    public void forEach() {
        // 你不鸟我,我也不鸟你
        List<String> list = Arrays.asList("you", "don't", "bird", "me", ",",
                "I", "don't", "bird", "you");
        // 方式一：JDK1.8之前的循环方式
        for (String item : list) {
            System.out.println(item);
        }
        // 方式二：使用Stream的forEach方法
        // void forEach(Consumer<? super T> action)
        list.stream().forEach(item -> System.out.println(item));
        // 方式三：方式二的简化方式
        // 由于方法引用也属于函数式接口，所以方式二Lambda表达式也可以使用方法引用来代替
        // 此种方式就是方式一、方式二的简写形式
        list.stream().forEach(System.out::println);
    }

    @Test
    public void filter() {
        List<User> users = Arrays.asList(
                new User(1L, "mengday", 28),
                new User(2L, "guoguo", 18),
                new User(3L, "liangliang", 17)
        );
        // Stream<T> filter(Predicate<? super T> predicate);
        users.stream().filter(user -> user.getAge() > 18).forEach(System.out::println);
    }
}


