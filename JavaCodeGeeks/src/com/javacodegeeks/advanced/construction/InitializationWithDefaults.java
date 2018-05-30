package com.javacodegeeks.advanced.construction;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;

public class InitializationWithDefaults {
	static {
		//static initialization code 
	}
	private boolean booleanMember;
	private byte byteMember;
	private short shortMember;
	private int intMember;
	private long longMember;
	private char charMember;
	private float floatMember;
	private double doubleMember;
	private Object referenceMember;

	public InitializationWithDefaults() {
		System.out.println("booleanMember = " + booleanMember);
		System.out.println("byteMember = " + byteMember);
		System.out.println("shortMember = " + shortMember);
		System.out.println("intMember = " + intMember);
		System.out.println("longMember = " + longMember);
		System.out.println("charMember = " + Character.codePointAt(new char[] { charMember }, 0));
		System.out.println("floatMember = " + floatMember);
		System.out.println("doubleMember = " + doubleMember);
		System.out.println("referenceMember = " + referenceMember);
	}

	public static void main(String[] args) {
		final InitializationWithDefaults initializationWithDefaults = new InitializationWithDefaults();
		
		Path path = null;
		OpenOption options = null;
		//Finalizers
		try (final InputStream in = Files.newInputStream(path,options)){
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
