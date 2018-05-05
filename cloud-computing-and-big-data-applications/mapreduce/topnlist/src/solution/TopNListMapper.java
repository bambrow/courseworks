package solution;

import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TopNListMapper extends Mapper<LongWritable, Text, NullWritable, Text> {

	private int N = 10;
	private SortedMap<Integer, String> topN = new TreeMap<Integer, String>();
	
	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		this.N = context.getConfiguration().getInt("N", 10);
	}
	
	@Override
	public void map(LongWritable key, Text value, Context context) 
		throws IOException, InterruptedException {
		
		String[] items = value.toString().trim().split("\\W+");
		String id_count = items[0] + "," + items[1];
		int rating = Integer.parseInt(items[1]);
		topN.put(rating, id_count);
		
		if (topN.size() > N) {
			topN.remove(topN.firstKey());
		}
		
	}
	
	@Override
	protected void cleanup(Context context) throws IOException, InterruptedException {
		for (String s : topN.values()) {
			context.write(NullWritable.get(), new Text(s));
		}
	}
	
}
