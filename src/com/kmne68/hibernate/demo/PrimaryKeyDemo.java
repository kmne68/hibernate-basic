package com.kmne68.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kmne68.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();		
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {

			System.out.println("Creating 3 new Students...");
			Student student1 = new Student("John", "Doe", "jdoe@example.com");
			Student student2 = new Student("Mary", "Public", "mpublic@example.com");
			Student student3 = new Student("Bonita", "Applebaum", "bapple@example.com");
			
			session.beginTransaction();
			
			System.out.println("Saving the students...");
			session.save(student1);
			session.save(student2);
			session.save(student3);
			
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
