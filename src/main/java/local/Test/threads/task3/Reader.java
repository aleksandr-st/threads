package local.Test.threads.task3;

import java.util.Random;

public class Reader implements Runnable {
	
	static RWDictionary rwd = null;
	static Random random = new Random();
	
	public Reader(RWDictionary rwd){
		if (Reader.rwd == null){
			Reader.rwd = rwd; 
		}
	}
	
	public void run() {
		while (MainThread.isEnable()){
			String[] keys = rwd.allKeys();
			String key = keys[random.nextInt(keys.length)];
			int result = rwd.get(key);
			System.out.println(" Read result: "+result+" by key: "+key);
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
