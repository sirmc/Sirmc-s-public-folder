import java.io.PrintStream;
import java.util.Scanner;


public class VATCalculator {

	// Name			: Sebastian …sterlund
	// Assignment	: VAT
	// Date			: 3.11.2012
	PrintStream out;
	static final double VAT = 0.21; 
	double priceIncludingVAT;
	double priceExcludingVAT;
	
	VATCalculator() {
		
		out = new PrintStream(System.out);
		
	}
	
	void start(){
		Scanner in = new Scanner(System.in);
		out.printf("Input price of item (including VAT): ");
		priceIncludingVAT = in.nextDouble();
		
		priceExcludingVAT = priceIncludingVAT/(1+VAT);
		
		out.printf("The price of the item withouth VAT is: %.2f", priceExcludingVAT);
		
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new VATCalculator().start();
	}

}
