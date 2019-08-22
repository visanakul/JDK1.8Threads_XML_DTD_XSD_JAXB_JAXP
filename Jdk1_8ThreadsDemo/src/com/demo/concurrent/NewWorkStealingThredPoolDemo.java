package com.demo.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NewWorkStealingThredPoolDemo {

	public static void main(String[] args) {
		Runtime rt = Runtime.getRuntime();
		System.out.println("Total cores 		: " + rt.availableProcessors());
		System.out.println("Max memory 		: " + rt.maxMemory());
		System.out.println("Free memory 		: " + rt.freeMemory());
		System.out.println("Available memory 	: " + rt.totalMemory());

		Callable<String> callable = () -> "hello";
		try {
			System.out.println(callable.call());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Creating ExecutorService");
		ExecutorService executorService = Executors.newWorkStealingPool();
		List<Callable<String>> callableList = new ArrayList<>(5);
		for (int n = 1; n <= 5; n++) {
			final int x = n;
			System.out.println("n=" + n);
			callableList.add(() -> {
				StringBuilder sb = new StringBuilder();
				for (int i = 1; i <= 10; i++) {
					sb.append(String.format("%5d", x * i));
				}
				return sb.toString();
			});
		}
		System.out.println("Calling invokeall");
		try {
			List<Future<String>> futures = executorService.invokeAll(callableList);

			futures.stream().map(future -> {
				try {
					String res = future.get();
					return res;
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				return null;
			}).forEach(System.out::println);
			executorService.shutdownNow();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("End of main");
	}
	

}
