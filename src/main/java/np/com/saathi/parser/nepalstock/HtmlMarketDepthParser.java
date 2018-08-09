/**
 * 
 */
package np.com.saathi.parser.nepalstock;

import java.io.File;
import java.io.IOException;
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
import np.com.saathi.model.Buyer;
import np.com.saathi.model.MarketDepth;
import np.com.saathi.model.Seller;
import np.com.saathi.model.StockLive;
import np.com.saathi.model.StockTurnover;
import np.com.saathi.util.DepthIdSymbolMapper;
import np.com.saathi.util.NumberUtil;

/**
 * @author Raju KC Sep 7, 2017
 * 
 */

@Getter
@Setter
@Data
public class HtmlMarketDepthParser {


  @Getter
  @Setter
  private MarketDepth depth;

  public void run(String symbol) {
    final MarketDepth marketDepth = new MarketDepth();
    String dateTime = "";
    Document doc;
    final List<Buyer> buyer = new ArrayList<Buyer>();
    final List<Seller> seller = new ArrayList<Seller>();


    try {
      System.out.println("market depth running............:" + (new Date()));

      // File input = new File("E://market.html");
      // doc = Jsoup.parse(input, "UTF-8", "http://nepalstock.com/stocklive");
      doc = Jsoup.connect("http://nepalstock.com/marketdepthofcompany/"
          + DepthIdSymbolMapper.getId(symbol.toUpperCase())).userAgent("Mozilla").get();
      marketDepth.setSymbol(symbol.toUpperCase());
      Element date_time = doc.getElementById("date");
      if (date_time.text() != null && date_time.text().contains("As of")) {
        dateTime += date_time.text().substring(6);
        marketDepth.setDateTime(dateTime);
      }

     // System.out.println(">>" + dateTime);
      Elements table_live = doc.select("table.depthIndex");
     // System.out.println(":::"+table_live.toString());
      Elements tr_live = table_live.select("tr");
      for (Element tr : tr_live) {

        Element td0 = tr.select("td").get(0);
        marketDepth.setCurrentStatus(td0.text());
      }

      Elements table_buy = doc.select("table.table.orderTable");
      Elements tr_buy = table_buy.select("tr");
      int counter = 1;
      System.out.println("tr_buy::"+tr_buy.toString());
      for (Element tr : tr_buy) {

        if (tr.text().contains("Buy") || tr.text().contains("Sell") || tr.select("td").size() != 3)
          continue;
        if (tr.text().contains("---") && counter <= 5) {
          counter = 6;
          continue;
        }

        Element td0 = tr.select("td").get(0);
        Element td1 = tr.select("td").get(1);
        Element td2 = tr.select("td").get(2);
        if (counter <= 5) {
          Buyer buy = new Buyer();
          buy.setOrder(td0.text());
          buy.setQuantity(td1.text());
          buy.setPrice(td2.text());
          buyer.add(buy);
          System.out.println("Buyer:" + td1.text().replaceAll("---", ""));

        } else {
          Seller sell = new Seller();
          sell.setOrder(td2.text());
          sell.setQuantity(td1.text());
          sell.setPrice(td0.text());
          seller.add(sell);
          System.out.println("Seller:" + td1.text().replaceAll("---", ""));


        }
        counter++;
      }
      if (buyer.size() > 0)
        marketDepth.setBuyer(buyer);
      if (seller.size() > 0)
        marketDepth.setSeller(seller);

      Elements table_total = doc.select("table.table.orderTable");
      Elements tr_total = table_total.select("tr");
      for (Element tr : tr_total) {

        if (tr.select("td").size() != 5)
          continue;
        Element td0 = tr.select("td").get(1);
        Element td1 = tr.select("td").get(3);
        System.out.println("total:" + td1.text());
        marketDepth.setTotalBuyer(td0.text());
        marketDepth.setTotalSeller(td1.text());
      }

      setDepth(marketDepth);
      System.out.println(marketDepth);
    } catch (

    IOException e) {
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    new HtmlMarketDepthParser().run("131");
  }

}
