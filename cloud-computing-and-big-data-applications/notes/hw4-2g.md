# HW4-2G

```
[training@localhost src]$ hadoop jar lfa.jar stubs.ProcessLogs weblog outputs/weblog
17/02/15 12:44:12 INFO client.RMProxy: Connecting to ResourceManager at /0.0.0.0:8032
17/02/15 12:44:13 INFO input.FileInputFormat: Total input paths to process : 1
17/02/15 12:44:13 INFO mapreduce.JobSubmitter: number of splits:4
17/02/15 12:44:13 INFO mapreduce.JobSubmitter: Submitting tokens for job: job_1487176799402_0002
17/02/15 12:44:14 INFO impl.YarnClientImpl: Submitted application application_1487176799402_0002
17/02/15 12:44:14 INFO mapreduce.Job: The url to track the job: http://localhost:8088/proxy/application_1487176799402_0002/
17/02/15 12:44:14 INFO mapreduce.Job: Running job: job_1487176799402_0002
17/02/15 12:44:25 INFO mapreduce.Job: Job job_1487176799402_0002 running in uber mode : false
17/02/15 12:44:25 INFO mapreduce.Job:  map 0% reduce 0%
17/02/15 12:44:39 INFO mapreduce.Job:  map 17% reduce 0%
17/02/15 12:44:42 INFO mapreduce.Job:  map 25% reduce 0%
17/02/15 12:44:55 INFO mapreduce.Job:  map 50% reduce 0%
17/02/15 12:45:08 INFO mapreduce.Job:  map 75% reduce 0%
17/02/15 12:45:20 INFO mapreduce.Job:  map 100% reduce 0%
17/02/15 12:45:33 INFO mapreduce.Job:  map 100% reduce 67%
17/02/15 12:45:36 INFO mapreduce.Job:  map 100% reduce 77%
17/02/15 12:45:39 INFO mapreduce.Job:  map 100% reduce 100%
17/02/15 12:45:39 INFO mapreduce.Job: Job job_1487176799402_0002 completed successfully
17/02/15 12:45:39 INFO mapreduce.Job: Counters: 49
	File System Counters
		FILE: Number of bytes read=88596977
		FILE: Number of bytes written=177750607
		FILE: Number of read operations=0
		FILE: Number of large read operations=0
		FILE: Number of write operations=0
		HDFS: Number of bytes read=504954292
		HDFS: Number of bytes written=5307821
		HDFS: Number of read operations=15
		HDFS: Number of large read operations=0
		HDFS: Number of write operations=2
	Job Counters
		Launched map tasks=4
		Launched reduce tasks=1
		Data-local map tasks=4
		Total time spent by all maps in occupied slots (ms)=0
		Total time spent by all reduces in occupied slots (ms)=0
		Total time spent by all map tasks (ms)=46985
		Total time spent by all reduce tasks (ms)=17607
		Total vcore-seconds taken by all map tasks=46985
		Total vcore-seconds taken by all reduce tasks=17607
		Total megabyte-seconds taken by all map tasks=12028160
		Total megabyte-seconds taken by all reduce tasks=9014784
	Map-Reduce Framework
		Map input records=4477843
		Map output records=4477843
		Map output bytes=79641285
		Map output materialized bytes=88596995
		Input split bytes=472
		Combine input records=0
		Combine output records=0
		Reduce input groups=333923
		Reduce shuffle bytes=88596995
		Reduce input records=4477843
		Reduce output records=333923
		Spilled Records=8955686
		Shuffled Maps =4
		Failed Shuffles=0
		Merged Map outputs=4
		GC time elapsed (ms)=886
		CPU time spent (ms)=34750
		Physical memory (bytes) snapshot=1266352128
		Virtual memory (bytes) snapshot=4407828480
		Total committed heap usage (bytes)=910647296
	Shuffle Errors
		BAD_ID=0
		CONNECTION=0
		IO_ERROR=0
		WRONG_LENGTH=0
		WRONG_MAP=0
		WRONG_REDUCE=0
	File Input Format Counters
		Bytes Read=504953820
	File Output Format Counters
		Bytes Written=5307821
[training@localhost src]$ hadoop fs -cat outputs/weblog/part-r-00000 | wc -l
333923
[training@localhost src]$ hadoop fs -cat outputs/weblog/part-r-00000 | grep -Ew '^10.1.100.199|^10.1.100.5|^10.99.99.58'
10.1.100.199	35
10.1.100.5	1
10.99.99.58	21
```
