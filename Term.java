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



public class Term {
	
	
	public static class TopNMapper
	extends Mapper<Object, Text, Text, LongWritable>{
	
    private final static LongWritable one = new LongWritable(1);
    private Text word = new Text();
    private TreeMap<String, Long> tmap;// = new HashMap<String, Long>(); 
    
    
    @Override
    public void setup(Context context) throws IOException, 
                                     InterruptedException 
    { 
        tmap = new TreeMap<String, Long>(); 
    } 
	
    
    @Override
    public void map(Object key, Text value, Context context
    		) throws IOException, InterruptedException {
    	
    	// split input data
    	String line = value.toString();
    	String[] curr = line.split("~");
    	
    	if(curr.length > 1) {
    		
    		String[] test = curr[0].split("\t");
    		
    		if(test.length > 0) {
    			context.write(new Text(line), new LongWritable(test[0].length()));
    		}
	    	
			
			
			
    	}
    
    	
    	
    }
	
} // End of mapper.
	
	public static class TopNReducer 
	extends Reducer<Text, LongWritable, Text, Text> {
	private TreeMap<Long, String> tmap2;// = new TreeMap<Long, String>(); 
	
	@Override
    public void setup(Context context) throws IOException, 
                                     InterruptedException 
    { 
        tmap2 = new TreeMap<Long, String>(); 
    } 

	
	@Override
	public void reduce(Text key, Iterable<LongWritable> values, 
						Context context) throws IOException, InterruptedException {
		
		String name = key.toString();
		
		Configuration conf = context.getConfiguration();
		
		String term = conf.get("term");
		String[] curr = name.split("~");
		String[] test = curr[0].split("\t");
		
		Long count = (long) term.length();
		
		//context.write(new Text(test[0]), new LongWritable(count));
		String out = "";
		//context.write(new Text(term), new LongWritable(count));
		if(curr.length > 0 && test[0].equals(term)) {
			
			
			for(int i = 1; i < curr.length; i++) {
				String[] wCount = curr[i].split(" ");
				//System.out.println(wCount[0]);
				out = out + wCount[0] + ":" + wCount[1] + " ";
				
			}
			
			context.write(new Text(curr[0]), new Text(out));
			
			
		}
		
		
		

	
		
	}
	

	
	
	
	
} // End of reducer
	
	
	
	public static void main(String[] args) throws Exception{
		
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		conf.set("term", args[2]);
		
		if(otherArgs.length < 2) {
			
			// if arguments are not provided 
			System.err.println("Error: please provide two paths");
			System.exit(2);
		}
		
		Job job = Job.getInstance(conf, "Term");
		job.setJarByClass(Term.class);
		
		job.setMapperClass(TopNMapper.class);
		job.setReducerClass(TopNReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		
		job.setNumReduceTasks(1);
		
		
		job.setOutputKeyClass(LongWritable.class);
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		
		
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
		
		
		
	}
	
	
	
	
	
	

}
