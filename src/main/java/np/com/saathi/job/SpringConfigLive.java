package np.com.saathi.job;

import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import np.com.saathi.parser.HtmlLiveParser;

@Configuration
@EnableScheduling
public class SpringConfigLive {
 
	@Scheduled(fixedDelay = 2 * 1000)
	public void scheduleFixedDelayTask() {
		//System.out.println(
		//	      "Live Fixed delay task - " + new Date());
		//HtmlLiveParser.run();
			    
	}
	
}