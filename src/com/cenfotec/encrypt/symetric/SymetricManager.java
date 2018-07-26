package com.cenfotec.encrypt.symetric;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.cenfotec.manager.FileManager;


public class SymetricManager {

	private final int KEYSIZE = 8;
	private final String KEY_EXTENSION = ".key";
	private final String PATH = "C:/encrypt/symetric/";
	private FileManager fileM = new FileManager();
	
	public void createKey(String name) throws Exception {
		byte [] key = generatedSequenceOfBytes();
		fileM.writeBytesFile(PATH, name,key,KEY_EXTENSION);
	}

	public void encryptMessage(String messageName, String message, String keyName) throws Exception {
		byte[] key = readKeyFile(keyName);
		Cipher cipher = Cipher.getInstance("AES");
		SecretKeySpec k = new SecretKeySpec(key,"AES");
		cipher.init(Cipher.ENCRYPT_MODE, k);
		
		fileM.cipher(PATH, messageName, cipher, message);
	}
	
	public void decryptMessage(String messageName, String keyName) throws Exception {
		byte[] key = readKeyFile(keyName);
		byte[] encryptedMessage = fileM.readMessageFile(PATH, messageName);
		System.out.println(encryptedMessage.length);
		Cipher cipher = Cipher.getInstance("AES");
		SecretKeySpec k = new SecretKeySpec(key,"AES");
		cipher.init(Cipher.DECRYPT_MODE, k);
		byte[] DecryptedData = cipher.doFinal(encryptedMessage);
		String message = new String(DecryptedData, StandardCharsets.UTF_8);
		System.out.println("El mensaje era: ");
		System.out.println(message);
	}
	
	private byte[] readKeyFile(String keyName) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(PATH + keyName + KEY_EXTENSION));
		String everything = "";
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        line = br.readLine();
		    }
		    everything = sb.toString();
		} finally {
		    br.close();
		}
		return everything.getBytes(StandardCharsets.UTF_8);
	}
	

	private byte[] generatedSequenceOfBytes() throws Exception {
		StringBuilder randomkey = new StringBuilder();
		for (int i = 0;i < KEYSIZE;i++){
			randomkey.append(Integer.parseInt(Double.toString((Math.random()+0.1)*1000).substring(0,2)));
		}
		return randomkey.toString().getBytes("UTF-8");
	}
}
