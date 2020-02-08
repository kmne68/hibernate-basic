package com.kmne68.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kmne68.hibernate.demo.entity.Student;

public class CreateStudentDemo {
	

	public static void main(String[] args) {

		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();		
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {

			System.out.println("Creating new Student...");
			Student student = new Student("Paul", "Wall", "paul@example.com");
			
			session.beginTransaction();
			
			System.out.println("Saving the student...");
			session.save(student);
			
			System.out.println("Committing the transaction");
			session.getTransaction().commit();
			
			System.out.println("Student was saved.");
			
			
			
		} catch(Exception e) {
			
			System.out.println("Saving student failed because: " + e.getMessage());
			
		} finally {
			
			factory.close();
		}

	}

}
