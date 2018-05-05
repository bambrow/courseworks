package stubs;

import java.io.IOException;

import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Reducer;

/**
 * On input, the reducer receives a word as the key and a set
 * of locations in the form "play name@line number" for the values. 
 * The reducer builds a readable string in the valueList variable that
 * contains an index of all the locations of the word. 
 */
public class IndexReducer extends Reducer<Text, Text, Text, Text> {

  @Override
  public void reduce(Text key, Iterable<Text> values, Context context)
      throws IOException, InterruptedException {

    /*
     * TODO implement
     */
	
	StringBuilder output = new StringBuilder();  
	
	for (Text value : values) {
		output.append(value.toString());
		output.append(",");
	}
	
	// delete the last comma, which is the last char in the string
	output.deleteCharAt(output.length() - 1);
	
    context.write(key, new Text(output.toString()));
	
  }
}