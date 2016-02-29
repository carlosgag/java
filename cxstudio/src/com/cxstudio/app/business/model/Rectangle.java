package com.cxstudio.app.business.model;

import com.cxstudio.app.business.Shape;
import com.cxstudio.app.visitor.ShapeVisitor;

public class Rectangle implements Shape<ShapeVisitor> {

	private final double height;
	private final double width;

	public Rectangle(double height, double width) {
		this.height = height;
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * 
	 */
	public void calculate(ShapeVisitor s) {
		s.visit(this);
	}

}
