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
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import np.com.saathi.model.StockLive;
import np.com.saathi.util.NameSymbolMapper;
import np.com.saathi.util.NumberUtil;

/**
 * @author Raju KC Sep 7, 2017
 * 
 */


@Data
public class HtmlTodayParser {
  @Getter
  @Setter
  private static List<StockLive> today = new ArrayList<StockLive>();

  public static void run() {
    final List<StockLive> stockLives = new ArrayList<StockLive>();
    String dateTime = "";
    Document doc;

    try {
      System.out.println("Today running............:" + (new Date()));

      // File input = new File("E://today.html");
      // doc = Jsoup.parse(input, "UTF-8",
      // "http://nepalstock.com/main/todays_price/index");
      doc = Jsoup.connect("http://nepalstock.com/main/todays_price/index?_limit=200")
          .userAgent("Mozilla").get();

      Elements date_element = doc.select("label.pull-left");
      dateTime = date_element.text().substring(6);
      System.out.println("DT:" + dateTime);
      Elements table = doc.select("table.table.table-condensed");
      long cnt = 1;
      Elements trs = table.select("tr");
      for (Element tr : trs) {

        if (tr.select("td").size() <= 5 || tr.text().contains("As of") || tr.text().contains("S.N.")
            || tr.text().contains("Total") || tr.select("td").get(0).text().contains("Page"))
          continue;
        Element td1 = tr.select("td").get(1); // company
        Element td5 = tr.select("td").get(5); // ltp
        Element td6 = tr.select("td").get(6); // vol
        Element td9 = tr.select("td").get(9); // pt ch
        Element td8 = tr.select("td").get(8); // previosu close

        StockLive stockLive = new StockLive();

        stockLive.setDateTime(dateTime);
        stockLive.setSn(cnt++);
        stockLive.setSymbol(NameSymbolMapper.getSymbol(td1.text()));
        stockLive.setLtp(Double.parseDouble(NumberUtil.convertToNumber(td5.text())));
        stockLive.setPointchange(Double.parseDouble(NumberUtil.convertToNumber(td9.text())));
        stockLive.setPercentchange(NumberUtil.calcuatePercentage(
            Double.parseDouble(NumberUtil.convertToNumber(td9.text())),
            Double.parseDouble(NumberUtil.convertToNumber(td8.text()))));
        double vol = (double) (Double.parseDouble(NumberUtil.convertToNumber(td6.text())));
        stockLive.setVolume((long) vol);
        stockLive.setPreviousclosing(Double.parseDouble(NumberUtil.convertToNumber(td8.text())));
        // System.out.println("stocklive:" + stockLive.toString());
        stockLives.add(stockLive);

      }
      // System.out.println("Live:" + stockLives.size());
      if (stockLives.size() > 0)
        setToday(stockLives);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }



  public static void main(String[] args) {
    HtmlTodayParser.run();
  }

}
