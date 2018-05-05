package stubs;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class ImageCounter extends Configured implements Tool {

  @Override
  public int run(String[] args) throws Exception {

    if (args.length != 2) {
      System.out.printf("Usage: ImageCounter <input dir> <output dir>\n");
      return -1;
    }

    Job job = new Job(getConf());
    job.setJarByClass(ImageCounter.class);
    job.setJobName("Image Counter");

    /*
     * TODO implement
     */
    
    job.setNumReduceTasks(0);
    
    FileInputFormat.setInputPaths(job, new Path(args[0]));
	FileOutputFormat.setOutputPath(job, new Path(args[1]));

	job.setMapperClass(ImageCounterMapper.class);
	
	job.setOutputKeyClass(Text.class);
	job.setOutputValueClass(IntWritable.class);

    boolean success = job.waitForCompletion(true);
    if (success) {
      /*
       * Print out the counters that the mappers have been incrementing.
       * TODO implement
       */
      long gifRecords = job.getCounters().findCounter("RecordType", "gif").getValue();
      long jpgRecords = job.getCounters().findCounter("RecordType", "jpg").getValue();
      long otherRecords = job.getCounters().findCounter("RecordType", "other").getValue();
      System.out.println("gif: " + gifRecords);
      System.out.println("jpg: " + jpgRecords);
      System.out.println("other: " + otherRecords);
      
      return 0;
    } else
      return 1;

  }

  public static void main(String[] args) throws Exception {
    int exitCode = ToolRunner.run(new Configuration(), new ImageCounter(), args);
    System.exit(exitCode);
  }
}