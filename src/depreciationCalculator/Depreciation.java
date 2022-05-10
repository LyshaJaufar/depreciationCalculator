package depreciationCalculator;

import java.util.Hashtable;
import java.util.Scanner;

public class Depreciation {
	
	int cost[];
	String purchaseDate[];
	String yearEnding;
	int yearsToCalcDepFor;
	int firstYear;
	int numOfNCA;
	String[] yearEndingDateSplit;
	public int yearEndingDay;
	public int yearEndingMonth;
	public static Scanner scanner = new Scanner(System.in);  
	
	public Hashtable<Integer, Integer>[] yearlyDepreciation;
	public Hashtable<Integer, Integer> totalDepreciationForTheYear;
	
	public Depreciation(int[] cost, String[] purchaseDate, String yearEnding, int yearsToCalcDepFor, int firstYear, int numOfNCA) {
		this.numOfNCA = numOfNCA;
		this.yearEnding = yearEnding;
		this.yearsToCalcDepFor = yearsToCalcDepFor;
		this.firstYear = firstYear;
		this.cost = cost;
	    this.purchaseDate = purchaseDate;
	     
	    this.yearlyDepreciation = new Hashtable[numOfNCA];
	    this.totalDepreciationForTheYear = new Hashtable<>();
	}
	

	public void splitYearEndingDate() {
		
		// Split year ending date of business
		int[] dayMonthYear = new int[2];
		this.yearEndingDateSplit = yearEnding.split("/");
		for (int i = 0; i < yearEndingDateSplit.length; i++) {
			dayMonthYear[i] = Integer.parseInt(this.yearEndingDateSplit[i]);
		}
		
		this.yearEndingDay = dayMonthYear[0];
		this.yearEndingMonth = dayMonthYear[1];
		System.out.println(this.yearEndingDay);
		System.out.println(this.yearEndingMonth);
	}
	
	public void findTotalDepreciationForTheYear() {
		int totalDepForYear = 0;

		for (int i = 0; i < this.yearsToCalcDepFor; i++) {
			for (int j = 0; j < this.numOfNCA; j++) {
				totalDepForYear += this.yearlyDepreciation[j].get(this.firstYear + i);
				
				if (j == this.numOfNCA - 1) {
					this.totalDepreciationForTheYear.put(this.firstYear + i, totalDepForYear);
					totalDepForYear = 0;
				}
			}
		}
	}

}
