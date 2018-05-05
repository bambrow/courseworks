-- Load only the ad_data1 and ad_data2 directories
data = LOAD 'test_ad_data.txt' AS (campaign_id:chararray,
             date:chararray, time:chararray,
             keyword:chararray, display_site:chararray,
             placement:chararray, was_clicked:int, cpc:int);

-- Include only records where the ad was clicked
clicked = FILTER data BY was_clicked == 1;

-- A: Group everything so we can call the aggregate function
grouped = GROUP clicked ALL;

-- B: Count the records 
totals = FOREACH grouped GENERATE SUM(clicked.was_clicked);

-- C: Display the result to the screen
DUMP totals;

