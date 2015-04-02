package local.Test.threads;

public class HungryStudent implements Runnable {

	public static Food myFood = new Food();

	public void run() {

		while (true) {
			synchronized (myFood) {
				try {
					myFood.notify();
					HungryStudent.myFood.wait();
					if (myFood.isPrepared == false) {
						System.out.println("Студент не поел");
					} else {
						myFood.isPrepared = false;
						System.out.println("Студент поел");
					}
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
