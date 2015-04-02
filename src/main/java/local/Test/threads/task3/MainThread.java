package local.Test.threads.task3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainThread {
	
	static ExecutorService es = Executors.newCachedThreadPool();
	private static boolean enable = true;
	private final static int MAX_THREADS = 10;
	
	public static void main(String[] args) {
		RWDictionary rwd = new RWDictionary();
		rwd.put("first", 1);
		rwd.put("second", 2);
		rwd.put("third", 3);
		
		for(int i = 0; i < MAX_THREADS; i++){
			if ((i == 4) || (i == 8)){
				es.execute(new Thread(new Writer(rwd)));
			} else {
				es.execute(new Thread(new Reader(rwd)));
			}	
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MainThread.setEnable(false);
		es.shutdown();
	}

	public static boolean isEnable() {
		return enable;
	}

	public static void setEnable(boolean enable) {
		MainThread.enable = enable;
	}

}
