import java.io.PrintStream;
import java.util.Scanner;

public class Manny {

	// Name			: Sebastian …sterlund
	// Assignment	: Manny
	// Date			: 3.11.2012
	PrintStream out;
	private double donation;
	
	Manny() {
		out = new PrintStream(System.out);
	}
	double requestDonation(){
		double donation;
		Scanner in = new Scanner(System.in);
		
		out.printf("Enter amount you want to donate: ");
		donation = in.nextDouble();
		if (donation < 50){
			donation = requestDonation(); // Recursive call
		}
		return donation;
		
	}
	void start() {
		
		donation = requestDonation();
		out.printf("Thank you for your donation of %.2f euros", donation);
		
	}
	public static void main(String[] args) {
		
		new Manny().start();
	}

}
