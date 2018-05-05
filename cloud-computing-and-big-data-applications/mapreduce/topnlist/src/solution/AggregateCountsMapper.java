package solution;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AggregateCountsMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	private Text K = new Text();
	private IntWritable V = new IntWritable();
	
	@Override
	public void map(LongWritable key, Text value, Context context) 
		throws IOException, InterruptedException {
		
		String[] items = value.toString().trim().split(",");
		
		if (items.length != 3) {
			return;
		} 
		
		String movieID = items[0];
		int rating = (int) Double.parseDouble(items[2]);
		K.set(movieID);
		V.set(rating);
		context.write(K, V);
		
	}
	
}
