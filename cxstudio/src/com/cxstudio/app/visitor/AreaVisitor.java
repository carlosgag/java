package com.cxstudio.app.visitor;

import com.cxstudio.app.business.model.Circle;
import com.cxstudio.app.business.model.Rectangle;
import com.cxstudio.app.business.model.Triangle;

public class AreaVisitor implements ShapeVisitor {

	@Override
	public void visit(Circle c) {
		Double area = Math.pow(c.getRadius(), 2)*Math.PI;
		System.out.println(area);
	}

	@Override
	public void visit(Rectangle rectangle) {
		Double area = rectangle.getWidth() * rectangle.getHeight();
		System.out.println(area);

	}

	@Override
	public void visit(Triangle triangle) {
		Double area = triangle.getBase() * triangle.getHeight() / 2;
		System.out.println(area);

	}

}
