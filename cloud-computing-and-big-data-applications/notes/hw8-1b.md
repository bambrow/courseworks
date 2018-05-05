# HW8-1B

```
$ hadoop jar tnl.jar solution.AggregateCountsDriver -fs=file:/// -jt=local -files movie_titles.txt ~/netflix_subset/SimpleRatings.txt ~/netflix_output/SimpleRatings_inter
17/03/29 02:05:56 INFO Configuration.deprecation: session.id is deprecated. Instead, use dfs.metrics.session-id
17/03/29 02:05:56 INFO jvm.JvmMetrics: Initializing JVM Metrics with processName=JobTracker, sessionId=
17/03/29 02:05:57 INFO input.FileInputFormat: Total input paths to process : 1
17/03/29 02:05:57 INFO mapreduce.JobSubmitter: number of splits:1
17/03/29 02:05:57 INFO mapreduce.JobSubmitter: Submitting tokens for job: job_local1320485968_0001
17/03/29 02:05:58 INFO mapred.LocalDistributedCacheManager: Localized file:/home/training/workspace/topnlist/src/movie_titles.txt as file:/var/lib/hadoop-hdfs/cache/training/mapred/local/1490771157933/movie_titles.txt
17/03/29 02:05:58 INFO mapreduce.Job: The url to track the job: http://localhost:8080/
17/03/29 02:05:58 INFO mapreduce.Job: Running job: job_local1320485968_0001
17/03/29 02:05:58 INFO mapred.LocalJobRunner: OutputCommitter set in config null
17/03/29 02:05:58 INFO output.FileOutputCommitter: File Output Committer Algorithm version is 1
17/03/29 02:05:58 INFO mapred.LocalJobRunner: OutputCommitter is org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter
17/03/29 02:05:58 INFO mapred.LocalJobRunner: Waiting for map tasks
17/03/29 02:05:58 INFO mapred.LocalJobRunner: Starting task: attempt_local1320485968_0001_m_000000_0
17/03/29 02:05:58 INFO output.FileOutputCommitter: File Output Committer Algorithm version is 1
17/03/29 02:05:58 INFO mapred.Task:  Using ResourceCalculatorProcessTree : [ ]
17/03/29 02:05:58 INFO mapred.MapTask: Processing split: file:/home/training/netflix_subset/SimpleRatings.txt:0+158
17/03/29 02:05:59 INFO mapred.MapTask: (EQUATOR) 0 kvi 26214396(104857584)
17/03/29 02:05:59 INFO mapred.MapTask: mapreduce.task.io.sort.mb: 100
17/03/29 02:05:59 INFO mapred.MapTask: soft limit at 83886080
17/03/29 02:05:59 INFO mapred.MapTask: bufstart = 0; bufvoid = 104857600
17/03/29 02:05:59 INFO mapred.MapTask: kvstart = 26214396; length = 6553600
17/03/29 02:05:59 INFO mapred.MapTask: Map output collector class = org.apache.hadoop.mapred.MapTask$MapOutputBuffer
17/03/29 02:05:59 INFO mapred.LocalJobRunner:
17/03/29 02:05:59 INFO mapred.MapTask: Starting flush of map output
17/03/29 02:05:59 INFO mapred.MapTask: Spilling map output
17/03/29 02:05:59 INFO mapred.MapTask: bufstart = 0; bufend = 66; bufvoid = 104857600
17/03/29 02:05:59 INFO mapred.MapTask: kvstart = 26214396(104857584); kvend = 26214356(104857424); length = 41/6553600
17/03/29 02:05:59 INFO mapred.MapTask: Finished spill 0
17/03/29 02:05:59 INFO mapred.Task: Task:attempt_local1320485968_0001_m_000000_0 is done. And is in the process of committing
17/03/29 02:05:59 INFO mapred.LocalJobRunner: map
17/03/29 02:05:59 INFO mapred.Task: Task 'attempt_local1320485968_0001_m_000000_0' done.
17/03/29 02:05:59 INFO mapred.LocalJobRunner: Finishing task: attempt_local1320485968_0001_m_000000_0
17/03/29 02:05:59 INFO mapred.LocalJobRunner: map task executor complete.
17/03/29 02:05:59 INFO mapred.LocalJobRunner: Waiting for reduce tasks
17/03/29 02:05:59 INFO mapred.LocalJobRunner: Starting task: attempt_local1320485968_0001_r_000000_0
17/03/29 02:05:59 INFO output.FileOutputCommitter: File Output Committer Algorithm version is 1
17/03/29 02:05:59 INFO mapred.Task:  Using ResourceCalculatorProcessTree : [ ]
17/03/29 02:05:59 INFO mapred.ReduceTask: Using ShuffleConsumerPlugin: org.apache.hadoop.mapreduce.task.reduce.Shuffle@45605b09
17/03/29 02:05:59 INFO reduce.MergeManagerImpl: MergerManager: memoryLimit=709551680, maxSingleShuffleLimit=177387920, mergeThreshold=468304128, ioSortFactor=10, memToMemMergeOutputsThreshold=10
17/03/29 02:05:59 INFO reduce.EventFetcher: attempt_local1320485968_0001_r_000000_0 Thread started: EventFetcher for fetching Map Completion Events
17/03/29 02:05:59 INFO reduce.LocalFetcher: localfetcher#1 about to shuffle output of map attempt_local1320485968_0001_m_000000_0 decomp: 42 len: 46 to MEMORY
17/03/29 02:05:59 INFO reduce.InMemoryMapOutput: Read 42 bytes from map-output for attempt_local1320485968_0001_m_000000_0
17/03/29 02:05:59 INFO reduce.MergeManagerImpl: closeInMemoryFile -> map-output of size: 42, inMemoryMapOutputs.size() -> 1, commitMemory -> 0, usedMemory ->42
17/03/29 02:05:59 WARN io.ReadaheadPool: Failed readahead on ifile
EBADF: Bad file descriptor
	at org.apache.hadoop.io.nativeio.NativeIO$POSIX.posix_fadvise(Native Method)
	at org.apache.hadoop.io.nativeio.NativeIO$POSIX.posixFadviseIfPossible(NativeIO.java:267)
	at org.apache.hadoop.io.nativeio.NativeIO$POSIX$CacheManipulator.posixFadviseIfPossible(NativeIO.java:146)
	at org.apache.hadoop.io.ReadaheadPool$ReadaheadRequestImpl.run(ReadaheadPool.java:206)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
	at java.lang.Thread.run(Thread.java:745)
17/03/29 02:05:59 INFO reduce.EventFetcher: EventFetcher is interrupted.. Returning
17/03/29 02:05:59 INFO mapred.LocalJobRunner: 1 / 1 copied.
17/03/29 02:05:59 INFO reduce.MergeManagerImpl: finalMerge called with 1 in-memory map-outputs and 0 on-disk map-outputs
17/03/29 02:05:59 INFO mapred.Merger: Merging 1 sorted segments
17/03/29 02:05:59 INFO mapred.Merger: Down to the last merge-pass, with 1 segments left of total size: 38 bytes
17/03/29 02:05:59 INFO reduce.MergeManagerImpl: Merged 1 segments, 42 bytes to disk to satisfy reduce memory limit
17/03/29 02:05:59 INFO reduce.MergeManagerImpl: Merging 1 files, 46 bytes from disk
17/03/29 02:05:59 INFO reduce.MergeManagerImpl: Merging 0 segments, 0 bytes from memory into reduce
17/03/29 02:05:59 INFO mapred.Merger: Merging 1 sorted segments
17/03/29 02:05:59 INFO mapred.Merger: Down to the last merge-pass, with 1 segments left of total size: 38 bytes
17/03/29 02:05:59 INFO mapred.LocalJobRunner: 1 / 1 copied.
17/03/29 02:05:59 INFO Configuration.deprecation: mapred.skip.on is deprecated. Instead, use mapreduce.job.skiprecords
17/03/29 02:05:59 INFO mapred.Task: Task:attempt_local1320485968_0001_r_000000_0 is done. And is in the process of committing
17/03/29 02:05:59 INFO mapred.LocalJobRunner: 1 / 1 copied.
17/03/29 02:05:59 INFO mapred.Task: Task attempt_local1320485968_0001_r_000000_0 is allowed to commit now
17/03/29 02:05:59 INFO output.FileOutputCommitter: Saved output of task 'attempt_local1320485968_0001_r_000000_0' to file:/home/training/netflix_output/SimpleRatings_inter/_temporary/0/task_local1320485968_0001_r_000000
17/03/29 02:05:59 INFO mapred.LocalJobRunner: reduce > reduce
17/03/29 02:05:59 INFO mapred.Task: Task 'attempt_local1320485968_0001_r_000000_0' done.
17/03/29 02:05:59 INFO mapred.LocalJobRunner: Finishing task: attempt_local1320485968_0001_r_000000_0
17/03/29 02:05:59 INFO mapred.LocalJobRunner: reduce task executor complete.
17/03/29 02:05:59 INFO mapreduce.Job: Job job_local1320485968_0001 running in uber mode : false
17/03/29 02:05:59 INFO mapreduce.Job:  map 100% reduce 100%
17/03/29 02:05:59 INFO mapreduce.Job: Job job_local1320485968_0001 completed successfully
17/03/29 02:05:59 INFO mapreduce.Job: Counters: 33
	File System Counters
		FILE: Number of bytes read=1207940
		FILE: Number of bytes written=1783588
		FILE: Number of read operations=0
		FILE: Number of large read operations=0
		FILE: Number of write operations=0
	Map-Reduce Framework
		Map input records=11
		Map output records=11
		Map output bytes=66
		Map output materialized bytes=46
		Input split bytes=117
		Combine input records=11
		Combine output records=5
		Reduce input groups=5
		Reduce shuffle bytes=46
		Reduce input records=5
		Reduce output records=5
		Spilled Records=10
		Shuffled Maps =1
		Failed Shuffles=0
		Merged Map outputs=1
		GC time elapsed (ms)=45
		CPU time spent (ms)=0
		Physical memory (bytes) snapshot=0
		Virtual memory (bytes) snapshot=0
		Total committed heap usage (bytes)=335290368
	Shuffle Errors
		BAD_ID=0
		CONNECTION=0
		IO_ERROR=0
		WRONG_LENGTH=0
		WRONG_MAP=0
		WRONG_REDUCE=0
	File Input Format Counters
		Bytes Read=158
	File Output Format Counters
		Bytes Written=34

$ cat ~/netflix_output/SimpleRatings_inter/*
1	2
3	3
5	13
8	14
9	9
```
