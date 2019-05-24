package body Msort is

	procedure Merge(arr: in out int_array; lo, mid, hi: array_index) is
 		sorted: int_array;
 		left_cur_index: integer:= lo;
 		right_cur_index: integer:= mid + 1;
 		sorted_cur_index: integer:= lo;
 	begin
 		sorted(lo..hi) := arr(lo..hi);
 		while left_cur_index <= mid and right_cur_index <= hi loop
 			if arr(left_cur_index) < arr(right_cur_index) then
 				sorted(sorted_cur_index) := arr(left_cur_index);
 				left_cur_index := left_cur_index + 1;
 			else
 				sorted(sorted_cur_index) := arr(right_cur_index);
 				right_cur_index := right_cur_index + 1;
 			end if;
 			sorted_cur_index := sorted_cur_index + 1;
 		end loop;
 		if left_cur_index <= mid then
			sorted(sorted_cur_index..hi) := arr(left_cur_index..mid);
 		end if;
 		if right_cur_index <= hi then
 			sorted(sorted_cur_index..hi) := arr(right_cur_index..hi);
 		end if;
 		arr(lo..hi) := sorted(lo..hi);
 	end Merge;

 	procedure ConcurrentSort(arr: in out int_array; lo, mid, hi: array_index) is
 		task type concurrent_sort is
 			entry start(i, j: array_index);
 		end concurrent_sort;
 		task body concurrent_sort is
 			x, y : array_index;
 		begin
 			accept start(i, j: array_index) do
 				x := i;
 				y := j;
 			end start;
 			SortIndex(arr, x, y);
 		end concurrent_sort;
 		sort_left, sort_right: concurrent_sort;
 	begin
 		sort_left.start(lo, mid);
 		sort_right.start(mid+1, hi);
 	end ConcurrentSort;

 	procedure SortIndex(arr: in out int_array; lo, hi: array_index) is
 	begin
 		if lo < hi then
 			declare
 				mid: array_index:= lo + (hi - lo) / 2;
 			begin
 				ConcurrentSort(arr, lo, mid, hi);
 				Merge(arr, lo, mid, hi);
 			end;
 		end if;
 	end SortIndex;

 	procedure Sort(A: in out int_array) is
 	begin
 		SortIndex(A, A'first, A'last);
 	end Sort;

end Msort;

