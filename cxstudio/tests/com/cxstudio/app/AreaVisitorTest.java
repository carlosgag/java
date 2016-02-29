package com.cxstudio.app;

import com.cxstudio.app.business.Shape;
import com.cxstudio.app.business.model.Circle;
import com.cxstudio.app.business.model.Rectangle;
import com.cxstudio.app.business.model.Triangle;
import com.cxstudio.app.visitor.AreaVisitor;
import com.cxstudio.app.visitor.ShapeVisitor;

import junit.framework.TestCase;

public class AreaVisitorTest extends TestCase {
	
	ShapeVisitor areaVisitor;

	protected void setUp() throws Exception {
		super.setUp();
		areaVisitor = new AreaVisitor();
	}

	public void testCircle() {
		Shape<ShapeVisitor> c = new Circle(0.5);
		c.calculate(areaVisitor);
	}

	public void testRectangle() {
		Shape<ShapeVisitor> r = new Rectangle(2, 4);
		r.calculate(areaVisitor);
	}

	public void testTriangle() {
		Shape<ShapeVisitor> t = new Triangle(2, 3);
		t.calculate(areaVisitor);
	}

}
