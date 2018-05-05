package stubs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.SequenceFile.Writer;

public class SequenceFileWriter {
  
  public static void main(String[] args) throws IOException {

    Configuration conf = new Configuration();
    Path path = new Path(args[1]);

    Text key = new Text();
    Text value = new Text();
    BufferedReader reader = null;
    SequenceFile.Writer writer = null;
    
    try {
    	
      reader = new BufferedReader(new FileReader(new File(args[0])));
      writer = SequenceFile.createWriter(conf, Writer.file(path), 
    		  Writer.keyClass(key.getClass()), Writer.valueClass(value.getClass()));
      String line;
      
      while ((line = reader.readLine()) != null) {
    	String ip = line.split("-")[0].trim();
        key.set(ip);
        value.set(line);
        
        if (ip.length() > 0) {
        	System.out.printf("[%s]\t%s\t%s\n", writer.getLength(), key, value);
            writer.append(key, value);
        }
        
      }
      
    } finally {
      reader.close();
      IOUtils.closeStream(writer);
    }
  }
}
