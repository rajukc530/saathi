package np.com.saathi.job;

import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import np.com.saathi.parser.HtmlParserSummary;

@Configuration
@EnableScheduling
public class SpringConfigSummary {
 
	@Scheduled(fixedDelay = 2 * 1000)
	public void scheduleFixedDelayTask() {
	   // System.out.println(
	    //  "Summary Fixed delay task start - " + new Date());
	    //HtmlParserSummary.run();
	   // System.out.println(
	  	//      "Summary Fixed delay task end - " + new Date());
	    
	}
	
}