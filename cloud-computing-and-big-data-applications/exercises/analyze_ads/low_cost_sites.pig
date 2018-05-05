-- TODO (A): Replace 'FIXME' to load the test_ad_data.txt file.

/* data = LOAD 'test_ad_data.txt' AS (campaign_id:chararray,
             date:chararray, time:chararray,
             keyword:chararray, display_site:chararray, 
             placement:chararray, was_clicked:int, cpc:int);
*/

data = LOAD '/dualcore/ad_data[1-2]' AS (campaign_id:chararray,
             date:chararray, time:chararray,
             keyword:chararray, display_site:chararray, 
             placement:chararray, was_clicked:int, cpc:int);

-- TODO (B): Include only records where was_clicked has a value of 1

data_clicked = FILTER data BY was_clicked == 1;

-- TODO (C): Group the data by the appropriate field

data_by_site = GROUP data_clicked BY display_site;

/* TODO (D): Create a new relation which includes only the 
 *           display site and the total cost of all clicks 
 *           on that site
 */

total_cost = FOREACH data_by_site GENERATE group AS display_site, SUM(data_clicked.cpc) AS sum;

-- TODO (E): Sort that new relation by cost (ascending)

sorted = ORDER total_cost BY sum;

-- TODO (F): Display just the first three records to the screen

top_four = LIMIT sorted 4;
DUMP top_four;

