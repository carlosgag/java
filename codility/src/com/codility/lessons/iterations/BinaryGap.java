package com.codility.lessons.iterations;

public class BinaryGap {

	public int solution(int N) {
		String binary = Integer.toBinaryString(N);
		System.out.println(binary);
		int i = 0;
		int gap = 0;
		int gapCandidate = gap;
		while (i < binary.length()) {
			while (i < binary.length() && binary.charAt(i) == '1') {
				i++;
			}
			gapCandidate = 0;
			while (i < binary.length() && binary.charAt(i) == '0') {
				gapCandidate++;
				i++;
			}
			if (i < binary.length()) {
				// the next is a 1 and we have a gapCandidate, now have to check
				// if is the best
				System.out.println("candidate gap: " + gapCandidate);
				if (gapCandidate > gap) {
					gap = gapCandidate;
				}
			}
		}
		return gap;
	}

	public int solution2(int N) {
		String binary = Integer.toBinaryString(N);
		System.out.println(binary);
		int i = 0;
		int gap = 0;
		int gapCandidate = gap;
		while (i < binary.length()) {
			if (binary.charAt(i) == '0') {
				if (binary.charAt(i - 1) == '1') {
					gapCandidate = 0;
				}
				gapCandidate++;
			}
			i++;
			if (gapCandidate > 0 && i < binary.length() && binary.charAt(i) == '1') {
				// the next is a 1 and we have a gapCandidate, now have to check
				// if it is the best
				if (gapCandidate > gap) {
					System.out.println(">>candidate gap: " + gapCandidate);
					gap = gapCandidate;
				}
			}
		}
		return gap;
	}

	public static void main(String[] args) {
		BinaryGap bg = new BinaryGap();
		for (int i = 0; i < 10; i++) {
			double rnd = Math.random();
			System.out.println(bg.solution((int) (rnd * 1000)));
			System.out.println(bg.solution2((int) (rnd * 1000)));
			System.out.println("-------------------------");
		}
	}

}
