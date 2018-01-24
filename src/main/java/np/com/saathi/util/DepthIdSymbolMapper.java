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
public class DepthIdSymbolMapper {
	private static Hashtable <String, String> codesID = new Hashtable <String, String>();
	
	public static String getId(String name) {
		return codesID.get(name);
	}
	
	
	public static void putId(String symbol, String id) {
		codesID.put(symbol,id);
	}
	
	public static Hashtable <String, String> getDepthIDMapper(){
		return codesID;
	}
	
	
	public static void init() {
		
		
	}
	
	

	
	
}
