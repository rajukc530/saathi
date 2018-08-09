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
import np.com.saathi.util.DepthIdSymbolMapper;

/**
 * @author Raju KC Sep 7, 2017
 * 
 */
public class HtmlIDSymbolParser {

  public static void run() {
    Document doc;

    try {
      System.out.println("Company ID Symbol running............:" + (new Date()));
      doc = Jsoup.connect("http://nepalstock.com/marketdepth").userAgent("Mozilla").get();
      Element table = doc.getElementById("StockSymbol_Select");
      Elements options = table.select("select > option");
      for (Element option : options) {
        if (option.text().contains("Choose Symbol"))
          continue;
        DepthIdSymbolMapper.putId(option.text(), option.attr("value"));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    HtmlIDSymbolParser.run();
  }

}
