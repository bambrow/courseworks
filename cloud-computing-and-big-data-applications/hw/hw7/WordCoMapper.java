package stubs;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCoMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    
    /*
     * TODO implement
     */
	
	String line = value.toString().toLowerCase();
	
	String[] words = line.split("\\W+");
	
	for (int i = 0; i < words.length - 1; i++) {
		
		if (words[i].length() > 0 && words[i+1].length() > 0) {
			context.write(new Text(words[i] + "," + words[i+1]), new IntWritable(1));
		}
		
	}
  }
}
