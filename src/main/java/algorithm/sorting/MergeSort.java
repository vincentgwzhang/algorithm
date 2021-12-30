package algorithm.sorting;

import java.util.Arrays;

/**
 * 这是归并排序
 *
 * 时间复杂度 : o(nlog(n)), 空间复杂度 : o(n)
 *
 */
public class MergeSort extends SortInterface {

    public void coreAlorithm(int[] sourceArray) {
        try {
            int[] result = sort(sourceArray);
            System.arraycopy(result,0, sourceArray,0, result.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int[] sort(int[] sourceArray) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        if (arr.length < 2) {
            return arr;
        }
        int middle = (int) Math.floor(arr.length / 2);

        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);

        return merge(sort(left), sort(right));
    }

    protected int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int resultIndex = 0;
        int leftIndex = 0;
        int rightIndex = 0;
        while (leftIndex < left.length && rightIndex < right.length) {
            if ( left[leftIndex] <= right[rightIndex] ) {
                result[resultIndex++] = left[leftIndex++];
            } else {
                result[resultIndex++] = right[rightIndex++];
            }
        }
        System.arraycopy(left,leftIndex, result,resultIndex, left.length - leftIndex);
        System.arraycopy(right,rightIndex, result,resultIndex, right.length - rightIndex);

        return result;
    }
}
