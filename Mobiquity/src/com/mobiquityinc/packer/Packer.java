package com.mobiquityinc.packer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mobiquityinc.exception.APIException;

public class Packer {

	public static void main(String[] args) throws APIException {
		System.out.println(Packer.pack(args[0]));
	}

	public static String pack(String absolutePath) throws APIException {
		List<String> packages;
		try {
			packages = Files.readAllLines(Paths.get(absolutePath));
			StringBuilder builder = new StringBuilder();
			for (String packageStr : packages) {
				OptimizePackage optimizePackage = getOptimizePackage(packageStr);
				List<PackageItem> optimizedList = optimizePackage.getOptimizedList();
				StringBuilder listBuilder = new StringBuilder();
				for (PackageItem packageItem : optimizedList) {
					listBuilder.append(packageItem.getIndex());
					listBuilder.append(",");
				}
				if (!optimizedList.isEmpty()) {
					String items = listBuilder.toString();
					items = items.substring(0, items.length() - 1);
					builder.append(items);
				} else {
					builder.append("-");
				}
				builder.append("\n");
			}
			return builder.toString();
		} catch (IOException e) {
			throw new APIException();
		}
	}

	private static OptimizePackage getOptimizePackage(String line) throws APIException {
		OptimizePackage optimizePackage = new OptimizePackage();
		//remove unneeded chars from string
		Pattern pattern = Pattern.compile("[\\s€(]");
		Matcher matcher = pattern.matcher(line);
		line = matcher.replaceAll("");
		String[] parts = line.split(":");
		if(parts.length != 2){
			throw new APIException();
		}
		String itemsList = parts[1].substring(0, parts[1].length() - 1);
		String[] items = itemsList.split("\\)");
		Double maxWeight = Double.valueOf(parts[0]);
		optimizePackage.setMaxWeightAllowed(maxWeight.intValue()*1000);
		for (String item : items) {
			String[] elems = item.split(",");
			if(elems.length != 3){
				throw new APIException();
			}
			PackageItem packageItem = new PackageItem();
			packageItem.setIndex(Integer.valueOf(elems[0]));
			Double weightInGrams = Double.valueOf(elems[1])*1000;
			packageItem.setWeight(weightInGrams.intValue());
			packageItem.setCost(Integer.valueOf(elems[2]));
			optimizePackage.addItem(packageItem);
		}
		return optimizePackage;
	}
}
