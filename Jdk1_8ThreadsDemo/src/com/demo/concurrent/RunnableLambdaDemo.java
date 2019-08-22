package com.demo.concurrent;

import java.util.concurrent.TimeUnit;

public class RunnableLambdaDemo {

	public static void main(String[] args) {
		// Functional interface- no arguments
		MyFunctionalInterface functionalInterface = () -> {
			System.out.println("Inside show actually...");
		};
		functionalInterface.show();
		// Functional interface with parameters
		Arithmetic arithmetic = (a, b) -> {
			return a + b;
		};
		int res = arithmetic.add(10, 20);
		System.out.println("Result=" + res);
		
		//Jdk functional interface
		Runnable task = () -> {
			System.out.println("Inside lambda");
			System.out.println("Thread is : " + Thread.currentThread().getName());
			System.out.println("Sleep using TimeUnit...");
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			System.out.println("Sleep end...");
		};
		//Calling run without thread--- Main thread will invoke run()
		task.run();

		//Using thread to call run()
		Thread taskThread = new Thread(task);
		taskThread.start();

		try {
			taskThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("End...");
	}

	@FunctionalInterface
	public interface MyFunctionalInterface {
		void show();
	}

	@FunctionalInterface
	public interface Arithmetic {
		int add(int x, int y);
	}

}
