package com.cxstudio.app.business.model;

import com.cxstudio.app.business.Shape;
import com.cxstudio.app.visitor.ShapeVisitor;

public class Triangle implements Shape<ShapeVisitor> {
	private double height;
	private double base;
	private double side1;
	private double side2;

	public Triangle(double height, double base) {
		this.height = height;
		this.base = base;
	}

	public Triangle(double base, double side1, double side2) {
		this.setSide1(side1);
		this.setSide2(side2);
		this.base = base;
	}

	public double getSide2() {
		return side2;
	}

	public void setSide2(double side2) {
		this.side2 = side2;
	}

	public double getSide1() {
		return side1;
	}

	public void setSide1(double side1) {
		this.side1 = side1;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * 
	 * @param height
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * @return the width
	 */
	public double getBase() {
		return base;
	}

	/**
	 * 
	 * @param base
	 */
	public void setBase(double base) {
		this.base = base;
	}

	public void calculate(ShapeVisitor s) {
		s.visit(this);
	}

}
