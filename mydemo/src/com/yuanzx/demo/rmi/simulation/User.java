package com.yuanzx.demo.rmi.simulation;

import java.io.Serializable;

/**
 * Created by yuanzx on 2019/3/25.
 */
public class User implements Serializable {

    private static final long serialVersionUID = -4828347140176952682L;

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
