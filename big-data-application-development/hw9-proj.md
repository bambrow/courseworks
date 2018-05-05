# BDAD HW9 Project

```scala
import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.SparkContext._
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LinearRegressionWithSGD

def seconds(time: String) : Int = {
      val arr = time.split(":").map(_.toInt)
      return arr(2) + arr(1) * 60 + arr(0) * 3600
}

val data = sc.textFile("proj/citibike_sample").map(rec => rec.split(",")).map(items => Array(items(2).toDouble, seconds(items(1)), items(3).toDouble, items(4).toDouble, items(5).toDouble, items(6).toDouble)).cache()

val Array(training, test) = data.randomSplit(Array(0.7,0.3))

val parsedTraining = training.map { items =>
      val d = items.slice(1,items.size)
      val v = Vectors.dense(d)
      LabeledPoint(items(0), v)
}.cache()

// val numIterations = 1000
// val stepSize = 0.00000001
val model = LinearRegressionWithSGD.train(parsedTraining, 100, 0.000000001)

val valuesAndPredsForTrainingData = parsedTraining.map { point =>
      val prediction = model.predict(point.features)
      (point.label, prediction)
}

val MSEforTrainingData = valuesAndPredsForTrainingData.map{ case(v, p) => math.pow((v - p), 2) }.mean()
println("Mean Squared Error for training data: " + MSEforTrainingData)



valuesAndPredsForTrainingData.take(10).foreach(result => println(s"actual value: ${result._1}, predicted value: ${result._2}"))
valuesAndPredsForTrainingData.take(100).foreach(result => println(s"actual value: ${result._1}, predicted value: ${result._2}"))


val parsedTest = test.map { items =>
      val d = items.slice(1,items.size)
      val v = Vectors.dense(d)
      LabeledPoint(items(0), v)
}.cache()

val valuesAndPredsForTestData = parsedTest.map { point =>
      val prediction = model.predict(point.features)
      (point.label, prediction)
}

val MSEforTestData = valuesAndPredsForTestData.map{ case(v, p) => math.pow((v - p), 2) }.mean()
println("Mean Squared Error for test data: " + MSEforTestData)
valuesAndPredsForTestData.take(10).foreach(result => println(s"actual value: ${result._1}, predicted value: ${result._2}"))
valuesAndPredsForTestData.take(100).foreach(result => println(s"actual value: ${result._1}, predicted value: ${result._2}"))

```
