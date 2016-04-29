package com.codility.lessons.timecomplexity;

public class TapeEquilibrium {

	public static void main(String[] args) {
		TapeEquilibrium te = new TapeEquilibrium();
		int size = 10;
		int[] A = new int[size];
		for (int i = 0; i < size; i++) {
			A[i] = (int) (Math.random() * 1000) * (Math.random() > 0.5 ? -1 : 1);
		}
		te.print(A);
		System.out.println(te.solution(A));
		System.out.println("-------------------------");
		int[] B = { 3, 1, 2, 4, 3 };
		te.print(B);
		System.out.println(te.solution(B));
	}

	private int solution(int[] A) {
		int N = A.length;
		int sumIzq = A[0];
		int sumDer = 0;
		for (int i = 1; i < N; i++) {
			sumDer += A[i];
		}
		int min = Math.abs(sumIzq - sumDer);
		for (int p = 1; p < N; p++) {
			int actualMin = Math.abs(sumIzq - sumDer);
			if (actualMin < min) {
				min = actualMin;
			}
			sumIzq += A[p];
			sumDer -= A[p];
		}
		return min;
	}

	public void print(int[] array) {
		for (int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

}
