# BDAD HW6

```scala
val accounts = sc.textFile("loudacre/accounts").map(line => line.split(','))
val requests = sc.textFile("loudacre/weblog/2014-03-15.log").map(line => line.split("\\s+"))
val reqPair = requests.map(items => (items(2), 1))
val reqNum = reqPair.reduceByKey(_ + _)
val freqCount = reqNum.map(p => (p._2, 1)).reduceByKey(_ + _).sortByKey().map(p => p._1 + ":" + p._2)
val joined = accounts.map(items => (items(0), null)).join(requests.map(items => (items(2), (items(0))))).map{ case (k,(v1,v2)) => (k,v2) }
val grouped = joined.distinct.groupByKey
```
```scala
scala> reqPair.take(10).foreach(println)
```
```
(8495,1)
(8495,1)
(22676,1)
(22676,1)
(126729,1)
(126729,1)
(51102,1)
(51102,1)
(184,1)
(184,1)
```
```scala
scala> reqNum.take(10).foreach(println)
```
```
(99664,2)
(41553,2)
(98135,2)
(10942,2)
(62910,2)
(17943,2)
(94016,2)
(48471,2)
(111085,2)
(100580,2)
```
```scala
scala> freqCount.collect.foreach(println)
```
```
2:1666
3:3
4:445
5:1
6:53
7:1
8:39
10:36
12:20
14:21
16:17
18:6
20:3
```
```scala
scala> grouped.take(10).foreach(println)
```
```
(18817362699,CompactBuffer(61.48.25.116, 114.212.172.156, 107.65.41.203, 104.231.206.19))
(98135,CompactBuffer(239.215.133.194))
(19112,CompactBuffer(146.37.48.233))
(24467,CompactBuffer(90.178.253.28))
(17943,CompactBuffer(101.82.119.120))
(57080,CompactBuffer(217.195.207.128))
(119833,CompactBuffer(117.100.10.103))
(33077,CompactBuffer(31.119.202.129))
(25825,CompactBuffer(151.13.207.198))
(34442,CompactBuffer(217.204.46.123))
```
```scala
val grouped = joined.distinct.groupByKey.map{ case (k,v) => (k,v.toList) }

scala> grouped.take(10).foreach(println)
```
```
(18817362699,List(61.48.25.116, 114.212.172.156, 107.65.41.203, 104.231.206.19))
(98135,List(239.215.133.194))
(19112,List(146.37.48.233))
(24467,List(90.178.253.28))
(17943,List(101.82.119.120))
(57080,List(217.195.207.128))
(119833,List(117.100.10.103))
(33077,List(31.119.202.129))
(25825,List(151.13.207.198))
(34442,List(217.204.46.123))
```
