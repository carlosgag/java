package com.codility.lessons.countingelements;

public class MaxCounters {

	public static void main(String[] args) {
		MaxCounters mc = new MaxCounters();
		int[] A = { 3, 4, 4, 6, 1, 4, 4 };
		int N = 5;
		mc.print(A);
		mc.print(mc.solution(N, A));
	}

	public void print(int[] A) {
		for (int i : A) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	public int[] solution(int N, int[] A) {
		int[] counters = new int[N];
		int maxCounter = 0;
		for (int i : A) {
			if (i == (N + 1)) {
				for (int j = 0; j < counters.length; j++) {
					counters[j] = maxCounter;
				}
			} else {
				counters[i - 1] += 1;
				if (counters[i - 1] > maxCounter) {
					maxCounter = counters[i - 1];
				}
			}
		}
		return counters;
	}
}
