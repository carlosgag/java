package com.cxstudio.app.visitor;

import com.cxstudio.app.business.model.Circle;
import com.cxstudio.app.business.model.Rectangle;
import com.cxstudio.app.business.model.Triangle;

public class PerimeterVisitor implements ShapeVisitor {

	@Override
	public void visit(Circle c) {
		Double perimeter = 2 * Math.PI * c.getRadius();
		System.out.println(perimeter);
	}

	@Override
	public void visit(Rectangle rectangle) {
		Double perimeter = (rectangle.getHeight() + rectangle.getWidth()) * 2;
		System.out.println(perimeter);
	}

	@Override
	public void visit(Triangle triangle) {
		Double perimeter = triangle.getBase() + triangle.getSide1() + triangle.getSide2();
		System.out.println(perimeter);
	}
}