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

public class TopN {
	//static int keep = 5;
	
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
	    	String theWord = curr[0];
	    	
	    	long count = 0;
			for(int i = 1; i < curr.length; i++) {
				
				String[] fcount = curr[i].split(" ");
	
				count = count + Integer.valueOf(fcount[1]);
				
			}
			
			
			context.write(new Text(theWord), new LongWritable(count));
    	}
    
    	
    	
    }
	
} // End of mapper.
	
	public static class TopNReducer 
	extends Reducer<Text, LongWritable, Text, LongWritable> {
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
		
		Configuration conf = context.getConfiguration();
		String value = conf.get("top");
		int keep = Integer.parseInt(value);
		
		String name = key.toString();
		long count = 0;
		for(LongWritable val: values) {
			// count all values.
			count += val.get();
		}
		
		// make negative for least 5
		//count = -count;
		tmap2.put(count, name);
		
		
		//tmap2.put(count, name);
		
		
		if(tmap2.size() > keep) {
			tmap2.remove(tmap2.firstKey());
			//System.out.println("HERE");
		}
		
	}
	
	@Override
	public void cleanup(Context context) throws IOException, InterruptedException {
		
		// sort values.
		Configuration conf = context.getConfiguration();
		String val = conf.get("top");
		int keep = Integer.parseInt(val);
		
		
		while(tmap2.size() > keep) {
			tmap2.remove(tmap2.firstKey());
		}
		
		int counter = 0;
	
		
		for (Entry<Long, String> entry : tmap2.entrySet()) {
			
			long count = entry.getKey();
			//count = -count;
			//String name = entry.getValue();
			String curr = entry.getValue();
			
			// write 
			
			context.write(new Text(curr), new LongWritable(count));
		
			
			
			
		}
		
		
	}
	
	
	
	
} // End of reducer
	
	
	public static void main(String[] args) throws Exception{
		
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		conf.set("top", args[2]);
		if(otherArgs.length < 2) {
			
			// if arguments are not provided 
			System.err.println("Error: please provide two paths");
			System.exit(2);
		}
		
		Job job = Job.getInstance(conf, "Least 5");
		job.setJarByClass(TopN.class);
		
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
