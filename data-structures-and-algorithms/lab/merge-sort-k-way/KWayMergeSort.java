package kwaymergesort;

import timing.Ticker;

public class KWayMergeSort {
	
	/**
	 * 
	 * @param K some positive power of 2.
	 * @param input an array of unsorted integers.  Its size is either 1, or some other power of 2 that is at least K
	 * @param ticker call .tick() on this to account for the work you do
	 * @return
	 */
	public static Integer[] kwaymergesort(int K, Integer[] input, Ticker ticker) {
		int n = input.length;
		
		//
		// FIXME
		// Following just copies the input as the answer
		//
		// You must replace the loop below with code that performs
		// a K-way merge sort, placing the result in ans
		//
		// The web page for this assignment provides more detail.
		//
		// Use the ticker as you normally would, to account for
		// the operations taken to perform the K-way merge sort.
		//
		Integer[] ans = new Integer[n];
		for (int i=0; i < n; ++i) {
			ans[i] = input[i];
			ticker.tick();
		}
		
		// if the size of array is 1 (or 0), return it directly
		if (n == 0 || n == 1) {
			ticker.tick(); // one tick for return
			return ans;
		}
		
		// split it into K arrays, each has n/K elements
		Integer[][] split = new Integer[K][n/K];
		
		// start splitting
		for (int i = 0; i < K; ++i) {
			for (int j = 0; j < n/K; ++j) {
				split[i][j] = ans[i*n/K+j]; // assign the elements in each array
				ticker.tick(); // one tick per copy
			}
			split[i] = kwaymergesort(K, split[i], ticker); // recursively merge sort each array of K arrays
		}
		
		// use 2-way merge method to merge the K arrays
		for (int i = K; i >= 2; i /= 2) { // merge logK times
			
			// create a new array in each merge step
			Integer[][] merge = new Integer[i/2][2*n/i];
			
			// start merging
			for (int j = 0; j < i/2; ++j) { // i arrays have i/2 pairs
				
				int left = 0; 
				int right = 0; // one index indicator for each array in a pair
				
				while (left < n/i || right < n/i) { // before both arrays reach the end
					
					ticker.tick(2); // two ticks of comparison
										
					if (left >= n/i) {
						merge[j][left+right] = split[2*j+1][right];
						right++; // if the left reaches the end, directly take the element in the right
					}
					else if (right >= n/i) {
						merge[j][left+right] = split[2*j][left];
						left++; // if the right reaches the end, directly take the element in the left
					}
					else if (split[2*j][left] <= split[2*j+1][right]) {
						merge[j][left+right] = split[2*j][left];
						left++; // if the left is smaller, take the element in the left
					} else {
						merge[j][left+right] = split[2*j+1][right];
						right++; // if the right is smaller, take the element in the right
					}
					
					ticker.tick(2); // two ticks of comparison and merge
					
				}
				
			}
			split = merge; // set split points to merge to prepare for the next round of merge
			ticker.tick(); // one tick for this setting
		}
		
		// finally copy all the sorted elements back to ans
		for (int i=0; i < n; ++i) {
			ans[i] = split[0][i];
			ticker.tick(); // one tick per copy
		}
		return ans;
	}

}
