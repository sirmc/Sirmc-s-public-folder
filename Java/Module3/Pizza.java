import java.io.PrintStream;


public class Pizza {

	/**
	 * @param args
	 */
	static final int MARIO_AVAILABLE_TOPPINGS = 10;
	static final int LUIGI_AVAILABLE_TOPPINGS = 9;
	static final int MARIO_TOPPINGS_PER_PIZZA = 3;
	static final int LUIGI_TOPPINGS_PER_PIZZA = 4;
	
	PrintStream out = System.out;
	int faculty(int n) {
		for (int i = n-1; i > 0; i--) {
			n *= i;
		}
		
		return n;
	}
	int numberOfPossibilities(int k, int n) {

		return faculty(n)/(faculty(k) * faculty(n-k));
		
	}
	void printWinner() {
		int marioPossibilities = numberOfPossibilities(MARIO_TOPPINGS_PER_PIZZA, MARIO_AVAILABLE_TOPPINGS);
		int luigiPossibilities = numberOfPossibilities(LUIGI_TOPPINGS_PER_PIZZA, LUIGI_AVAILABLE_TOPPINGS);
		out.printf("Mario has %d possible combinations \n", marioPossibilities);
		out.printf("Luigi has %d possible combinations \n", luigiPossibilities);
		if (marioPossibilities > luigiPossibilities) {
			out.printf("The winner is Mario");
		} else if (luigiPossibilities > marioPossibilities) {
			out.printf("The winner is Luigi");
		} else {
			out.printf("Mario and Luigi have an equal number of combinations");
		}
	}
	void start() {
		printWinner();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Pizza().start();
	}

}
