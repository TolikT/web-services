/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tikhoa.soap.standalone;

import java.io.Serializable;

public class Person implements Serializable{

    private String name;
    private String surname;
    private Integer age;
    private String contactDate;
    private Boolean isEmployee;

    public Person() {
    }

    public Person(String name, String surname, int age, boolean isEmployee, String contactDate) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.contactDate = contactDate;
        this.isEmployee = isEmployee;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getAge() {
        return age;
    }

    public Boolean getIsEmployee() {
        return isEmployee;
    }

    public String getContactDate() {
        return contactDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setIsEmployee(Boolean isEmployee) {
        this.isEmployee = isEmployee;
    }

    public void setContactDate(String contactDate) {
        this.contactDate = contactDate;
    }

    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", surname=" + surname + ", age="
                + age + ", isEmployee=" + String.valueOf(isEmployee)
                + ", contactDate=" + contactDate + "}";
    }
}
