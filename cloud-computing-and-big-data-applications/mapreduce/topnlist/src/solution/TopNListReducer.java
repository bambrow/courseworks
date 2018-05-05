package solution;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TopNListReducer extends Reducer<NullWritable, Text, IntWritable, Text> {
	
	private int N = 10;
	private SortedMap<Integer, String> topN = new TreeMap<Integer, String>();
	private Map<Integer, String> lookup = new HashMap<Integer, String>();
	
	@Override
	protected void setup(Context context) 
			throws IOException, InterruptedException, FileNotFoundException {
		
		this.N = context.getConfiguration().getInt("N", 10);
		
		File movieFile = new File("movie_titles.txt");
		BufferedReader br = new BufferedReader(new FileReader(movieFile));
		String line;
		
		while ((line = br.readLine()) != null) {

			String[] items = line.trim().split(",", 3);
			
			if (items.length != 3) {
				continue;
			}
			
			int id = Integer.parseInt(items[0]);
			String title = items[2];
			lookup.put(id, title);
			
		}
		
		br.close();
		
	}
	
	@Override
	public void reduce(NullWritable key, Iterable<Text> values, Context context) 
		throws IOException, InterruptedException {
		
		for (Text value : values) {
			
			String[] items = value.toString().trim().split(",");
			String id = items[0];
			Integer rating = Integer.parseInt(items[1]);
			topN.put(rating, id);
			if (topN.size() > N) {
				topN.remove(topN.firstKey());
			}
					
		}
		
		List<Integer> keys = new ArrayList<Integer>(topN.keySet());
		for (int i = keys.size() - 1; i >= 0; i--) {
			int id = Integer.parseInt(topN.get(keys.get(i)));
			context.write(new IntWritable(keys.get(i)), new Text(lookup.get(id)));
		}
		
	}
	
}
