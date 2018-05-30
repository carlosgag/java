package com.javacodegeeks.advanced.design;

public interface InterfaceWithDefinitions {
	
	String CONSTANT = "CONSTANT";
	//identical to: public static final String CONSTANT = "CONSTANT";

	enum InnerEnum {
		E1, E2;
	}

	//as default, inner classes, enumerations or interfaces are static
	class InnerClass {
	}

	interface InnerInterface {
		void performInnerAction();
	}

	void performAction();
}
