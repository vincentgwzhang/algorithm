package algorithm.duilie;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SingleLinkedListDemo {

    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();
        list.add(new SingleHeroNode(4,"name4", "nickname4"));
        list.add(new SingleHeroNode(1,"name1", "nickname1"));
        list.add(new SingleHeroNode(3,"name3", "nickname3"));
        list.add(new SingleHeroNode(2,"name2", "nickname2"));

        list.displayLinkedList();
        list.reverseDisplayLinkedList();
    }

}

class SingleLinkedList {
    private SingleHeroNode head = new SingleHeroNode(0, "", "");

    public void add(SingleHeroNode heroNode) {
        getLast().next = heroNode;
    }

    public void displayLinkedList() {
        System.out.println("=========================================================");
        SingleHeroNode temp = head.next;
        while (temp!=null) {
            System.out.println(temp);
            temp = temp.next;
        }
        System.out.println("=========================================================");
    }

    public void reverseDisplayLinkedList() {
        System.out.println("=========================================================");
        SingleHeroNode temp = head.next;
        Stack<SingleHeroNode> stack = new Stack<>();

        while (temp!=null) {
            stack.push(temp);
            temp = temp.next;
        }

        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
        System.out.println("=========================================================");
    }

    public void reverse() {
        List<SingleHeroNode> list = new ArrayList<>();
        SingleHeroNode temp = head.next;
        while (temp!= null) {
            list.add(temp);
            temp = temp.next;
        }
        SingleHeroNode temp2 = head;
        for(int index = list.size() -1;index>=0;index--) {
            temp2.next = list.get(index);
            temp2 = temp2.next;
        }
        temp2.next = null;
    }

    public void reverse2() {
        if(head.next == null || head.next.next == null) return;

        SingleHeroNode cur = head.next;
        SingleHeroNode next = null;
        SingleHeroNode reverseHead = new SingleHeroNode(0, "", "");
        while (cur != null) {
            next = cur.next;
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
        head.next = reverseHead.next;
    }

    private SingleHeroNode getLast() {
        SingleHeroNode temp = head;
        while (temp.next!=null) {
            temp = temp.next;
        }
        return temp;
    }
}

class SingleHeroNode {
    public int no;
    public String name;
    public String nickname;
    public SingleHeroNode next;

    public SingleHeroNode(int hNo, String hName, String hNickname) {
        this.no = hNo;
        this.name = hName;
        this.nickname = hNickname;
    }

    @Override
    public String toString() {
        return String.format("No=[%s], Name=[%s], Nickname=[%s]", no, name, nickname);
    }
}