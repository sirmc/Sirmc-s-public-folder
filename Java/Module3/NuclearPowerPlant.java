import java.io.PrintStream;


public class NuclearPowerPlant {

	/**
	 * @param args
	 */
	static final int TIMES_TO_PRINT_OUT_WARNING = 3;
	PrintStream out;
	NuclearPowerPlant() {
		out = new PrintStream(System.out);
	}
	void start() {
		printWarning(TIMES_TO_PRINT_OUT_WARNING);
	}
	
	void printWarning(int timesToPrint) {
		
		
		for (int i = 0; i < timesToPrint; i++){
			
		out.printf("NUCLEAR CORE UNSTABLE!!! \n");
		out.printf("Quarantine is in effect. \n");
		out.printf("Surrounding hamlets will be evacuated. \n");
		out.printf("Anti-radiationsuits and iodine pills are mandatory. \n");
	
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new NuclearPowerPlant().start();
	}

}
