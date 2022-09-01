package com.nesstraining;


import javax.persistence.*;

public class JpaDemo {
    public static void main(String[] args) {
        JpaDemo demo=new JpaDemo();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("customerms");
        EntityManager em = emf.createEntityManager();
        Customer customer1 = new Customer("nitin", 26);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(customer1);//will add in persistence context
        System.out.println("before commit, object in PC details=");
        demo.display(customer1);

        long id=customer1.getId();

        transaction.commit();

        Customer found=em.find(Customer.class, id);
        System.out.println("customer details after fetching from db");
        demo.display(found);

    }

    void  display(Customer customer){
        System.out.println("customer="+customer.getId()+" "+customer.getName()+" "+customer.getAge());
    }

}
