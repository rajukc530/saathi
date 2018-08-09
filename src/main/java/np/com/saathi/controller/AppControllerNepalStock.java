package np.com.saathi.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import np.com.saathi.model.MarketDepth;
import np.com.saathi.model.StockData;
import np.com.saathi.model.StockLive;
import np.com.saathi.model.StockSummary;
import np.com.saathi.model.Symbol;
import np.com.saathi.model.Symbols;
import np.com.saathi.parser.nepalstock.HtmlLiveParser;
import np.com.saathi.parser.nepalstock.HtmlMarketDepthParser;
import np.com.saathi.parser.nepalstock.HtmlParserSummary;
import np.com.saathi.util.DepthIdSymbolMapper;
import np.com.saathi.util.NameSymbolMapper;

@RestController
public class AppControllerNepalStock {

	@RequestMapping(value = "/app/stock", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public final ResponseEntity<StockData> getStockData() {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>Live Stock >>>>>>>>>>>>>>>>>>>>>>>");
		StockData stock = null;
		callAPI();
		
			stock = new StockData(HtmlParserSummary.getSummary(), HtmlLiveParser.getLives(),
					HtmlLiveParser.getTurnovers());
		return new ResponseEntity(stock, HttpStatus.OK);

	}

	@RequestMapping(value = "/app/depth/{symbol}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public final ResponseEntity<MarketDepth> getDepth(@PathVariable("symbol") String symbol) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>Market Depth>>>>>>>>>>>>>>>>>>>>>>>:"+symbol);
		System.out.println("ID:"+DepthIdSymbolMapper.getId(symbol.toUpperCase()));
		if (DepthIdSymbolMapper.getId(symbol.toUpperCase()) != null) {
			final HtmlMarketDepthParser parser = new HtmlMarketDepthParser();
			parser.run(symbol.toUpperCase());
			return new ResponseEntity(parser.getDepth(), HttpStatus.OK);
		} else {
			return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/app/summary", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public final ResponseEntity<StockSummary> getSummary() {
		HtmlParserSummary.run();
		return new ResponseEntity(HtmlParserSummary.getSummary(), HttpStatus.OK);

	}

	@RequestMapping(value = "/app/live", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public final ResponseEntity<List<StockLive>> getLive() {

		return new ResponseEntity(HtmlLiveParser.getLives(), HttpStatus.OK);

	}

	@RequestMapping(value = "/app/symbolMapper", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public final ResponseEntity<Hashtable<String, String>> getSymbolMapper() {
		return new ResponseEntity(NameSymbolMapper.getNameSymbolMapper(), HttpStatus.OK);

	}

	@RequestMapping(value = "/app/depthMapper", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public final ResponseEntity<Hashtable<String, String>> getDepthMapper() {
		return new ResponseEntity(DepthIdSymbolMapper.getDepthIDMapper(), HttpStatus.OK);

	}

	@RequestMapping(value = "/app/symbol", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public final ResponseEntity<Symbols> getSymbol() {
		return new ResponseEntity(getSymbolData(), HttpStatus.OK);

	}

	public Symbols getSymbolData() {
		final Hashtable<String, String> ht = NameSymbolMapper.getNameSymbolMapper();
		final List<Symbol> symbols = new ArrayList<Symbol>();
		Set<String> keys = ht.keySet();
		for (String key : keys) {
			symbols.add(new Symbol((String) ht.get(key)));
		}
		Symbols symbolsList = new Symbols(symbols);
		return symbolsList;

	}
	
	public void callAPI() {
		HtmlParserSummary.run();
		HtmlLiveParser.run();
		//HtmlTodayParser.run();
		
	}

}
