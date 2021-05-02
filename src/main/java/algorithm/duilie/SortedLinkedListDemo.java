package algorithm.duilie;

public class SortedLinkedListDemo {

    public static void main(String[] args) {
        SortedLinkedList list = new SortedLinkedList();
        list.add(new SortedHeroNode(5,"name5", "nickname5"));
        list.add(new SortedHeroNode(4,"name4", "nickname4"));
        list.add(new SortedHeroNode(1,"name1", "nickname1"));
        list.add(new SortedHeroNode(8,"name6", "nickname6"));
        list.add(new SortedHeroNode(3,"name3", "nickname3"));
        list.add(new SortedHeroNode(6,"name3", "nickname3"));
        list.add(new SortedHeroNode(0,"name0", "nickname0"));
        list.add(new SortedHeroNode(2,"name2", "nickname2"));
        list.delete(new SortedHeroNode(7,"name2", "nickname2"));
        list.delete(new SortedHeroNode(-1,"name2", "nickname2"));
        list.delete(new SortedHeroNode(0,"name2", "nickname2"));
        list.update(new SortedHeroNode(7,"name2", "nickname2"));
        list.delete(new SortedHeroNode(4,"name2", "nickname2"));
        list.update(new SortedHeroNode(4,"name2", "nickname2"));
        list.update(new SortedHeroNode(3,"AAAAAAAA", "VVVVVVVVV"));

        list.displayLinkedList();
    }

}

class SortedLinkedList {
    private SortedHeroNode head = new SortedHeroNode(0, "", "");

    public void add(SortedHeroNode heroNode) {
        SortedHeroNode target = getTarget(heroNode);
        SortedHeroNode nextNode = target.next;

        target.next = heroNode;
        heroNode.next = nextNode;
    }

    public void update(SortedHeroNode heroNode) {
        SortedHeroNode temp = getTarget(heroNode);
        if(temp == null) return;
        SortedHeroNode nextTemp = temp.next;
        if(nextTemp == null) return;
        if(nextTemp.no != heroNode.no) return;
        nextTemp.name = heroNode.name;
        nextTemp.nickname = heroNode.nickname;
    }

    public void delete(SortedHeroNode heroNode) {
        SortedHeroNode temp = getTarget(heroNode);
        if(temp == null) return;
        SortedHeroNode nextTemp = temp.next;
        if(nextTemp == null) return;
        if(nextTemp.no != heroNode.no) return;
        SortedHeroNode nextNextTemp = nextTemp.next;

        temp.next = nextNextTemp;
    }

    public void displayLinkedList() {
        SortedHeroNode temp = head.next;
        while (temp!=null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    private SortedHeroNode getTarget(SortedHeroNode current) {
        SortedHeroNode temp = head.next;
        SortedHeroNode result = head;
        while(temp!=null) {
            if(temp.no < current.no) {
                result = temp;
                temp = temp.next;
            } else {
                break;
            }
        }
        return result;
    }
}

class SortedHeroNode {
    public int no;
    public String name;
    public String nickname;
    public SortedHeroNode next;

    public SortedHeroNode(int hNo, String hName, String hNickname) {
        this.no = hNo;
        this.name = hName;
        this.nickname = hNickname;
    }

    @Override
    public String toString() {
        return String.format("No=[%s], Name=[%s], Nickname=[%s]", no, name, nickname);
    }
}