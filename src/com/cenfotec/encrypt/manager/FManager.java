package com.cenfotec.encrypt.manager;

import com.cenfotec.encrypt.asymetric.AsymetricManager;
import com.cenfotec.encrypt.md5.Md5Manager;
import com.cenfotec.encrypt.symetric.SymetricManager;

public class FManager {

	public static IManager encryptType(int type) {
		
		switch (type) {
		case 1:
			
			return new AsymetricManager();
			
		case 2:
			
			return new SymetricManager();
			
		case 3:
			
			return new Md5Manager();
			
		default:
			
			return null;

		}
	}
}
