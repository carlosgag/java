package com.app.codes;

import junit.framework.TestCase;

public class UtilsTest extends TestCase {

	public void testIsInvalidValidWindowsPath() {
		assertFalse(Utils.isValidWindowsPath("D\\carlos"));		
	}

	public void testIsInvalidValidLinuxPath() {		
		assertFalse(Utils.isValidWindowsPath("/home//carlos"));
	}

	public void testIsValidWindowsPath() {
		assertTrue(Utils.isValidWindowsPath("D:\\carlos"));		
	}

	public void testIsValidLinuxPath() {		
		assertTrue(Utils.isValidLinuxPath("/home/carlos"));
	}

}
