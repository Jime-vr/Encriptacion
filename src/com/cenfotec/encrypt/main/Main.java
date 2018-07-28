package com.cenfotec.encrypt.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

import com.cenfotec.encrypt.manager.FManager;
import com.cenfotec.encrypt.manager.IManager;

public class Main {

	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	private static PrintStream out = System.out;
	private static IManager IM;

	public static void main(String[] args) throws Exception {

		int type = 0;

		do {

			out.println();
			out.println("---------- ENCRYPTION ----------");
			out.println();
			out.println("What type of encryption do you want?");
			out.println();
			out.println("1. Asymetric");
			out.println("2. Symetric");
			out.println("3. MD5");
			out.println("4. Exit");
			type = Integer.parseInt(in.readLine());

			IM = FManager.encryptType(type);
			menuEncrypt();

		} while (type != 4);

	}

	private static void menuEncrypt() throws Exception {
		int option = 0;

		do {
			out.println();
			out.println("1. Create key");
			out.println("2. Encript Message");
			out.println("3. Decrypt Message");
			out.println("4. Return to main menu ");
			option = Integer.parseInt(in.readLine());
			out.println();

			executeAction(option);

		} while (option != 4);
	}

	private static void executeAction(int option) throws Exception {

		if (option == 1) {
			out.println("Key name: ");
			String name = in.readLine();
			IM.createKey(name);
		}
		if (option == 2) {
			out.println("Key name: ");
			String keyName = in.readLine();
			out.println("Message name: ");
			String messageName = in.readLine();
			out.println("Message: ");
			String message = in.readLine();

			IM.encryptMessage(messageName, message, keyName);
		}
		if (option == 3) {
			out.println("Key name: ");
			String keyName = in.readLine();
			out.println("Message name: ");
			String messageName = in.readLine();

			IM.decryptMessage(messageName, keyName);
		}
	}

}
