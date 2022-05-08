package depreciationCalculator;

import java.util.Scanner;

public class Depreciation {
	
	int cost;
	String purchaseDate;
	String yearEnding;
	int yearsToCalcDepFor;
	int firstYear;
	public static Scanner scanner = new Scanner(System.in);  
	
	public Depreciation(int cost, String purchaseDate, String yearEnding, int yearsToCalcDepFor, int firstYear) {
		this.cost = cost;
		this.purchaseDate = purchaseDate;
		this.yearEnding = yearEnding;
		this.yearsToCalcDepFor = yearsToCalcDepFor;
		this.firstYear = firstYear;
	}
}
