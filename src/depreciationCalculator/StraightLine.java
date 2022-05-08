package depreciationCalculator;

import java.util.Hashtable;

public class StraightLine extends Depreciation {
	
	public int scrapValue;
	public int expectedUsefulLife;
	public float depreciationRate;
	
	public int purchaseDay;
	public int purchaseMonth;
	public int purchaseYear;
	
	public int yearEndingDay;
	public int yearEndingMonth;
	
    int ignoreYearYesOrNo;
    
    public Hashtable<Integer, Integer> yearlyDepreciation = new Hashtable<Integer, Integer>();

	public StraightLine(int cost, String purchaseDate, String yearEnding, int yearsToCalcDepFor, int firstYear) {
		super(cost, purchaseDate, yearEnding, yearsToCalcDepFor, firstYear);
		
		splitDates();
	}
	
	public void splitDates() {
		
		// Split date non-current asset was purchased
		int[] dayMonthYear = new int[3];
		String[] purchaseDateSplit = purchaseDate.split("/");
		for (int i = 0; i < purchaseDateSplit.length; i++) {
			dayMonthYear[i] = Integer.parseInt(purchaseDateSplit[i]);
		}
		
		this.purchaseDay = dayMonthYear[0];
		this.purchaseMonth = dayMonthYear[1];
		this.purchaseYear = dayMonthYear[2];
		
		// Split year ending date of business
		String[] yearEndingDateSplit = yearEnding.split("/");
		for (int i = 0; i < yearEndingDateSplit.length; i++) {
			dayMonthYear[i] = Integer.parseInt(yearEndingDateSplit[i]);
		}
		
		this.yearEndingDay = dayMonthYear[0];
		this.yearEndingMonth = dayMonthYear[1];

	}
	
	
	public void calculate() {
		int technique;
		float depreciation = 0;
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
			depreciation = this.depreciationRate * this.cost;
			
			for (int i = 0; i < this.yearsToCalcDepFor; i++) {
				this.yearlyDepreciation.put(this.firstYear + i, (int)depreciation);
			}
			
		} else {
	        System.out.print("Enter scrap value: ");
	        this.scrapValue = scanner.nextInt();
	        
	        System.out.print("Enter expected useful life: ");
	        this.expectedUsefulLife = scanner.nextInt();
	        
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
	        	depreciation = (this.cost - this.scrapValue) / this.expectedUsefulLife;
	        	
				for (int i = 0; i < this.yearsToCalcDepFor; i++) {
					yearlyDepreciation.put(this.firstYear + i, (int)depreciation);
				}
				
	        } else {
	        	
	        	if (firstYear == purchaseYear) {
		        	if (this.purchaseMonth > this.yearEndingMonth) {
		        		depreciation = 0;
		        	} else if (this.purchaseMonth < this.yearEndingMonth) {
		        		int monthsElapsed = this.yearEndingMonth - this.purchaseMonth + 1;
		        		depreciation = ((this.cost - this.scrapValue) / this.expectedUsefulLife) * monthsElapsed/12;
		        	}
		        	yearlyDepreciation.put(this.firstYear, (int) depreciation);		        	
		        	
		        	depreciation = ((this.cost - this.scrapValue) / this.expectedUsefulLife);
					for (int i = 1; i < this.yearsToCalcDepFor; i++) {
						yearlyDepreciation.put(this.firstYear + i, (int)depreciation);
					}
		        	
	        	} else if (firstYear > purchaseYear) {
					for (int i = 0; i < this.yearsToCalcDepFor; i++) {
						yearlyDepreciation.put(this.firstYear + i, (int)depreciation);
					}
	        	}
	        } 
		}
	}
	
}
