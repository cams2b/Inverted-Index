import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.util.Arrays;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;


public class Index {
	
	
	public static class LeastFiveMapper 
		extends Mapper<Object, Text, Text, LongWritable>{
		
	    private final static LongWritable one = new LongWritable(1);
	    private Text word = new Text();
	    private TreeMap<String, Object[]> tmap;// = new HashMap<String, Long>(); 
	    
	    
	    @Override
	    public void setup(Context context) throws IOException, 
	                                     InterruptedException 
	    { 
	    	// value is object array.
	        tmap = new TreeMap<String, Object[]>(); 
	    } 
		
	    
	    @Override
	    public void map(Object key, Text value, Context context
	    		) throws IOException, InterruptedException {
	    	
	    	// convert key to a string.
	    	//String fileName = (String)key;
	    	String fileName = ((FileSplit) context.getInputSplit()).getPath().getName();
	    	// split input data
	    	String tokens = value.toString();
	    	 
	    	StringTokenizer tokenizer = new StringTokenizer(tokens);
	    	// iterate through the words
	    	while(tokenizer.hasMoreTokens()) {
	    		
	    		String curr = tokenizer.nextToken();
	    		
	    		String test = curr;
				curr = cleaner(curr);
				
				
		
	    		if(topWords(curr)) {
	    			
	    			curr = curr + " " + fileName;
		    		context.write(new Text(curr), one);
	    		}
	    		
	    		
	    		//curr = curr + " " + fileName;
	    		//context.write(new Text(curr), one);
	    		
	    	} // end of for loop
	    	
	    	
	    	
	    	
	    	
	    }
	    public static boolean topWords(String word) {
	    	
	    	boolean add = true;
	    	
	    	if(word.equals("the")) {
	    		add = false;
	    	}
	    	else if(word.equals("be")) {
	    		add = false;
	    	}
	    	else if(word.equals("to")) {
	    		add = false;
	    	}
	    	else if(word.equals("of")) {
	    		add = false;
	    	}
	    	else if(word.equals("and")) {
	    		add = false;
	    	}
	    	
	    	
	    	return add;
	    }
		public static String cleaner(String word){

			String[] arr = word.split("");

			Boolean adj = true;
			int counter = 0;
			while(arr.length > 1 && adj == true){

				// " ", ", ", ', ', , , -, ., (),
				adj = false;

				if(arr[0].equals("\"") && arr[arr.length-1].equals("\"")) {
					System.out.println("Here ");
					//System.out.println(" \"");

					arr = word.split("\"");
					//System.out.println(arr[1]);
					word = arr[1];
					adj = true;
					
				}
				else if (arr[0].equals("\"")) {
					System.out.println("Here2");
					arr = word.split("\"");
					//System.out.println(arr[1]);
					word = arr[1];
					adj = true;
					
				}
				else if (arr[arr.length - 1].equals("\"")) {
					System.out.println("Here3");
					arr = word.split("\"");
					//System.out.println(arr[0]);
					word = arr[0];
					adj = true;
					
				}
				// end of quotes check
				else if (arr[0].equals("'")) {
					System.out.println("Here4");
					arr = word.split("'");
					word = arr[1];
					adj = true;
					
				}
				else if (arr[arr.length - 1].equals("'")) {
					System.out.println("Here5");
					arr = word.split("'");
					word = arr[0];
					adj = true;
					
				}
				// end of ' check
				else if (arr[arr.length-1].equals(",")) {
					
					arr = word.split(",");
					//System.out.println(arr[1]);
					word = arr[0];
					adj = true;
					
				} // comma check
				else if (!word.equals("-") && arr[0].equals("-")) {
					arr = word.split("-");
					//System.out.println(arr[1]);
					
					if(arr.length > 1) {
						word = arr[1];
						adj = true;
						
					}
					
					
				}
				else if (arr[arr.length - 1].equals("-")) {
					arr = word.split("-");
					//System.out.println(arr[0]);
					word = arr[0];
					adj = true;
					
				}
				else if (!word.equals(".") && arr[arr.length-1].equals(".")) {
					
					//arr = word.split("\\.");
					//System.out.println(arr[1]);
					//word = arr[0];
					//adj = true;
					
				} // period check
				else if (arr[arr.length-1].equals("?")) {
					
					arr = word.split("\\?");
					//System.out.println(arr[1]);
					word = arr[0];
					adj = true;
					
				} // question mark check
				else if (arr[arr.length-1].equals("!")) {
					
					arr = word.split("\\!");
					//System.out.println(arr[1]);
					word = arr[0];
					adj = true;
					
				} // period check
				else if (arr[0].equals("(")) {
					System.out.println("Here2");
					arr = word.split("\\(");
					//System.out.println(arr[1]);
					word = arr[1];
					adj = true;
					
				}
				else if (arr[arr.length - 1].equals(")")) {
					System.out.println("Here3");
					arr = word.split("\\)");
					//System.out.println(arr[0]);
					word = arr[0];
					adj = true;
				} // () check
				else if (arr[arr.length - 1].equals(":")) {
					System.out.println("Here3");
					arr = word.split(":");
					//System.out.println(arr[0]);
					word = arr[0];
					adj = true;
				} // () check


				arr = word.split("");
				counter++;


			} // end of while loop
			System.out.println(counter);
			return word;





		}
	    
		
	} // End of mapper.
	
	
	
	
	
