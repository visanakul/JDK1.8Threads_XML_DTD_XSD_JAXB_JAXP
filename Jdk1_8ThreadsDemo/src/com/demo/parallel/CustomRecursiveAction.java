package com.demo.parallel;

import java.util.concurrent.RecursiveAction;

public class CustomRecursiveAction extends RecursiveAction {
	final int THRESHOLD = 2;
	double[] numbers;
	int indexStart, indexLast;

	CustomRecursiveAction(double[] n, int s, int l) {
		System.out.println("Constructor : "+s+" "+l);
		numbers = n;
		indexStart = s;
		indexLast = l;
	}

	@Override
	protected void compute() {
		System.out.println("Push : "+indexStart+" "+indexLast);
		if ((indexLast - indexStart) > THRESHOLD) {
			System.out.println("if");
			for (int i = indexStart; i < indexLast; i++)
				numbers[i] = numbers[i] + Math.random();
		}
		else {
			System.out.println("else");
			invokeAll(new CustomRecursiveAction(numbers, indexStart, (indexStart - indexLast) / 2),
					new CustomRecursiveAction(numbers, (indexStart - indexLast) / 2, indexLast));
		}
		System.out.println("Pop : "+indexStart+" "+indexLast);
	}
}
