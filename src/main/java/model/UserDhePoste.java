package model;

import java.sql.Date;

public class UserDhePoste {
    private int id;
    private String name;
    private String email;
    private String country;
    private String password;
    private String address;
    private int postime_id;
    private String description;
    private Date date;
    private String surname;


    public UserDhePoste(String description, Date date) {

        super();
        this.description = description;
        this.date = date;
    }


    public UserDhePoste(int id, int postime_id, String name, String email, String country, String description, String address, Date date,String surname) {
        this.id = id;
        this.postime_id = postime_id;
        this.name = name;
        this.email = email;
        this.country = country;
        this.description = description;
        this.address = address;
        this.date = date;
        this.surname=surname;
    }

    public UserDhePoste(int id, String description) {
        this.id=id;
        this.description=description;
    }

    public UserDhePoste(String description) {
        this.description=description;
    }

    public UserDhePoste(String description, int postime_id) {
        this.description=description;
        this.postime_id=postime_id;
    }

    public UserDhePoste(int postime_id) {
        this.postime_id=postime_id;
    }


    public String getDescription() {

        return description;
    }

    public Date getDate() {

        return date;
    }

    public void setDate(Date date) {

        this.date = date;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public int getPostime_id() {

        return postime_id;
    }

    public void setPostime_id(int postime_id) {

        this.postime_id = postime_id;
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
