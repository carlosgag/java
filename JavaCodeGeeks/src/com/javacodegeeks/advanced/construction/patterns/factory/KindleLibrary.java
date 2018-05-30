package com.javacodegeeks.advanced.construction.patterns.factory;

public class KindleLibrary implements BookFactory {

	@Override
	public Book newBook() {
		return new KindleBook();
	}

}
