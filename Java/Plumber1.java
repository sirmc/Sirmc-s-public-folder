import java.io.PrintStream;
import java.util.Scanner;

public class Plumber1 {

	// Name			: Sebastian Österlund
	// Assignment	: Plumber1
	// Date			: 3.11.2012
	static final double CALL_OUT_COST = 16.00;
	
	PrintStream out;
	private double hourlyWage;
	private int hoursWorked;
	private double totalCost;
	
	
	Plumber1(){
		out = new PrintStream(System.out);
		
	}
	
	void start() {
		Scanner in = new Scanner(System.in);
		
		out.printf("Enter the hourly wage: ");
		hourlyWage = in.nextDouble();
		
		out.printf("Enter the number of billable hours: ");
		hoursWorked = in.nextInt();
		
		totalCost = CALL_OUT_COST + hourlyWage*hoursWorked;
		out.printf("The total cost of the repair is: %.2f€", totalCost);
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Plumber1().start();

	}

}
