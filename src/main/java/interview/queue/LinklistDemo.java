package interview.queue;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * add 是顺序 add 尾
 * poll 是拉头，再删除
 * peak 是拉头，不删除
 * remove 是拉头，删除
 * push 是拉头，删除
 * pop 是拉头，再删除
 * offer 是发尾
 *
 *
 *
 */
public class LinklistDemo
{
    /**
     *  * add 是顺序 add 尾
     *  * poll 是拉头，再删除
     *  * peak 是拉头，不删除
     *  * remove 是拉头，删除
     *  * push 是拉头，删除
     *  * pop 是拉头，再删除
     *  * offer 是发尾
     */

    public boolean checkValid(String values) {
        LinkedList<Character> linkedList = new LinkedList<>();
        Map<Character, Character> maps = new HashMap<>();

        maps.put('}','{');
        maps.put(']','[');
        maps.put(')','(');

        char[] arr = values.toCharArray();

        if (arr.length % 2 == 1) {
            return false;
        } else if (arr.length == 0) {
            return true;
        } else if (!maps.values().contains(arr[0])) {
            return false;
        }

        for (int index = 0; index < arr.length; index ++) {
            char target = arr[index];
            if (maps.values().contains(target)) {
                linkedList.addFirst(target);
                continue;
            } else if (!maps.containsKey(target)) {
                return false;
            } else {
                char expectedMatch = maps.get(target);
                if (linkedList.remove() != expectedMatch) {
                    return false;
                }
            }
        }

        return linkedList.isEmpty();
    }

    @Test
    public void test1()
    {
        Assert.assertTrue(checkValid("()"));
        Assert.assertTrue(checkValid("()[]{}"));
        Assert.assertTrue(!checkValid("(]"));
        Assert.assertTrue(!checkValid(")("));

        Assert.assertTrue(!checkValid("([)]"));
        Assert.assertTrue(checkValid("{[]}"));
        Assert.assertTrue(!checkValid(")"));
        Assert.assertTrue(!checkValid("([]"));

        Assert.assertTrue(!checkValid("{"));
        Assert.assertTrue(!checkValid("([)])"));
        Assert.assertTrue(checkValid("(((((((((())))))))))"));
        Assert.assertTrue(checkValid(""));
    }

}





















