/**
 * 
 */
package np.com.saathi.model;

import java.util.List;

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
public class StockLive {
	private String dateTime;
	private Long sn;
	private String symbol;
	private Double ltp;
	private Long ltv;
	private Double pointchange;
	private Double percentchange;
	//private Double open;
	//private Double high;
	//private Double low;
	private Long volume;
	private Double previousclosing;
	//private Double totalTurnover;
	}
