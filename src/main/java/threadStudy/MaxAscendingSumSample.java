package threadStudy;

import org.junit.Test;

public class MaxAscendingSumSample {
    public int countPairs(int[] nums, int low, int high) {
        int count = 0;
        for (int indexI = 0; indexI < nums.length; indexI++) {
            for (int indexJ = indexI + 1; indexJ < nums.length; indexJ++) {
                if (satisified(nums[indexI], nums[indexJ], low, high)) {
                    count ++;
                }
            }
        }
        return count;
    }

    private boolean satisified(int a1, int a2, int low, int high) {
        int xor = a1 ^ a2;
        return xor >= low && xor <=high;
    }

    @Test
    public void test1() {
        System.out.println(countPairs(new int[]{1,4,2,7} ,2 ,6));
    }
}
