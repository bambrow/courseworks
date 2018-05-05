rec = LOAD 'TrainingRatings.txt' USING PigStorage(',') AS (movie_id:int, user_id:int, rating:double);
ratings = FOREACH rec GENERATE user_id, rating;
by_user = GROUP ratings BY user_id;
avg = FOREACH by_user GENERATE group, AVG(ratings.rating);
STORE avg INTO 'avg_rating' USING PigStorage(',');
