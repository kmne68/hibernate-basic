package com.kmne68.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kmne68.hibernate.demo.entity.Student;

public class DeleteStudentDemo {
	

	public static void main(String[] args) {

		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();		
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			int studentId = 1;
		
			// get a new session and start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve a student based on the id
			System.out.println("\nGetting student with id: " + studentId);
			Student student = session.get(Student.class, studentId);
			
			// delete a student METHOD 1
			//System.out.println("Deleting student: " + student);
			//session.delete(student);
			
			// delete a student METHOD 2
			System.out.println("Deleting student with id = 2");
			session.createQuery("DELETE FROM Student where id = 2").executeUpdate();
			
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
