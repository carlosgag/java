package com.codility.lessons.countingelements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FrogRiverOne {

	public static void main(String[] args) {
		int[] A = { 1, 3, 1, 4, 2, 3, 5, 4 };
		FrogRiverOne fro = new FrogRiverOne();
		System.out.println(fro.solution(5, A));
		System.out.println(fro.solution2(5, A));
		System.out.println(fro.solution3(5, A));
	}

	public int solution(int X, int[] A) {
		int size = A.length;
		List<Boolean> list = new ArrayList<>();
		list.add(0, true);
		for (int i = 1; i <= X; i++) {
			Boolean value = new Boolean(false);
			list.add(i, value);
		}
		if (size < X) {
			return -1;
		} else {
			for (int i = 0; i < size; i++) {
				list.set(A[i], true);
				if (!list.contains(false)) {
					return i;
				}
			}
			if (list.contains(false)) {
				return -1;
			}
		}
		return -1;
	}

	public int solution2(int X, int[] A) {
		int size = A.length;
		Map<Integer, Boolean> map = new HashMap<>();
		map.put(0, true);
		for (int i = 1; i <= X; i++) {
			Boolean value = new Boolean(false);
			map.put(i, value);
		}
		if (size < X) {
			return -1;
		} else {
			for (int i = 0; i < size; i++) {
				map.put(A[i], true);
				if (!map.containsValue(false)) {
					return i;
				}
			}
			if (map.containsValue(false)) {
				return -1;
			}
		}
		return -1;
	}

	public int solution3(int X, int[] A) {
		int size = A.length;
		int[] ocurrences = new int[X + 1];
		int x = 0;
		for (int i = 0; i < size; i++) {
			if (ocurrences[A[i]] == 0) {
				ocurrences[A[i]]++;
				x++;
				if (x == X) {
					return i;
				}
			}
		}
		return -1;
	}

	public int solution4(int X, int[] A) {
		Set<Integer> values = new HashSet<>();
		for (int i = 0; i < A.length; i++) {
			if (values.add(A[i])) {
				X--;
			}
			if (X == 0) {
				return i;
			}
		}
		return -1;
	}
}
