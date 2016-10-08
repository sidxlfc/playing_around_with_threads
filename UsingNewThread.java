
class Runner extends Thread {
	
	@Override
	public void run() {
		//do something here
	}
	
	
}

public class UsingNewThread {
	
	public static void main(String args[]) {
		
		Runner r1 = new Runner();
		
		//doing r1.run() will work,
		// but it will run in the main thread of the application
		// this kills the entire purpose of multithreading
		r1.start();
		
		//r1 and r2 will run in separate threads
		// **concurrency, bitches**
		Runner r2 = new Runner();
		r2.start();
	}
}