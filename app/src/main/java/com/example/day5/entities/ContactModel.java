package com.example.day5.entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_contact")
public class ContactModel {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "col_name")
    private String name;
    private String email;
    private String address;
    private String phone;
    private String city;
    private String gender;
   @ColumnInfo(name = "bloodgroup")
    private String bloodGroup;

    public ContactModel(String name, String email, String address, String phone, String city, String gender, String bloodGroup) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.city = city;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
    }
    @Ignore
    public ContactModel(long id, String name, String email, String address, String phone, String city, String gender, String bloodGroup) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.city = city;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
    }




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public String getGender() {
        return gender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    @Override
    public String toString() {
        return "ContactModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", gender='" + gender + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                '}';
    }
}
