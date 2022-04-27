package com.is_web.entities;

public class Employee implements Entity {

    Integer id = null;
    String surname;
    String name;
    String city;
    String street;
    String postal;
    String email;
    String phone;
    Integer localityId;
    boolean is_deleted;

    public Employee(String surname, String name, String city, String street, String postal, String email, String phone, Integer localityId, boolean is_deleted) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.city = city;
        this.street = street;
        this.postal = postal;
        this.email = email;
        this.phone = phone;
        this.localityId = localityId;
        this.is_deleted = is_deleted;
    }

    public Employee() {
    }

    public Employee(String surname, String name, String email, String phone, String city, String street, String postal, Integer localityId) {
        this.id = null;
        this.surname = surname;
        this.name = name;
        this.city = city;
        this.street = street;
        this.postal = postal;
        this.email = email;
        this.phone = phone;
        this.localityId = localityId;
        this.is_deleted = false;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public boolean getIsDeleted() {return is_deleted;};

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostal() {
        return postal;
    }

    public void setEmail(String email){this.email = email;}
    public String getEmail(){return email;}

    public void setPhone(String phone){this.phone = phone;}
    public String getPhone(){return phone;}

    public void setIs_deleted(Boolean is_deleted){this.is_deleted = is_deleted;}

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public Integer getLocalityId() {
        return localityId;
    }

    public void setLocalityId(Integer localityId) {
        this.localityId = localityId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", postal='" + postal + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", localityId=" + localityId + '\'' +
                ", is_deleted=" + is_deleted + '\'' +
                '}';
    }
}
