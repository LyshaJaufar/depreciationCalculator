package depreciationCalculator;

import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class DepreciationCalculator {
	
	static int numOfNCA;
	static int[] cost;
	static int yearsToCalcDepFor;
	static int firstYear;
	static String[] purchaseDate;
	static String yearEnding;
	static Scanner scanner = new Scanner(System.in);  
	static StraightLine straightLine;

	public static void main(String[] args) {

		getUserInput();
		int method = chooseDepMethod();
		
		if (method == 1) {
			straightLine = new StraightLine(cost, purchaseDate, yearEnding, yearsToCalcDepFor, firstYear, numOfNCA);
			straightLine.calculate();
		}
		
		JFrame jFrame = new JFrame();
		JTable table = new JTable(new DefaultTableModel(new Object[]{"-", "Depreciation"}, 0));

		DefaultTableModel model = (DefaultTableModel) table.getModel();

		model.addRow(new Object[]{"End of the accounting year ", yearEnding});
		for (int i = 0; i < numOfNCA; i++) {

			model.addRow(new Object[]{"Non-current asset " + (i + 1)});
			model.addRow(new Object[]{"Cost", cost[i]});
			
			model.addRow(new Object[]{"Date of purchase", purchaseDate[i]});
			for (int j = 0; j < yearsToCalcDepFor; j++) {
				model.addRow(new Object[] {"Depreciation for " + String.valueOf(firstYear + j), 
						String.valueOf(straightLine.yearlyDepreciation[i].get(firstYear + j))});
			}
		}
		
		for (int i = 0; i < yearsToCalcDepFor; i++) {
			model.addRow(new Object[] {"Total depreciation for the year " + (firstYear + i) + ":",
					straightLine.totalDepreciationForTheYear.get(firstYear + i)});
		}
		

		table.setBounds(30, 40, 230, 280);

		JScrollPane jScrollPane = new JScrollPane(table);
		jFrame.add(jScrollPane);
		jFrame.setSize(500, 500);
		jFrame.setVisible(true);

	}
	
	public static void getUserInput() {
		
		// prompt user for number of assets to calc dep for
	    System.out.println("Enter the number of non-current assets you want to calculate depreciation for: ");
	    numOfNCA = scanner.nextInt();
	    
	    cost = new int[numOfNCA];
	    purchaseDate = new String[numOfNCA];
	    
	    for (int i = 0; i < numOfNCA; i++) {
		    System.out.println("Enter cost of your non-current asset " + (i + 1) + ":");
		    cost[i] = scanner.nextInt();
	    }
	    
	    for (int i = 0; i < numOfNCA; i++) {
	    	while (true) {

		        System.out.print("Enter the date the non-current asset " +(i + 1) + " was purchased (day/month/year separated by '/'): ");
		        purchaseDate[i] = scanner.next();
		        if (purchaseDate[i].length() != 0) {
		        	break;	        	
		        }
	    	}
	    }
	
		while (true) {
	        System.out.print("Enter the end of the accounting year (day/month separated by '/'): ");
	        yearEnding = scanner.next();
	        if (yearEnding.length() != 0) {
	        	break;	        	
	        }
		}
        	    
	    while (true) {
	    	System.out.println("Enter first year to calculate depreciation for: ");
	    	firstYear = scanner.nextInt();
	    	
	    	if (firstYear > 1000) {
	    		break;
	    	}
	    }
	    
	    while (true) {
	    	System.out.println("Enter the number of years to calculate depreciation for (including current year): ");
		    yearsToCalcDepFor = scanner.nextInt();
		    
		    if (yearsToCalcDepFor > 0) {
		    	break;
		    }
	    }
	    
	}
	
	public static int chooseDepMethod() {
		int method;
		while (true) {
		    System.out.println("Enter the corresponding number for the desired method: ");
		    System.out.println(" 1.  Straight Line \n 2.  Reducing Balance");
		    
		    method = scanner.nextInt();
		    if (method == 1 || method == 2) {
		    	break;
		    }
		}
	   return method;
	}

}
