# HW6B1-HDFS

```
org.apache.hadoop.io.LongWritable
org.apache.hadoop.io.Text
org.apache.hadoop.io.compress.SnappyCodec
```

```
[training@localhost src]$ hadoop jar csf.jar stubs.CreateSequenceFile weblog outputs/uncompressedsf
17/03/03 13:24:28 INFO client.RMProxy: Connecting to ResourceManager at /0.0.0.0:8032
17/03/03 13:24:29 INFO input.FileInputFormat: Total input paths to process : 1
17/03/03 13:24:29 INFO mapreduce.JobSubmitter: number of splits:4
17/03/03 13:24:30 INFO mapreduce.JobSubmitter: Submitting tokens for job: job_1488554522287_0003
17/03/03 13:24:30 INFO impl.YarnClientImpl: Submitted application application_1488554522287_0003
17/03/03 13:24:30 INFO mapreduce.Job: The url to track the job: http://localhost:8088/proxy/application_1488554522287_0003/
17/03/03 13:24:30 INFO mapreduce.Job: Running job: job_1488554522287_0003
17/03/03 13:24:41 INFO mapreduce.Job: Job job_1488554522287_0003 running in uber mode : false
17/03/03 13:24:41 INFO mapreduce.Job:  map 0% reduce 0%
17/03/03 13:24:53 INFO mapreduce.Job:  map 25% reduce 0%
17/03/03 13:25:04 INFO mapreduce.Job:  map 50% reduce 0%
17/03/03 13:25:16 INFO mapreduce.Job:  map 75% reduce 0%
17/03/03 13:25:26 INFO mapreduce.Job:  map 100% reduce 0%
17/03/03 13:25:26 INFO mapreduce.Job: Job job_1488554522287_0003 completed successfully
17/03/03 13:25:26 INFO mapreduce.Job: Counters: 30
	File System Counters
		FILE: Number of bytes read=0
		FILE: Number of bytes written=443684
		FILE: Number of read operations=0
		FILE: Number of large read operations=0
		FILE: Number of write operations=0
		HDFS: Number of bytes read=504954292
		HDFS: Number of bytes written=582639761
		HDFS: Number of read operations=20
		HDFS: Number of large read operations=0
		HDFS: Number of write operations=8
	Job Counters
		Launched map tasks=4
		Data-local map tasks=4
		Total time spent by all maps in occupied slots (ms)=0
		Total time spent by all reduces in occupied slots (ms)=0
		Total time spent by all map tasks (ms)=38876
		Total vcore-seconds taken by all map tasks=38876
		Total megabyte-seconds taken by all map tasks=9952256
	Map-Reduce Framework
		Map input records=4477843
		Map output records=4477843
		Input split bytes=472
		Spilled Records=0
		Failed Shuffles=0
		Merged Map outputs=0
		GC time elapsed (ms)=323
		CPU time spent (ms)=13860
		Physical memory (bytes) snapshot=490319872
		Virtual memory (bytes) snapshot=3373903872
		Total committed heap usage (bytes)=250871808
	File Input Format Counters
		Bytes Read=504953820
	File Output Format Counters
		Bytes Written=582639761
[training@localhost src]$ hadoop fs -ls outputs/uncompressedsf
Found 5 items
-rw-rw-rw-   1 training supergroup          0 2017-03-03 13:25 outputs/uncompressedsf/_SUCCESS
-rw-rw-rw-   1 training supergroup  155035085 2017-03-03 13:24 outputs/uncompressedsf/part-m-00000
-rw-rw-rw-   1 training supergroup  154734268 2017-03-03 13:25 outputs/uncompressedsf/part-m-00001
-rw-rw-rw-   1 training supergroup  154831861 2017-03-03 13:25 outputs/uncompressedsf/part-m-00002
-rw-rw-rw-   1 training supergroup  118038547 2017-03-03 13:25 outputs/uncompressedsf/part-m-00003
```
