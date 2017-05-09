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
	
	public int solution3(int N){
		int gap = 0;
		boolean hasOne = false; 
		int gapCandidate = gap;		
		System.out.println(N);
		while (N > 0) {
			int digit = N - (N / 10) * 10;
			//System.out.print(digit + " ");
			N = N / 10;
			if(digit != 0){
				if(gapCandidate > 0) {
//					System.out.println();
//					System.out.println("gapCandidate: " + gapCandidate);
					if(gapCandidate > gap){
						gap = gapCandidate;
					}
					gapCandidate = 0;
				}
				hasOne = true;
			} else if(digit == 0 && hasOne == true){
				gapCandidate = gapCandidate + 1;
			}else{
				// N isn't a valid binary number
			}
		}
		return gap;
	}

	public static void main(String[] args) {
		BinaryGap bg = new BinaryGap();
		for (int i = 0; i < 10; i++) {
			double rnd = Math.random();
			int binary = Integer.parseInt(Integer.toBinaryString((int) (rnd * 1000)));
			System.out.println("Binary: " + binary);
//			System.out.println(bg.solution(binary));
//			System.out.println(bg.solution2(binary));
			System.out.println(bg.solution3(binary));
			System.out.println("-------------------------");
		}
	}

}
