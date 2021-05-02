package algorithm.sorting;

import java.util.Arrays;

import org.junit.Test;

public abstract class SortInterface {
	
	@Test
	public void testDemo() {
		long start = System.currentTimeMillis();
		for(int index = 0; index < 1; index++) {
			int ARRAY_SIZE = 10;
			int[] intArray = new int[ARRAY_SIZE];
			for(int g_loop = 0; g_loop < ARRAY_SIZE; g_loop++) {
				intArray[g_loop] = (int)(Math.random()*100);
			}
			System.out.println("Before:" + Arrays.toString(intArray));
			coreAlorithm(intArray);
			System.out.println("After: " + Arrays.toString(intArray));
			int temp = -1;
			for(int g_loop = 0; g_loop < ARRAY_SIZE; g_loop++) {
				if(intArray[g_loop] >= temp) {
					temp = intArray[g_loop];
				} else {
					throw new RuntimeException("Has bug");
				}
			}
			//System.out.println("the [" + index + "]th round complete");
		}
		long end = System.currentTimeMillis();
		System.out.println("Run time:" + (end - start));
	}
	
	
	public abstract void coreAlorithm(int[] intArray);
}
