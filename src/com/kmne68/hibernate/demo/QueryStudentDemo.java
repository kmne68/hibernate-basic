package com.kmne68.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kmne68.hibernate.demo.entity.Student;

public class QueryStudentDemo {
	

	public static void main(String[] args) {

		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();		
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();

			// query students
			List<Student> students = session.createQuery("from Student").getResultList();
			
			// display the students
			displayStudents(students);
			
			// query students: lastName = 'Doe'
			students = session.createQuery("from Student s where s.lastName = 'Doe'").getResultList();
			
			// display students with last name of Doe
			displayStudents(students);
			
			// query students last name Doe or first name Friedrich
			students = session.createQuery("from Student s where s.lastName = 'Doe' OR s.firstName='Friedrich'").getResultList();
			
			// display students with last name Doe or first name Friedrich
			displayStudents(students);
			
			// query on email domain LIKE '%example.com'
			students = session.createQuery("from Student s where s.email LIKE '%example.com'").getResultList();
			
			// display students with example.com email address
			displayStudents(students);
			
			
			System.out.println("Committing the transaction");
			session.getTransaction().commit();
			
			System.out.println("Done.");
			
			
			
		} catch(Exception e) {
			
			System.out.println("Saving student failed because: " + e.getMessage());
			
		} finally {
			
			factory.close();
		}

	}

	private static void displayStudents(List<Student> students) {
		for(Student s : students) {
			System.out.println(s);
		}
	}

}
