package com.xs.dmall.java.copy;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertNotSame;

public class CopyDemo implements Serializable {
    /**
     * 地址
     */
    class Address implements Serializable {

        private String city;
        private String country;

        public void setCity(String city) {
            this.city = city;
        }

        public String getCity() {
            return city;
        }

        // constructors, getters and setters

        public Address(String city, String country) {
            this.city = city;
            this.country = country;
        }
    }


    /**
     * 用户
     */
    class User implements Serializable {

        private String name;
        private Address address;

        // constructors, getters and setters

        public User(String name, Address address) {
            this.name = name;
            this.address = address;
        }

        public Address getAddress() {
            return address;
        }
    }

    @Test
    public void serializableCopy() {

        Address address = new Address("杭州", "中国");
        User user = new User("大山", address);

        // 使用Apache Commons Lang序列化进行深拷贝
        User copyUser = (User) SerializationUtils.clone(user);

        // 修改源对象的值
        user.getAddress().setCity("深圳");

        // 检查两个对象的值不同
        assertNotSame(user.getAddress().getCity(), copyUser.getAddress().getCity());

    }
}
