package com.cenfotec.encrypt.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cenfotec.encrypt.asymetric.AsymetricManager;
import com.cenfotec.encrypt.manager.FileManager;
import com.cenfotec.encrypt.md5.Md5Manager;
import com.cenfotec.encrypt.symetric.SymetricManager;

public class EncryptTest {

	public static AsymetricManager AM;
	public static SymetricManager SM;
	public static Md5Manager M5;
	public static FileManager FM;

	@BeforeClass
	public static void init() {
		AM = new AsymetricManager();
		SM = new SymetricManager();
		M5 = new Md5Manager();
		FM = new FileManager();
	}

	@Test
	public void decryptAsymetricMessageKiki() throws Exception {
		/* 
		 * Esta prueba solo funciona si la ruta "C:/encrypt/asymetric/" existe, y si en 
		 * esta carpeta  existe key = "kiki", messageName = "doyou", message = "love me"
		 */
		String message = "love me";
		String messageName = "doyou";
		String keyName = "kiki";

		String messageTest = AM.decryptMessage(messageName, keyName);

		assertEquals(message, messageTest);
	}
	
	@Test
	public void decryptSymetricMessageKiki() throws Exception {
		/* 
		 * Esta prueba solo funciona si la ruta "C:/encrypt/asymetric/" existe, y si en 
		 * esta carpeta  existe key = "kiki", messageName = "doyou", message = "not love me"
		 */
		String message = "not love me";
		String messageName = "doyou";
		String keyName = "kiki";

		String messageTest = SM.decryptMessage(messageName, keyName);

		assertEquals(message, messageTest);
	}
	
	@Test
	public void decryptMD5MessageKiki() throws Exception {
		/* 
		 * Esta prueba solo funciona si la ruta "C:/encrypt/md5/" existe, y si en 
		 * esta carpeta  existe key = "kiku", messageName = "doyou", message = "love me more"
		 */
		String message = "love me more";
		String messageName = "doyou";
		String keyName = "kiku";

		String messageTest = M5.decryptMessage(messageName, keyName);

		assertEquals(message, messageTest);
	}
	


}
