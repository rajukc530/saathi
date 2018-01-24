/**
 * 
 */
package np.com.saathi.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Raju KC  

 * Sep 9, 2017
 * 
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockData {
	private StockSummary stockSummary;
	private List<StockLive> stockLives;
	private List<StockTurnover> turnovers;
	
}
