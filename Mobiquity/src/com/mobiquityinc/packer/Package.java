package com.mobiquityinc.packer;

import java.util.ArrayList;
import java.util.List;

public class Package {
	
	private List<PackageItem> packageItems;
	private Double totalWeight;
	private Integer totalCost;

	public Package() {
		setPackageItems(new ArrayList<>());
	}

	public List<PackageItem> getPackageItems() {
		return packageItems;
	}

	public void setPackageItems(List<PackageItem> packageItems) {
		this.packageItems = packageItems;
	}

	public void addPackageItem(PackageItem packageItem) {
		this.packageItems.add(packageItem);
		this.totalCost += packageItem.getCost();
		this.totalWeight += packageItem.getWeight();
	}

	public Double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public Integer getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Integer totalCost) {
		this.totalCost = totalCost;
	}
}
