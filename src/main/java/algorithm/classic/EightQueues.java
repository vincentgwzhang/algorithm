package algorithm.classic;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 八个皇后不能同一直线，不能同一竖线，不能同一邪线
 */
public class EightQueues {

    int max = 8;
    int[] array = new int[max];

    static List<int[]> arrays = new ArrayList<>();

    @Test
    public void testDemo() {
        check(0);
        System.out.println("count=[" + arrays.size() + "]");
    }

    private void check(int n) {
        if(n==max) {
            arrays.add(array);
            return;
        }

        for(int i = 0; i < max; i++) {
            array[n] = i;
            if(judge(n)) {
                check(n+1);
            }
        }
    }

    private boolean judge(int n) {
        for(int i = 0; i < n; i++) {
            if(array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }
}