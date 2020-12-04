package aoc.utilities;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class MD5 {
	
	public static void day4IdealStockingStufferAdventCoins(List<String> input, int numZeros) {
		String line = input.get(0);
		String compareStr = "";
		
		for(int i = 0; i < numZeros; i++) {
			compareStr += "0";
		}

		int cur = 0;
		boolean found = false;
	        
		while(!found) {
			cur++;
			try {
				MessageDigest md5 = MessageDigest.getInstance("MD5");
				md5.update(StandardCharsets.UTF_8.encode(line + Integer.toString(cur)));
				String r = String.format("%032x", new BigInteger(1, md5.digest()));
		        
				if(r.startsWith(compareStr)) {
					found = true;
				}
			}
			catch(NoSuchAlgorithmException nsae) {
				System.out.println("error");
			}
		}
		
//		return cur;
	}
}
