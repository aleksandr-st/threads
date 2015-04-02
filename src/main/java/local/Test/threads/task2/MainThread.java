package local.Test.threads.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainThread {

	public static ExecutorService executorService = Executors.newFixedThreadPool(1);
	static ExecutorService produsersPool = Executors.newCachedThreadPool();
	
	public static void main(String[] args) {
		
		final int PRODUSERS_NUMBER = 3;
		List<Produser> produsers = new ArrayList<Produser>();
		
		for (int i=0;i<PRODUSERS_NUMBER;i++){
			produsers.add(new Produser(""+i));
			produsersPool.execute(produsers.get(i));
		}
		System.out.println("Sleep for 5 seconds");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Send signal for shutdown produsers and thread executor");
		produsersPool.shutdown();
		executorService.shutdown();
	}

}
