/**
 * 
 */
package np.com.saathi.util;

import java.text.DecimalFormat;

/**
 * @author Raju KC  
 * Sep 11, 2017
 * 
 */
public class NumberUtil {
	public static DecimalFormat doubleFormatter = new DecimalFormat("#.##");
	public static DecimalFormat decimalFormatter = new DecimalFormat("############");
	public static String convertToNumber(String text) {
		String txt =  text.replaceAll("[^0-9,.-]", "");
		 return txt.replaceAll(",", "");
		}
	
	public static Double calcuatePercentage(Double change, Double original) {
		if(original == 0.0 || change==0.0)
			return 0.0;
		
		Double cal = change/original*100;
		return Double.parseDouble(doubleFormatter.format(Math.abs(cal)));
	}
	
	public static void main(String[] args) {
		System.out.println(NumberUtil.calcuatePercentage(-10.0, 100.0999));
		//System.out.println(NumberUtil.decimalFormatter.format((Double.parseDouble("1234343432423456789"))));
	}
}
