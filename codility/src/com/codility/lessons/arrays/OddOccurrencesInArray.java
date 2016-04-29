package com.codility.lessons.arrays;

import java.util.HashMap;
import java.util.Map;

public class OddOccurrencesInArray {

	public int solution(int[] A) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i : A) {
			if(map.containsKey(i)){
				if(map.get(i) == 1){
					map.put(i, 0);
				} else {
					map.put(i, 1);
				}
			} else {
				map.put(i,1);
			}
		}
		for(Map.Entry<Integer, Integer> entry:map.entrySet()){
			int value = entry.getValue();
			if( value == 1){
				return entry.getKey();
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		// number of elements between 1 and 1000000
		// each element is between 1 and 1000000000
		int[] array = { 9, 3, 9, 3, 9, 7, 9 };
		Examples e = new Examples();
		e.print(array);
		OddOccurrencesInArray oo = new OddOccurrencesInArray();
		System.out.println("-----");
		int result = oo.solution(array);
		System.out.println(result);

	}

}
