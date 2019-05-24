package Msort is
	LENGTH: constant integer:= 40;
 	subtype array_index is integer range 1..LENGTH;
 	subtype array_value is integer range -300..300;
 	type int_array is array(array_index) of array_value;
	procedure Sort(A: in out int_array);
private
	procedure SortIndex(arr: in out int_array; lo, hi: array_index);
	procedure ConcurrentSort(arr: in out int_array; lo, mid, hi: array_index);
	procedure Merge(arr: in out int_array; lo, mid, hi: array_index);
end Msort;
