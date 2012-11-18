import java.io.PrintStream;
import java.util.Scanner;

import ui.UserInterfaceFactory;
import ui.UIAuxiliaryMethods;
import ui.BarChartUserInterface;

public class Statistics {
	// Name			: Sebastian Osterlund
	// Assignment	: Statistics
	// Date			: 12.11.2012
	static final int NUMBER_OF_BARS = 20;
	BarChartUserInterface barChart;
	PrintStream out = System.out;
	Statistics() {
		UIAuxiliaryMethods.askUserForInput();
		barChart = UserInterfaceFactory.getBarChartUI(NUMBER_OF_BARS);
		barChart.setShowValues(true);
	}
	
	void initializeBar(){
		double percentageRange = 100.0/NUMBER_OF_BARS;
		for (int i = 0; i < NUMBER_OF_BARS; i++) {
			String name = String.format("%.0f%%", i*percentageRange);
			barChart.nameBar(i, name);
		}
	}
	
	void addEntryToChart(int percentage) {
		
		int bar = (percentage*NUMBER_OF_BARS)/100;
		
		
		if (bar >= NUMBER_OF_BARS){
			// Last interval is 1 percent larger than the others [90-100]
			out.printf("New item: %d, %d \n", bar, percentage);
			barChart.raiseBar(NUMBER_OF_BARS-1);
		} else {
			out.printf("New item: %d, %d \n", bar, percentage);
			barChart.raiseBar(bar);
		}
	}
	
	void processEntry(Scanner input){
		input.useDelimiter("\t");
		input.next(); // Remove name
		String entryData = input.next();
		
		Scanner dataScanner = new Scanner(entryData);
		int thinkingTime = dataScanner.nextInt();
		int percentageConquered = dataScanner.nextInt();
		addEntryToChart(percentageConquered);
		//out.printf("Test2: %d \n", thinkingTime);
		
	}
	
	void processStudy(Scanner input){
		input.next(); // Remove name of study	
		input.nextLine(); // Remove white line
		while (input.hasNext()) {
			
			Scanner entryData = new Scanner(input.nextLine());
			
			processEntry(entryData);
		}
	}
	
	void processYear(Scanner input){
		
		input.nextInt(); // Remove year
		input.useDelimiter("=");	
		while (input.hasNext()) {
			
			Scanner studyData = new Scanner(input.next());
			processStudy(studyData);
		}
	}
	
	void putDataInChart() {
		Scanner in = new Scanner(System.in);
		in.useDelimiter("-");
		
		while (in.hasNext()) {
			Scanner yearData = new Scanner(in.next());
			processYear(yearData);
		}
	}
	
	void drawChart(){
		initializeBar();
		putDataInChart();
		barChart.showChanges();
	}
	
	void start() {
		drawChart();
	}
	public static void main(String[] args) {
		new Statistics().start();

	}

}
