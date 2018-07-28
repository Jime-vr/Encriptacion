package com.cenfotec.encrypt.md5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.cenfotec.encrypt.manager.FileManager;
import com.cenfotec.encrypt.manager.IManager;

public class Md5Manager implements IManager {

	private final int KEYSIZE = 8;
	private final String KEY_EXTENSION = ".key";
	private final String PATH = "C:/encrypt/md5/";

	private FileManager fileM = new FileManager();

	public void createKey(String name)
			throws Exception, IOException, InvalidKeySpecException, NoSuchAlgorithmException {
		byte[] key = generatedSequenceOfBytes();
		fileM.writeBytesFile(PATH, name, key, KEY_EXTENSION);
	}

	public void encryptMessage(String messageName, String message, String keyName)
			throws IOException, IllegalBlockSizeException, BadPaddingException {
		byte[] key = readKeyFile(keyName);
		Cipher cipher = null;

		try {
			cipher = Cipher.getInstance("DESede");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(message.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);

			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		byte[] digestOfPassword = md.digest(key);
		byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

		SecretKeySpec k = new SecretKeySpec(keyBytes, "DESede");
		try {
			cipher.init(Cipher.ENCRYPT_MODE, k);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}

		fileM.cipher(PATH, messageName, cipher, message);
	}

	public String decryptMessage(String messageName, String keyName) throws Exception {

		MessageDigest md = null;
		md = MessageDigest.getInstance("MD5");
		String finalMessage = null;
		
		byte[] secretKey = readKeyFile(keyName);
		byte[] encryptedMessage = fileM.readMessageFile(PATH, messageName);

		byte[] digestOfPassword = md.digest(secretKey);
		byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
		SecretKey key = new SecretKeySpec(keyBytes, "DESede");
		
		Cipher cipher = Cipher.getInstance("DESede");
		cipher.init(Cipher.DECRYPT_MODE, key);
		
		byte[] decryptedData = cipher.doFinal(encryptedMessage);


		finalMessage = fileM.createDescryptedMessage(decryptedData);
		return finalMessage;
	}

	private byte[] generatedSequenceOfBytes() throws Exception {
		StringBuilder randomkey = new StringBuilder();
		for (int i = 0; i < KEYSIZE; i++) {
			randomkey.append(Integer.parseInt(Double.toString((Math.random() + 0.1) * 1000).substring(0, 2)));
		}
		return randomkey.toString().getBytes("UTF-8");
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
}
