package com.demo.parallel;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinWorker {

	public static void main(String[] args) {

		int n = 20;
		long t1 = System.currentTimeMillis();
		FibonacciProblem bigProblem = new FibonacciProblem(n);

		long result = bigProblem.solve();

		System.out.println("Computing Fib number: " + n);
		System.out.println("Computed Result: " + result);
		long et1=(System.currentTimeMillis() - t1);
		
		// Check the number of available processors
		int processors = Runtime.getRuntime().availableProcessors();
		System.out.println("No of processors: " + processors);

		t1 = System.currentTimeMillis();

		FibonacciTask task = new FibonacciTask(bigProblem);
		ForkJoinPool pool = new ForkJoinPool(processors);
		pool.invoke(task);

		result = task.result;
		System.out.println("Computed Result: " + result);
		long et2=(System.currentTimeMillis() - t1);
		System.out.println("Elapsed Time no parallel processing : " + et1 + " ms");
		System.out.println("Elapsed Time parallel processing : " + et2 + " ms");

	}

}