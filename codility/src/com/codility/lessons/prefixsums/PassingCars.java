package com.codility.lessons.prefixsums;

public class PassingCars {

	/**
	 * 0 represents a car going east 1 represents a car going west (P,Q) is a
	 * pair of cars where 0<=P<Q<N
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PassingCars pc = new PassingCars();
		int[] A = { 0, 1, 0, 1, 1 };
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
		/**
		 * 0 1 0 1 1
		 * 0 1 2 3 4
		 * foreach 0, number of one's to the right
		 * (0,1),(0,3),(0,4),(2,3),(2,4)
		 */
		int zeros = 0;
		int ones = 0;
		for (int i = 0; i < A.length - 1; i++) {
			if(A[i] == 1 && zeros == 0){
				//nothing, only 1's to the left aren't considered
			} else if(A[i] == 0){
				
			}
		}
		return -1;
	}

}
