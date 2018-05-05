# BDAD HW8 MTA

```scala
// Read the data.
// The first is the actual buses data. The second is the schedule data, in order to determine the scheduled arrival time.
val test = sc.textFile("proj/mta/2016")
val sch = sc.textFile("proj/mta/trip")
```
```scala
// Using SimpleDateFormat to handle the dates and times.
// The dates in the test RDD are in UTC format.
val t1 = new java.text.SimpleDateFormat("HH:mm:ss")
val t2 = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
t2.setTimeZone(java.util.TimeZone.getTimeZone("UTC"))
val t3 = new java.text.SimpleDateFormat("yyyy-MM-dd")
```
```scala
// Cleaning and filtering the buses data; prepare for joining.
// The join uses the (trip-id,stop-id) pair as the key.
val trip = test.filter(rec => !rec.startsWith("time")).map(rec => rec.split(',')).filter(items => items.length == 12).map(items => ((items(7), items(9)), (t3.format(t2.parse(items(0))), t1.format(t2.parse(items(0))), items(2), items(3), items(11))))
```
```scala
// Cleaning and filtering the schedule data; prepare for joining.
val schedule = sch.filter(rec => !rec.startsWith("trip")).map(rec => rec.split(',')).filter(items => items.length == 7).map(items => ((items(0), items(3)), t1.format(t1.parse(items(1)))))
```
```scala
// Join the two RDDs; rearrange the values in the new RDD.
val joined = trip.join(schedule).map{ case ((k1,k2), ((v1,v2,v3,v4,v5), v6)) => Array(v1,v2,v6,v3,v4,v5) }.persist()
```
```scala
// Construct the schema; this is just for better clarification of the choices of features.
val schema = sc.parallelize(Array(Array("date","current-time","scheduled-time","lat","lon","dist-from-stop"))).map(items => items.mkString(","))
```
```scala
// Create the data RDD for outputs.
val data = joined.map(items => items.mkString(","))
val data_with_header = schema.union(data)

data.saveAsTextFile("proj/mta_prof")
```
```scala
scala> joined.map(items => items(0)).max
res6: String = 2016-07-03 // max of date

scala> joined.map(items => items(0)).min
res7: String = 2016-01-03 // min of date

scala> joined.map(items => items(1)).max
res8: String = 23:59:59 // max of current time

scala> joined.map(items => items(1)).min
res9: String = 00:00:00 // min of current time

scala> joined.map(items => items(2)).max
res10: String = 23:59:59 // max of scheduled time

scala> joined.map(items => items(2)).min
res11: String = 00:00:00 // min of scheduled time

scala> joined.map(items => items(3).toDouble).max
res12: Double = 40.912389 // max of latitude

scala> joined.map(items => items(3).toDouble).min
res13: Double = 40.502879 // min of latitude

scala> joined.map(items => items(4).toDouble).max
res14: Double = -73.701407 // max of longitude

scala> joined.map(items => items(4).toDouble).min
res15: Double = -74.25234 // min of longitude

scala> joined.map(items => items(5).toDouble).max
res16: Double = 41566.74 // max of  distance to the stop

scala> joined.map(items => items(5).toDouble).min
res17: Double = 0.0 // min of distance to the stop

```
```scala
scala> test.take(5).foreach(println)
timestamp,vehicle_id,latitude,longitude,bearing,progress,service_date,trip_id,block_assigned,next_stop_id,dist_along_route,dist_from_stop
2016-01-01T00:00:01Z,7478,40.765221,-73.887702,169.51,2,20151231,9943210-LGPD5-LG_D5-Weekday-10,1,550624,0.06,0.03
2016-01-01T00:00:02Z,4452,40.541569,-74.209659,183.92,0,20151231,CH_E5-Weekday-108000_S7484_718,1,203521,21081.06,56.67
2016-01-01T00:00:02Z,6495,40.685374,-73.972102,158.04,0,20151231,FP_W5-Weekday-108800_B26_38,1,302426,7672.84,72.25
2016-01-01T00:00:02Z,7420,40.759151,-73.831356,19.07,2,20151231,CS_W5-Weekday-110300_MISC_790,1,904011,7459.08,91.08
```
```scala
scala> sch.take(5).foreach(println)
trip_id,arrival_time,departure_time,stop_id,stop_sequence,pickup_type,drop_off_type
CA_A6-Saturday-000000_MISC_401,00:00:00,00:00:00,202538,1,0,0
CA_A6-Saturday-000000_MISC_401,00:00:45,00:00:45,200176,2,0,0
CA_A6-Saturday-000000_MISC_401,00:01:40,00:01:40,200177,3,0,0
CA_A6-Saturday-000000_MISC_401,00:02:00,00:02:00,905003,4,0,0
```
```scala
scala> data.count
res3: Long = 425524770 // total final output records

scala> schema.collect
res37: Array[String] = Array(date,current-time,scheduled-time,lat,lon,dist-from-stop)
```
```scala
scala> data_with_header.take(5).foreach(println)
date,current-time,scheduled-time,lat,lon,dist-from-stop
2016-02-19,07:28:50,07:29:10,40.688581,-73.930035,48.87
2016-02-17,07:26:59,07:29:10,40.688723,-73.928793,154.76
2016-02-17,07:28:02,07:29:10,40.688581,-73.930035,48.87
2016-02-17,07:28:34,07:29:10,40.68852,-73.930576,2.72
```
