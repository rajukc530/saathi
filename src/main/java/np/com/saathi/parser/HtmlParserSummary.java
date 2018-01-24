/**
 * 
 */
package np.com.saathi.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import np.com.saathi.model.StockSummary;
import np.com.saathi.util.NumberUtil;

/**
 * @author Raju KC Sep 7, 2017
 * 
 */
public class HtmlParserSummary  {
	private static StockSummary summary;
	
	public static void run() {
		final StockSummary stockSummary = new StockSummary();
		Document doc;
		try {
			System.out.println("summary running............:"+(new Date()));
			//File input = new File("E://stock.html");
			//Document doc = Jsoup.parse(input, "UTF-8", "http://nepalstock.com/stocklive");
			//doc = Jsoup.connect("http://www.nmbl.com.np/live").userAgent("Mozilla").get();
			Connection conn = Jsoup.connect("http://www.nmbl.com.np/live");
	        conn.timeout(300000);
	        doc = conn.get();
			Elements date_time = doc.select("tbody");
			for (Element ptext : date_time) {
				//System.out.println("datetime:"+ptext.text());
				if (ptext.text() != null && ptext.text().contains("As of")) {
					//System.out.println();
					stockSummary.setDateTime(ptext.text().substring(6,25 ));
				}
			}

			Elements top_marketinfo = doc.getElementsByClass("top_marketinfo"); 
			for (Element heading2 : top_marketinfo) {
				Elements p = heading2.select("div");
				for (Element ptext : p) {
					stockSummary.setStatus(ptext.text().substring(7));
				}
			}
			
			
			Elements sv = doc.getElementsByClass("sv"); 
			for (Element heading2 : sv) {
				Elements p = heading2.select("div");
				for (Element ptext : p) {
					String [] vol = ptext.text().split(" ");
					stockSummary.setTotalVolume(Long.parseLong(NumberUtil.convertToNumber(vol[3])));
					stockSummary.setTotalTurnover(Long.parseLong(NumberUtil.convertToNumber(vol[6])));
				}
			}
			
			Elements title = doc.getElementsByClass("banner-item"); 
			for (Element heading2 : title) {
				Elements p = heading2.select("div");
				for (Element ptext : p) {
					//System.out.println("INDEX:"+ptext.text());
					if(ptext.text().contains("NEPSE ")) {
						String [] nepse = ptext.text().split(" ");
						stockSummary.setNepseIndex(Double.parseDouble(NumberUtil.convertToNumber(nepse[1])));
						stockSummary.setNepsePointChange(Double.parseDouble(NumberUtil.convertToNumber(nepse[2])));
						stockSummary.setNepsePercentChange(Double.parseDouble(NumberUtil.convertToNumber(nepse[3])));
					}
				}
			}
			
			
			Elements title1 = doc.getElementsByClass("banner-item"); 
			for (Element heading2 : title1) {
				Elements p = heading2.select("div");
				for (Element ptext : p) {
					if(ptext.text().contains("Sensitive ")) {
						String [] nepse = ptext.text().split(" ");
						stockSummary.setSensitiveIndex(Double.parseDouble(NumberUtil.convertToNumber(nepse[1])));
						stockSummary.setSensitivePointChange(Double.parseDouble(NumberUtil.convertToNumber(nepse[2])));
						stockSummary.setSensitivePercentChange(Double.parseDouble(NumberUtil.convertToNumber(nepse[3])));
					}
		
				}
			}
			//System.out.println(stockSummary.toString());
			
			if(stockSummary.getStatus().equalsIgnoreCase("CLOSED"))
				stockSummary.setDateTime(stockSummary.getDateTime().substring(0,11)+"15:00:00");
			
				System.out.println(stockSummary.toString());
			setSummary(stockSummary);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static StockSummary getSummary() {
		return summary;
	}

	public static synchronized void setSummary(StockSummary summary) {
		HtmlParserSummary.summary = summary;
	}
	
	public static void main(String[] args) {
		HtmlParserSummary.run();
	}

}
