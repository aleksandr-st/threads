package local.Test.threads.task1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainThread {

	
	public static void main(String[] args) {
		final int NOT_DEFINE = -1000;
		int avgTemperatureSum = NOT_DEFINE;
		int t1 = NOT_DEFINE;
		int t2 = NOT_DEFINE;
		int hadResults = 0;
		double avgTemperature = 0.;
		
		ExecutorService es = Executors.newCachedThreadPool();
		Future<Integer> tf1 = es.submit(new TemperatureParseYandex("34504"));
		Future<Integer> tf2 = es.submit(new TemperatureParseWeatherCoUa("16"));
		
		System.out.println("Wait for to 5 seconds");
		try {
			t1 = tf1.get(5, TimeUnit.SECONDS);
			t2 = tf2.get(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (t1 > NOT_DEFINE){
			avgTemperatureSum = t1;
			hadResults++;
			System.out.println("Current temperature on yandex: "+t1+" C");
		}else{
			System.out.println("Error by getting current temperature on yandex!");
		}
		if (t2 > NOT_DEFINE){
			if (avgTemperatureSum == NOT_DEFINE){
				avgTemperatureSum = t2;
			}else{
				avgTemperatureSum += t2;
 			}
			hadResults++;
			System.out.println("Current temperature on weather.co.ua: "+t2+" C");
		}else{
			System.out.println("Error by getting current temperature on weather.co.ua!");
		}
		if (hadResults != 0){
			avgTemperature = avgTemperatureSum / (double)hadResults;
			System.out.println("Current avg. temperature: "+avgTemperature+" C");
		}else{
			System.out.println("Error by getting current avg. temperature!");
		}
		System.out.println("Send signal for shutdown executor");
		es.shutdown();
	}

}
