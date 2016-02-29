package com.app.codes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

import com.app.codes.exceptions.OnFilteringException;
import com.app.codes.exceptions.ParameterInvalidException;
import com.app.codes.exceptions.ValidatingFileException;

/**
 * 
 * @author 
 *
 */
public class PostalCodes {

	/**
	 * pattern for dutch postal codes = "^[1-9][0-9]{3}\\s?[a-zA-Z]{2}$"
	 */
	private final static String PATTERN_POSTAL_CODES = "^(.*)[1-9][0-9]{3}\\s?[a-zA-Z]{2}(.*)$";
	private final static String PATTERN_FILE_NAMES = "^(.+)(post)\\w+(\\.txt)";
	private final static int MAX_FILE_SIZE = 1048576;
	private final static int MAX_AMOUNT_OF_FILES = 1000;
	
	final static Logger logger = Logger.getLogger(PostalCodes.class);


	/**
	 * 
	 * @param args
	 * @throws ParameterInvalidException
	 * @throws OnFilteringException 
	 * @throws ValidatingFileException 
	 */
	public static void main(String[] args) 
			throws ParameterInvalidException, OnFilteringException, ValidatingFileException {
		
		PostalCodes m = new PostalCodes();
		String parentPath = "D:\\carlos";
		if (args.length != 0) {
			String tentativeNewPath = Arrays.asList(args).stream().collect(Collectors.joining(""));
			if (Utils.isValidWindowsPath(tentativeNewPath)) {
				// valid windows path
				logger.info(tentativeNewPath + " is a valid windows path");
				String escapedFilepath = tentativeNewPath.replace("\\", "\\\\");
				parentPath = escapedFilepath;
			} else if (Utils.isValidLinuxPath(tentativeNewPath)) {
				parentPath = tentativeNewPath;
				logger.info(tentativeNewPath + " is a valid linux path");
			} else {
				throw new ParameterInvalidException("Parameter invalid path");
			}
		}

		List<String> result = m.processFiles(parentPath);
		m.printResult(result);
	}
	
	private void printResult(List<String> elems){

		for (String string : elems) {
			System.out.println(string);
		}
	}

	/**
	 * 
	 * @param parentPath
	 * @throws OnFilteringException 
	 * @throws ValidatingFileException 
	 */
	public List<String> processFiles(String parentPath) 
			throws OnFilteringException, ValidatingFileException {
		try {
			List<String> elems = new ArrayList<>();
			List<String> list = getFilesInPath(parentPath);
			for (String filePath : list) {
				logger.info("processing file " + filePath + "...");
				if (isAddecuatedSize(filePath)) {
					Stream<String> lines = Files.lines(Paths.get(filePath));
					lines.filter(s -> s.matches(PATTERN_POSTAL_CODES)).forEach(elems::add);
					lines.close();
				}
			}
			return elems;
		} catch (IOException e) {
			throw new OnFilteringException("Couldn't process the files successfuly");
		}
	}

	/**
	 * 
	 * @param parentDir
	 * @return
	 * @throws OnFilteringException
	 */
	private List<String> getFilesInPath(String parentDir) throws OnFilteringException {
		try {
			Stream<Path> stream = Files.list(Paths.get("D:\\carlos"));
			List<String> list = stream.map(String::valueOf)
					.filter(path -> path.matches(PATTERN_FILE_NAMES))
					.collect(Collectors.toList());
			stream.close();
			if(list.size() > MAX_AMOUNT_OF_FILES){
				logger.warn("too many files! will be considered only the first " + MAX_AMOUNT_OF_FILES);
				list = list.subList(0, MAX_AMOUNT_OF_FILES);
			}
			return list;
		} catch (IOException e) {
			throw new OnFilteringException("Error getting the list of files to be processed");
		}
	}
	
	/**
	 * 
	 * @param filePath
	 * @return
	 * @throws ValidatingFileException
	 */
	private boolean isAddecuatedSize(String filePath) 
			throws ValidatingFileException{
		try {
			return (Files.size(Paths.get(filePath)) < MAX_FILE_SIZE);
		} catch (IOException e) {
			throw new ValidatingFileException("Couldn't validate file size for the path: " + filePath);
		}
	}

}
