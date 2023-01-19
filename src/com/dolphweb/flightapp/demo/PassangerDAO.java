package com.dolphweb.flightapp.demo;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.dolphweb.flightapp.entity.Flight;
import com.dolphweb.flightapp.entity.Passanger;

public class PassangerDAO {
	private Scanner scan;

	public PassangerDAO() {
		this.scan = new Scanner(System.in);
	}

	public void addPassangers(int num, Flight f) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Flight.class)
				.addAnnotatedClass(Passanger.class).buildSessionFactory();
		
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			for (int i = 1; i <= num; i++) {
				

				System.out.println("Unesite ime " + i + ". putnika:");
				String name = scan.nextLine();
				System.out.println("Unesite prezime " + i + ". putnika");
				String lastName = scan.nextLine();
				System.out.println("Unesite email adresu " + i + ". putnika");
				String email = scan.nextLine();

				Passanger tempPass = new Passanger(name, lastName, email);

				f.addPassanger(tempPass);
				tempPass.setFlight(f);

				session.save(tempPass);

			}
			f.setPassengerNumber(f.getPassengerNumber()- num);
			session.getTransaction().commit();
		} finally {
			session.close();
			factory.close();
		}
		
		
		
		
	}

	public void removePassangers(int passNumber, Flight f) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Flight.class)
				.addAnnotatedClass(Passanger.class).buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			for(int i=1; i<=passNumber; i++) {
				System.out.println("Unesite prezime " + i + ". putnika");
				String lastName = scan.nextLine();
				System.out.println("Unesite email adresu " + i + ". putnika");
				String email = scan.nextLine();
				
				String hql = "delete from Passanger where lastName= :last and email= :ema";
				session.createQuery(hql).setParameter("last", lastName).setParameter("ema", email).executeUpdate();			
			}
			session.getTransaction().commit();
		} finally {
			session.close();
			factory.close();
		}
		
	}
}
