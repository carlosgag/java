package com.codility.lessons.arrays;

public class Examples {

	public static void main(String[] args) {
		int[] array = new int[10];
		for (int i = 0; i < 10; i++) {
			array[i] = (int) (Math.random() * 1000);
		}
		int[] array2 = new int[15];
		for (int i = 0; i < 15; i++) {
			array2[i] = (int) (Math.random() * 1000);
		}
		Examples e = new Examples();
		e.print(array);
		e.reverse(array);
		e.print(array);
		e.print(array2);
		e.reverse(array2);
		e.print(array2);
	}

	public void print(int[] array) {
		for (int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	public int[] reverse(int[] array) {
		int N = array.length;
		for (int i = 0; i < N / 2; i++) {
			int temp = array[i];
			array[i] = array[N - i - 1];
			array[N - i - 1] = temp;
		}
		return array;
	}

}
