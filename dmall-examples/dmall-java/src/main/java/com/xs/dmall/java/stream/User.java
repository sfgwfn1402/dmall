package com.xs.dmall.java.stream;


public class User {
    private String username;
    private Long id;
    private String phone;
    private Integer age;

    public User() {
    }

    public User(Long id, String username, Integer age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }
    // Getter & Setter & toString


 public String getUsername() {
  return username;
 }

 public void setUsername(String username) {
  this.username = username;
 }

 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public String getPhone() {
  return phone;
 }

 public void setPhone(String phone) {
  this.phone = phone;
 }

 public Integer getAge() {
  return age;
 }

 public void setAge(Integer age) {
  this.age = age;
 }
}
