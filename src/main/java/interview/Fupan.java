package interview;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class Fupan
{
    public Character firstRepeatedCharacter(String value) {
        char[] charArray = value.toCharArray();
        Set<Character> sets = new HashSet<>();
        for (int x = 0, y = charArray.length - 1; x < y; x ++, y--) {
            if (!sets.add(charArray[x])) return charArray[x];
            if (!sets.add(charArray[y])) return charArray[y];
        }
        return null;
    }

    @Test
    public void test1() {
//        Assert.assertEquals(firstRepeatedCharacter("ABCDA"), 'A');
//        Assert.assertEquals(firstRepeatedCharacter("BCABA"), 'B');
//        Assert.assertEquals(firstRepeatedCharacter("glovol"), 'l');
        System.out.println(firstRepeatedCharacter("glovlxxxxxxxxxxxxxxxxxxxxxxxxxxo"));
        //Assert.assertEquals(firstRepeatedCharacter("ABC"), null);
    }
}
