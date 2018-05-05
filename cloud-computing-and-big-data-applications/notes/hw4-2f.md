# HW4-2F

```
[training@localhost src]$ ls
hints  lfa.jar  solution  stubs
[training@localhost src]$ hadoop jar lfa.jar stubs.ProcessLogs \
> testlog outputs/testlog
17/02/15 12:36:57 INFO client.RMProxy: Connecting to ResourceManager at /0.0.0.0:8032
17/02/15 12:36:58 INFO input.FileInputFormat: Total input paths to process : 1
17/02/15 12:36:59 INFO mapreduce.JobSubmitter: number of splits:1
17/02/15 12:36:59 INFO mapreduce.JobSubmitter: Submitting tokens for job: job_1487176799402_0001
17/02/15 12:36:59 INFO impl.YarnClientImpl: Submitted application application_1487176799402_0001
17/02/15 12:37:00 INFO mapreduce.Job: The url to track the job: http://localhost:8088/proxy/application_1487176799402_0001/
17/02/15 12:37:00 INFO mapreduce.Job: Running job: job_1487176799402_0001
17/02/15 12:37:13 INFO mapreduce.Job: Job job_1487176799402_0001 running in uber mode : false
17/02/15 12:37:13 INFO mapreduce.Job:  map 0% reduce 0%
17/02/15 12:37:22 INFO mapreduce.Job:  map 100% reduce 0%
17/02/15 12:37:32 INFO mapreduce.Job:  map 100% reduce 100%
17/02/15 12:37:33 INFO mapreduce.Job: Job job_1487176799402_0001 completed successfully
17/02/15 12:37:33 INFO mapreduce.Job: Counters: 49
	File System Counters
		FILE: Number of bytes read=100600
		FILE: Number of bytes written=423825
		FILE: Number of read operations=0
		FILE: Number of large read operations=0
		FILE: Number of write operations=0
		HDFS: Number of bytes read=485370
		HDFS: Number of bytes written=178
		HDFS: Number of read operations=6
		HDFS: Number of large read operations=0
		HDFS: Number of write operations=2
	Job Counters
		Launched map tasks=1
		Launched reduce tasks=1
		Data-local map tasks=1
		Total time spent by all maps in occupied slots (ms)=0
		Total time spent by all reduces in occupied slots (ms)=0
		Total time spent by all map tasks (ms)=6874
		Total time spent by all reduce tasks (ms)=7034
		Total vcore-seconds taken by all map tasks=6874
		Total vcore-seconds taken by all reduce tasks=7034
		Total megabyte-seconds taken by all map tasks=1759744
		Total megabyte-seconds taken by all reduce tasks=3601408
	Map-Reduce Framework
		Map input records=5000
		Map output records=5000
		Map output bytes=90594
		Map output materialized bytes=100600
		Input split bytes=124
		Combine input records=0
		Combine output records=0
		Reduce input groups=10
		Reduce shuffle bytes=100600
		Reduce input records=5000
		Reduce output records=10
		Spilled Records=10000
		Shuffled Maps =1
		Failed Shuffles=0
		Merged Map outputs=1
		GC time elapsed (ms)=159
		CPU time spent (ms)=2520
		Physical memory (bytes) snapshot=352010240
		Virtual memory (bytes) snapshot=1888268288
		Total committed heap usage (bytes)=216465408
	Shuffle Errors
		BAD_ID=0
		CONNECTION=0
		IO_ERROR=0
		WRONG_LENGTH=0
		WRONG_MAP=0
		WRONG_REDUCE=0
	File Input Format Counters
		Bytes Read=485246
	File Output Format Counters
		Bytes Written=178
[training@localhost src]$ hadoop fs -cat outputs/testlog/*
10.114.184.86	1
10.130.195.163	21
10.150.176.47	12
10.153.239.5	547
10.187.129.140	18
10.207.190.45	21
10.211.47.159	2860
10.216.113.172	1196
10.223.157.186	115
10.82.30.199	209
```
