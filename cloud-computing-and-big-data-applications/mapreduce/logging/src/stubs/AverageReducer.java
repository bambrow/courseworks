package stubs;
import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AverageReducer extends Reducer<Text, IntWritable, Text, DoubleWritable> {

	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {

		/*
		 * TODO implement
		 */

		int sum = 0;
		int count = 0;

		for (IntWritable value : values) {
			sum += value.get();
			count += 1;
		}

		double avg = 1.0 * sum / count;
		context.write(key, new DoubleWritable(avg));

	}
}