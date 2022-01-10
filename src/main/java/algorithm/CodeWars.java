package algorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class CodeWars {
    public static int[] parse(String data) {
        if (data == null || data.length() == 0) {
            return new int[]{};
        }
        int value = 0;
        List<Integer> lists = new ArrayList<>();
        for (int index = 0; index < data.length(); index ++) {
            if (data.charAt(index) == 'i') {
                value = value + 1;
            } else if (data.charAt(index) == 'd') {
                value = value - 1;
            } else if (data.charAt(index) == 's') {
                value = value * value;
            } else if (data.charAt(index) == 'o') {
                lists.add(value);
            }
        }
        return lists.stream().mapToInt(i -> i).toArray();
    }

    @Test
    public void exampleTests() {
        assertArrayEquals(new int[] {8, 64}, parse("iiisdoso"));
        assertArrayEquals(new int[] {8, 64, 3600}, parse("iiisdosodddddiso"));
    }
}
