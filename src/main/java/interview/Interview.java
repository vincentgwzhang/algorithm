package interview;

import org.junit.Test;

public class Interview
{
    public int getMinDistance(int[] nums, int target, int start) {
        int va = Integer.MAX_VALUE;
        int lastIndex = -1;
        for (int index = 0; index < nums.length && index < va; index++) {
            if (nums[index] == target) {
                if (index >= start) {
                    return index - start;
                } else {
                    lastIndex = index;
                    va = start + start - index;
                }
            }
        }
        return Math.abs(lastIndex - start);
    }
}
