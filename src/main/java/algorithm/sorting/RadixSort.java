package algorithm.sorting;

/**
 * 
 * 基数排序
 *
 */
public class RadixSort extends SortInterface {

	@Override
	public void coreAlorithm(int[] intArray) {
		int[][] bucket = new int[10][intArray.length];

		int[] bucketElementCounts = new int[10];

		int max = Integer.MIN_VALUE;
		for(int i = 1; i < intArray.length; i++) {
			if(intArray[i] > max) {
				max = intArray[i];
			}
		}
		
		int maxLength = (max + "").length();
		
		for(int i = 0; i < maxLength; i++) {
			for (int j = 0; j < intArray.length; j++) {
				int digitOfElement = intArray[j] / (int)Math.pow(10, i) % 10;
				bucket[digitOfElement][bucketElementCounts[digitOfElement]] = intArray[j];
				bucketElementCounts[digitOfElement] = bucketElementCounts[digitOfElement] + 1;
			}

			int index = 0;
			for (int k = 0; k < bucket.length; k++) {
				if (bucketElementCounts[k] != 0) {
					for (int loop = 0; loop < bucketElementCounts[k]; loop++) {
						intArray[index++] = bucket[k][loop];
						bucket[k][loop] = 0;
					}
				}
				bucketElementCounts[k] = 0;
			}	
		}
	}
}
