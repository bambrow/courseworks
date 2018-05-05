# HW2

```
$ hadoop fs -ls shakespeare
Found 4 items
-rw-rw-rw-   1 training supergroup    1784616 2017-02-01 18:05 shakespeare/comedies
-rw-rw-rw-   1 training supergroup    1479035 2017-02-01 18:05 shakespeare/histories
-rw-rw-rw-   1 training supergroup     268140 2017-02-01 18:05 shakespeare/poems
-rw-rw-rw-   1 training supergroup    1752440 2017-02-01 18:05 shakespeare/tragedies
```

```
$ hadoop fs -cat shakespeare/poems | head -n 16



	SONNETS



TO THE ONLY BEGETTER OF
THESE INSUING SONNETS
MR. W. H. ALL HAPPINESS
AND THAT ETERNITY
PROMISED BY
OUR EVER-LIVING POET WISHETH
THE WELL-WISHING
ADVENTURER IN
SETTING FORTH
```

```
$ hadoop fs -cat wordcounts/part-r-00000 | grep -Ew '^ADRIANO|^Whether|^love|^loves|^the|^whether|^we|^zodiac'
ADRIANO	111
Whether	41
love	2221
loves	203
the	25578
we	2922
whether	79
zodiac	1
```

```
$ hadoop fs -cat wordcounts/part-r-00000 | wc -l
29183
```
