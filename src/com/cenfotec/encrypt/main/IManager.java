package com.cenfotec.encrypt.main;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;


public interface IManager {

	public void createKey(String name) throws Exception, IOException, InvalidKeySpecException, NoSuchAlgorithmException;
	public void encryptMessage(String messageName, String message, String keyName) throws IOException, IllegalBlockSizeException, BadPaddingException;
	public String decryptMessage(String messageName, String keyName) throws Exception;
}