	public static class LeastFiveReducer 
		extends Reducer<Text, LongWritable, Text, Text> {
		private TreeMap<String, Stack<String>> tmap2;// = new TreeMap<Long, String>(); 
		
		@Override
	    public void setup(Context context) throws IOException, 
	                                     InterruptedException 
	    { 
	        tmap2 = new TreeMap<String, Stack<String>>(); 
	    } 

		
		@Override
		public void reduce(Text key, Iterable<LongWritable> values, 
							Context context) throws IOException, InterruptedException {
			
			String name = key.toString();
			// first value is word, second value is file
			String[] word_file = name.split(" ");
			
			
    		// end
			
			
			
			long count = 0;
			for(LongWritable val: values) {
				// count all values.
				Long curr = val.get();
				count = count + curr;
				//count += val.get();
			}
			
			// If the word does not yet exist in the tree
			if(tmap2.get(word_file[0]) == null) {
				Stack<String> curr = new Stack<String>();
				
				// add word to tree
				tmap2.put(word_file[0], curr);
				//tmap2.get(count).push(name);
				
				
				
			}
			
			// push String fileName + count
			name = word_file[1] + " " + Long.toString(count);
			tmap2.get(word_file[0]).push(name);
			
			
			
			
			
			
			
		} // end of reduce method.
		
		@Override
		public void cleanup(Context context) throws IOException, InterruptedException {
			
		
			for (Entry<String, Stack<String>> entry : tmap2.entrySet()) {
				
				String word = entry.getKey();
				//count = -count;
				//String name = entry.getValue();
				Stack<String> curr = entry.getValue();
				
				// write 
				String payload = "";
				while(curr.empty() == false) {
					String current = curr.pop();
					payload = payload + "~" + current;
					
				}
				context.write(new Text(word), new Text(payload));
				//context.write(new Text(name), new LongWritable(count));
			
				
				
				
			}
			
			
		} // end of cleanup method
		
		
		
		
	} // End of reducer
	
	
	
	public static void main(String[] args) throws Exception{
		
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		
		if(otherArgs.length < 2) {
			
			// if arguments are not provided 
			System.err.println("Error: please provide two paths");
			System.exit(2);
		}
		
		Job job = Job.getInstance(conf, "Inverted Index");
		job.setJarByClass(Index.class);
		
		job.setMapperClass(LeastFiveMapper.class);
		job.setReducerClass(LeastFiveReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		
		job.setNumReduceTasks(1);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
		
		
		
	}
	
	

}