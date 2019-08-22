package com.demo.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableChainingDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		CompletableFuture<Void> cf = CompletableFuture.supplyAsync(() -> {
			System.out.println("supplyAsync Thread : " + Thread.currentThread().getName());
			return 5;
		}, executor).thenApplyAsync(x -> {
			System.out.println("thenApply Thread : " + Thread.currentThread().getName());
			return x *3;
		}, executor).thenAcceptAsync(x -> {
			System.out.println("thenAccept Thread : " + Thread.currentThread().getName());
			System.out.println("Value of x=" + x);
		}, executor).thenRunAsync(() -> {
			System.out.println("thenRun Thread : " + Thread.currentThread().getName());
			System.out.println("End");
		}, executor).exceptionally(e -> {
			System.out.println(e.getCause()); // returns a throwable back
			return null;
		});
		cf.join();
		
		CompletableFuture<String> future1  
		  = CompletableFuture.supplyAsync(() -> "Hello");
		CompletableFuture<String> future2  
		  = CompletableFuture.supplyAsync(() -> "Beautiful");
		CompletableFuture<String> future3  
		  = CompletableFuture.supplyAsync(() -> "World");
		 
		String combined = Stream.of(future1, future2, future3)
				  .map(CompletableFuture::join)
				  .collect(Collectors.joining(" "));
		System.out.println("Combined : "+combined);
		executor.shutdown();
	}

}
