# HW9D

```python
data = sc.textFile("/loudacre/weblogs")\
.map(lambda line: line.split())\
.map(lambda fields: fields[0] + "/" + fields[2])

for i in data.take(10): print i
```

```
3.94.78.5/69827
3.94.78.5/69827
19.38.140.62/21475
19.38.140.62/21475
129.133.56.105/2489
129.133.56.105/2489
217.150.149.167/4712
217.150.149.167/4712
217.150.149.167/4712
217.150.149.167/4712
```

```python
sc.textFile("/loudacre/weblogs")\
.map(lambda line: line.split())\
.map(lambda fields: fields[0] + "/" + fields[2])\
.saveAsTextFile("/loudacre/transform")
```

```
$ hadoop fs -cat /loudacre/transform/part* | head -10
3.94.78.5/69827
3.94.78.5/69827
19.38.140.62/21475
19.38.140.62/21475
129.133.56.105/2489
129.133.56.105/2489
217.150.149.167/4712
217.150.149.167/4712
217.150.149.167/4712
217.150.149.167/4712
```
