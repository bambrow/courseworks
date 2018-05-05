package stubs;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class AvgWordLength extends Configured implements Tool{

	public static void main(String[] args) throws Exception {

		Configuration conf = new Configuration();
		conf.setBoolean("caseSensitive", true);

		int exitCode = ToolRunner.run(conf, new AvgWordLength(), args);
		System.exit(exitCode);

	}

	public int run(String[] args) throws Exception {

		/*
		 * Validate that two arguments were passed from the command line.
		 */
		if (args.length != 2) {
			System.out.printf("Usage: %s [generic options] <input dir> <output dir>\n", getClass().getSimpleName());
			return -1;
		}

		/*
		 * Instantiate a Job object for your job's configuration. 
		 */

		Job job = new Job(getConf());

		/*
		 * Specify the jar file that contains your driver, mapper, and reducer.
		 * Hadoop will transfer this jar file to nodes in your cluster running 
		 * mapper and reducer tasks.
		 */
		job.setJarByClass(AvgWordLength.class);

		/*
		 * Specify an easily-decipherable name for the job.
		 * This job name will appear in reports and logs.
		 */
		job.setJobName("Average Word Length");

		/*
		 * TODO implement
		 * Set paths and input/output classes.
		 */

		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.setMapperClass(LetterMapper.class);
		job.setReducerClass(AverageReducer.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);

		/*
		 * Start the MapReduce job and wait for it to finish.
		 * If it finishes successfully, return 0. If not, return 1.
		 */
		boolean success = job.waitForCompletion(true);
		return success ? 0 : 1;

	}
}

