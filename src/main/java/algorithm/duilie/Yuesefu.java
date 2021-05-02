package algorithm.duilie;

import org.junit.Test;

public class Yuesefu {

    private int[] coreAlgorithm(YuesefuNodeList list , int k, int m) {
        int[] result = new int[list.total];
        int loop = 0;
        YuesefuNode temp = list.findIndex(k);
        YuesefuNode nextNode = list.findNextM(m, temp, true);

        while(temp.no != nextNode.no) {
            result[loop++] = nextNode.no;
            nextNode.flag = false;
            if(list.countLeft()==0) break;
            temp = nextNode;
            nextNode = list.findNextM(m, nextNode, false);
        }
        return result;
    }

    @Test
    public void testWithNode() {
        int n = 5;
        int m = 2;
        int k = 1;
        YuesefuNodeList list = new YuesefuNodeList(n);
        int[] result = coreAlgorithm(list, k, m);
        for(int num : result) {
            System.out.printf("%d\t", num);
        }
    }

}

class YuesefuNodeList {

    public YuesefuNode head = new YuesefuNode(-1);

    public int total;

    public YuesefuNodeList(int n) {
        YuesefuNode temp = head;
        for(int i = 1; i <= n; i++) {
            temp.next = new YuesefuNode(i);
            temp = temp.next;
        }
        temp.next = head.next;
        total = n;
    }

    public YuesefuNode findIndex(int k) {
        YuesefuNode temp = head;
        for(int index = 0; index < k; index++) {
            temp = temp.next;
        }
        return temp;
    }

    public YuesefuNode findNextM(int m, YuesefuNode node, boolean isSelfCount) {
        YuesefuNode result = node;
        int loop = 1;

        if(!isSelfCount) {
            loop  = 0;
        }

        while(loop != m) {
            result = result.next;
            if(result.flag) {
                loop ++;
            }
        }
        return result;
    }

    public int countLeft() {
        YuesefuNode temp = head.next;
        int result = 0;
        if(total==0) {
            return 0;
        }
        for(int i = 1; i <= total; i++) {
            if(temp.flag) {
                result++;
            }
            temp = temp.next;
        }
        return result;
    }

}

class YuesefuNode {

    public int no;

    public boolean flag;

    public YuesefuNode next;

    public YuesefuNode(int no) {
        this.no = no;
        this.flag = true;
        this.next = null;
    }

}
