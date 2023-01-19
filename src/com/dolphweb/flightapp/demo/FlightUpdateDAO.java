package com.dolphweb.flightapp.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.dolphweb.flightapp.entity.Flight;
import com.dolphweb.flightapp.entity.Passanger;

public class FlightUpdateDAO {
	private int code;
	
	public FlightUpdateDAO(int code) {
		this.code=code;
	}

	public void changeCompany(String company) {
		SessionFactory factory=new Configuration()
							.configure("hibernate.cfg.xml")
							.addAnnotatedClass(Flight.class)
							.addAnnotatedClass(Passanger.class)
							.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			String hql = "update Flight f set company= :company where f.code= :code";
			session.createQuery(hql).setParameter("code", this.code).setParameter("company", company).executeUpdate();
			
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}
	}

	public void changeStart(String city) {
		SessionFactory factory=new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Flight.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			
			String hql = "update Flight f set originCity= :city where f.code= :code";
			session.createQuery(hql).setParameter("code", this.code).setParameter("city", city).executeUpdate();
			
			session.getTransaction().commit();
		
		} finally {
			factory.close();
		}
	}

	public void changeDestination(String destination) {
		SessionFactory factory=new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Flight.class)
				.addAnnotatedClass(Passanger.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			
			String hql = "update Flight f set destinationCity= :city where f.code= :code";
			session.createQuery(hql).setParameter("code", this.code).setParameter("city", destination).executeUpdate();
			
			session.getTransaction().commit();
		
		} finally {
			factory.close();
		}
	}

	public void changeCapacity(int cap) {
		SessionFactory factory=new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Flight.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			
			String hql = "update Flight f set capacity= :cap where f.code= :code";
			session.createQuery(hql).setParameter("code", this.code).setParameter("cap", cap).executeUpdate();
			
			session.getTransaction().commit();
		
		} finally {
			factory.close();
		}
	}
			
}
