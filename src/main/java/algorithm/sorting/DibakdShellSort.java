package algorithm.sorting;

/**
 *
 * 希尔排序说明:
 * 希尔排序法，最坏情况下需要（n^ 2/3）次
 *
 *
 *
 * 条件：假设有 16 个数字需要排序
 *
 * 1, 第一阶段： 以 16 / 2 = 8 为阶段, 从 index=8 开始比较，[0,8], [1,9], [2, 10] ... etc
 * 2, 第二阶段： 以  8 / 2 = 4 为阶段，
 *              从 index=4  开始比较，[0,4], [1,5], [2,6], [3,7]
 *              当 index=8  的时候，就变成 [4, 8]了，但是 begin index = 4 足以减去 gap = 4 而大于零了, 所以变成 [0, 4, 8] 三者比较。首先 [4, 8] 比较，再 [0, 4] 比较
 *              继续， index = 9 的时候，就变成 [1,5,9] 之间比较, 直到 [7,11,15]
 * 3, 第三阶段： 以 4 / 2 = 2 为阶段，（跟第二阶段很类似）
 *              从 index = 2 开始比较，首先是 [0,2], [1, 3]
 *              当 index = 4 的时候，就变成 [2, 4]了，跟第二阶段一样，此时 begin index - gap = 2 - 2 >= 0, 所以变成 [0,2,4] 之间比较
 *              当 index = 6 的时候，就变成 [0,2,4,6]了
 *              类似地， 当 index = 9 的时候就变成 [1,3,5,7,9] 之间比较
 *              总之，从 index = n 一直减去 gap > 0 之间的所有数比较
 * 4, 第N阶段： 最后一定是 gap = 1, 所以首先是 [0, 1], 然后是 [0,1,2], 然后是 [0,1,2,3]
 *
 *
 *
 * PS: 无论哪一个阶段，无论初始 index 从什么时候开始，都是从 index 到 array.length
 *
 */
public class DibakdShellSort extends SortInterface {

    public void coreAlorithm(int[] arr) {
        int temp = 0;
        int gap = arr.length;
        for (gap = gap / 2; gap > 0; gap = gap / 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j = j - gap) {

                    int left = j;
                    int right = j + gap;

                    if (arr[left] > arr[right]) {
                        temp = arr[left];
                        arr[left] = arr[right];
                        arr[right] = temp;
                    } else {
                        break; // 因为之前已经排好序了
                    }
                }
            }
        }
    }

}
