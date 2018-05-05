# HW6B4

```java
/*
conf.setOutputFormat(SequenceFileOutputFormat.class);
SequenceFileOutputFormat.setOutputCompressionType(conf, CompressionType.BLOCK);
SequenceFileOutputFormat.setCompressOutput(conf, true);
conf.set("mapred.output.compression.codec","org.apache.hadoop.io.compress.SnappyCodec");
*/
```

```java
FileOutputFormat.setCompressOutput(job, true);
FileOutputFormat.setOutputCompressorClass(job, SnappyCodec.class);
SequenceFileOutputFormat.setOutputCompressionType(job, CompressionType.BLOCK);
```

```
$ hadoop jar csfp.jar stubs.CreateSequenceFileParameter -fs=file:/// -jt=local -D mapred.output.compress=true -D mapred.output.compression.codec=org.apache.hadoop.io.compress.SnappyCodec -D mapred.output.compression.type=BLOCK  ~/datainput/testlog/ ~/dataoutput/hw6_csfp_compressed
```
