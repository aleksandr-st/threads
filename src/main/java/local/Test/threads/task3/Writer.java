package local.Test.threads.task3;

import java.util.Random;

public class Writer implements Runnable {

	static RWDictionary rwd = null;
	static Random random = new Random();
	
	public Writer(RWDictionary rwd){
		if (Writer.rwd == null){
			Writer.rwd = rwd; 
		}
	}
	
	public void run() {
		int counter = 1;
		while (MainThread.isEnable()){
			String[] keys = rwd.allKeys();
			String key = keys[random.nextInt(keys.length)];
			int result = rwd.put(key,counter++);
			System.out.println(" Write result: "+result+" by key: "+key);
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
