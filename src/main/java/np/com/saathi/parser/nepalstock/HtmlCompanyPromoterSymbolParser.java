/**
 * 
 */
package np.com.saathi.parser.nepalstock;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import np.com.saathi.model.StockLive;
import np.com.saathi.util.NumberUtil;
import np.com.saathi.util.NameSymbolMapper;

/**
 * @author Raju KC Sep 7, 2017
 * 
 */
public class HtmlCompanyPromoterSymbolParser {

  public static void run() {
    Document doc;

    try {
      System.out.println("Company Promoter Symbol running............:" + (new Date()));
      doc = Jsoup.connect("http://nepalstock.com/promoter-share?_limit=300").userAgent("Mozilla")
          .get();

      Elements table = doc.select("table.table.my-table");
      Elements trs = table.select("tr");
      for (Element tr : trs) {
        if (tr.text().contains("Symbol") || tr.text().contains("S.N.")
            || tr.text().contains("Total") || tr.select("td").get(0).text().contains("Page")
            || tr.select("td").size() != 5)
          continue;

        Element td1 = tr.select("td").get(2); // company
        Element td5 = tr.select("td").get(3); // symbol
        NameSymbolMapper.putSymbol(td1.text(), td5.text());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }



  public static void main(String[] args) {
    HtmlCompanyPromoterSymbolParser.run();
  }

}
