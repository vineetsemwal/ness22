package com.nesstraining;


import javax.persistence.*;

public class JpaDemo {
    public static void main(String[] args) {
        JpaDemo demo=new JpaDemo();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("customerms");
        EntityManager em = emf.createEntityManager();
        Address address=new Address("bangaluru", "whitefield 123-abc");

        Employee employee = new Employee("nitin", 26,address);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(employee);//will add in persistence context
        System.out.println("before commit, object in PC details=");
        demo.displayEmployee(employee);

        long id=employee.getId();

        transaction.commit();

        Employee employeeFound=em.find(Employee.class, id);
        System.out.println("employee details after fetching from db");
        demo.displayEmployee(employeeFound);

        Address empAddress=employeeFound.getAddress();
        demo.displayAddress(empAddress);


    }

    void displayAddress(Address address){
        System.out.println(address.getId() +" -"+address.getCity()+"-"+address.getHouseAdd());
    }

    void displayEmployee(Employee customer){
        System.out.println("employee="+customer.getId()+" "+customer.getName()+" "+customer.getAge());
    }

}
