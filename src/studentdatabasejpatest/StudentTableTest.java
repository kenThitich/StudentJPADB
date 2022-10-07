/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentdatabasejpatest;

import studentdatabasejpatest.Student;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author TUFGAMING
 */
public class StudentTableTest {
    public static void insertStudent(Student stu){
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentDatabaseJPATestPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(stu);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public static void updateStudent(Student stu) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentDatabaseJPATestPU");
        EntityManager st = emf.createEntityManager();
        Student fromDb = st.find(Student.class, stu.getId());//method find search col by primary key
        fromDb.setName(stu.getName());
        fromDb.setGpa(stu.getGpa());
        st.getTransaction().begin();
        try {
            st.persist(fromDb);
            st.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            st.getTransaction().rollback();
        } finally {
            st.close();
        }
    }
    
     public static Student findStudentById(Integer id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentDatabaseJPATestPU");
        EntityManager st = emf.createEntityManager();
        Student stu = st.find(Student.class, id);
        st.close();
        return stu;
    }
    public static List<Student> findAllStudent() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentDatabaseJPATestPU");
        EntityManager st = emf.createEntityManager();
        List<Student> stuList = (List<Student>) st.createNamedQuery("Student.findAll").getResultList();
        st.close();
        return stuList;
    }
    public static List<Student> findStudentByName(String name) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentDatabaseJPATestPU");
        EntityManager st = emf.createEntityManager();
        Query query = st.createNamedQuery("Student.findByName");
        query.setParameter("name", name);
        List<Student> stuList = (List<Student>) query.getResultList();
        st.close();
        return stuList;
    }
    public static void removeStudent(Student stu) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentDatabaseJPATestPU");
        EntityManager st = emf.createEntityManager();
        Student fromDb = st.find(Student.class, stu.getId());
        st.getTransaction().begin();
        try {
            st.remove(fromDb);
            st.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            st.getTransaction().rollback();
        } finally {
            st.close();
        }
    }
}
