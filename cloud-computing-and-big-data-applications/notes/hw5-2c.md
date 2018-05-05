# HW5-2C

```
// without combiner
[training@localhost dataoutput]$ cd wc_partitioner_no_combiner/
[training@localhost wc_partitioner_no_combiner]$ ls
part-r-00000  part-r-00001  part-r-00002  _SUCCESS
[training@localhost wc_partitioner_no_combiner]$ wc -l part-r-00000
405 part-r-00000
[training@localhost wc_partitioner_no_combiner]$ wc -l part-r-00001
805 part-r-00001
[training@localhost wc_partitioner_no_combiner]$ wc -l part-r-00002
5215 part-r-00002


// with combiner
[training@localhost wc_partitioner]$ ls
part-r-00000  part-r-00001  part-r-00002  _SUCCESS
[training@localhost wc_partitioner]$ wc -l part-r-00000
405 part-r-00000
[training@localhost wc_partitioner]$ wc -l part-r-00001
805 part-r-00001
[training@localhost wc_partitioner]$ wc -l part-r-00002
5215 part-r-00002
```

```
[training@localhost dataoutput]$ ls
testlog        wc_partitioner              weblog_count
testlog_count  wc_partitioner_no_combiner
[training@localhost dataoutput]$ diff wc_partitioner/part-r-00000 wc_partitioner_no_combiner/part-r-00000
[training@localhost dataoutput]$ diff wc_partitioner/part-r-00001 wc_partitioner_no_combiner/part-r-00001
[training@localhost dataoutput]$ diff wc_partitioner/part-r-00002 wc_partitioner_no_combiner/part-r-00002

// no difference

```

```
[training@localhost src]$ hadoop fs -cat outputs/hw5_partitioner/part-r-00000 | wc -l
405
[training@localhost src]$ hadoop fs -cat outputs/hw5_partitioner/part-r-00001 | wc -l
805
[training@localhost src]$ hadoop fs -cat outputs/hw5_partitioner/part-r-00002 | wc -l
5215
[training@localhost src]$ hadoop fs -cat outputs/hw5_partitioner/* | wc -l
6425
[training@localhost src]$ cat ~/dataoutput/wc_poems_lower/* | wc -l
6425

```
