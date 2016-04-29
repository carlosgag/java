package com.codility.lessons.countingelements;

public class MissingInteger {

	public static void main(String[] args) {
		MissingInteger mi = new MissingInteger();
		int[] A = { 1, 3, 5, 4, 1, 2 };
		mi.print(A);
		System.out.println(mi.solution(A));
	}

	public void print(int[] A) {
		for (int i : A) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	public int solution(int[] A) {
		int size = A.length;
		boolean[] ocurrences = new boolean[size + 2];
		ocurrences[0] = true;
		for (int i = 0; i < size; i++) {
			if (0 < A[i] && A[i] <= size + 1) {
				ocurrences[A[i]] = true;
			}
		}
		int missing = 1;
		for (int i = 1; i < ocurrences.length; i++) {
			if (ocurrences[i] == false) {
				missing = i;
				break;
			}
		}
		return missing;
	}
}
