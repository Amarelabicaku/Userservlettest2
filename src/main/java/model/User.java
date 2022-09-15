package model;

import java.util.Date;

public class User {
    private int id ;
    private String name;
    private String email;
    private String country;
    private String password;
    private String address;
private String surname;
    public User(int id , String name, String email, String country, String password, String address,String surname) {
        super();
        this.id = id;
        this.name=name;
        this.email=email;
        this.country=country;
        this.password=password;
        this.address=address;
        this.surname=surname;
    }

    public User(String name,String email,String country,String password,String address,String surname) {
    super ();
        this.name = name;
        this.email=email;
        this.country=country;
        this.password=password;
        this.address=address;
        this.surname=surname;
    }

    public User(int id, String name, String email, String country, String address,String surname) {
        this.id=id;
        this.name=name;
        this.email=email;
        this.country=country;
        this.address=address;
        this.surname=surname;
    }

    public User(String name, int postime_id, String description, Date date) {
        this.email = email;
        this.password=password;
    }

    public User(int id, int postime_id, String name, String email, String country, String description, String address,Date date,String surname) {
    this.id=id;
    this.name=name;
    this.email=email;
    this.country=country;
    this.address=address;
    this.surname=surname;
    }

    public User(int id) {
        this.id=id;
    }

    public User(int id, String surname) {
        this.id=id;
        this.surname=surname;
    }




    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
