package com.codility.lessons.countingelements;

public class PermCheck {

	public static void main(String[] args) {
		PermCheck pc = new PermCheck();
		int[] A = { 3 };
		pc.print(A);
		System.out.println(pc.solution(A));
	}

	public void print(int[] A) {
		for (int i : A) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	public int solution(int[] A) {
		// repeated numbers in A are allowed
		int isPermutation = 1;
		int[] elems = new int[A.length + 1];
		elems[0] = 1;
		for (int i = 0; i < A.length; i++) {
			if (A[i] < elems.length) {
				if (elems[A[i]] != 0) {
					// is already in the array => the permutation isn't complete
					isPermutation = 0;
					break;
				} else {
					elems[A[i]] = 1;
				}
			} else {
				// there are less elements than required for the sequence values indicated
				isPermutation = 0;
				break;
			}
		}
		return isPermutation;
	}
}
