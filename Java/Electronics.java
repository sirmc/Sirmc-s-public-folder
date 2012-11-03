import java.io.PrintStream;
import java.util.Scanner;


public class Electronics {

	// Name			: Sebastian …sterlund
	// Assignment	: Electronics
	// Date			: 3.11.2012
	static final double DISCOUNT_RATE = 0.15;
	PrintStream out;
	private double article1, article2, article3, totalDiscount, totalPrice;
	
	Electronics() {
		
		out = new PrintStream(System.out);
	}
	void start(){
		Scanner in = new Scanner(System.in);
		
		out.printf("Input price of first article: ");
		article1 = in.nextDouble();
		
		out.printf("Input price of second article: ");
		article2 = in.nextDouble();
		
		out.printf("Input price of third article: ");
		article3 = in.nextDouble();
		
		if (article1 > article2 && article1 > article3){
			// article1 most expensive
			totalDiscount = DISCOUNT_RATE*article1;
			
		} else if (article2 > article1 && article2 > article3){
			// article2 most expensive
			totalDiscount = DISCOUNT_RATE*article2;
			
		} else if (article3 > article1 && article3 > article2){
			// article3 most expensive
			totalDiscount = DISCOUNT_RATE*article3;
			
		} 
		
		totalPrice = article1 + article2+ article3 - totalDiscount;
		
		out.printf("Discount: %.2f \n", totalDiscount);
		out.printf("Total: %.2f \n", totalPrice);
		
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Electronics().start();
	}

}
