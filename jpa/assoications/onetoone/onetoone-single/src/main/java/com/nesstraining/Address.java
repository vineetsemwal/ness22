package com.nesstraining;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="address_data")
@Entity
public class Address {
    @GeneratedValue
    @Id
    private Long id;
    private String city;
    private  String houseAdd;

    public Address(){
    }

    public Address(String city, String houseAdd){
        this.city=city;
        this.houseAdd=houseAdd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHouseAdd() {
        return houseAdd;
    }

    public void setHouseAdd(String houseAdd) {
        this.houseAdd = houseAdd;
    }
}
