package com.cenfotec.encrypt.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

import com.cenfotec.encrypt.asymetric.AsymetricManager;
import com.cenfotec.encrypt.symetric.SymetricManager;


public class Main {

	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	private static PrintStream out = System.out;
	private static AsymetricManager asymetric = new AsymetricManager();
	private static SymetricManager symetric = new SymetricManager();	

	public static void main(String[] args) throws Exception {

		int option = 0, type = 0;

		do {
			
			out.println("What type of encryption do you want?");
			out.println();
			out.println("1. Asymetric");
			out.println("2. Symetric");
			type = Integer.parseInt(in.readLine());
			
			out.println("1.Create key");
			out.println("2.Encript Message");
			out.println("3.Decrypt Message");
			out.println("4.Exit ");
			option = Integer.parseInt(in.readLine());
			if (option >= 1 && option <= 3) {
				executeAction(option, type);
			}
		} while (option != 4);

	}

	private static void executeAction(int option, int type) throws Exception {

		if (option == 1) {
			out.println("Key name: ");
			String name = in.readLine();
			if (type == 1) {
				asymetric.createKey(name);
			}
			if (type == 2) {
				symetric.createKey(name);
			}
		}
		if (option == 2) {
			out.println("Key name: ");
			String name = in.readLine();
			out.println("Message name: ");
			String messageName = in.readLine();
			out.println("Message: ");
			String message = in.readLine();

			
			if (type == 1) {
				asymetric.encryptMessage(messageName, message, name);
			}
			if (type == 2) {
				symetric.encryptMessage(messageName, message, name);
			}
		}
		if (option == 3) {
			out.println("Key name: ");
			String keyName = in.readLine();
			out.println("Message name: ");
			String messageName = in.readLine();
			
			if (type == 1) {
				asymetric.decryptMessage(messageName, keyName);
			}
			if (type == 2) {
				symetric.decryptMessage(messageName, keyName);
			}
		}
	}

}
