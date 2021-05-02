package algorithm.tree;

import com.google.common.base.MoreObjects;
import org.junit.Test;

/**
 *
 * 这个类是关于 二叉排序树 的介绍
 *
 */
public class BSTDemo {

    @Test
    public void test1() {
        BinarySortTree bst = new BinarySortTree();
        int[] arr = {7,3,10,12,5,1,9};
        for (int val : arr) {
            bst.add(new Node(val));
        }

        bst.infixOrder();
    }

    /**
     * 创建二叉排序树
     */
    class BinarySortTree {

        private Node node;

        public void add(Node node) {
            if (this.node == null) {
                this.node = node;
            } else {
                this.node.add(node);
            }
        }

        public void infixOrder() {
            if (this.node != null) {
                this.node.infixOrder();
            } else {
                System.out.println("Root is null");
            }
        }
    }

    /**
     * 创建节点
     */
    class Node {
        int value;
        Node left;
        Node right;

        public Node(final int value) {
            this.value = value;
        }

        // 添加节点的方法
        // 递归的形式添加节点
        public void add(Node node) {
            if (node == null) {
                return;
            } else {
                if (node.value >= this.value) {
                    if (this.right != null) {
                        this.right.add(node);
                    } else {
                        this.right = node;
                    }
                } else {
                    if (this.left != null) {
                        this.left.add(node);
                    } else {
                        this.left = node;
                    }
                }
            }
        }

        //中序遍历
        public void infixOrder() {
            if (this.left != null) {
                this.left.infixOrder();
            }
            System.out.println(this);
            if (this.right != null) {
                this.right.infixOrder();
            }
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this).add("value", value).toString();
        }
    }
}
