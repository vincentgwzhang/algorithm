package interview.sabdbox;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Sandbox {

    public int[] advantageCount(int[] A, int[] B) {
        int[] temp = Arrays.copyOf(B, B.length);
        int[] indexArray = new int[temp.length];
        int[] result = new int[A.length];
        Arrays.sort(temp);

        for (int flag = 0; flag < temp.length; flag ++) {
            for (int index = 0; index < B.length; index ++) {
                if (temp[flag] == B[index]) {
                    indexArray[flag] = index;
                    break;
                }
            }
        }//0 2 1 3

        Arrays.sort(A);

        for (int index = 0; index < indexArray.length; index ++) {
            result[indexArray[index]] = A[index];
        }

        return result;
    }

    @Test
    public void test1() {
        int[] A = new int[]{12,24,8,32};
        int[] B = new int[]{13,25,32,11};
        int[] C = advantageCount(A, B);
        for (int value: C) {
            System.out.print(value + " ");
        }
    }
}
