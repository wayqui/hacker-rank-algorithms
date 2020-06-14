package com.wayqui.hackerrank.arrays.moveelements.bean;

import java.util.Objects;

public class MyObject {

    private String name;
    private String email;

    public MyObject(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyObject myObject = (MyObject) o;
        return Objects.equals(name, myObject.name) &&
                Objects.equals(email, myObject.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }

    @Override
    public String toString() {
        return "MyObject{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
