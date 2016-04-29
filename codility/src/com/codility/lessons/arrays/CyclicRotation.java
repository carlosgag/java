package com.codility.lessons.arrays;

public class CyclicRotation {

	public static void main(String[] args) {
		int[] array = new int[10];
		for (int i = 0; i < 10; i++) {
			array[i] = (int) (Math.random() * 1000);
		}
		Examples e = new Examples();
		e.print(array);
		CyclicRotation cr = new CyclicRotation();
		System.out.println("-----");
		array = cr.solution(array, 3);
		e.print(array);
	}

	private int[] solution(int[] array, int k) {
		int N = array.length;
		if (N > 0) {
			for (int i = 0; i < k; i++) {
				int last = array[N - 1];
				for (int j = N - 2; j >= 0; j--) {
					array[j + 1] = array[j];
				}
				array[0] = last;
				// Examples e = new Examples();
				// e.print(array);

			}
		}
		return array;

	}

}
