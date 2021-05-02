package algorithm.sorting;

public class HeapSortingDemo extends SortInterface{

    @Override
    public void coreAlorithm(final int[] intArray) {
        int length = intArray.length;
        int temp;
        for (int k = length / 2; k >= 1; k--) {
            sink(intArray, k, length);
        }
        while (length > 0) {
            temp = intArray[0];
            intArray[0] = intArray[length - 1];
            intArray[length - 1] = temp;
            length--;
            sink(intArray, 1, length);
        }
    }

    private static void sink(int[] a, int k, int n) {
        int temp;
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && a[j - 1] < a[j]) {
                j++;
            }
            if (a[k - 1] >= a[j - 1]) {
                break;
            }
            temp = a[k - 1];
            a[k - 1] = a[j - 1];
            a[j - 1] = temp;
            k = j;
        }
    }

    public static void main(String[] args) {

    }
}
