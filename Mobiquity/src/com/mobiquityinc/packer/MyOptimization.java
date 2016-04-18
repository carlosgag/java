package com.mobiquityinc.packer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyOptimization implements OptimizationStrategy {

	@Override
	public List<PackageItem> getOptimizedList(OptimizePackage optimizePackage) {

		List<PackageItem> optimizedList = new ArrayList<>();
		List<PackageItem> allTheItems = optimizePackage.getItems();
		Integer N = allTheItems.size();
		Integer W = optimizePackage.getMaxWeightAllowed();
		Integer availableWeight = W;
		List<Package> potentialPackages = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			boolean[] visited = new boolean[N];
			Package potentialPackage = new Package();
			Integer totalCost = 0;
			Integer totalWeight = 0;
			//potentialPackage.addPackageItem(packageItem);
			for (int j = 0; j < i; j++) {
				if (!visited[i]) {
					if (allTheItems.get(i).getWeight() <= availableWeight) {
						availableWeight -= allTheItems.get(i).getWeight();
					}
					visited[j] = true;
				}
			}
			potentialPackage.setTotalCost(totalCost);
			//potentialPackage.setTotalWeight(totalWeight);
			potentialPackages.add(potentialPackage);
		}

		Integer[] cost = new Integer[N + 1];
		Integer[] weight = new Integer[N + 1];
		PackageItem[] itemsArray = new PackageItem[N + 1];

		for (int n = 1; n <= N; n++) {
			itemsArray[n] = allTheItems.get(n - 1);
			cost[n] = allTheItems.get(n - 1).getCost();
			weight[n] = allTheItems.get(n - 1).getWeight();
		}

		// opt[n][w] = max cost of packing items 1..n with weight limit w
		// sol[n][w] = does opt solution to pack items 1..n with weight limit w
		// include item n?
		int[][] opt = new int[N + 1][W + 1];
		boolean[][] sol = new boolean[N + 1][W + 1];

		for (int n = 1; n <= N; n++) {
			for (int w = 1; w <= W; w++) {

				// don't take item n
				int option1 = opt[n - 1][w];

				// take item n
				int option2 = Integer.MIN_VALUE;
				if (weight[n] <= w) {
					option2 = cost[n] + opt[n - 1][w - weight[n]];
				}
				// select better of two options
				opt[n][w] = Math.max(option1, option2);
				sol[n][w] = (option2 > option1);
			}
		}

		// determine which items to take
		boolean[] take = new boolean[N + 1];
		for (int n = N, w = W; n > 0; n--) {
			if (sol[n][w]) {
				take[n] = true;
				w = w - weight[n];
			} else {
				take[n] = false;
			}
		}

		for (int n = 1; n <= N; n++) {
			if (take[n] == true) {
				optimizedList.add(itemsArray[n]);
			}
		}

		return optimizedList;
	}

}
