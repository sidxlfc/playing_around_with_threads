package Synchronized;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SynchronizingDifferentMethods {
	
	private Random random = new Random();
	
	private List<Integer> l1 = new ArrayList<Integer>();
	private List<Integer> l2 = new ArrayList<Integer>();
	
	/*
	 * we can also create separate lock objects :
	 * Object lock1 = new Object();
	 * Object lock2 = new Object();
	 * In this way, data objects and lock objects are separated.
	 */
	
	/*
	 * suppose our class does work in 2 stages
	 */
	
	/*
	 * we can make the methods stage1() and stage2() synchronized, 
	 * but that will create a lock on the class Object, and one thread cannot start executing unless the other has finished.
	 * this will take double the execution time.
	 * to prevent this, we create synchronized blocks inside the method. 
	 * in this way, 2 threads can run simultaneously on 2 different methods,
	 * but if a thread wants to execute the same method on which a lock has been placed,
	 * it will have to wait until the lock is removed.
	 */
	public void stage1() {
		
		synchronized(l1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			l1.add(random.nextInt(100));
		}
	}
	
	public void stage2() {
		
		synchronized(l2) {
		
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			l2.add(random.nextInt(100));
		}
	}
	
	public void process() {
		
		for (int i = 0; i<1000; i++){
			stage1();
			stage2();
		}
	}
	
	public void main() {
		
		System.out.println("Starting...");
		
		long start = System.currentTimeMillis();
		
			Thread t1 = new Thread(new Runnable() {

				@Override
				public void run() {
					
					process();
				}
				
			});
			
			Thread t2 = new Thread(new Runnable() {

				@Override
				public void run() {
					process();
				}
				
			});
			
			t1.start();
			t2.start();
			
			try {
				t1.join();
				t2.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		long end = System.currentTimeMillis();
		
		System.out.println("Time taken : " + (end - start));
		
		System.out.println("List1 : " + l1.size());
		System.out.println("List2 : " + l2.size());
	}
}
