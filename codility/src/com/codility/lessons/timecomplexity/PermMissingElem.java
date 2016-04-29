package com.codility.lessons.timecomplexity;

import java.util.HashMap;
import java.util.Map;

public class PermMissingElem {

	public static void main(String[] args) {
		PermMissingElem pme = new PermMissingElem();
		int[] A = { 2, 3, 1, 5 };
		System.out.println(pme.solution(A));
	}

	public int solution(int[] A) {
		Map<Integer, Boolean> map = new HashMap<>();
		int N = A.length;
		for (int i = 0; i < N; i++) {
			map.put(A[i], true);
		}
		for (int i = 1; i <= N + 1; i++) {
			if (!map.containsKey(i)) {
				return i;
			}
		}
		return 0;
	}

}
