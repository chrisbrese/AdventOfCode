package aoc.utilities;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MD5 {
	
	/**
	 * Given an input string, increasingly add integer to the end of the input and find the first MD5 hash that starts with numZeros.
	 * @param input The string to add integer value to.
	 * @param numZeros The number of zeros that should be at the start of the hash
	 * @param numLoops The number of hashes to find that start with numZeros. Must be &gt; 0
	 * @param returnHashes True to return a list of found hashes, false to return a list of ints that created those hashes
	 * @param orderResults true if this should keep track of exactly one each hash that starts with numZeros + [0-7] in the next character slot
	 * 
	 * @return a List of String, each containing the hashes that start with numZeros
	 */
	// TODO: This is extremely slow (8 loops takes > 30 seconds). How to speed this up?
	public static List<String> findMD5HashesOfIncreasingIntegersWithNumZeros(String input, int numZeros, int numLoops, boolean returnHashes, boolean orderResults) {
		List<String> hashes = new ArrayList<String>(numLoops);
		List<String> hashInts = new ArrayList<String>(numLoops);
		
		HashMap<Integer,String> ordered = null;
		if(orderResults) {
			ordered = new HashMap<Integer,String>(numLoops);
		}
		
		String zeroPad = "";
		for(int i = 0; i < numZeros; i++) {
			zeroPad += "0";
		}

		int curLoop = 0;
		long curInt = 0;
		while(curLoop < numLoops) {
			curInt++;
			try {
				MessageDigest md5 = MessageDigest.getInstance("MD5");
				String curIntStr = Long.toString(curInt);
				md5.update(StandardCharsets.UTF_8.encode(input + curIntStr));
				String hash = String.format("%032x", new BigInteger(1, md5.digest()));
		        
				if(hash.startsWith(zeroPad)) {
					if(!orderResults) {
						hashes.add(hash);
						hashInts.add(curIntStr);
						curLoop++;
					}
					else {
						String nextStr = hash.substring(numZeros, numZeros+1);						
						if(nextStr.matches("^[0-9]+$")) {
							Integer nextInt = Integer.valueOf(nextStr);
							if(numLoops > nextInt) {
								if(ordered.get(nextInt) == null) {
									ordered.put(nextInt, hash.substring(numZeros+1,numZeros+2));
									hashes.add(hash);
									hashInts.add(curIntStr);									
									curLoop++;
								}
							}
						}
					}
				}
			}
			catch(NoSuchAlgorithmException nsae) {
				System.out.println("error");
			}
		}
		
		if(returnHashes) {
			return hashes;
		}
		else if(orderResults) {
			return new ArrayList<String>(ordered.values());
		}
		else {
			return hashInts;
		}
	}
}
