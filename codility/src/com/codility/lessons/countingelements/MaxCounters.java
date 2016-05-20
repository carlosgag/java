package com.codility.lessons.countingelements;

public class MaxCounters {

	public static void main(String[] args) {
		MaxCounters mc = new MaxCounters();
		int[] A = { 3, 4, 4, 6, 1, 4, 4 };
		int N = 5;
		mc.print(A);
		mc.print(mc.solution(N, A));
		mc.print(mc.solution2(N, A));
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

	public int[] solution2(int N, int[] A) {
		int[] counters = new int[N];
		int maxCounter = 0;
		int minCounter = 0;
		boolean[] minFlags = new boolean[N];
		for (int i = 0; i < A.length; i++) {
			if (A[i] == (N + 1)) {
				minCounter = maxCounter;
				// reset fromMin to false
				minFlags = new boolean[N];
			} else {
				if (minFlags[A[i] - 1] == false) {
					if (minCounter == 0) {
						// while we don't have an max counter operation
						counters[A[i] - 1] += 1;
					} else {
						// there is a stored min because of an maxCounter op
						counters[A[i] - 1] = 1 + minCounter;
					}
				} else {
					// was already incremented from min, so it has to increment
					// from
					// the previous value
					counters[A[i] - 1] += 1;
				}
				// is updated minFlags for the actual index
				minFlags[A[i] - 1] = true;
				if (counters[A[i] - 1] > maxCounter) {
					// is updated MaxCounter
					maxCounter = counters[A[i] - 1];
				}
			}
		}
		for (int i = 0; i < counters.length; i++) {
			if (counters[i] < minCounter) {
				counters[i] = minCounter;
			}
		}
		return counters;
	}
}
