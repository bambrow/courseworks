package stubs;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.conf.Configuration;

public class LetterMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	boolean caseSensitive;
	
	public void setup(Context context) {
		Configuration conf = context.getConfiguration();
		caseSensitive = conf.getBoolean("caseSensitive", true);
	}


	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		/*
		 * TODO implement
		 */

		String line = value.toString();
		
		if (!caseSensitive) {
			line = line.toLowerCase();
		}

		for (String word : line.split("\\W+")) {
			if (word.length() > 0) {
				context.write(new Text(word.charAt(0) + ""), new IntWritable(word.length()));
			}
		}

	}
}
