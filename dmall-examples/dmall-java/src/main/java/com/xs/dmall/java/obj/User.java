package com.xs.dmall.java.obj;

import java.util.*;
import java.util.stream.Stream;

public class User {
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("关闭资源. user" + id + "即将被回收");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {

        String[] dd = { "a", "b", "c" };
        Stream<String> stream = Arrays.stream(dd);
        stream.filter(str -> str.equals("a")).forEach(System.out::println);


        List<User> l = new ArrayList<User>();
        l.add(new User(1,"1"));

        Set<Integer> s = new HashSet<>();

        l.stream().filter(g->s.contains(g.getId()));
    }
}
