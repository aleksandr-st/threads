package local.Test.threads.task2;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

public class Produser implements Runnable {
	static ExecutorService es;
	static Random random = new Random();

	private int threadsStarted = 0;
	private boolean enable;
	private String name;
	
	public Produser(String name){
		this.name = name;
		enable = true;
	}
	
	public void run() {
		while (enable) {
			if (random.nextInt(9)>4){
				try {
					//make new thread
					final int number = threadsStarted;
					MainThread.executorService.submit(new Runnable() {
						public void run() {
							try {
								Thread.sleep(random.nextInt(1000));
								System.out.println(" Thread "+number+" from producer "+name
										+" was executed");
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					});
					threadsStarted++;
				} catch (RejectedExecutionException ree) {
					enable = false;
				}
			}else{
				//sleep for random time
				try {
					Thread.sleep(random.nextInt(1500));
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
		};
		System.out.println("Producer "+name+" Start: "+threadsStarted);

	}

}
