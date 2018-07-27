package com.cenfotec.encrypt.main;

import com.cenfotec.encrypt.asymetric.AsymetricManager;
import com.cenfotec.encrypt.symetric.SymetricManager;

public class FManager {

	public static IManager encryptType(int type) {
		
		switch (type) {
		case 1:
			
			return new AsymetricManager();
			
		case 2:
			
			return new SymetricManager();
			
		default:
			
			return null;

		}
	}
}
