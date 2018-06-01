package com.examples.binding;

public class HelloAndroid {
	public static void main(String args[]) { 
		Parent p = new Child(); 
		p.whoAmI();  // static method, resolved at compile time 
		p.whoAreYou(); // virtual method, runtime resolution
		System.out.println("---------------------------------------------");
		Child c = new Child(); 
		c.whoAmI();  // static method, resolved at compile time 
		c.whoAreYou(); // virtual method, runtime resolution
	}
}
