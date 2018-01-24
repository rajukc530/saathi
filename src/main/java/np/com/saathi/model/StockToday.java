/**
 * 
 */
package np.com.saathi.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Raju KC  

 * Sep 7, 2017
 * 
 */

@Data
@Getter
@Setter
public class StockToday {
	private String dateTime;
	private Long sn;
	private String symbol;
	private Double ltp;
	private Long volume;
	//private Long noOfTransaction;
	private Double pointchange;
	private Double percentchange;
	
	}
