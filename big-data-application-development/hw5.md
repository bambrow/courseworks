# BDAD HW5

```scala
import scala.xml._

def getActs(str: String): Iterator[Node] = {
	val nodes = XML.loadString(str) \\ "activation"
	nodes.toIterator
}

def getModel(act: Node): String = {
	(act \ "model").text
}

def getAccount(act: Node): String = {
	(act \ "account-number").text
}


val acts = sc.wholeTextFiles("loudacre/activations")

val records = acts.flatMap{ case (k,v) => getActs(v) }

val mapped = records.map(act => getAccount(act) + ":" + getModel(act))

mapped.saveAsTextFile("loudacre/activations/account-models")
```

```scala
scala> sc.textFile("loudacre/activations/account-models").take(10).foreach(println)
```
```
93893:Titanic 1100
10524:Titanic 1000
116:Sorrento F00L
428:Titanic 1100
237:Titanic 1000
51:Titanic 1100
195:iFruit 1
38:Titanic 1100
147:Titanic 1100
120:Sorrento F00L
```
```
[wl1731@login-2-1 ~]$ hdfs dfs -cat loudacre/activations/account-models/* | head -10
93893:Titanic 1100
10524:Titanic 1000
116:Sorrento F00L
428:Titanic 1100
237:Titanic 1000
51:Titanic 1100
195:iFruit 1
38:Titanic 1100
147:Titanic 1100
120:Sorrento F00L
```
