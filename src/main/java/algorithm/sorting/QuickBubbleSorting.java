package algorithm.sorting;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
 */
public class QuickBubbleSorting extends SortInterface {

	@Override
	public void coreAlorithm(int[] intArray) {
		int[] result = quickSort(intArray);
		for (int index = 0; index < result.length; index++) {
			intArray[index] = result[index];
		}
	}

	private int[] quickSort(final int[] intArray) {
		if (intArray.length <= 1) {
			return intArray;
		} else {
			List<Integer> list = new LinkedList<>();
			int referenceIndex = intArray.length / 2;
			list.add(intArray[referenceIndex]);
			for (int index = 0; index < intArray.length; index++) {
				if (index != referenceIndex) {
					if (intArray[index] <= intArray[referenceIndex]) {
						list.add(0, intArray[index]);
					} else {
						list.add(intArray[index]);
					}
				}
			}
			int flagIndex = list.indexOf(intArray[referenceIndex]);
			int[] leftArr = list.subList(0, flagIndex).stream().mapToInt(i -> i).toArray();

			int[] rightArr = list.subList(flagIndex+1, list.size()).stream().mapToInt(i -> i).toArray();

			int[] leftResult = quickSort(leftArr);
			int[] rightResult = quickSort(rightArr);

			int[] Result = new int[intArray.length];
			int addIndex = 0;
			for ( int index = 0;index < leftResult.length; index++) {
				Result[addIndex++] = leftResult[index];
			}
			Result[addIndex++] = intArray[referenceIndex];
			for ( int index = 0;index < rightResult.length; index++) {
				Result[addIndex++] = rightResult[index];
			}
			return Result;
 		}
	}

}
