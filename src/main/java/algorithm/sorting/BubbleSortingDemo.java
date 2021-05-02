package algorithm.sorting;

/**
 * 冒泡排序
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
