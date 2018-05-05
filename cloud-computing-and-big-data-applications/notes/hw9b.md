# HW9B

##### top-3 of test data
```
(TABLET,100)
(MOVIES,96)
(SCREEN,74)
```

##### top-3 of all data
```
(PRESENT,165606)
(TABLET,106509)
(DUALCORE,95124)
```

##### scripts

```sql
/* data = LOAD 'test_ad_data.txt' AS (campaign_id:chararray,
             date:chararray, time:chararray,
             keyword:chararray, display_site:chararray,
             placement:chararray, was_clicked:int, cpc:int);
*/

data = LOAD '/dualcore/ad_data[1-2]' AS (campaign_id:chararray,
             date:chararray, time:chararray,
             keyword:chararray, display_site:chararray,
             placement:chararray, was_clicked:int, cpc:int);

data_clicked = FILTER data BY was_clicked == 1;

data_by_keyword = GROUP data_clicked BY keyword;

total_cost = FOREACH data_by_keyword GENERATE group AS keyword, SUM(data_clicked.cpc) AS sum;

sorted = ORDER total_cost BY sum DESC;

top_three = LIMIT sorted 3;
DUMP top_three;
```
