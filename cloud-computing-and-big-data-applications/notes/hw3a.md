# HW3A

```
[training@localhost src]$ ls
hints  solution  stubs
[training@localhost src]$ cd stubs
[training@localhost stubs]$ touch text_input.txt
[training@localhost stubs]$ ls
AverageReducer.java  AvgWordLength.java  LetterMapper.java  text_input.txt
[training@localhost stubs]$ vi text_input.txt
[training@localhost stubs]$ cat text_input.txt
No now is definitely not the best time
[training@localhost stubs]$ hadoop fs -put text_input.txt testfiles/text_input.txt
[training@localhost stubs]$ hadoop fs -ls testfiles
Found 1 items
-rw-rw-rw-   1 training supergroup         39 2017-02-06 00:33 testfiles/text_input.txt
[training@localhost stubs]$ cd ..
[training@localhost src]$ javac -classpath `hadoop classpath` stubs/*.java
Note: stubs/AvgWordLength.java uses or overrides a deprecated API.
Note: Recompile with -Xlint:deprecation for details.
[training@localhost src]$ jar cvf awl.jar stubs/*.class
added manifest
adding: stubs/AverageReducer.class(in = 1681) (out= 713)(deflated 57%)
adding: stubs/AvgWordLength.class(in = 1701) (out= 911)(deflated 46%)
adding: stubs/LetterMapper.class(in = 1916) (out= 805)(deflated 57%)
[training@localhost src]$ ls
awl.jar  hints  solution  stubs
[training@localhost src]$ hadoop jar awl.jar stubs.AvgWordLength \
> testfiles/text_input.txt testresults
17/02/06 00:36:18 INFO client.RMProxy: Connecting to ResourceManager at /0.0.0.0:8032
17/02/06 00:36:19 WARN mapreduce.JobSubmitter: Hadoop command-line option parsing not performed. Implement the Tool interface and execute your application with ToolRunner to remedy this.
17/02/06 00:36:20 INFO input.FileInputFormat: Total input paths to process : 1
17/02/06 00:36:20 INFO mapreduce.JobSubmitter: number of splits:1
17/02/06 00:36:20 INFO mapreduce.JobSubmitter: Submitting tokens for job: job_1486337258252_0001
17/02/06 00:36:21 INFO impl.YarnClientImpl: Submitted application application_1486337258252_0001
17/02/06 00:36:21 INFO mapreduce.Job: The url to track the job: http://localhost:8088/proxy/application_1486337258252_0001/
17/02/06 00:36:21 INFO mapreduce.Job: Running job: job_1486337258252_0001
17/02/06 00:36:34 INFO mapreduce.Job: Job job_1486337258252_0001 running in uber mode : false
17/02/06 00:36:34 INFO mapreduce.Job:  map 0% reduce 0%
17/02/06 00:36:42 INFO mapreduce.Job:  map 100% reduce 0%
17/02/06 00:36:52 INFO mapreduce.Job:  map 100% reduce 100%
17/02/06 00:36:53 INFO mapreduce.Job: Job job_1486337258252_0001 completed successfully
17/02/06 00:36:53 INFO mapreduce.Job: Counters: 49
	File System Counters
		FILE: Number of bytes read=70
		FILE: Number of bytes written=222513
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
		Total time spent by all map tasks (ms)=6082
		Total time spent by all reduce tasks (ms)=6576
		Total vcore-seconds taken by all map tasks=6082
		Total vcore-seconds taken by all reduce tasks=6576
		Total megabyte-seconds taken by all map tasks=1556992
		Total megabyte-seconds taken by all reduce tasks=3366912
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
		GC time elapsed (ms)=132
		CPU time spent (ms)=1450
		Physical memory (bytes) snapshot=354017280
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
[training@localhost src]$ hadoop fs -ls
Found 6 items
drwxrwxrwx   - training supergroup          0 2017-02-01 18:09 shakespeare
drwxrwxrwx   - training supergroup          0 2017-02-06 00:33 testfiles
drwxrwxrwx   - training supergroup          0 2017-02-01 18:08 testlog
drwxrwxrwx   - training supergroup          0 2017-02-06 00:36 testresults
drwxrwxrwx   - training supergroup          0 2017-02-01 18:07 weblog
drwxrwxrwx   - training supergroup          0 2017-02-01 18:49 wordcounts
[training@localhost src]$ hadoop fs -cat testresults/part-r-00000
N	2.0
b	4.0
d	10.0
i	2.0
n	3.0
t	3.5
```
