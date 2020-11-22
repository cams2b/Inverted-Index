package myproj;
import java.io.*;
import java.util.*;

public class TopN {
	
	public static String Term(String path, String outPath, String term) throws IOException{
		String ret = "val";
		BufferedReader br = new BufferedReader(new FileReader(path));
		
		String line = br.readLine();
		String[] curr = line.split(" ");
		
		while(line != null) {
			
			curr = line.split("~");
			String alter = "";
			String[] test = curr[0].split("\t");
			

			String out = "";
			if(test.length > 0 && test[0].equals(term)) {
			
				
				// Iterate over line and generate vals.
				
				for(int i = 1; i < curr.length; i++) {
					String[] wCount = curr[i].split(" ");
					//System.out.println(wCount[0]);
					out = out + wCount[0] + ":" + wCount[1] + " ";
					
				}
				
				
				// write file out
				outPath = outPath + "/term.txt";
				FileWriter myWriter = new FileWriter(outPath);
				myWriter.write(out);
				myWriter.close();
				
				
				
				
				
				return out;
				
				
			} 
			
			line = br.readLine();
		}
		return null;
		
		
	
		
		
	}
	
	
	
	
	
	
	public static String topVal(String path, String outPath, int n) throws IOException {
		String ret = "val";
		
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = br.readLine();
		
		TreeMap<Integer, String> tmap = new TreeMap<Integer, String>();
		
		while(line != null) {
			
			// split at comas.
			String[] curr = line.split("~");
			
			String word = curr[0];
			//System.out.println(word);
			int count = 0;
			for(int i = 1; i < curr.length; i++) {
				
				String[] fcount = curr[i].split(" ");
				//System.out.println(fcount[0]);

				count = count + Integer.valueOf(fcount[1]);
				
			}
			
			tmap.put(count, word);
			line = br.readLine();
			
			
			
			
		}
		
		
		while(tmap.size() > n) {
			
			tmap.remove(tmap.firstKey());
		}
		
		String out = "";
		for(Map.Entry<Integer, String> entry : tmap.entrySet()) {
			
			long count = entry.getKey();
			String word = entry.getValue();
			//System.out.println(word + ":" + count);
			out = Long.toString(count) + ":" + word + " " + out;
		}
		
		outPath = outPath + "/topN.txt";
		FileWriter myWriter = new FileWriter(outPath);
		myWriter.write(out);
		myWriter.close();
		
		
		return out;
	}
	
	
	public static void main(String[] args) throws IOException {
		
		
		int n;
		String term;
		String path = args[0];
		String out = args[1];
		// run search
		if(args[3].equals("top")) {
			
			n = Integer.parseInt(args[2]);
			topVal(path, out, n);
		} else {
			
			term = args[2];
			Term(path, out, term);
		}
		
		
	}

}
