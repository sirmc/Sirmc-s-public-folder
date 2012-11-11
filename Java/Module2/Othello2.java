import java.io.PrintStream;
import java.util.Scanner;

public class Othello2 {

	// Name			: Sebastian …sterlund
	// Assignment	: Othello2
	// Date			: 3.11.2012
	PrintStream out;
	private long blackTime, whiteTime, computerTime, playerTime;
	
	Othello2() {
		out = new PrintStream(System.out);
		
	}
	
	void start(){
		Scanner in = new Scanner(System.in);
		
		out.printf("Input time the black player thought: ");
		blackTime = in.nextInt();
		
		out.printf("Input time the white player thought: ");
		whiteTime = in.nextInt();
		
		playerTime = Math.max(blackTime, whiteTime);
		computerTime = Math.min(blackTime, whiteTime);
		long hours, minutes, seconds, playerTimeInSeconds;
		
		playerTimeInSeconds = playerTime / 1000; // Convert milliseconds to seconds
		
		seconds = playerTimeInSeconds % (60); // Seconds is remainder of time/60
		minutes = ((playerTimeInSeconds - seconds) % (60*60))/60; // Minutes is remainder of time/3600
		hours = (playerTimeInSeconds-60*minutes-seconds)/(60*60); 
		
		out.printf("Time the human player has spent thinking: %02d:%02d:%02d", hours, minutes, seconds);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Othello2().start();
	}

}
