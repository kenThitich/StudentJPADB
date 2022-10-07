/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentdatabasejpatest;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author TUFGAMING
 */
public class StudentDatabaseJPATest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Student stu1 = new Student(3,"James",3.00);
        
       Student stu;
       stu = StudentTableTest.findStudentById(1);
       if (stu != null) {
           stu.setName("Jack");
           //EmployeeTable.removeEmployee(emp);
           StudentTableTest.updateStudent(stu);
       }
       
       List<Student> stuList = StudentTableTest.findAllStudent();
       printAllStu(stuList);
    }

    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentDatabaseJPATestPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
     public static void printAllStu(List<Student> stuList) {
        for(Student stu : stuList) {
           System.out.print(stu.getId() + " ");
           System.out.print(stu.getName() + " ");
           System.out.println(stu.getGpa() + " ");
       }
    }
    
}
