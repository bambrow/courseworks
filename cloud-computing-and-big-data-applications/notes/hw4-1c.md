# HW4-1C

```
[training@localhost src]$ hadoop jar awl.jar stubs.AvgWordLength -D caseSensitive=false shakespeare avgwordlength_lowercase
17/02/14 00:39:42 INFO client.RMProxy: Connecting to ResourceManager at /0.0.0.0:8032
17/02/14 00:39:43 INFO input.FileInputFormat: Total input paths to process : 4
17/02/14 00:39:43 INFO mapreduce.JobSubmitter: number of splits:4
17/02/14 00:39:44 INFO mapreduce.JobSubmitter: Submitting tokens for job: job_1487041143874_0003
17/02/14 00:39:44 INFO impl.YarnClientImpl: Submitted application application_1487041143874_0003
17/02/14 00:39:44 INFO mapreduce.Job: The url to track the job: http://localhost:8088/proxy/application_1487041143874_0003/
17/02/14 00:39:44 INFO mapreduce.Job: Running job: job_1487041143874_0003
17/02/14 00:39:55 INFO mapreduce.Job: Job job_1487041143874_0003 running in uber mode : false
17/02/14 00:39:55 INFO mapreduce.Job:  map 0% reduce 0%
17/02/14 00:40:04 INFO mapreduce.Job:  map 25% reduce 0%
17/02/14 00:40:14 INFO mapreduce.Job:  map 50% reduce 0%
17/02/14 00:40:23 INFO mapreduce.Job:  map 75% reduce 0%
17/02/14 00:40:31 INFO mapreduce.Job:  map 100% reduce 0%
17/02/14 00:40:42 INFO mapreduce.Job:  map 100% reduce 100%
17/02/14 00:40:42 INFO mapreduce.Job: Job job_1487041143874_0003 completed successfully
17/02/14 00:40:43 INFO mapreduce.Job: Counters: 49
	File System Counters
		FILE: Number of bytes read=7715630
		FILE: Number of bytes written=15988678
		FILE: Number of read operations=0
		FILE: Number of large read operations=0
		FILE: Number of write operations=0
		HDFS: Number of bytes read=5284714
		HDFS: Number of bytes written=589
		HDFS: Number of read operations=15
		HDFS: Number of large read operations=0
		HDFS: Number of write operations=2
	Job Counters
		Launched map tasks=4
		Launched reduce tasks=1
		Data-local map tasks=4
		Total time spent by all maps in occupied slots (ms)=0
		Total time spent by all reduces in occupied slots (ms)=0
		Total time spent by all map tasks (ms)=30380
		Total time spent by all reduce tasks (ms)=8672
		Total vcore-seconds taken by all map tasks=30380
		Total vcore-seconds taken by all reduce tasks=8672
		Total megabyte-seconds taken by all map tasks=7777280
		Total megabyte-seconds taken by all reduce tasks=4440064
	Map-Reduce Framework
		Map input records=173126
		Map output records=964453
		Map output bytes=5786718
		Map output materialized bytes=7715648
		Input split bytes=483
		Combine input records=0
		Combine output records=0
		Reduce input groups=35
		Reduce shuffle bytes=7715648
		Reduce input records=964453
		Reduce output records=35
		Spilled Records=1928906
		Shuffled Maps =4
		Failed Shuffles=0
		Merged Map outputs=4
		GC time elapsed (ms)=446
		CPU time spent (ms)=11720
		Physical memory (bytes) snapshot=1038774272
		Virtual memory (bytes) snapshot=4407648256
		Total committed heap usage (bytes)=677707776
	Shuffle Errors
		BAD_ID=0
		CONNECTION=0
		IO_ERROR=0
		WRONG_LENGTH=0
		WRONG_MAP=0
		WRONG_REDUCE=0
	File Input Format Counters
		Bytes Read=5284231
	File Output Format Counters
		Bytes Written=589
[training@localhost src]$ hadoop fs -ls
Found 8 items
drwxrwxrwx   - training supergroup          0 2017-02-06 00:45 avgwordlength
drwxrwxrwx   - training supergroup          0 2017-02-14 00:40 avgwordlength_lowercase
drwxrwxrwx   - training supergroup          0 2017-02-01 18:09 shakespeare
drwxrwxrwx   - training supergroup          0 2017-02-06 00:33 testfiles
drwxrwxrwx   - training supergroup          0 2017-02-01 18:08 testlog
drwxrwxrwx   - training supergroup          0 2017-02-14 00:32 testresults
drwxrwxrwx   - training supergroup          0 2017-02-01 18:07 weblog
drwxrwxrwx   - training supergroup          0 2017-02-01 18:49 wordcounts
[training@localhost src]$ hadoop fs -cat avgwordlength_lowercase/part-r-00000
1	1.02
2	1.0588235294117647
3	1.0
4	1.5
5	1.5
6	1.5
7	1.0
8	1.5
9	1.0
a	3.275899648342265
b	4.43676859192148
c	6.204073527743107
d	4.306200411401704
e	5.307238813182565
f	4.87378966665806
g	5.163681818181818
h	3.966131770968778
i	2.1290417039114753
j	5.148983570036202
k	4.622562809195848
l	4.454545454545454
m	3.990697595029614
n	3.749964544036307
o	2.8046206567868732
p	6.209215222076958
q	5.852795739825028
r	5.854965809182676
s	4.48601978893492
t	3.772103219434822
u	4.588696504410324
v	5.540627750073336
w	4.373096283946263
x	3.1650485436893203
y	3.51717399473882
z	5.053333333333334
[training@localhost src]$ hadoop fs -cat avgwordlength_lowercase/part-r-00000 | grep -E '^a|^w|^z'
a	3.275899648342265
w	4.373096283946263
z	5.053333333333334
```
