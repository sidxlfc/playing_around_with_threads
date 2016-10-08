package UsingRunnable;

class Runner implements Runnable {
	
	@Override
	public void run() {
		//do something here
	}	
}

public class UsingRunnable {
	
	public static void main(String args[]) {
		
		/* this does the same work as 
		 * UsingNewThread does
		 * 
		 */
		Thread t1 = new Thread(new Runner());
		Thread t2 = new Thread(new Runner());
		t1.start();
		t2.start();
	}
}
