package com.dolphweb.flightapp.demo;

import java.util.Scanner;

public class UserInterface {

	private Scanner scan;
	private FlightDAO bazaLetova;
	
	public UserInterface(Scanner scan, FlightDAO baza) {
		this.scan=scan;
		this.bazaLetova=baza;
	}
	
	public void start() {
		System.out.println("Dobrodosli u FlightApp!");
		System.out.println();
		
		while(true) {
			System.out.println("Opcije:");
			System.out.println("1.Dodaj let\n2.Izmeni let\n3.Dodaj putnika\n4.Obrisi putnika\n5.Pretraga\n6.Izlaz");
			
			String choice=scan.nextLine();
			
			pickupUserChoice(choice);
		}
		
		
	}

	private void pickupUserChoice(String choice) {
		switch(choice) {
		case "1":
			bazaLetova.addFlight();
			break;
		case "2":
			bazaLetova.changeFlight();
			break;
		case "3":
			bazaLetova.addPassenger();
			break;
		case "4":
			bazaLetova.removePassenger();
			break;
		case "5":
			bazaLetova.searchFligts();
			break;
		case "6":
			break;
		}
		
	}
}
