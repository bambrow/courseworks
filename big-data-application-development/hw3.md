# BDAD HW3


1
```scala
val mydata = sc.textFile("file:/home/cloudera/BDAD/hw3/frostroad.txt")
```
2
```scala
mydata.count()
```
3
```
23
```
4
```scala
mydata.collect()
```
5
```
hdfs dfs -mkdir loudacre
hdfs dfs -mkdir loudacre/weblog
```
6
```
hdfs dfs -put ~/BDAD/hw3/2014-03-15.log loudacre/weblog/2014-03-15.log
```
7
```
hdfs dfs -cat loudacre/weblog/2014-03-15.log | less
```
8
```scala
val logfile = "loudacre/weblog/2014-03-15.log"
```
9
```scala
val log = sc.textFile(logfile)
```
10
```scala
log.take(10)
```
11
```scala
val logJPG = log.map(line => line.toLowerCase()).filter(line => line.contains("jpg"))
```
12
```scala
logJPG.take(10)
```
13
```scala
sc.textFile(logfile).map(line => line.toLowerCase()).filter(line => line.contains("jpg")).count()
```
```
423
```
14
```scala
val lineLength = log.map(line => line.length())
```
15
```scala
val lineArray = log.map(line => line.split("\\s+"))
```
16
```scala
val ip = lineArray.map(items => items(0))
```
17
```scala
ip.foreach(println)
```
18
```scala
ip.saveAsTextFile("loudacre/iplist")
```
19
```
see png
```
20
```scala
val logfile = "loudacre/weblogs"
```
21
```scala
val newlog = sc.textFile(logfile)
```
22
```scala
newlog.take(10)
```
23
```scala
val newlogJPG = newlog.map(line => line.toLowerCase()).filter(line => line.contains("jpg"))
```
24
```scala
newlogJPG.take(10)
```
25
```scala
sc.textFile(logfile).map(line => line.toLowerCase()).filter(line => line.contains("jpg")).count()
```
```
64978
```
26
```scala
val newlineLength = newlog.map(line => line.length())
```
27
```scala
val newlineArray = newlog.map(line => line.split("\\s+"))
```
28
```scala
val newip = newlineArray.map(items => items(0))
```
29
```scala
newip.foreach(println)
```
30
```scala
newip.saveAsTextFile("loudacre/bigiplist")
```
31
```
see png
```
