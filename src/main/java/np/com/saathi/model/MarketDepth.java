/**
 * 
 */
package np.com.saathi.model;

import java.util.ArrayList;
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
public class MarketDepth {
	private List <Buyer> buyer = new ArrayList<Buyer>();
	private List <Seller> seller = new ArrayList<Seller>();
	private String totalBuyer;
	private String totalSeller;
	private String symbol;
	private String dateTime;
	private String currentStatus;
	

}




