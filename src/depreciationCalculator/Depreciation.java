package depreciationCalculator;

import java.util.Scanner;

public class Depreciation {
	
	int cost[];
	String purchaseDate[];
	String yearEnding;
	int yearsToCalcDepFor;
	int firstYear;
	int numOfNCA;
	public static Scanner scanner = new Scanner(System.in);  
	
	public Depreciation(int[] cost, String[] purchaseDate, String yearEnding, int yearsToCalcDepFor, int firstYear, int numOfNCA) {
		this.numOfNCA = numOfNCA;
		this.yearEnding = yearEnding;
		this.yearsToCalcDepFor = yearsToCalcDepFor;
		this.firstYear = firstYear;
		this.cost = cost;
	    this.purchaseDate = purchaseDate;
	}


}
