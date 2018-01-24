/**
 * 
 */
package np.com.saathi.util;

import java.util.Hashtable;

/**
 * @author Raju KC  
 * Sep 11, 2017
 * 
 */
public class NameSymbolMapper {
	private static Hashtable <String, String> codes = new Hashtable <String, String>();
	
	public static String getSymbol(String name) {
		return codes.get(name);
	}
	
	
	public static void putSymbol(String name, String symbol) {
		 codes.put(name,symbol);
	}
	
	public static  Hashtable <String, String> getNameSymbolMapper(){
		return codes;
	}
	
	
	public static void init() {
		
		
	}
	
	

	
	
}
