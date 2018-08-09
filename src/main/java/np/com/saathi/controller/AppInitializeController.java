/**
 * 
 */
package np.com.saathi.controller;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

import np.com.saathi.parser.HtmlCompanySymbolParser;
import np.com.saathi.parser.nepalstock.HtmlIDSymbolParser;


/**
 * @author Raju KC  
 * Sep 12, 2017
 * 
 */

@Component
public class AppInitializeController {
	
	@PostConstruct
	public void initIt() throws Exception {
	  System.out.println("Init method after properties are set : ");
	  //HtmlCompanySymbolParser.run();
	  HtmlIDSymbolParser.run();
	}
	
	@PreDestroy
	public void cleanUp() throws Exception {
	  System.out.println("Spring Container is destroy! Customer clean up");
	}

}
