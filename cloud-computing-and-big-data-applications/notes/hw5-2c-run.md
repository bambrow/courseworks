# HW5-2C-RUN

```
[training@localhost src]$ hadoop jar wc_partitioner_no_combiner.jar stubs.WordCountDriver -fs=file:/// -jt=local -files positive-words.txt,negative-words.txt ~/datainput/shakespeare/poems ~/dataoutput/wc_partitioner_no_combiner17/02/22 23:20:34 INFO Configuration.deprecation: session.id is deprecated. Instead, use dfs.metrics.session-id
17/02/22 23:20:34 INFO jvm.JvmMetrics: Initializing JVM Metrics with processName=JobTracker, sessionId=
17/02/22 23:20:35 INFO input.FileInputFormat: Total input paths to process : 1
17/02/22 23:20:35 INFO mapreduce.JobSubmitter: number of splits:1
17/02/22 23:20:36 INFO mapreduce.JobSubmitter: Submitting tokens for job: job_local607471389_0001
17/02/22 23:20:36 INFO mapred.LocalDistributedCacheManager: Localized file:/home/training/workspace/combiner/src/positive-words.txt as file:/var/lib/hadoop-hdfs/cache/training/mapred/local/1487827236611/positive-words.txt
17/02/22 23:20:37 INFO mapred.LocalDistributedCacheManager: Localized file:/home/training/workspace/combiner/src/negative-words.txt as file:/var/lib/hadoop-hdfs/cache/training/mapred/local/1487827236612/negative-words.txt
17/02/22 23:20:37 INFO mapreduce.Job: The url to track the job: http://localhost:8080/
17/02/22 23:20:37 INFO mapreduce.Job: Running job: job_local607471389_0001
17/02/22 23:20:37 INFO mapred.LocalJobRunner: OutputCommitter set in config null
17/02/22 23:20:37 INFO output.FileOutputCommitter: File Output Committer Algorithm version is 1
17/02/22 23:20:37 INFO mapred.LocalJobRunner: OutputCommitter is org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter
17/02/22 23:20:37 INFO mapred.LocalJobRunner: Waiting for map tasks
17/02/22 23:20:37 INFO mapred.LocalJobRunner: Starting task: attempt_local607471389_0001_m_000000_0
17/02/22 23:20:37 INFO output.FileOutputCommitter: File Output Committer Algorithm version is 1
17/02/22 23:20:37 INFO mapred.Task:  Using ResourceCalculatorProcessTree : [ ]
17/02/22 23:20:37 INFO mapred.MapTask: Processing split: file:/home/training/datainput/shakespeare/poems:0+268140
17/02/22 23:20:37 INFO mapred.MapTask: (EQUATOR) 0 kvi 26214396(104857584)
17/02/22 23:20:37 INFO mapred.MapTask: mapreduce.task.io.sort.mb: 100
17/02/22 23:20:37 INFO mapred.MapTask: soft limit at 83886080
17/02/22 23:20:37 INFO mapred.MapTask: bufstart = 0; bufvoid = 104857600
17/02/22 23:20:37 INFO mapred.MapTask: kvstart = 26214396; length = 6553600
17/02/22 23:20:37 INFO mapred.MapTask: Map output collector class = org.apache.hadoop.mapred.MapTask$MapOutputBuffer
17/02/22 23:20:38 INFO mapreduce.Job: Job job_local607471389_0001 running in uber mode : false
17/02/22 23:20:38 INFO mapreduce.Job:  map 0% reduce 0%
17/02/22 23:20:38 INFO mapred.LocalJobRunner:
17/02/22 23:20:38 INFO mapred.MapTask: Starting flush of map output
17/02/22 23:20:38 INFO mapred.MapTask: Spilling map output
17/02/22 23:20:38 INFO mapred.MapTask: bufstart = 0; bufend = 458198; bufvoid = 104857600
17/02/22 23:20:38 INFO mapred.MapTask: kvstart = 26214396(104857584); kvend = 26013552(104054208); length = 200845/6553600
17/02/22 23:20:39 INFO mapred.MapTask: Finished spill 0
17/02/22 23:20:39 INFO mapred.Task: Task:attempt_local607471389_0001_m_000000_0 is done. And is in the process of committing
17/02/22 23:20:39 INFO mapred.LocalJobRunner: map
17/02/22 23:20:39 INFO mapred.Task: Task 'attempt_local607471389_0001_m_000000_0' done.
17/02/22 23:20:39 INFO mapred.LocalJobRunner: Finishing task: attempt_local607471389_0001_m_000000_0
17/02/22 23:20:39 INFO mapred.LocalJobRunner: map task executor complete.
17/02/22 23:20:39 INFO mapred.LocalJobRunner: Waiting for reduce tasks
17/02/22 23:20:39 INFO mapred.LocalJobRunner: Starting task: attempt_local607471389_0001_r_000000_0
17/02/22 23:20:39 INFO output.FileOutputCommitter: File Output Committer Algorithm version is 1
17/02/22 23:20:39 INFO mapred.Task:  Using ResourceCalculatorProcessTree : [ ]
17/02/22 23:20:39 INFO mapred.ReduceTask: Using ShuffleConsumerPlugin: org.apache.hadoop.mapreduce.task.reduce.Shuffle@3b231444
17/02/22 23:20:39 INFO reduce.MergeManagerImpl: MergerManager: memoryLimit=709551680, maxSingleShuffleLimit=177387920, mergeThreshold=468304128, ioSortFactor=10, memToMemMergeOutputsThreshold=10
17/02/22 23:20:39 INFO reduce.EventFetcher: attempt_local607471389_0001_r_000000_0 Thread started: EventFetcher for fetching Map Completion Events
17/02/22 23:20:39 INFO reduce.LocalFetcher: localfetcher#1 about to shuffle output of map attempt_local607471389_0001_m_000000_0 decomp: 34167 len: 34171 to MEMORY
17/02/22 23:20:39 INFO reduce.InMemoryMapOutput: Read 34167 bytes from map-output for attempt_local607471389_0001_m_000000_0
17/02/22 23:20:39 INFO reduce.MergeManagerImpl: closeInMemoryFile -> map-output of size: 34167, inMemoryMapOutputs.size() -> 1, commitMemory -> 0, usedMemory ->34167
17/02/22 23:20:39 INFO reduce.EventFetcher: EventFetcher is interrupted.. Returning
17/02/22 23:20:39 INFO mapred.LocalJobRunner: 1 / 1 copied.
17/02/22 23:20:39 INFO reduce.MergeManagerImpl: finalMerge called with 1 in-memory map-outputs and 0 on-disk map-outputs
17/02/22 23:20:39 WARN io.ReadaheadPool: Failed readahead on ifile
EBADF: Bad file descriptor
	at org.apache.hadoop.io.nativeio.NativeIO$POSIX.posix_fadvise(Native Method)
	at org.apache.hadoop.io.nativeio.NativeIO$POSIX.posixFadviseIfPossible(NativeIO.java:267)
	at org.apache.hadoop.io.nativeio.NativeIO$POSIX$CacheManipulator.posixFadviseIfPossible(NativeIO.java:146)
	at org.apache.hadoop.io.ReadaheadPool$ReadaheadRequestImpl.run(ReadaheadPool.java:206)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
	at java.lang.Thread.run(Thread.java:745)
17/02/22 23:20:39 INFO mapred.Merger: Merging 1 sorted segments
17/02/22 23:20:39 INFO mapred.Merger: Down to the last merge-pass, with 1 segments left of total size: 34155 bytes
17/02/22 23:20:39 INFO reduce.MergeManagerImpl: Merged 1 segments, 34167 bytes to disk to satisfy reduce memory limit
17/02/22 23:20:39 INFO reduce.MergeManagerImpl: Merging 1 files, 34171 bytes from disk
17/02/22 23:20:39 INFO reduce.MergeManagerImpl: Merging 0 segments, 0 bytes from memory into reduce
17/02/22 23:20:39 INFO mapred.Merger: Merging 1 sorted segments
17/02/22 23:20:39 INFO mapred.Merger: Down to the last merge-pass, with 1 segments left of total size: 34155 bytes
17/02/22 23:20:39 INFO mapred.LocalJobRunner: 1 / 1 copied.
17/02/22 23:20:39 INFO Configuration.deprecation: mapred.skip.on is deprecated. Instead, use mapreduce.job.skiprecords
17/02/22 23:20:39 INFO mapred.Task: Task:attempt_local607471389_0001_r_000000_0 is done. And is in the process of committing
17/02/22 23:20:39 INFO mapred.LocalJobRunner: 1 / 1 copied.
17/02/22 23:20:39 INFO mapred.Task: Task attempt_local607471389_0001_r_000000_0 is allowed to commit now
17/02/22 23:20:39 INFO output.FileOutputCommitter: Saved output of task 'attempt_local607471389_0001_r_000000_0' to file:/home/training/dataoutput/wc_partitioner_no_combiner/_temporary/0/task_local607471389_0001_r_000000
17/02/22 23:20:39 INFO mapred.LocalJobRunner: reduce > reduce
17/02/22 23:20:39 INFO mapred.Task: Task 'attempt_local607471389_0001_r_000000_0' done.
17/02/22 23:20:39 INFO mapred.LocalJobRunner: Finishing task: attempt_local607471389_0001_r_000000_0
17/02/22 23:20:39 INFO mapred.LocalJobRunner: Starting task: attempt_local607471389_0001_r_000001_0
17/02/22 23:20:39 INFO output.FileOutputCommitter: File Output Committer Algorithm version is 1
17/02/22 23:20:39 INFO mapred.Task:  Using ResourceCalculatorProcessTree : [ ]
17/02/22 23:20:39 INFO mapred.ReduceTask: Using ShuffleConsumerPlugin: org.apache.hadoop.mapreduce.task.reduce.Shuffle@6a7512be
17/02/22 23:20:39 INFO reduce.MergeManagerImpl: MergerManager: memoryLimit=709551680, maxSingleShuffleLimit=177387920, mergeThreshold=468304128, ioSortFactor=10, memToMemMergeOutputsThreshold=10
17/02/22 23:20:39 INFO reduce.EventFetcher: attempt_local607471389_0001_r_000001_0 Thread started: EventFetcher for fetching Map Completion Events
17/02/22 23:20:39 INFO reduce.LocalFetcher: localfetcher#2 about to shuffle output of map attempt_local607471389_0001_m_000000_0 decomp: 43786 len: 43790 to MEMORY
17/02/22 23:20:39 INFO reduce.InMemoryMapOutput: Read 43786 bytes from map-output for attempt_local607471389_0001_m_000000_0
17/02/22 23:20:39 INFO reduce.MergeManagerImpl: closeInMemoryFile -> map-output of size: 43786, inMemoryMapOutputs.size() -> 1, commitMemory -> 0, usedMemory ->43786
17/02/22 23:20:39 INFO reduce.EventFetcher: EventFetcher is interrupted.. Returning
17/02/22 23:20:40 INFO mapred.LocalJobRunner: 1 / 1 copied.
17/02/22 23:20:40 INFO reduce.MergeManagerImpl: finalMerge called with 1 in-memory map-outputs and 0 on-disk map-outputs
17/02/22 23:20:40 INFO mapred.Merger: Merging 1 sorted segments
17/02/22 23:20:40 INFO mapred.Merger: Down to the last merge-pass, with 1 segments left of total size: 43772 bytes
17/02/22 23:20:40 WARN io.ReadaheadPool: Failed readahead on ifile
EBADF: Bad file descriptor
	at org.apache.hadoop.io.nativeio.NativeIO$POSIX.posix_fadvise(Native Method)
	at org.apache.hadoop.io.nativeio.NativeIO$POSIX.posixFadviseIfPossible(NativeIO.java:267)
	at org.apache.hadoop.io.nativeio.NativeIO$POSIX$CacheManipulator.posixFadviseIfPossible(NativeIO.java:146)
	at org.apache.hadoop.io.ReadaheadPool$ReadaheadRequestImpl.run(ReadaheadPool.java:206)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
	at java.lang.Thread.run(Thread.java:745)
17/02/22 23:20:40 INFO reduce.MergeManagerImpl: Merged 1 segments, 43786 bytes to disk to satisfy reduce memory limit
17/02/22 23:20:40 INFO reduce.MergeManagerImpl: Merging 1 files, 43790 bytes from disk
17/02/22 23:20:40 INFO reduce.MergeManagerImpl: Merging 0 segments, 0 bytes from memory into reduce
17/02/22 23:20:40 INFO mapred.Merger: Merging 1 sorted segments
17/02/22 23:20:40 INFO mapred.Merger: Down to the last merge-pass, with 1 segments left of total size: 43772 bytes
17/02/22 23:20:40 INFO mapred.LocalJobRunner: 1 / 1 copied.
17/02/22 23:20:40 INFO mapred.Task: Task:attempt_local607471389_0001_r_000001_0 is done. And is in the process of committing
17/02/22 23:20:40 INFO mapred.LocalJobRunner: 1 / 1 copied.
17/02/22 23:20:40 INFO mapred.Task: Task attempt_local607471389_0001_r_000001_0 is allowed to commit now
17/02/22 23:20:40 INFO output.FileOutputCommitter: Saved output of task 'attempt_local607471389_0001_r_000001_0' to file:/home/training/dataoutput/wc_partitioner_no_combiner/_temporary/0/task_local607471389_0001_r_000001
17/02/22 23:20:40 INFO mapred.LocalJobRunner: reduce > reduce
17/02/22 23:20:40 INFO mapred.Task: Task 'attempt_local607471389_0001_r_000001_0' done.
17/02/22 23:20:40 INFO mapred.LocalJobRunner: Finishing task: attempt_local607471389_0001_r_000001_0
17/02/22 23:20:40 INFO mapred.LocalJobRunner: Starting task: attempt_local607471389_0001_r_000002_0
17/02/22 23:20:40 INFO output.FileOutputCommitter: File Output Committer Algorithm version is 1
17/02/22 23:20:40 INFO mapred.Task:  Using ResourceCalculatorProcessTree : [ ]
17/02/22 23:20:40 INFO mapred.ReduceTask: Using ShuffleConsumerPlugin: org.apache.hadoop.mapreduce.task.reduce.Shuffle@5237118b
17/02/22 23:20:40 INFO reduce.MergeManagerImpl: MergerManager: memoryLimit=709551680, maxSingleShuffleLimit=177387920, mergeThreshold=468304128, ioSortFactor=10, memToMemMergeOutputsThreshold=10
17/02/22 23:20:40 INFO reduce.EventFetcher: attempt_local607471389_0001_r_000002_0 Thread started: EventFetcher for fetching Map Completion Events
17/02/22 23:20:40 INFO reduce.LocalFetcher: localfetcher#3 about to shuffle output of map attempt_local607471389_0001_m_000000_0 decomp: 480675 len: 480679 to MEMORY
17/02/22 23:20:40 INFO reduce.InMemoryMapOutput: Read 480675 bytes from map-output for attempt_local607471389_0001_m_000000_0
17/02/22 23:20:40 INFO reduce.MergeManagerImpl: closeInMemoryFile -> map-output of size: 480675, inMemoryMapOutputs.size() -> 1, commitMemory -> 0, usedMemory ->480675
17/02/22 23:20:40 INFO reduce.EventFetcher: EventFetcher is interrupted.. Returning
17/02/22 23:20:40 INFO mapred.LocalJobRunner: 1 / 1 copied.
17/02/22 23:20:40 INFO reduce.MergeManagerImpl: finalMerge called with 1 in-memory map-outputs and 0 on-disk map-outputs
17/02/22 23:20:40 INFO mapred.Merger: Merging 1 sorted segments
17/02/22 23:20:40 INFO mapred.Merger: Down to the last merge-pass, with 1 segments left of total size: 480671 bytes
17/02/22 23:20:40 INFO mapreduce.Job:  map 100% reduce 67%
17/02/22 23:20:40 INFO reduce.MergeManagerImpl: Merged 1 segments, 480675 bytes to disk to satisfy reduce memory limit
17/02/22 23:20:40 INFO reduce.MergeManagerImpl: Merging 1 files, 480679 bytes from disk
17/02/22 23:20:40 INFO reduce.MergeManagerImpl: Merging 0 segments, 0 bytes from memory into reduce
17/02/22 23:20:40 INFO mapred.Merger: Merging 1 sorted segments
17/02/22 23:20:40 INFO mapred.Merger: Down to the last merge-pass, with 1 segments left of total size: 480671 bytes
17/02/22 23:20:40 INFO mapred.LocalJobRunner: 1 / 1 copied.
17/02/22 23:20:40 INFO mapred.Task: Task:attempt_local607471389_0001_r_000002_0 is done. And is in the process of committing
17/02/22 23:20:40 INFO mapred.LocalJobRunner: 1 / 1 copied.
17/02/22 23:20:40 INFO mapred.Task: Task attempt_local607471389_0001_r_000002_0 is allowed to commit now
17/02/22 23:20:40 INFO output.FileOutputCommitter: Saved output of task 'attempt_local607471389_0001_r_000002_0' to file:/home/training/dataoutput/wc_partitioner_no_combiner/_temporary/0/task_local607471389_0001_r_000002
17/02/22 23:20:40 INFO mapred.LocalJobRunner: reduce > reduce
17/02/22 23:20:40 INFO mapred.Task: Task 'attempt_local607471389_0001_r_000002_0' done.
17/02/22 23:20:40 INFO mapred.LocalJobRunner: Finishing task: attempt_local607471389_0001_r_000002_0
17/02/22 23:20:40 INFO mapred.LocalJobRunner: reduce task executor complete.
17/02/22 23:20:41 INFO mapreduce.Job:  map 100% reduce 100%
17/02/22 23:20:41 INFO mapreduce.Job: Job job_local607471389_0001 completed successfully
17/02/22 23:20:41 INFO mapreduce.Job: Counters: 33
	File System Counters
		FILE: Number of bytes read=2725800
		FILE: Number of bytes written=4401302
		FILE: Number of read operations=0
		FILE: Number of large read operations=0
		FILE: Number of write operations=0
	Map-Reduce Framework
		Map input records=7308
		Map output records=50212
		Map output bytes=458198
		Map output materialized bytes=558640
		Input split bytes=112
		Combine input records=0
		Combine output records=0
		Reduce input groups=6425
		Reduce shuffle bytes=558640
		Reduce input records=50212
		Reduce output records=6425
		Spilled Records=100424
		Shuffled Maps =3
		Failed Shuffles=0
		Merged Map outputs=3
		GC time elapsed (ms)=49
		CPU time spent (ms)=0
		Physical memory (bytes) snapshot=0
		Virtual memory (bytes) snapshot=0
		Total committed heap usage (bytes)=670580736
	Shuffle Errors
		BAD_ID=0
		CONNECTION=0
		IO_ERROR=0
		WRONG_LENGTH=0
		WRONG_MAP=0
		WRONG_REDUCE=0
	File Input Format Counters
		Bytes Read=268140
	File Output Format Counters
		Bytes Written=61401
```
