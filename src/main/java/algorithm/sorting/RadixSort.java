package algorithm.sorting;

import java.util.Arrays;

import org.junit.Test;

/**
 * 
 * 基数排序
 *
 */
public class RadixSort {

	private void coreAlgorithm(int[] arr) {
		int[][] bucket = new int[10][arr.length];

		int[] bucketElementCounts = new int[10];

		int max = Integer.MIN_VALUE;
		for(int i = 1; i < arr.length; i++) {
			if(arr[i] > max) {
				max = arr[i];
			}
		}
		
		int maxLength = (max + "").length();
		
		for(int i = 0; i < maxLength; i++) {
			for (int j = 0; j < arr.length; j++) {
				int digitOfElement = arr[j] / (int)Math.pow(10, i) % 10;
				bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
				bucketElementCounts[digitOfElement] = bucketElementCounts[digitOfElement] + 1;
			}

			int index = 0;
			for (int k = 0; k < bucket.length; k++) {
				if (bucketElementCounts[k] != 0) {
					for (int loop = 0; loop < bucketElementCounts[k]; loop++) {
						arr[index++] = bucket[k][loop];
					}
				}
				bucketElementCounts[k] = 0;
			}	
		}
	}

	@Test
	public void test() {
		int[] arr = new int[] { 53, 3, 542, 748, 14, 214 };
		coreAlgorithm(arr);
		System.out.println(Arrays.toString(arr));
	}

}
