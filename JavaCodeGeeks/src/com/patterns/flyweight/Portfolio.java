package com.patterns.flyweight;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * Class responsible to create the cache
 *
 */
public class Portfolio {

	private Map<String, Product> products = new HashMap<String, Product>();

	/**
	 * Factory method Pattern
	 */
	public Product lookup(String productName) {
		if (!products.containsKey(productName)) {
			products.put(productName, new Product(productName));
		}
		return products.get(productName);
	}

	public int totalProductsMade() {
		return products.size();
	}
}