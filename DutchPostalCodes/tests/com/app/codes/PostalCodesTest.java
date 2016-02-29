package com.app.codes;

import com.app.codes.exceptions.OnFilteringException;
import com.app.codes.exceptions.ValidatingFileException;

import junit.framework.TestCase;

public class PostalCodesTest extends TestCase {

	public void testProcessFiles() {
		PostalCodes pc = new PostalCodes();
		boolean check = false;
		try {
			pc.processFiles("D:\\carlos");
			check = true;
		} catch (OnFilteringException | ValidatingFileException e) {
			fail("Couldn't process files");
		}
		assertTrue(check);
	}

}
