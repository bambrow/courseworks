# BDAD HW8 Citibike

```scala
val test2015 = sc.textFile("proj/bike/2015") // data of 2015
val test2016 = sc.textFile("proj/bike/2016") // data of 2016
```
```scala
// Using SimpleDateFormat to handle the dates and times.
val f1 = new java.text.SimpleDateFormat("MM/dd/yyyy")
val f2 = new java.text.SimpleDateFormat("yyyy-MM-dd")
val t = new java.text.SimpleDateFormat("HH:mm:ss")
```
```scala
// Data cleaning process.
// The cleaning is painful because there are lots of different formats, depending on the month of the data collected.
// Some data records are surrounded with double quotes; some have date format of yyyy-MM-dd instead of MM/dd/yyyy;
// Some only have HH:mm in the times and others have HH:mm:ss;
// In the final process, regular expression filters are used to make sure that data is clean.
val trip2015 = test2015.filter(rec => (!rec.startsWith("trip") && !rec.startsWith("\"trip"))).map(rec => rec.split(',')).filter(items => (items.length == 15 && items(1).contains(" ") && !items.contains(""))).map(items => items.map(w => if (w.startsWith("\"")) w.substring(1,w.length()) else w)).map(items => items.map(w => if (w.endsWith("\"")) w.substring(0,w.length()-1) else w)).map(items => Array(f2.format(f1.parse(items(1).split(' ')(0))), t.format(t.parse(if (items(1).split(' ')(1).split(':').length == 2) items(1).split(' ')(1)+":00" else items(1).split(' ')(1))), items(0), items(5), items(6), items(9), items(10))).filter(items => (items(2).matches("\\d+") && items(3).matches("\\d{2}.\\d+") && items(4).matches("-\\d{2}.\\d+") && items(5).matches("\\d{2}.\\d+") && items(6).matches("-\\d{2}.\\d+")))
```
```scala
val trip2016 = test2016.filter(rec => (!rec.startsWith("Trip") && !rec.startsWith("\"trip"))).map(rec => rec.split(',')).filter(items => (items.length == 15 && items(1).contains(" ") && !items.contains(""))).map(items => items.map(w => if (w.startsWith("\"")) w.substring(1,w.length()) else w)).map(items => items.map(w => if (w.endsWith("\"")) w.substring(0,w.length()-1) else w)).map(items => Array(if (items(1).split(' ')(0).contains("-")) items(1).split(' ')(0) else f2.format(f1.parse(items(1).split(' ')(0))), t.format(t.parse(if (items(1).split(' ')(1).split(':').length == 2) items(1).split(' ')(1)+":00" else items(1).split(' ')(1))), items(0), items(5), items(6), items(9), items(10))).filter(items => (items(2).matches("\\d+") && items(3).matches("\\d{2}.\\d+") && items(4).matches("-\\d{2}.\\d+") && items(5).matches("\\d{2}.\\d+") && items(6).matches("-\\d{2}.\\d+")))
```
```scala
// Construct the schema; this is just for better clarification of the choices of features.
val schema = sc.parallelize(Array(Array("date","time","duration","start-lat","start-lon","end-lat","end-lon"))).map(items => items.mkString(","))
```
```scala
// Combine the two RDDs using union.
// Create the data RDD for outputs.
val combined = trip2015.union(trip2016).persist()
val data = combined.map(items => items.mkString(","))
val data_with_header = schema.union(data)

data.saveAsTextFile("proj/citibike_prof")
```
```scala
scala> combined.map(items => items(0)).max()
res22: String = 2016-12-31 // max of date

scala> combined.map(items => items(0)).min()
res23: String = 2015-01-01 // min of date

scala> combined.map(items => items(1)).max()
res24: String = 23:59:59 // max of time

scala> combined.map(items => items(1)).min()
res25: String = 00:00:00 // min of time

scala> combined.map(items => items(2).toInt).max()
res26: Int = 8933552 // max of trip duration in seconds

scala> combined.map(items => items(2).toInt).min()
res27: Int = 60 // min of trip duration in seconds

scala> combined.map(items => items(3).toDouble).max()
res28: Double = 40.804213 // max of latitude of starting

scala> combined.map(items => items(3).toDouble).min()
res29: Double = 40.44535 // min of latitude of starting

scala> combined.map(items => items(4).toDouble).max()
res30: Double = -73.92850399017334 // max of longitude of starting

scala> combined.map(items => items(4).toDouble).min()
res31: Double = -74.02544975280762 // min of longitude of starting

scala> combined.map(items => items(5).toDouble).max()
res32: Double = 40.804213 // max of latitude of ending

scala> combined.map(items => items(5).toDouble).min()
res33: Double = 40.44535 // min of latitude of ending

scala> combined.map(items => items(6).toDouble).max()
res34: Double = -73.92850399017334 // max of longitude of ending

scala> combined.map(items => items(6).toDouble).min()
res35: Double = -74.0836394 // min of longitude of ending

```
```scala

scala> test2015.take(5).foreach(println)
tripduration,starttime,stoptime,start station id,start station name,start station latitude,start station longitude,end station id,end station name,end station latitude,end station longitude,bikeid,usertype,birth year,gender
1346,1/1/2015 0:01,1/1/2015 0:24,455,1 Ave & E 44 St,40.75001986,-73.96905301,265,Stanton St & Chrystie St,40.72229346,-73.99147535,18660,Subscriber,1960,2
363,1/1/2015 0:02,1/1/2015 0:08,434,9 Ave & W 18 St,40.74317449,-74.00366443,482,W 15 St & 7 Ave,40.73935542,-73.99931783,16085,Subscriber,1963,1
346,1/1/2015 0:04,1/1/2015 0:10,491,E 24 St & Park Ave S,40.74096374,-73.98602213,505,6 Ave & W 33 St,40.74901271,-73.98848395,20845,Subscriber,1974,1
182,1/1/2015 0:04,1/1/2015 0:07,384,Fulton St & Waverly Ave,40.68317813,-73.9659641,399,Lafayette Ave & St James Pl,40.68851534,-73.9647628,19610,Subscriber,1969,1
```
```scala
scala> test2016.take(5).foreach(println)
"tripduration","starttime","stoptime","start station id","start station name","start station latitude","start station longitude","end station id","end station name","end station latitude","end station longitude","bikeid","usertype","birth year","gender"
"923","1/1/2016 00:00:41","1/1/2016 00:16:04","268","Howard St & Centre St","40.71910537","-73.99973337","3002","South End Ave & Liberty St","40.711512","-74.015756","22285","Subscriber","1958","1"
"379","1/1/2016 00:00:45","1/1/2016 00:07:04","476","E 31 St & 3 Ave","40.74394314","-73.97966069","498","Broadway & W 32 St","40.74854862","-73.98808416","17827","Subscriber","1969","1"
"589","1/1/2016 00:00:48","1/1/2016 00:10:37","489","10 Ave & W 28 St","40.75066386","-74.00176802","284","Greenwich Ave & 8 Ave","40.7390169121","-74.0026376103","21997","Subscriber","1982","2"
"889","1/1/2016 00:01:06","1/1/2016 00:15:56","268","Howard St & Centre St","40.71910537","-73.99973337","3002","South End Ave & Liberty St","40.711512","-74.015756","22794","Subscriber","1961","2"

```
```scala
scala> trip2015.count
res0: Long = 9786829 // 2015 trip counts

scala> trip2016.count
res1: Long = 13506178 // 2016 trip counts

scala> data.count
res23: Long = 23293007 // total final output records

scala> schema.collect
res2: Array[String] = Array(date,time,duration,start-lat,start-lon,end-lat,end-lon)
```
```scala
scala> data_with_header.take(5).foreach(println)
date,time,duration,start-lat,start-lon,end-lat,end-lon
2015-01-01,00:01:00,1346,40.75001986,-73.96905301,40.72229346,-73.99147535
2015-01-01,00:02:00,363,40.74317449,-74.00366443,40.73935542,-73.99931783
2015-01-01,00:04:00,346,40.74096374,-73.98602213,40.74901271,-73.98848395
2015-01-01,00:04:00,182,40.68317813,-73.9659641,40.68851534,-73.9647628
```
