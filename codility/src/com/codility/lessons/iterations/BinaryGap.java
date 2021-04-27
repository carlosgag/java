package com.codility.lessons.iterations;

import java.util.Random;

public class BinaryGap {

	public int solution(Integer N) {
		int gap = 0;
		int candidate = gap;
		while(N >= 1) {
			Integer reminder = N % 2;
			N = N / 2;
			if(reminder == 0) {
				candidate++;
			} else {
				if(candidate > gap) {
					gap = candidate;
				}
				candidate = 0;
			}

		}
		return gap;
	}

	public static void main(String[] args) {
		BinaryGap binaryGap = new BinaryGap();
		for (int i = 0; i < 10; i++) {
			Random random = new Random();
			Integer N = random.nextInt(1000000);
			System.out.println("Binary: " + Integer.toBinaryString(N));
			System.out.println("Solution: " + binaryGap.solution(N));
			System.out.println("-------------------------");
		}
	}

}
