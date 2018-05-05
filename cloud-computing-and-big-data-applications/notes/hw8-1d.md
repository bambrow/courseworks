# HW8-1D

```
$ hadoop jar tnl.jar solution.AggregateCountsDriver -fs=file:/// -jt=local -files movie_titles.txt ~/netflix_subset/TrainingRatings.txt ~/netflix_output/TrainingRatings_inter

$ hadoop jar tnl.jar solution.TopNListDriver -fs=file:/// -jt=local -files movie_titles.txt 15  ~/netflix_output/TrainingRatings_inter ~/netflix_output/TrainingRatings_15

$ cat part-r-00000
103701	Ferris Bueller's Day Off
95216	Rain Man
94398	Seven
92377	The Godfather
92029	The Incredibles
90891	Pretty Woman
88670	As Good as It Gets
82862	The Italian Job
81889	Terminator 2: Extreme Edition
80580	Good Morning, Vietnam
78936	When Harry Met Sally
78892	National Lampoon's Vacation
76587	Beverly Hills Cop
76473	Office Space
75145	Air Force One
```
