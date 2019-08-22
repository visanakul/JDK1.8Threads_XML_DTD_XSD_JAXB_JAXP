package com.demo.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CallableFutureDemo {

	public static void main(String[] args) {
		Callable<Integer> callable1 = () -> 123;
		// Calling call() without Thread
		try {
			Integer res = callable1.call();
			System.out.println("Result : " + res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End of main");

		Callable<Integer> callable2 = () -> {
			TimeUnit.SECONDS.sleep(5);
			return 123;
		};
		// Using executorService submit to call callable call() and get Future for
		// result
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		Future<Integer> resultFuture = executorService.submit(callable2);
		System.out.println("Task done ? " + resultFuture.isDone());
		try {
			Integer result = resultFuture.get();
			System.out.println("Task done ? " + resultFuture.isDone());
			System.out.println("Result : " + result);

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		// Using executorService submit to call callable call() and get Future for
		// result with timeout
		resultFuture = executorService.submit(callable2);
		System.out.println("Task done ? " + resultFuture.isDone());
		try {
			Integer result = resultFuture.get(2, TimeUnit.SECONDS);
			System.out.println("Task done ? " + resultFuture.isDone());
			System.out.println("Result : " + result);

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			System.out.println("Task not completed within 2 seconds");
		}
		executorService.shutdownNow();
	}
}
