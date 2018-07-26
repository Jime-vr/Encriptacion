package com.cenfotec.manager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

public class FileManager {
	
	private final String MESSAGE_ENCRYPT_EXTENSION = ".encript";

	public void writeBytesFile(String path, String name, byte[] content, String type) throws FileNotFoundException, IOException {
		FileOutputStream fos = new FileOutputStream(path + name + type);
		fos.write(content);
		fos.close();
	}
	
	public void cipher(String path, String name, Cipher cipher, String message) throws IllegalBlockSizeException, BadPaddingException, FileNotFoundException, IOException {
		byte[] encryptedData = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
	    Encoder oneEncoder = Base64.getEncoder();
	    encryptedData = oneEncoder.encode(encryptedData);
	   
	    
	    writeBytesFile(path, name, encryptedData, MESSAGE_ENCRYPT_EXTENSION);
	}
	
	public byte[] readMessageFile(String path, String messageName) throws Exception {
		File file = new File(path + messageName + MESSAGE_ENCRYPT_EXTENSION);
		int length = (int) file.length();
		BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
		byte[] bytes = new byte[length];
		reader.read(bytes, 0, length);
		reader.close();
		Decoder oneDecoder = Base64.getDecoder();
		return oneDecoder.decode(bytes);

	}
}
