package main.java.business.util;

public class Constants {

	public static final String key = "'mykeystring'";
	
	public static String encryptString(String columnName)
	{
		return String.format("AES_ENCRYPT('%s',%s)", columnName, key);
	}
	
	public static String decryptString()
	{
		return String.format("AES_ENCRYPT(?,%s)", key);
	}
}
