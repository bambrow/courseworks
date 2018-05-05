# BDAD HW8 Uber

```scala
val test2014 = sc.textFile("proj/uber/2014") // data of 2014
val coor2015 = sc.textFile("proj/uber/2015/taxi-zone-coordinates.csv") // coordination data of 2015
val test2015 = sc.textFile("proj/uber/2015/uber-raw-data-janjune-15.csv") // data of 2015
```
```scala
// Using SimpleDateFormat to handle the dates and times.
val f1 = new java.text.SimpleDateFormat("MM/dd/yyyy")
val f2 = new java.text.SimpleDateFormat("yyyy-MM-dd")

val t = new java.text.SimpleDateFormat("HH:mm:ss")
```
```scala
// Data cleaning process.
// All the data records are surrounded with double quotes so those need to be removed.
// The dates and times are reformatted.
// In the final process, regular expression filters are used to make sure that data is clean.
val trip2014 = test2014.filter(rec => !rec.startsWith("\"Date")).map(rec => rec.split(',')).filter(items => (items.length == 4 && items(0).contains(" "))).map(items => items.map(w => if (w.startsWith("\"")) w.substring(1,w.length()) else w)).map(items => items.map(w => if (w.endsWith("\"")) w.substring(0,w.length()-1) else w)).map(items => Array(f2.format(f1.parse(items(0).split(' ')(0))), t.format(t.parse(items(0).split(' ')(1))), items(1), items(2))).filter(items => (items(2).matches("\\d{2}.\\d+") && items(3).matches("-\\d{2}.\\d+")))
```
```scala
// For test2015 RDD, the data should be formatted so it can be joined with the coordinates.
// The test2015 RDD only contains location IDs. These need to be translated to coordinates.
// The coordinates are generated using a helper Python program (see appendix).
val trip2015 = test2015.filter(rec => !rec.startsWith("Dispatching")).map(rec => rec.split(',')).filter(items => (items.length == 4 && items(1).contains(" "))).map(items => (items(3), f2.format(f2.parse(items(1).split(' ')(0)))+","+t.format(t.parse(items(1).split(' ')(1))))).join(coor2015.filter(rec => !rec.startsWith("Location")).map(rec => rec.split(',')).filter(items => items.length == 5).map(items => (items(0), items(3)+","+items(4)))).map{ case (k,(v1,v2)) => Array(v1.split(",")(0), v1.split(",")(1), v2.split(",")(0), v2.split(",")(1))}
```
```scala
// Construct the schema; this is just for better clarification of the choices of features.
val schema = sc.parallelize(Array(Array("date","time","lat","lon"))).map(items => items.mkString(","))
```
```scala
// Combine the two RDDs using union.
// Create the data RDD for outputs.
val combined = trip2014.union(trip2015).persist()
val data = combined.map(items => items.mkString(","))
val data_with_header = schema.union(data)

data.saveAsTextFile("proj/uber_prof")
```
```scala
scala> combined.map(items => items(0)).max()
res18: String = 2015-06-30 // max of date

scala> combined.map(items => items(0)).min()
res19: String = 2014-04-01 // min of date

scala> combined.map(items => items(1)).max()
res20: String = 23:59:59 // max of time

scala> combined.map(items => items(1)).min()
res21: String = 00:00:00 // min of time

scala> combined.map(items => items(2).toDouble).max()
res22: Double = 42.1166 // max of latitude

scala> combined.map(items => items(2).toDouble).min()
res23: Double = 38.876392 // min of latitude

scala> combined.map(items => items(3).toDouble).max()
res24: Double = -72.0666 // max of longitude

scala> combined.map(items => items(3).toDouble).min()
res25: Double = -77.0721696 // min of longitude
```
```scala
scala> test2014.take(5).foreach(println)
"Date/Time","Lat","Lon","Base"
"4/1/2014 0:11:00",40.769,-73.9549,"B02512"
"4/1/2014 0:17:00",40.7267,-74.0345,"B02512"
"4/1/2014 0:21:00",40.7316,-73.9873,"B02512"
"4/1/2014 0:28:00",40.7588,-73.9776,"B02512"
```
```scala
scala> test2015.take(5).foreach(println)
Dispatching_base_num,Pickup_date,Affiliated_base_num,locationID
B02617,2015-05-17 09:47:00,B02617,141
B02617,2015-05-17 09:47:00,B02617,65
B02617,2015-05-17 09:47:00,B02617,100
B02617,2015-05-17 09:47:00,B02774,80
```
```scala
scala> coor2015.take(5).foreach(println)
LocationID,Borough,Zone,Lat,Lon
1,EWR,Newark Airport,40.6895314,-74.1744624
2,Queens,Jamaica Bay,40.6056632,-73.8713099
3,Bronx,Allerton/Pelham Gardens,40.8651604,-73.8426731
4,Manhattan,Alphabet City,40.7258428,-73.9774916
```
```scala
scala> trip2014.count
res12: Long = 4526759 // 2014 trip counts

scala> trip2015.count
res13: Long = 14270479 // 2015 trip counts

scala> data.count
res14: Long = 18797238 // total final output records

scala> schema.collect
res15: Array[String] = Array(date,time,lat,lon)
```
```scala
scala> data_with_header.take(5).foreach(println)
date,time,lat,lon
2014-04-01,00:11:00,40.769,-73.9549
2014-04-01,00:17:00,40.7267,-74.0345
2014-04-01,00:21:00,40.7316,-73.9873
2014-04-01,00:28:00,40.7588,-73.9776
```
