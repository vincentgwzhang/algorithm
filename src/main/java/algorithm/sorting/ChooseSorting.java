package algorithm.sorting;

/**
 *
 * 时间复杂度 : o(n^2), 空间复杂度 : o(1)
 *
 */
public class ChooseSorting extends SortInterface {

    //核心思想：
    //每一次，找到最少得，然后跟数组第一个数字交换
    //首先在未排序序列中找到最小元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小元素，然后放到已排序序列的末尾。
    //时间复杂度：O(n^2)，最优时间复杂度：O(n^2),平均时间复杂度：O(n^2)

	@Override
	public void coreAlorithm(int[] arr) {
        int min, flag = 0, temp;
        for(int outIndex = 0; outIndex < arr.length; outIndex++) {
            min = Integer.MAX_VALUE;
            for(int innerIndex = outIndex; innerIndex < arr.length; innerIndex++) {
                if(arr[innerIndex] < min) {
                    min = arr[innerIndex];
                    flag = innerIndex;
                }
            }
            temp = arr[outIndex];
            arr[outIndex] = min;
            arr[flag] = temp;
        }
	}

}
