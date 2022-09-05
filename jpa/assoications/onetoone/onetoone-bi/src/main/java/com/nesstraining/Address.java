package com.nesstraining;


import javax.persistence.*;
import java.util.Objects;

@Table(name="address_data")
@Entity
public class Address {
    @GeneratedValue
    @Id
    private Long id;
    private String city;
    private  String houseAdd;

    @OneToOne(mappedBy = "address")
    private Employee employee;

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

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
