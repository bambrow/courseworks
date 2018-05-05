data = LOAD '/dualcore/ad_data1.txt' AS (keyword:chararray, campaign_id:chararray, date:chararray, time:chararray, display_site:chararray, was_clicked:int, cpc:int, country:chararray, placement:chararray);

usa_data = FILTER data BY country == 'USA';

new_data = FOREACH usa_data GENERATE campaign_id, date, time, UPPER(TRIM(keyword)) AS keyword, display_site, placement, was_clicked, cpc; 

STORE new_data INTO '/dualcore/ad_data1';

