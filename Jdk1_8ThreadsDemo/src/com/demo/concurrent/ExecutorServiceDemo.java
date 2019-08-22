package com.demo.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceDemo {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.submit(() -> {
			System.out.println("Task1 is submitted to Thread : " + Thread.currentThread().getName());
			try {
				TimeUnit.SECONDS.sleep(2);
				System.out.println("End of Task1");
			} catch (InterruptedException e) {
				System.out.println("Task1 interrupted...");
			}
			
		});
		executorService.submit(() -> {
			System.out.println("Task2 is submitted to Thread : " + Thread.currentThread().getName());
			try {
				//executorService.shutdown();
				//executorService.shutdownNow();
				/*executorService.shutdown();
				executorService.awaitTermination(5, TimeUnit.SECONDS);*/
				
				TimeUnit.SECONDS.sleep(2);
				System.out.println("End of Task2");
			} catch (InterruptedException e) {
				System.out.println("Task2 interrupted...");
			}
			
		});
		System.out.println("End of main");

		System.out.println("Excecutor is not terminated automatically...");
	}

}
