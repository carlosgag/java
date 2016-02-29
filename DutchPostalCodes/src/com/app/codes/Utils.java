package com.app.codes;

import java.util.regex.Pattern;

public class Utils {


	/**
	 * Validates the path parameter, considering that the directories contains only chars validated by \w expression
	 * @param path
	 * @return
	 */
	public static boolean isValidWindowsPath(String path) {
		String regularExpression = "(?:[a-zA-Z]\\:)\\\\([\\w-]+\\\\)*\\w([\\w-.])+";
		return Pattern.matches(regularExpression, path);
	}

	/**
	 * Validates linux path
	 * @param path
	 * @return
	 */
	public static boolean isValidLinuxPath(String path) {
		String regularExpression = "^(/[^/ ]*)+/?$";
		return Pattern.matches(regularExpression, path);
	}
}
