package com.javacodegeeks.advanced.construction.patterns.factory;

public class PaperLibrary implements BookFactory {

	@Override
	public Book newBook() {
		return new PaperBook();
	}

}
