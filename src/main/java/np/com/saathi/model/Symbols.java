/**
 * 
 */
package np.com.saathi.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Raju KC  
 * Sep 14, 2017
 * 
 */
@Data
@AllArgsConstructor

public class Symbols {
	
	@Getter
	@Setter
	private List <Symbol> symbols;

}
