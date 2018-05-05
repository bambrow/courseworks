package stubs;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class IndexMapper extends Mapper<Text, Text, Text, Text> {

  @Override
  public void map(Text key, Text value, Context context) throws IOException,
      InterruptedException {

    /*
     * TODO implement
     */
    
	FileSplit fileSplit = (FileSplit) context.getInputSplit();
	String fileName = fileSplit.getPath().getName().trim();
	
	String line = value.toString();
	
	for (String word : line.split("\\W+")) {
		if (word.length() > 0) {
			context.write(new Text(word.trim().toLowerCase()), new Text(fileName + "@" + key.toString()));
		}
	}
	  
	  
  }
}