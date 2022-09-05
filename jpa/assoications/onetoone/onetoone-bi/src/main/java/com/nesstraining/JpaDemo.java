package com.nesstraining;


import javax.persistence.*;

public class JpaDemo {

    private EntityManager em;

    public static void main(String[] args) {
        JpaDemo demo = new JpaDemo();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empms");
        demo.em = emf.createEntityManager();

        Employee employee = demo.createEmployee("nitin", 26, "bangaluru", "whitefield 123-abc");
        System.out.println("***employee details after commit");
        demo.displayEmployee(employee);
        long empId=employee.getId();
        long addressId=employee.getAddress().getId();


        System.out.println("***querying on employee and fetching address also with the employee");
        Employee employeeFound = demo.findEmployeeById(empId);
        System.out.println("employee details after fetching from db");
        demo.displayEmployee(employeeFound);

        Address empAddress = employeeFound.getAddress();
        demo.displayAddress(empAddress);
        // bi directional
        System.out.println("***querying on addres and also fetching employee which has the address");
        Address foundAddress = demo.findAddressById(addressId);
        Employee empAssociatedWithAddress = foundAddress.getEmployee();
        demo.displayAddress(foundAddress);
        demo.displayEmployee(empAssociatedWithAddress);


        demo.em.close();
        emf.close();
    }

    Address findAddressById(long addressId) {
        Address address = em.find(Address.class, addressId);
        return address;
    }

    Employee findEmployeeById(long empId) {
        Employee emp = em.find(Employee.class, empId);
        return emp;
    }

    Employee createEmployee(String name, int age, String city, String houseAdd) {
        Address address = new Address(city, houseAdd);
        Employee employee = new Employee(name, age, address);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(employee);//will add in persistence context
        transaction.commit();
        address.setEmployee(employee);
        return employee;
    }

    void displayAddress(Address address) {
        System.out.println("address=" + address.getId() + " -" + address.getCity() + "-" + address.getHouseAdd());
    }

    void displayEmployee(Employee employee) {
        System.out.println("employee=" + employee.getId() + " " + employee.getName() + " " + employee.getAge());
    }

}
