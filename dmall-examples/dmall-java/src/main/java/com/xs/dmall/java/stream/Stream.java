package com.xs.dmall.java.stream;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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

    @Test
    public void listToMap() {
        List<Apple> appleList = new ArrayList<>();//存放apple对象集合

        Apple apple1 = new Apple(1, "苹果1", new BigDecimal("3.25"), 10);
        Apple apple12 = new Apple(1, "苹果2", new BigDecimal("1.35"), 20);
        Apple apple2 = new Apple(2, "香蕉", new BigDecimal("2.89"), 30);
        Apple apple3 = new Apple(3, "荔枝", new BigDecimal("9.99"), 40);

        appleList.add(apple1);
        appleList.add(apple12);
        appleList.add(apple2);
        appleList.add(apple3);
        Map<Integer, Apple> appleMap = appleList.stream().collect(Collectors.toMap(Apple::getId, a -> a, (k1, k2) -> k1));
        System.out.println(appleMap);
    }

    @Test
    public void findFirst(){
        List<Integer> integers = Lists.newArrayList(1,2,3);
        Optional<Integer> o = integers.stream().filter(item -> item.equals(9)).findFirst();
        if (o == null || !o.isPresent()) {
            System.out.println("没有找到值");
            return;
        }
        System.out.println(o.get());
    }


    class Apple {
        private Integer id;
        private String name;
        private BigDecimal money;
        private Integer num;

        public Apple(Integer id, String name, BigDecimal money, Integer num) {
            this.id = id;
            this.name = name;
            this.money = money;
            this.num = num;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getMoney() {
            return money;
        }

        public void setMoney(BigDecimal money) {
            this.money = money;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }
    }

}


