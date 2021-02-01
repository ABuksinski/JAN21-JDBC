package com.qa.main;

import java.sql.SQLException;
import java.util.Scanner;

public class Runner {

	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {

		// instantiating the class
		DBConnector dbQueries = new DBConnector();

		String action = "";
		action = getAction();
		try {
			do {
				switch (action) {
				case "create":
					System.out.println("Please enter your first name");
					String fname = scan.nextLine();
					System.out.println("Please enter your last name:");
					String lName = scan.nextLine();
					dbQueries.createPerson(fname, lName);
					break;
				case "update":
					System.out.println("Please enter the ID of the record you want to update");
					int id = scan.nextInt();
					scan.nextLine(); // capture enter key
					System.out.println("Please enter a the new firstname");
					String newFname = scan.nextLine();
					System.out.println("Please enter the new lastname");
					String newLname = scan.nextLine();
					dbQueries.updatePerson(id, newFname, newLname);
					break;
				case "read":
					dbQueries.readAllPeople();
					break;
				case "delete":
					System.out.println("Please enter the ID of the record you want to delete");
					int delid = scan.nextInt();
					scan.nextLine(); // capture the enter key
					dbQueries.deletePerson(delid);
					break;
				default:
					System.out.println("No mathing case found!");
				}
				action = getAction();
			} while (!action.equals("exit"));
			System.out.println("BYE FRIENDS");
		} finally {
			scan.close();
			dbQueries.close(); 
		}
	}

	private static String getAction() {
		System.out.println("Please enter the CRUD method you want: ");
		return scan.nextLine();
	}

}
