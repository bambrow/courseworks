package kwaymergesort;

import java.util.Arrays;

import timing.Algorithm;
import timing.Ticker;

public class MergeSort implements Algorithm<Integer[], Integer[]> {

	private int K;
	private Integer[] originalArray, sortedArray;
	private Ticker ticker;

	/**
	 * By default, do an 2-way mergesort
	 */
	public MergeSort() {
		this(2);
	}

	public MergeSort(int K) {
		this.K = K;
	}

	@Override
	public void reset(Ticker ticker) {
		this.ticker = ticker;
	}

	@Override
	public void loadInput(Integer[] input) {
		this.originalArray = Arrays.copyOf(input, input.length);
	}

	@Override
	public Integer[] getResults() {
		return this.sortedArray;
	}

	@Override
	public void run() {
		this.sortedArray = KWayMergeSort.kwaymergesort(K, originalArray, ticker);
	}

	public String toString() {
		if (this.originalArray == null) 
			return "empty merge sort";
		else
			return ""+K+"-way mergesort of " + this.originalArray.length + " integers";
	}

}
