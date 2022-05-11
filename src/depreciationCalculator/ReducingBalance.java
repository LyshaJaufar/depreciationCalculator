package depreciationCalculator;

import java.util.Hashtable;

public class ReducingBalance extends Depreciation {

	int ignoreYearYesOrNo;
	public float depreciationRate;
	public Hashtable<Integer, Integer>[] accumulatedDepreciation;
	public Hashtable<Integer, Integer>[] netBookValue;
	int purchaseDay, purchaseMonth, purchaseYear;

	public ReducingBalance(int[] cost, String[] purchaseDate, String yearEnding, int yearsToCalcDepFor, int firstYear, int numOfNCA) {
		super(cost, purchaseDate, yearEnding, yearsToCalcDepFor, firstYear, numOfNCA);
		
		this.accumulatedDepreciation = new Hashtable[numOfNCA];
		this.netBookValue = new Hashtable[numOfNCA];
		splitYearEndingDate();
	}
	
	public void calculate() {
		float depreciation = 0;
		float accumulatedDep = 0;
		float NBV = 0;
		boolean yearOne = true;
		
	    System.out.println("Enter depreciation rate: ");
	    this.depreciationRate = scanner.nextFloat();
	    
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
	    	String[] purchaseDateSplit;
		    for (int i = 0; i < this.numOfNCA; i++) {
	    		purchaseDateSplit = purchaseDate[i].split("/");
	    		purchaseDay = Integer.parseInt(purchaseDateSplit[0]);
	    		purchaseMonth = Integer.parseInt(purchaseDateSplit[1]);
	    		purchaseYear = Integer.parseInt(purchaseDateSplit[2]);
	    		
		    	this.yearlyDepreciation[i] = new Hashtable<Integer, Integer>();
		    	this.accumulatedDepreciation[i] = new Hashtable<Integer, Integer>();
		    	this.netBookValue[i] = new Hashtable<Integer, Integer>();
		    	
		    	accumulatedDep = 0;
				NBV = 0;
				depreciation = 0;

				for (int j = 0; j < this.yearsToCalcDepFor; j++) {
				    accumulatedDep += depreciation;
					NBV = this.cost[i] - accumulatedDep;
				    depreciation = (NBV * this.depreciationRate);
		    		
				    System.out.println(purchaseYear);
				    System.out.println(this.firstYear);
					if (purchaseYear > this.firstYear + j) {
					    accumulatedDep = 0;
						NBV = 0;
					    depreciation = 0;

					}
						
					this.yearlyDepreciation[i].put(this.firstYear + j, (int)depreciation);
					accumulatedDepreciation[i].put(this.firstYear + j, (int)(accumulatedDep));
					netBookValue[i].put(this.firstYear + j, (int)NBV);
				}
		    }
	    } else {
	    	String[] purchaseDateSplit;
	    	for (int i = 0; i < this.numOfNCA; i++) {
	    		purchaseDateSplit = purchaseDate[i].split("/");
	    		purchaseDay = Integer.parseInt(purchaseDateSplit[0]);
	    		purchaseMonth = Integer.parseInt(purchaseDateSplit[1]);
	    		purchaseYear = Integer.parseInt(purchaseDateSplit[2]);

		    	this.yearlyDepreciation[i] = new Hashtable<Integer, Integer>();
		    	this.accumulatedDepreciation[i] = new Hashtable<Integer, Integer>();
		    	this.netBookValue[i] = new Hashtable<Integer, Integer>();
		    	
		    	accumulatedDep = 0;
				NBV = 0;
				depreciation = 0;
				
				if (purchaseYear > firstYear) {
					depreciation = 0;
				}
				else if (purchaseMonth > this.yearEndingMonth) {
	    			depreciation = 0;
	    		} else if (purchaseMonth <= this.yearEndingMonth){
	    			int monthsElapsed = this.yearEndingMonth - purchaseMonth + 1;
			    	accumulatedDep += depreciation;
					NBV = this.cost[i] - accumulatedDep;
					depreciation = (NBV * this.depreciationRate) * monthsElapsed/12;
	    		}
	    		

			    this.yearlyDepreciation[i].put(this.firstYear, (int)depreciation);		       
				accumulatedDepreciation[i].put(this.firstYear, (int)(accumulatedDep));
				netBookValue[i].put(this.firstYear, (int)NBV);
	        	
		    	for (int j = 1; j < this.yearsToCalcDepFor; j++) {
		    		
				    accumulatedDep += depreciation;
					NBV = this.cost[i] - accumulatedDep;
				    depreciation = (NBV * this.depreciationRate);
		    		
					if (purchaseYear == this.firstYear + j && purchaseMonth <= this.yearEndingMonth) {
						
					    accumulatedDep = 0;
						NBV = 0;
					    depreciation = 0;

						int monthsElapsed = this.yearEndingMonth - purchaseMonth + 1;
				    	accumulatedDep += depreciation;
						NBV = this.cost[i] - accumulatedDep;
						depreciation = (NBV * this.depreciationRate) * monthsElapsed/12;
					}
		    		
					this.yearlyDepreciation[i].put(this.firstYear + j, (int)depreciation);
					accumulatedDepreciation[i].put(this.firstYear + j, (int)(accumulatedDep));
					netBookValue[i].put(this.firstYear + j, (int)NBV);

		    	}
	    	}
	    }
	    findTotalDepreciationForTheYear();
	}
}
