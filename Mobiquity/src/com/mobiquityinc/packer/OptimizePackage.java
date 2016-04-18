package com.mobiquityinc.packer;

import java.util.ArrayList;
import java.util.List;

public class OptimizePackage {

	private Integer maxWeightAllowed;
	private List<PackageItem> items;

	public OptimizePackage() {
		setItems(new ArrayList<>());
	}

	public List<PackageItem> getItems() {
		return items;
	}

	public void setItems(List<PackageItem> items) {
		this.items = items;
	}

	public void addItem(PackageItem packageItem) {
		this.items.add(packageItem);
	}

	public Integer getMaxWeightAllowed() {
		return maxWeightAllowed;
	}

	public void setMaxWeightAllowed(Integer maxWeightAllowed) {
		this.maxWeightAllowed = maxWeightAllowed;
	}

	public List<PackageItem> getOptimizedList() {
		OptimizationStrategy dynamicOptimization = new DynamicOptimization();
		return dynamicOptimization.getOptimizedList(this);
	}
}
