package com.sli.entity;
//
//import lombok.Data;
//
//import java.io.Serializable;
//
//@Data
//public class MyTest implements Serializable {
//    private Long id;
//    private String name;
//    private int age;
//    private Boolean inhere;
//
//    public MyTest() {
//    }
//
//    public MyTest(String name, int age, Boolean inhere) {
//        this.name = name;
//        this.age = age;
//        this.inhere = inhere;
//    }
//
//    public MyTest(Long id, String name, int age, Boolean inhere) {
//        this.id = id;
//        this.name = name;
//        this.age = age;
//        this.inhere = inhere;
//    }
//
//    @Override
//    public String toString() {
//        final StringBuffer sb = new StringBuffer("MyTest{");
//        sb.append("id=").append(id);
//        sb.append(", name='").append(name).append('\'');
//        sb.append(", age=").append(age);
//        sb.append(", inhere=").append(inhere);
//        sb.append('}');
//        return sb.toString();
//    }
//}


//import lombok.Data;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
////JPA
//@Data
//@Entity
//@Table(name = "test")
public class MyTest{
    private int id;

    private String name;

    private String sex;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public MyTest(int id, String name, String sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }

    public MyTest(int id) {
        this.id = id;
    }
}