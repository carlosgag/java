package com.mobiquityinc.packer;

import java.util.List;

public interface OptimizationStrategy {
	
	public List<PackageItem> getOptimizedList(OptimizePackage optimizePackage);
	
}
