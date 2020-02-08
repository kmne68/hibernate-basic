package com.kmne68.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kmne68.hibernate.demo.entity.Student;

public class ReadStudentDemo {
	

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
			Student student = new Student("Friedrich", "Hayek", "fahayek@example.com");
			
			session.beginTransaction();
			
			System.out.println("Saving the student...");
			System.out.println(student);
			session.save(student);
			
			System.out.println("Committing the transaction");
			session.getTransaction().commit();
			
			System.out.println("Student was saved.");
			
			// find the student's id: primary key
			System.out.println("Saved student. Generated id: " + student.getId());
			
			// get a new session and start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve a student based on the id
			System.out.println("\nGetting student with id: " + student.getId());
			Student myStudent = session.get(Student.class, student.getId());
			
			System.out.println("Get complete: " + myStudent);
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} catch(Exception e) {
			
			System.out.println("Saving student failed because: " + e.getMessage());
			
		} finally {
			
			factory.close();
		}

	}

}
