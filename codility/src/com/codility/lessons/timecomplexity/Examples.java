package com.codility.lessons.timecomplexity;

public class Examples {

	public int sum(int N) {
		return N * (N + 1) / 2;
	}

	public static void main(String[] args) {
		Examples e = new Examples();
		int num = 10;
		for (int i = 1; i <= num; i++) {
			System.out.println(e.sum(i));
		}
	}

}
