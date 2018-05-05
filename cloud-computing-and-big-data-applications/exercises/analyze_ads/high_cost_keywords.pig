
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

