package depreciationCalculator;

import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class DepreciationCalculator {
	
	static int cost;
	static int yearsToCalcDepFor;
	static int firstYear;
	static String purchaseDate;
	static String yearEnding;
	static Scanner scanner = new Scanner(System.in);  
	static StraightLine straightLine;

	public static void main(String[] args) {

		getUserInput();
		int method = chooseDepMethod();
		
		if (method == 1) {
			straightLine = new StraightLine(cost, purchaseDate, yearEnding, yearsToCalcDepFor, firstYear);
			straightLine.calculate();
		}

		JFrame jFrame = new JFrame();
		JTable table = new JTable(new DefaultTableModel(new Object[]{"-", "Non-current asset"}, 0));
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[]{"Date of purchase", String.valueOf(purchaseDate)});
		model.addRow(new Object[]{"Cost", String.valueOf(cost)});
		for (int i = 0; i < yearsToCalcDepFor; i++) {
			model.addRow(new Object[] {"Depreciation for: " + String.valueOf(firstYear + i), 
					String.valueOf(straightLine.yearlyDepreciation.get(firstYear + i))});
		}

		table.setBounds(30, 40, 230, 280);

		JScrollPane jScrollPane = new JScrollPane(table);
		jFrame.add(jScrollPane);
		jFrame.setSize(500, 500);
		jFrame.setVisible(true);

	}
	
	public static void getUserInput() {
	    
		while (true) {
	        System.out.print("Enter the date the non-current asset was purchased (day/month/year separated by '/'): ");
	        purchaseDate = scanner.nextLine();
	        if (purchaseDate.length() != 0) {
	        	break;	        	
	        }
		}
		while (true) {
	        System.out.print("Enter the end of the accounting year (day/month separated by '/'): ");
	        yearEnding = scanner.nextLine();
	        if (yearEnding.length() != 0) {
	        	break;	        	
	        }
		}
        
	    System.out.println("Enter cost of your non-current asset: ");
	    cost = scanner.nextInt();
	    
	    while (true) {
	    	System.out.println("Enter first year to calculate depreciation for: ");
	    	firstYear = scanner.nextInt();
	    	
	    	if (firstYear > 0) {
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
