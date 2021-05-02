package algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MaxEnvelopes {


    public int minOperations(int x) {
        if (x % 2 == 1) {
            int n = (x - 1) / 2;
            return cal(2, n);
        } else {
            int n = x / 2;
            return cal(1, n);
        }

    }

    public int cal (int a1, int n) {
        return n * (a1 +  n - 1);
    }
}
