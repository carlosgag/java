package com.codility.lessons.iterations;

public class Solution {
    public int solution(int N) {
        // write your code in Java SE 8
        if(N <=1) {
            return 0;
        } else {
            int gap = 0;
            String binary = Integer.toBinaryString(N);
            int i = 0;
            int candidate = 0;
            while( i < binary.length()){
                if(binary.charAt(i) == '0') {
                    candidate++;
                } else {
                    // is 1
                    if(candidate > gap) {
                        gap = candidate;
                    }
                    candidate = 0;
                }
                i++;
            }
            return gap;
        }
    }
}