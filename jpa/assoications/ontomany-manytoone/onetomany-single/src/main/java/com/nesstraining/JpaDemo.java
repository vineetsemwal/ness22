package com.nesstraining;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class JpaDemo {

    private EntityManager em;

    public static void main(String[] args) {
        JpaDemo demo = new JpaDemo();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empms");
        demo.em = emf.createEntityManager();


        Department department=demo.createDepartment("dev");
        int deptId=department.getId();
        Employee employee1 = demo.createEmployee("nitin", 26);
        Employee employee2=demo.createEmployee("akshay",27);
        System.out.println("***employee details after commit");
        demo.displayEmployee(employee1);

        demo.joinDepartment(employee1,department);
        demo.joinDepartment(employee2,department);
        System.out.println("***department fetched employees are also fetched");
        Department foundDept=demo.findDepartmentById(deptId);
        System.out.println("***fetched dept "+foundDept.getId()+"-"+foundDept.getName());
        Set<Employee>deptEmployees=foundDept.getEmployees();
        deptEmployees.stream().forEach(employee ->demo.displayEmployee(employee));
        demo.em.close();
        emf.close();
    }


    void joinDepartment(Employee employee,Department department){
        EntityTransaction transaction=em.getTransaction();
        transaction.begin();
        Set<Employee> employees =department.getEmployees();
       if (employees==null){
           employees=new HashSet<>();
           department.setEmployees(employees);
       }
       employees.add(employee);
       em.merge(department);
       transaction.commit();
    }

    Department createDepartment(String name){
        EntityTransaction transaction=em.getTransaction();
        transaction.begin();
        Department department=new Department("dev");
        em.persist(department);
        transaction.commit();
        return  department;
    }


    Department findDepartmentById(int deptId){
       Department department= em.find(Department.class,deptId);
      return  department;
    }

    Employee findEmployeeById(long empId) {
        Employee emp = em.find(Employee.class, empId);
        return emp;
    }

    Employee createEmployee(String name, int age) {
        Employee employee = new Employee(name, age);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(employee);//will add in persistence context
        transaction.commit();
        return employee;
    }

    void displayEmployee(Employee employee) {
        System.out.println("employee=" + employee.getId() + " " + employee.getName() + " " + employee.getAge());
    }

}
