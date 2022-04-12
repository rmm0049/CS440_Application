package main;

import static helpers.Configuration.JDBC_URL;

import java.sql.Connection;
import java.sql.DriverManager;

import helpers.Wizard;

// username -> TEAM_ALPHA
// password -> 875283

public class Main {

	public static void main(String[] args) {
		// Just simple connection to the Oracle DBMS
		// with team account
		System.out.println("Connecting...");
		try (
			Wizard wiz = new Wizard();
			Connection con = DriverManager.getConnection(
				JDBC_URL,
				wiz.getUsername(),
				wiz.getPassword()
			);
		) {
			System.out.println("Connected successfully.\n");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
