package com.cxstudio.app.business.model;

import com.cxstudio.app.business.Shape;
import com.cxstudio.app.visitor.ShapeVisitor;

public class Circle implements Shape<ShapeVisitor> {

	private final double radius;

	public Circle(double radius) {
		this.radius = radius;
	}

	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	public void calculate(ShapeVisitor s) {
		s.visit(this);
		
	}

}
