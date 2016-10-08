package Synchronized;

public class Synchronized {
	
	private int count = 0;
	
	public synchronized void increment() {
		count++;
	}
	
	
	public static void main(String args[]) {
		
		Synchronized s = new Synchronized();
		s.doWork();
	}
	
	public void doWork() {
		
		Thread t1 = new Thread(new Runnable() {
			
			public void run() {
				
				for (int i = 0; i < 10000; i++) {
					/*we can use count = count + 1 here,
					 * but its will give you weird values,
					 * because count = count + 1 involves 3 operations : 
					 * fetch, execute and write the value of count.
					 * So, while, t1 and t2 are both accessing the same variable,
					 * t1 might be updating the value while t2 is fetching, and vice versa.
					 * this is race condition, and to avoid it, we need to have a 
					 * mutex mechanism, which is provided by the synchronized keyword. 
					 */
					increment();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10000; i++) {
					increment();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		/*
		 * if we don't do join, the sop line is executed even before the threads start the loop
		 * to make the main thread wait for t1 and t2 to execute, we use join()
		 */
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(count);
	}
}
