package aoc.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadInputFile {
	public static final String INPUT_FILE_PATH = "input";
    public static final String INPUT_FILE_TYPE = ".txt";
    
	/**
	 * Generically read in a file. Each method will have to parse each entry in the input list.
	 */
	public static List<String> readFile(String year, String day) {
		BufferedReader br;
		List<String> input = null;
		
		try {
			String filename = INPUT_FILE_PATH + "/" + year + "/" + day + INPUT_FILE_TYPE;
			
			br = new BufferedReader(new FileReader(filename));
			input = new ArrayList<String>();
			
			String line;
			while((line = br.readLine()) != null) {
				input.add(line);
			}
			
			br.close();
		}
		catch (IOException e) {
			System.out.println("Could not find file for year " + year + " and day " + day);
			e.printStackTrace();
		}
		
		return input;
	}
}
