package np.com.saathi.job;

import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import np.com.saathi.parser.HtmlLiveParser;


@Configuration
@EnableScheduling
public class SpringConfigToday {
 
	@Scheduled(fixedDelay = 60 * 1000)
	public void scheduleFixedDelayTask() {
		//HtmlTodayParser.run();
			    
	}
	
}