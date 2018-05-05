package stubs;

import static org.junit.Assert.assertEquals;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.junit.Test;

public class SentimentPartitionTest {

	SentimentPartitioner mpart;

	@Test
	public void testSentimentPartition() {

		mpart = new SentimentPartitioner();
		mpart.setConf(new Configuration());
		int result;		

		/*
		 * Test the words "love", "deadly", and "zodiac". 
		 * The expected outcomes should be 0, 1, and 2. 
		 */
		

		/*
		 * TODO implement
		 */      
		
		result = mpart.getPartition(new Text("love"), new IntWritable(1), 3);
		assertEquals("\"love\" should return 0!", 0, result);
		
		result = mpart.getPartition(new Text("deadly"), new IntWritable(1), 3);
		assertEquals("\"deadly\" should return 1!", 1, result);
		
		result = mpart.getPartition(new Text("zodiac"), new IntWritable(1), 3);
		assertEquals("\"zodiac\" should return 2!", 2, result);

	}

}
