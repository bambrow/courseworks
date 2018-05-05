# BDAD HW9 A

```scala
val userData = sc.textFile("loudacre/accounts").map(line => line.split(',')).keyBy(items => items(0))
val requests = sc.textFile("loudacre/weblog/2014-03-15.log").map(line => line.split("\\s+"))
val reqPair = requests.map(items => (items(2), 1))
val reqNum = reqPair.reduceByKey(_ + _)
val joined = userData.join(reqNum)
val cleaned = joined.map{ case (id, (Array(v1,v2,v3,firstName,lastName,v4,v5,v6,v7,v8,v9,v10), count)) => Array(id,count.toString,firstName,lastName) }
val results = cleaned.map(items => items.mkString(" "))
val sortedResults = cleaned.map{ case (Array(id,count,firstName,lastName)) => (id.toInt, (count,firstName,lastName)) }.sortByKey().map{ case (id,(count,firstName,lastName)) => id.toString + " " + count + " " + firstName + " " + lastName }
```
```scala
scala> results.take(5).foreach(println)
178 12 Kimberly Mulder
20758 4 Antonio Lott
110253 2 Larry Anthony
990 2 James Vargas
100870 6 John Benfield

```
```scala
scala> sortedResults.take(5).foreach(println)
1 8 Donald Becton
2 14 Donna Jones
3 2 Dorthy Chalmers
4 6 Leila Spencer
5 4 Anita Laughlin


```
```scala
scala> userData.take(10).foreach(println)
(1,[Ljava.lang.String;@6c8ea19d)
(2,[Ljava.lang.String;@8148219)
(3,[Ljava.lang.String;@7a693b73)
(4,[Ljava.lang.String;@12be39ce)
(5,[Ljava.lang.String;@6c50cff)
(6,[Ljava.lang.String;@72d83cd1)
(7,[Ljava.lang.String;@5280f83e)
(8,[Ljava.lang.String;@1129d8c3)
(9,[Ljava.lang.String;@5255f302)
(10,[Ljava.lang.String;@8c53289)

```
```scala
scala> reqNum.take(10).foreach(println)
(100580,2)
(98135,2)
(10942,2)
(17943,2)
(62910,2)
(99664,2)
(48471,2)
(43902,2)
(111085,2)
(117597,2)

```
```scala
scala> joined.take(10).foreach(println)
(178,([Ljava.lang.String;@1a185ae,12))
(110253,([Ljava.lang.String;@134f6358,2))
(20758,([Ljava.lang.String;@4103304f,4))
(100870,([Ljava.lang.String;@3858b3e1,6))
(990,([Ljava.lang.String;@478995ad,2))
(74203,([Ljava.lang.String;@19c1b58c,2))
(28943,([Ljava.lang.String;@7c760789,2))
(109943,([Ljava.lang.String;@28e8f335,4))
(126939,([Ljava.lang.String;@55b7318e,2))
(59044,([Ljava.lang.String;@6e168d98,2))
```
