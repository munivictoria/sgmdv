package com.trascender.caja.gui.recursos;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MessagesCaja {
	private static final String BUNDLE_NAME = "properties.messages";

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private MessagesCaja() {
		
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	
	public static char getChar(String key) {
		return getString(key).charAt(0);
	}
}
