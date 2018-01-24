/**
 * 
 */
package np.com.saathi.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Raju KC  
 * Sep 7, 2017
 * 
 */
@Getter
@Setter
@Data
public class MarketDepths {
	private static Hashtable <Integer,MarketDepth> depth = new Hashtable<Integer,MarketDepth> ();
   
	public static void put(Integer id, MarketDepth marketDepth) {
		depth.put(id,marketDepth);
	}
	
	public static MarketDepth get(Integer id) {
		return depth.get(id);
	}
	
}
