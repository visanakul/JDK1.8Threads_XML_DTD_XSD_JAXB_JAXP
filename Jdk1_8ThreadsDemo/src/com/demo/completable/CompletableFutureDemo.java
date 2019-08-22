package com.demo.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo {

	public static void main(String[] args) {
		ExecutorService executor1 = Executors.newFixedThreadPool(2);
		// Without interlink
		CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> "Hello world!!!", executor1);
		completableFuture1.thenAccept(System.out::println);

		CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(5);
				return "Welcome to CompletableFuture!!!";
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

		}, executor1);
		System.out.println("Waiting for result...");
		completableFuture2.thenAccept(System.out::println);
		completableFuture1.join();
		completableFuture2.join();
		executor1.shutdown();

		ExecutorService executor2 = Executors.newFixedThreadPool(2);
		// With interlink
		System.out.println("Then Run");
		CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(() -> "Hello world!!!", executor2);
		completableFuture3.thenAccept(System.out::println).thenRun(() -> {
			try {
				TimeUnit.SECONDS.sleep(5);
				System.out.println("Welcome to CompletableFuture!!!");
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

		});

		executor2.shutdown();
	}

}
