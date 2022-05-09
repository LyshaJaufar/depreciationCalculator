package depreciationCalculator;

import java.util.ArrayList;
import java.util.Hashtable;

public class StraightLine extends Depreciation {
	
	public int[] scrapValue;
	public int[] expectedUsefulLife;
	public float depreciationRate;
	
	public int purchaseDay;
	public int purchaseMonth;
	public int purchaseYear;
	
	public int yearEndingDay;
	public int yearEndingMonth;
	
    int ignoreYearYesOrNo;
    
    public Hashtable<Integer, Integer>[] yearlyDepreciation;
    public Hashtable<Integer, Integer> totalDepreciationForTheYear = new Hashtable<>();

	public StraightLine(int[] cost, String[] purchaseDate, String yearEnding, int yearsToCalcDepFor, int firstYear, int numOfNCA) {
		super(cost, purchaseDate, yearEnding, yearsToCalcDepFor, firstYear, numOfNCA);	
		
		// Split year ending date of business
		int[] dayMonthYear = new int[2];
		String[] yearEndingDateSplit = yearEnding.split("/");
		for (int i = 0; i < yearEndingDateSplit.length; i++) {
			dayMonthYear[i] = Integer.parseInt(yearEndingDateSplit[i]);
		}
		
		this.yearEndingDay = dayMonthYear[0];
		this.yearEndingMonth = dayMonthYear[1];
		
	}
	
	public void findTotalDepreciationForTheYear() {
		int totalDepForYear = 0;
		for (int i = 0; i < this.yearsToCalcDepFor; i++) {
			for (int j = 0; j < this.numOfNCA; j++) {
				totalDepForYear += yearlyDepreciation[j].get(this.firstYear + i);
				
				if (j == this.numOfNCA - 1) {
					totalDepreciationForTheYear.put(this.firstYear + i, totalDepForYear);
					totalDepForYear = 0;
				}
			}
		}
	}
	
	public void calculate() {
		int technique;
		float depreciation = 0;
		this.scrapValue = new int[numOfNCA];
		this.expectedUsefulLife = new int[numOfNCA];
		yearlyDepreciation = new Hashtable[numOfNCA];
		
		while (true) {
		    System.out.println("Enter the corresponding for the info provided: ");
		    System.out.println(" 1.  Rate of depreciation \n 2.  Scrap Value");
		    
		    technique = scanner.nextInt();
		    if (technique == 1 || technique == 2) {
		    	break;
		    }
		}
		if (technique == 1) {
			
		    System.out.println("Enter depreciation rate: ");
		    this.depreciationRate = scanner.nextFloat();
		    
		    for (int i = 0; i < this.numOfNCA; i++) {
				depreciation = this.depreciationRate * this.cost[i];
				
				for (int j = 0; j < this.yearsToCalcDepFor; j++) {
					yearlyDepreciation[i].put(this.firstYear + j, (int)depreciation);
				}
		    }
			
		} else {
			for (int i = 0; i < this.numOfNCA; i++) {
		        System.out.print("Enter scrap value of non-current asset " + (i + 1) + ":");
		        this.scrapValue[i] = scanner.nextInt();
		        
		        System.out.print("Enter expected useful life of non-current asset " + (i + 1) + ":");
		        this.expectedUsefulLife[i] = scanner.nextInt();
			}
	        
		    while (true) {
				System.out.print("Calculate depreciation for the year of purchase? ");
			    System.out.println("Enter the corresponding number to answer: ");
			    System.out.println(" 1.  No \n 2.  Yes");
			    ignoreYearYesOrNo = scanner.nextInt();
		    	
		    	if (ignoreYearYesOrNo == 1|| ignoreYearYesOrNo == 2) {
		    		break;
		    	}
		    }

	        if (ignoreYearYesOrNo == 1) {
	        	
	        	for (int i = 0; i < numOfNCA; i++) {
		        	depreciation = (this.cost[i] - this.scrapValue[i]) / this.expectedUsefulLife[i];
		        	
					for (int j = 0; j < this.yearsToCalcDepFor; j++) {
						yearlyDepreciation[i].put(this.firstYear + j, (int)depreciation);
					}
	        	}
				
	        } else {
	        	
	        	String[] purchaseDateSplit;
	        	for (int i = 0; i < this.numOfNCA; i++) {
	        		purchaseDateSplit = purchaseDate[i].split("/");
	        		purchaseDay = Integer.parseInt(purchaseDateSplit[0]);
	        		purchaseMonth = Integer.parseInt(purchaseDateSplit[1]);
	        		purchaseYear = Integer.parseInt(purchaseDateSplit[2]);
	        		
	        		yearlyDepreciation[i] = new Hashtable<Integer, Integer>();

			        if (purchaseMonth > this.yearEndingMonth) {
			       		depreciation = 0;
			       	} else if (purchaseMonth < this.yearEndingMonth) {
			       		int monthsElapsed = this.yearEndingMonth - purchaseMonth + 1;
			       		depreciation = ((this.cost[i] - this.scrapValue[i]) / this.expectedUsefulLife[i]) * monthsElapsed/12;
			        }
			       	yearlyDepreciation[i].put(this.firstYear, (int)depreciation);		        	
			        	
			       	depreciation = ((this.cost[i] - this.scrapValue[i]) / this.expectedUsefulLife[i]);
					for (int j = 1; j < this.yearsToCalcDepFor; j++) {
						yearlyDepreciation[i].put(this.firstYear + j, (int)depreciation);
					}
	        	}
	        } 
		}
		findTotalDepreciationForTheYear();
	}
	
}
