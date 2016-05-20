package com.codility.lessons.prefixsums;

public class Examples {

	/*
	 * A of n elements with n between 1 and 100000 and each element between
	 * 0 and 1000 
	 * mushroom picker is in place k and should perform m moves,
	 * with 0 <= k,m <= n 
	 * 2 3 7 5 1 3 9 
	 * 0 1 2 3 4 5 6
	 * 
	 * calculate maximum number of mushroom that the picker can obtain in m
	 * moves starting from position k
	 */
	public static void main(String[] args) {
		Examples ex = new Examples();
		int k = 4;
		int m = 6;
		int[] A = { 2, 3, 7, 5, 1, 3, 9 };
		for (int i : A) {
			System.out.print(i + " ");
		}
		System.out.println();
		boolean [] visited = new boolean[A.length];
		System.out.println(ex.mushroomPicker(A, visited, k, m));
	}

	public int mushroomPicker(int[] A, boolean[] visited, int k, int m) {
		visited[k] = true;
		System.out.println("k = " + k + " m = " + m);
		System.out.print("visited: ");
		for (boolean b : visited) {
			System.out.print(b?"T ":"F ");
		}
		System.out.println();
		if (m == 1) {
			return A[k];
		} else {
			int left = 0;
			// we go to the left until we pass the first element or we find an
			// element not visited
			int leftCounter = k;
			while (leftCounter >= 0 && visited[leftCounter]) {
				leftCounter--;
			}
			if (leftCounter >= 0 && !visited[leftCounter]) {
				left = mushroomPicker(A, visited, leftCounter, m - 1);
			}
			int right = 0;
			// we go to the right until we pass the last element or we find an
			// element not visited
			int rightCounter = k;
			while (rightCounter <= A.length - 1 && visited[rightCounter]) {
				rightCounter++;
			}
			if (rightCounter < A.length && !visited[rightCounter]) {
				right = mushroomPicker(A, visited, rightCounter, m - 1);
			}
			return A[k] + ((left < right) ? right : left);
		}
	}
}
