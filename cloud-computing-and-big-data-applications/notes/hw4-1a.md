# HW4-1A

```
[training@localhost src]$ hadoop jar awl.jar stubs.AvgWordLength testfiles testresults
17/02/14 00:05:34 INFO client.RMProxy: Connecting to ResourceManager at /0.0.0.0:8032
17/02/14 00:05:35 INFO input.FileInputFormat: Total input paths to process : 1
17/02/14 00:05:36 INFO mapreduce.JobSubmitter: number of splits:1
17/02/14 00:05:36 INFO mapreduce.JobSubmitter: Submitting tokens for job: job_1487041143874_0001
17/02/14 00:05:37 INFO impl.YarnClientImpl: Submitted application application_1487041143874_0001
17/02/14 00:05:37 INFO mapreduce.Job: The url to track the job: http://localhost:8088/proxy/application_1487041143874_0001/
17/02/14 00:05:37 INFO mapreduce.Job: Running job: job_1487041143874_0001
17/02/14 00:05:51 INFO mapreduce.Job: Job job_1487041143874_0001 running in uber mode : false
17/02/14 00:05:51 INFO mapreduce.Job:  map 0% reduce 0%
17/02/14 00:06:00 INFO mapreduce.Job:  map 100% reduce 0%
17/02/14 00:06:09 INFO mapreduce.Job:  map 100% reduce 100%
17/02/14 00:06:10 INFO mapreduce.Job: Job job_1487041143874_0001 completed successfully
17/02/14 00:06:10 INFO mapreduce.Job: Counters: 49
	File System Counters
		FILE: Number of bytes read=70
		FILE: Number of bytes written=222787
		FILE: Number of read operations=0
		FILE: Number of large read operations=0
		FILE: Number of write operations=0
		HDFS: Number of bytes read=164
		HDFS: Number of bytes written=37
		HDFS: Number of read operations=6
		HDFS: Number of large read operations=0
		HDFS: Number of write operations=2
	Job Counters
		Launched map tasks=1
		Launched reduce tasks=1
		Data-local map tasks=1
		Total time spent by all maps in occupied slots (ms)=0
		Total time spent by all reduces in occupied slots (ms)=0
		Total time spent by all map tasks (ms)=6096
		Total time spent by all reduce tasks (ms)=6699
		Total vcore-seconds taken by all map tasks=6096
		Total vcore-seconds taken by all reduce tasks=6699
		Total megabyte-seconds taken by all map tasks=1560576
		Total megabyte-seconds taken by all reduce tasks=3429888
	Map-Reduce Framework
		Map input records=1
		Map output records=8
		Map output bytes=48
		Map output materialized bytes=70
		Input split bytes=125
		Combine input records=0
		Combine output records=0
		Reduce input groups=6
		Reduce shuffle bytes=70
		Reduce input records=8
		Reduce output records=6
		Spilled Records=16
		Shuffled Maps =1
		Failed Shuffles=0
		Merged Map outputs=1
		GC time elapsed (ms)=155
		CPU time spent (ms)=1480
		Physical memory (bytes) snapshot=356421632
		Virtual memory (bytes) snapshot=1888108544
		Total committed heap usage (bytes)=216465408
	Shuffle Errors
		BAD_ID=0
		CONNECTION=0
		IO_ERROR=0
		WRONG_LENGTH=0
		WRONG_MAP=0
		WRONG_REDUCE=0
	File Input Format Counters
		Bytes Read=39
	File Output Format Counters
		Bytes Written=37
[training@localhost src]$ hadoop fs -ls testresults
Found 2 items
-rw-rw-rw-   1 training supergroup          0 2017-02-14 00:06 testresults/_SUCCESS
-rw-rw-rw-   1 training supergroup         37 2017-02-14 00:06 testresults/part-r-00000
[training@localhost src]$ hadoop fs -cat testresults/part-r-00000
N	2.0
b	4.0
d	10.0
i	2.0
n	3.0
t	3.5
```
