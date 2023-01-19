package com.dolphweb.flightapp.demo;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.dolphweb.flightapp.entity.Flight;
import com.dolphweb.flightapp.entity.Passanger;

public class FlightDAO {

	private SessionFactory factory;
	private Session session;
	private Scanner scan;

	public FlightDAO() {
		this.factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Flight.class).addAnnotatedClass(Passanger.class)
				.buildSessionFactory();
		this.scan = new Scanner(System.in);
	}

	public void addFlight() {
		System.out.println("Unesite registarski kod leta: ");
		int code = Integer.valueOf(scan.nextLine());

		System.out.println("Unesite avio-kompaniju:");
		String company = scan.nextLine();

		System.out.println("Unesite polazni aerodrom");
		String originCity = scan.nextLine();

		System.out.println("Unesite dolazni aerodrom");
		String destinationCity = scan.nextLine();

		System.out.println("Unesite kapacitet leta");
		int capacity = Integer.valueOf(scan.nextLine());

		Flight f = new Flight(code, company, originCity, destinationCity, capacity);

		this.session = factory.getCurrentSession();

		this.session.beginTransaction();

		this.session.save(f);

		this.session.getTransaction().commit();
	}

	public void changeFlight() {
		System.out.println("Unesite registarski kod leta: ");
		int code = Integer.valueOf(scan.nextLine());
		FlightUpdateDAO flightUpdate=new FlightUpdateDAO(code);

		System.out.println("Opcije:");
		System.out.println("1.Izmeni kompaniju\n2.Izmeni polazni grad\n3.Izmeni odrediste\n4.Izmeni kapacitet");

		String choice = scan.nextLine();
		pickupChoice(choice, flightUpdate);

	}

	private void pickupChoice(String choice, FlightUpdateDAO flightUpdate) {
		
		switch(choice) {
		case "1":
			System.out.println("Unesite novu kompaniju: ");
			String company=scan.nextLine();
			flightUpdate.changeCompany(company);
			break;
		case "2":
			System.out.println("Unesite novu pocetnu tacku: ");
			String city=scan.nextLine();
			flightUpdate.changeStart(city);
			break;
		case "3":
			System.out.println("Unesite novu krajnju tacku: ");
			String destination=scan.nextLine();
			flightUpdate.changeDestination(destination);
			break;
		case "4":
			System.out.println("Unesite novi kapacitet: ");
			int cap=Integer.valueOf(scan.nextLine());
			flightUpdate.changeCapacity(cap);
			break;
		}
	}

	public void addPassenger() {
		Flight f = this.codeSearch();
		if (f == null) {
			return;
		}

		System.out.println("Unesite broj putnika koje zelite da dodate: ");
		int passNumber = Integer.valueOf(scan.nextLine());

		if (f.getPassengerNumber() + passNumber > f.getCapacity()) {
			System.out.println("Nema mesta u avionu!");
		} else {
			PassangerDAO passangerDB = new PassangerDAO();
			f.setPassengerNumber(f.getPassengerNumber() + passNumber);
			passangerDB.addPassangers(passNumber, f);
			
		}

		session.getTransaction().commit();

	}

	public void removePassenger() {
		Flight f = this.codeSearch();
		System.out.println(f.getPassangers());
		if (f == null) {
			System.out.println("Neispravan kod leta!");
			return;
		}

		System.out.println("Unesite broj putnika koje zelite da obrisete: ");
		int passNumber = Integer.valueOf(scan.nextLine());
		
		PassangerDAO passangerDB = new PassangerDAO();
		passangerDB.removePassangers(passNumber, f);
		
		session.getTransaction().commit();

	}

	public void searchFligts() {
		System.out.println("Izbor pretrage: ");
		System.out.println(
				"1.Pretraga po kodu leta\n2.Pretraga po kompaniji\n3.Pretraga po polaznom aerodromu\n4.Pretraga po dolaznom aerodromu\n5.Ispis svih letova\n6.Ispis svih putnika na letu");

		String choice = scan.nextLine();

		searchChoice(choice);

	}

	private void searchChoice(String choice) {
		switch (choice) {
		case "1":
			Flight f = codeSearch();
			if (f != null) {
				System.out.println("\nRezultat pretrage: ");
				System.out.println(f + "\n");
			}
			this.session.getTransaction().commit();
			break;
		case "2":
			companySearch();
			break;
		case "3":
			startCitySearch();
			break;
		case "4":
			destinationCitySearch();
			break;
		case "5":
			printAllFlights();
			break;
		case "6":
			Flight f1 = codeSearch();
			if(f1 != null) {
				System.out.println(f1.getPassangers());
			}
			this.session.getTransaction().commit();
		}


	}

	private void printAllFlights() {
		this.session=factory.getCurrentSession();
		
		this.session.beginTransaction();
		
		List<Flight> lista = session.createQuery("from Flight").getResultList();
		
		System.out.println("\nRezultati:");
		for(Flight f:lista) {
			System.out.println(f);
		}
		
		this.session.getTransaction().commit();
	}

	private void destinationCitySearch() {
		System.out.println("Unesite destinaciju:");
		String destination=scan.nextLine();
		
		this.session=factory.getCurrentSession();
		
		this.session.beginTransaction();
		
		String hql = "from Flight f where f.destinationCity= :destination";
		List<Flight> lista = session.createQuery(hql).setParameter("destination", destination).getResultList();
		
		System.out.println("\nRezultati:");
		
		for(Flight f:lista) {
			System.out.println(f);
		}
		
		this.session.getTransaction().commit();
	}

	private void startCitySearch() {
		System.out.println("Unesite polazni aerodrom");
		String startCity=scan.nextLine();
		
		this.session=factory.getCurrentSession();
		
		this.session.beginTransaction();
		
		String hql = "from Flight f where f.originCity= :city";
		List<Flight> lista = session.createQuery(hql).setParameter("city", startCity).getResultList();
		
		System.out.println("\nRezultati:");
		
		for(Flight f:lista) {
			System.out.println(f);
		}
		
		this.session.getTransaction().commit();
	}

	private void companySearch() {
		System.out.println("Unesite avio-kompaniju");
		String company=scan.nextLine();
		
		this.session=factory.getCurrentSession();
		
		this.session.beginTransaction();
		
		String hql = "from Flight f where f.company= :company";
		List<Flight> lista = session.createQuery(hql).setParameter("company", company).getResultList();
		
		System.out.println("\nRezultati:");
		
		for(Flight f:lista) {
			System.out.println(f);
		}
		
		this.session.getTransaction().commit();
	}

	private Flight codeSearch() {
		System.out.println("Unesite registarski kod leta: ");
		int code = Integer.valueOf(scan.nextLine());

		this.session = factory.getCurrentSession();

		this.session.beginTransaction();

		List<Flight> lista = session.createQuery("from Flight f where f.code=" + code).getResultList();

		if (lista.isEmpty()) {
			System.out.println("Nema rezultata za dati kod!\n");
			//this.session.getTransaction().commit();
			return null;
		} else {
			Flight f = lista.get(0);
			//this.session.getTransaction().commit();
			return f;
		}

	}

}
