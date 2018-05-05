data = LOAD '/dualcore/ad_data2.txt' USING PigStorage(',') AS (campaign_id:chararray, date:chararray, time:chararray, display_site:chararray, placement:chararray, was_clicked:int, cpc:int, keyword:chararray);

unique_data = DISTINCT data;

new_data = FOREACH unique_data GENERATE campaign_id, REPLACE(date,'-','/') AS date, time, UPPER(TRIM(keyword)) AS keyword, display_site, placement, was_clicked, cpc; 

STORE new_data INTO '/dualcore/ad_data2';
