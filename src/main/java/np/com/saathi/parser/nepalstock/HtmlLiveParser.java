/**
 * 
 */
package np.com.saathi.parser.nepalstock;

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
import lombok.Getter;
import lombok.Setter;
import np.com.saathi.model.StockLive;
import np.com.saathi.model.StockTurnover;
import np.com.saathi.util.NameSymbolMapper;
import np.com.saathi.util.NumberUtil;

/**
 * @author Raju KC Sep 7, 2017
 * 
 */

public class HtmlLiveParser {


  private static List<StockLive> lives = new ArrayList<StockLive>();


  private static List<StockTurnover> turnovers = new ArrayList();

  public static void run() {
    final List<StockLive> stockLives = new ArrayList<StockLive>();
    String dateTime = "";
    Document doc;

    try {
      System.out.println("Live running............:" + (new Date()));

      // File input = new File("E://stock.html");
      // doc = Jsoup.parse(input, "UTF-8", "http://nepalstock.com/stocklive");

      // Connection conn = Jsoup.connect();
      // conn.timeout(300000); //5 minutes
      // Document doc = conn.get();


      Connection conn = Jsoup.connect("http://nepalstock.com/stocklive");
      conn.timeout(300000);
      doc = conn.get();
      System.out.println("doc:" + doc.toString());

      Elements date_time = doc.select("div.panel-heading");
      for (Element ptext : date_time) {
        if (ptext.text() != null && ptext.text().contains("As of")) {
          dateTime += ptext.text().substring(6);
        }
      }

      Elements table_live = doc.select("table.table.table-condensed");
      Elements tr_live = table_live.select("tr");
      long cnt = 1;
      for (Element tr : tr_live) {
        if (tr.select("td").size() <= 5 || tr.select("td").get(0).text().contains("Symbol")
            || tr.text().contains("Symbol") || tr.text().contains("As of"))
          continue;

        Element td1 = tr.select("td").get(1);
        Element td2 = tr.select("td").get(2);
        Element td3 = tr.select("td").get(3);
        Element td4 = tr.select("td").get(4);
        Element td5 = tr.select("td").get(5);
        // Element td6 = tr.select("td").get(6);
        // Element td7 = tr.select("td").get(7);
        // Element td8 = tr.select("td").get(8);
        Element td9 = tr.select("td").get(9);
        Element td10 = tr.select("td").get(10);

        StockLive stockLive = new StockLive();

        stockLive.setDateTime(dateTime);
        stockLive.setSn(cnt++);
        stockLive.setSymbol(td1.text());
        stockLive.setLtp(Double.parseDouble(NumberUtil.convertToNumber(td2.text())));
        stockLive.setLtv(Long.parseLong(NumberUtil.convertToNumber(td3.text())));
        stockLive.setPointchange(Double.parseDouble(NumberUtil.convertToNumber(td4.text())));
        stockLive
            .setPercentchange(Math.abs(Double.parseDouble(NumberUtil.convertToNumber(td5.text()))));
        // stockLive.setOpen(Double.parseDouble(NumberUtil.convertToNumber(td6.text())));
        // stockLive.setHigh(Double.parseDouble(NumberUtil.convertToNumber(td7.text())));
        // stockLive.setLow(Double.parseDouble(NumberUtil.convertToNumber(td8.text())));
        stockLive.setVolume(Long.parseLong(NumberUtil.convertToNumber(td9.text())));
        stockLive.setPreviousclosing(Double.parseDouble(NumberUtil.convertToNumber(td10.text())));
        // System.out.println("stocklive:" + stockLive.toString());
        stockLives.add(stockLive);

      }
      Element table_turnover = doc.getElementById("top-by-turnover");
      Elements tr_turnover = table_turnover.select("tr");
      List<StockTurnover> stockTurnovers = new ArrayList<StockTurnover>();
      long counter = 1;
      for (Element tr : tr_turnover) {
        if (tr.text().contains("Symbol") || tr.text().contains("As of")
            || tr.text().contains("View All"))
          continue;
        Element td0 = tr.select("td").get(0);
        Element td1 = tr.select("td").get(1);
        Element td2 = tr.select("td").get(2);
        StockTurnover stockTurnover = new StockTurnover();
        stockTurnover.setSn(counter++);
        stockTurnover.setSymbol(td0.text());
        stockTurnover.setTurnOver(Double.parseDouble(NumberUtil.decimalFormatter
            .format(Double.parseDouble(NumberUtil.convertToNumber(td1.text())))));
        stockTurnover.setLtp(Double.parseDouble(NumberUtil.convertToNumber(td2.text())));
        stockTurnovers.add(stockTurnover);
        // System.out.println(stockTurnover);
      }

      if (stockTurnovers.size() > 0)
        setTurnovers(stockTurnovers);
      if (stockLives.size() > 0)
        setLives(stockLives);
      System.out.println("::" + stockLives.toString());

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    HtmlLiveParser.run();
  }

  public static List<StockLive> getLives() {
    return lives;
  }

  public static synchronized void setLives(List<StockLive> lives) {
    HtmlLiveParser.lives = lives;
  }

  public static List<StockTurnover> getTurnovers() {
    return turnovers;
  }

  public static synchronized void setTurnovers(List<StockTurnover> turnovers) {
    HtmlLiveParser.turnovers = turnovers;
  }

}
