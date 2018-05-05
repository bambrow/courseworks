# FINAL RUNTIME

#### System:
```
CentOS
    Release 6.7 (Final)
    Kernel Linux 2.6.32-573.18.1.el6.x86_64
    GNOME 2.28.2
Hardware
    Memory    3.9GiB
    Processor    Intel(R) Core(TM) i5-3230M CPU @ 2.60GHz
```

> 2 threads, local mode, k=5, GreatCircle

#### with persistent RDD:

##### device:
```
1)
real	2m48.086s
user	0m25.364s
sys	0m2.100s
2)
real	3m3.393s
user	0m26.098s
sys	0m2.269s
3)
real	2m57.698s
user	0m28.858s
sys	0m2.254s
```
##### synthetic:
```
1)
real	0m34.950s
user	0m21.971s
sys	0m1.549s
2)
real	0m34.563s
user	0m21.583s
sys	0m1.583s
3)
real	0m34.696s
user	0m21.765s
sys	0m1.528s
```
##### DBpedia:
```
1)
real	6m8.898s
user	0m33.515s
sys	0m3.901s
2)
real	6m19.170s
user	0m35.224s
sys	0m3.328s
3)
real	5m53.090s
user	0m34.831s
sys	0m2.908s
```

#### without persistent RDD:

##### device:
```
1)
real	4m7.790s
user	0m30.157s
sys	0m2.518s
2)
real	4m18.229s
user	0m31.355s
sys	0m3.096s
3)
real	4m12.582s
user	0m31.278s
sys	0m2.704s
```
##### synthetic:
```
1)
real	0m40.359s
user	0m23.247s
sys	0m1.658s
2)
real	0m43.366s
user	0m25.078s
sys	0m1.739s
3)
real	0m46.620s
user	0m26.237s
sys	0m2.019s
```
##### DBpedia:
```
1)
real	9m20.167s
user	0m48.877s
sys	0m5.062s
2)
real	9m51.086s
user	0m48.063s
sys	0m5.571s
3)    
real	9m24.849s
user	0m46.788s
sys	0m4.981s
```
