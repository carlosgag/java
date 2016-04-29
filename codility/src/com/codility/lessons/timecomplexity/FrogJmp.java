package com.codility.lessons.timecomplexity;

public class FrogJmp {

	public int solution(int X, int Y, int D) {
		return (int) Math.ceil(((double)Y - X) / D);
	}

	public static void main(String[] args) {
		FrogJmp fj = new FrogJmp();
		int X = 10;
		int Y = 85;
		int D = 30;
		System.out.println(fj.solution(X, Y, D));
	}

}
