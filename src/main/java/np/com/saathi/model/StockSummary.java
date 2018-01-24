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
public class StockSummary {
	private String dateTime;
	private String status;
	private Long totalVolume;
	private Long totalTurnover;
	private Double nepseIndex;
	private Double nepsePointChange;
	private Double nepsePercentChange;
	private Double sensitiveIndex;
	private Double sensitivePointChange;
	private Double sensitivePercentChange;
}
