/**
 * 
 */
package np.com.saathi.parser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import np.com.saathi.model.StockLive;
import np.com.saathi.util.NumberUtil;
import np.com.saathi.util.DepthIdSymbolMapper;
import np.com.saathi.util.NameSymbolMapper;

/**
 * @author Raju KC Sep 7, 2017
 * 
 */
public class HtmlCompanySymbolParser {
	
	public static void run() {
		Document doc;

		try {
			System.out.println("Company Symbol running............:" + (new Date()));
         	//doc = Jsoup.connect("http://nepalstock.com/company?_limit=300").userAgent("Mozilla").get();
			
			Connection conn = Jsoup.connect("http://www.nmbl.com.np/live");
	        conn.timeout(300000);
	        doc = conn
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                .get();
            
			

			Elements table = doc.select("table.table-bordered.table-striped");
			Elements trs = table.select("tr");
			for (Element tr : trs) {
			
				if (   tr.text().contains("Symbol")
						|| tr.text().contains("S.N.")
						|| tr.text().contains("Total")
						|| tr.select("td").get(0).text().contains("Page")
						//|| tr.select("td").size() != 6
						)
					continue;
			
				Element td1 = tr.select("td").get(2); // company
				Element td5 = tr.select("td").get(1); // symbol
				//System.out.println("tr:"+td5.text());
				NameSymbolMapper.putSymbol(td1.text(), td5.text());
				//System.out.println("id:"+tr.select("td").get(3).select("a").attr("companysymbol"));
				DepthIdSymbolMapper.putId(td5.text(), tr.select("td").get(3).select("a").attr("companysymbol"));	
							}
			} catch (IOException e) {
			e.printStackTrace();
		}
		//HtmlCompanyPromoterSymbolParser.run();
		
	}
	
	

	public static void main(String[] args) {
		HtmlCompanySymbolParser.run();
	}

}
