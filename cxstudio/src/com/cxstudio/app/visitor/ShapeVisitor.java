package com.cxstudio.app.visitor;

import com.cxstudio.app.business.model.Circle;
import com.cxstudio.app.business.model.Rectangle;
import com.cxstudio.app.business.model.Triangle;

public interface ShapeVisitor {

	public void visit(Rectangle rectangle);

	public void visit(Circle circle);
	
	public void visit(Triangle triangle);

}
