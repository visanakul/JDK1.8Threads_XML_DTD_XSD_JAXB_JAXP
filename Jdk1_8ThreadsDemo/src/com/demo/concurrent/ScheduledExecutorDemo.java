package com.demo.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorDemo {

	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		try {
			Runnable task = () -> System.out.println("Scheduling at : " + System.nanoTime());
			System.out.println("Please wait");
			ScheduledFuture<?> future = service.schedule(task, 5, TimeUnit.SECONDS);

			long delay = future.getDelay(TimeUnit.MILLISECONDS);
			System.out.println(String.format("Time delay %sms", delay));
			TimeUnit.MILLISECONDS.sleep(2000);
			delay = future.getDelay(TimeUnit.MILLISECONDS);
			System.out.println(String.format("Time delay %sms", delay));
			
			
			Runnable task2=()->System.out.println("Scheduled at : "+System.nanoTime());
			///service.scheduleAtFixedRate(task2, 0, 2, TimeUnit.SECONDS);
			service.scheduleWithFixedDelay(task2, 1, 2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			//service.shutdown();
		}
	}

}
