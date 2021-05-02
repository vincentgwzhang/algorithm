package algorithm.sorting;

import java.util.Arrays;

import org.junit.Test;

/**
 * [直接插入排序]
 *
 *
 * 一开始，把一个列变成两部分：
 * 1）无序队列(有N-1个元素)
 * 2）有序队列（只有一个元素）
 * 
 * 然后逐步把每个元素从无序队列拉到有序队列
 * 时间复杂度：O(n^2)，最优时间复杂度：O(n),平均时间复杂度：O(n^2)
 */
public class InsertSortingDemo extends SortInterface {
	
	public void coreAlorithm(int[] arr) {
		for(int index = 1; index < arr.length; index ++) {
			int currentValue = arr[index];
			int cursor = index - 1;
			while( cursor >= 0 && currentValue < arr[cursor] ) {
				arr[cursor+1] =  arr[cursor];
				arr[cursor] = currentValue;
				cursor --;
			}
		}
	}

}
