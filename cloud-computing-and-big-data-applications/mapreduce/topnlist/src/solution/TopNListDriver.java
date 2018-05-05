package solution;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class TopNListDriver extends Configured implements Tool {

public static void main(String[] args) throws Exception {
		
		int exitCode = ToolRunner.run(new TopNListDriver(), args);
		System.exit(exitCode);
		
	}
	
	public int run(String[] args) throws Exception {
		
		if (args.length != 2 && args.length != 3) {
			System.out.printf("Usage: %s <N> <input dir> <output dir>\n", getClass().getSimpleName());
			return -1;
		}
		
		Job job = new Job(getConf());
		job.setJarByClass(TopNListDriver.class);
		job.setJobName("Top-N List");
		
		if (args.length == 2) {
			FileInputFormat.setInputPaths(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
			job.getConfiguration().setInt("N", 10);
		}
		
		if (args.length == 3) {
			int N = Integer.parseInt(args[0]);
			if (N <= 0) {
				System.out.println("N must be a positive integer!");
				return -2;
			}
			FileInputFormat.setInputPaths(job, new Path(args[1]));
			FileOutputFormat.setOutputPath(job, new Path(args[2]));
			job.getConfiguration().setInt("N", N);
		}
		
		job.setMapperClass(TopNListMapper.class);
		job.setReducerClass(TopNListReducer.class);
		job.setNumReduceTasks(1);
		
		job.setMapOutputKeyClass(NullWritable.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(Text.class);
		
		boolean success = job.waitForCompletion(true);
		return success ? 0 : 1;
		
	}
	
}
