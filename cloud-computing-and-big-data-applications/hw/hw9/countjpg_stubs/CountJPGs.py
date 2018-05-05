import sys
from pyspark import SparkContext

if __name__ == "__main__":

    if len(sys.argv) < 2:
        print >> sys.stderr, "Usage: CountJPGs.py <file>"
        exit(-1)

    sc = SparkContext()

    counts = sc.textFile(sys.argv[1]) \
        .map(lambda line: line.split()) \
        .map(lambda fields: fields[6]) \
        .map(lambda item: item.split(".")) \
        .map(lambda fields: fields[-1].upper()) \
        .map(lambda item: (item,1)) \
        .reduceByKey(lambda v1,v2: v1+v2)
    
    for k,v in counts.collect():
        if k == "JPG": print k + "\t" + str(v)

    sc.stop()

