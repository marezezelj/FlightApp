package com.dolphweb.flightapp.demo;

import java.util.Scanner;

public class DemoApp {

	public static void main(String[] args) {
		
		Scanner scan=new Scanner(System.in);
		FlightDAO baza=new FlightDAO();
		UserInterface ui=new UserInterface(scan,baza);
		
		ui.start();

	}

}
