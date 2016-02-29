package com.cxstudio.app;

import com.cxstudio.app.business.Shape;
import com.cxstudio.app.business.model.Circle;
import com.cxstudio.app.visitor.AreaVisitor;
import com.cxstudio.app.visitor.ShapeVisitor;

public class Client {
	
	public Client(){
		
	}
	
	public void calculateShapeArea(Shape<ShapeVisitor> c){

		ShapeVisitor shapeVisitor = new AreaVisitor();
		c.calculate(shapeVisitor);
	}

	public static void main(String[] args) {

		Shape<ShapeVisitor> c = new Circle(0.5);
		
		Client client = new Client();
		client.calculateShapeArea(c);
	}

}
