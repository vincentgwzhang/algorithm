package algorithm.sorting;

/**
 * 把一个数列其中一个数字提出来，然后把这个数列比它小的放一堆，比他大的放一堆。
 * 分裂出来的数列再次递归行地分成两列，直至最后：
 * 1）递归得到的有1个数，那么直接返回。
 * 2）递归得到两个数，那么就比较，并形成数字返回。
 * 3)三个数字的情况不存在，因为三个数字就得分成两个数列。
 * 
 * 到最后再合并。
 * @author gzhang
 *
 * 时间复杂度： O(nlogn)
 */
public class QuickBubbleSorting extends SortInterface {

	@Override
	public void coreAlorithm(int[] intArray) {
		quickSort(intArray, 0, intArray.length-1);
	}

	public static void quickSort(int[] arr, int left, int right) {
		int l = left;
		int r = right;

		int pivot = arr[(left + right) / 2];
		int temp;

		while (l < r) { // while 循环的目的是让比 pivot 小的在左边，比 pivot 大的在右边

			while (arr[l] < pivot) {
				l += 1;
			}

			while (arr[r] > pivot) {
				r -= 1;
			}

			if (l == r) {
				break;
			}

			temp = arr[l];
			arr[l] = arr[r];
			arr[r] = temp;

			if (arr[l] == pivot) {
				r -= 1;
			}
			if (arr[r] == pivot) {
				l += 1;
			}
		}

		if (l == r) {
			l += 1;
			r -= 1;
		}

		if(left < r) {
			quickSort(arr, left, r);
		}

		if(right > l) {
			quickSort(arr, l, right);
		}
	}

}
