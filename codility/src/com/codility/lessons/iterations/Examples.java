package com.codility.lessons.iterations;

import java.math.BigInteger;

public class Examples {

	public BigInteger factorial(int n) {
		BigInteger factorial = new BigInteger("1");
		for (int i = 1; i < n + 1; i++) {
			factorial = factorial.multiply(new BigInteger(i+""));
		}
		return factorial;
	}
	
	public BigInteger factorialRec(int n) {
		if (n == 1) {
			return new BigInteger("1");
		} else {
			return factorialRec(n - 1).multiply(new BigInteger(n + ""));
		}
	}
	
	public void printTriangle(int n) {
		for (int i = n; i > 0; i--) {
			for(int j = 0; j < n - i; j++){
				System.out.print("  ");
			}
			for(int j = 0; j<2*i-1;j++){
				System.out.print("* ");
			}
			System.out.println();
		}
	}
	
	public BigInteger fibonacciRec(int n){
		if(n==0){
			return new BigInteger(0+"");
		}else if(n==1){
			return new BigInteger(1+"");
		}else{
			return fibonacciRec(n-1).add(fibonacciRec(n-2));
		}
	}
	
	public BigInteger fibonacci(int n) {
		// fib0
		BigInteger fib = new BigInteger(0 + "");
		if (n == 0) {
			return fib;
		} else if (n == 1) {
			return new BigInteger(1 + "");
		}
		BigInteger minus2 = new BigInteger(0 + "");
		BigInteger minus1 = new BigInteger(1 + "");
		for (int i = 2; i <= n; i++) {
			fib = minus1.add(minus2);
			minus2 = minus1;
			minus1 = fib;
		}
		return fib;
	}
	
	public static void main(String [] args){
		Examples e = new Examples();
//		long start = System.nanoTime();
//		for(int i=1;i<50;i++){
//			System.out.println(e.factorial(i));
//		}
//		long duration = System.nanoTime() - start;
//		System.out.println(">> " + duration);
//
//		start = System.nanoTime();
//		for(int i=1;i<50;i++){
//			System.out.println(e.factorialRec(i));
//		}
//		duration = System.nanoTime() - start;
//		System.out.println(">> " + duration);
//		e.printTriangle(10);
		for (int i = 0; i <= 15; i++) {
			System.out.println(e.fibonacciRec(i));
		}
		for (int i = 0; i <= 15; i++) {
			System.out.println(e.fibonacci(i));
		}
	}
}
