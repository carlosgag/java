package com.cxstudio.app;

import com.cxstudio.app.business.Shape;
import com.cxstudio.app.business.model.Circle;
import com.cxstudio.app.business.model.Rectangle;
import com.cxstudio.app.business.model.Triangle;
import com.cxstudio.app.visitor.PerimeterVisitor;
import com.cxstudio.app.visitor.ShapeVisitor;

import junit.framework.TestCase;

public class PerimeterVisitorTest extends TestCase {
	
	ShapeVisitor perimeterVisitor;

	protected void setUp() throws Exception {
		super.setUp();
		perimeterVisitor = new PerimeterVisitor();
	}

	public void testCircle() {
		Shape<ShapeVisitor> c = new Circle(0.5);
		c.calculate(perimeterVisitor);
	}

	public void testRectangle() {
		Shape<ShapeVisitor> r = new Rectangle(2, 4);
		r.calculate(perimeterVisitor);
	}

	public void testTriangle() {
		Shape<ShapeVisitor> t = new Triangle(2, 3, 4);
		t.calculate(perimeterVisitor);
	}

}
