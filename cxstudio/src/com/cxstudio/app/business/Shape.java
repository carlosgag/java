package com.cxstudio.app.business;

import com.cxstudio.app.visitor.ShapeVisitor;

public interface Shape<S extends ShapeVisitor> {

	public void calculate(S s);
}
