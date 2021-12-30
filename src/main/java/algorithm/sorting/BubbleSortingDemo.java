package algorithm.sorting;

/**
 * 冒泡排序
 *
 *
 * 冒泡排序（Bubble Sort）也是一种简单直观的排序算法。
 * 它重复地走访过要排序的数列，一次比较两个元素，如果他们的顺序错误就把他们交换过来。
 * 走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。
 * 这个算法的名字由来是因为越小的元素会经由交换慢慢"浮"到数列的顶端。
 *
 * 时间复杂度 : o(n^2), 空间复杂度 : o(1)
 */
public class BubbleSortingDemo extends SortInterface {

	@Override
	public void coreAlorithm(int[] arr) {
        int n = arr.length;
        int temp;
        for(int i = 0; i < n; i++) {
            boolean hasChanged = false;
            for(int j = 0; j < n - i - 1; j++) {
                if(arr[j] > arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    hasChanged = true;
                }
            }
            if(!hasChanged) break;
        }
	}


}
