package algorithm.tree;

import org.junit.Assert;

public class BinaryTreeDemo {

	public static void main(String[] args) {
		BinaryTree binaryTree = new BinaryTree();
		HeroNode root = new HeroNode(1, "AA1");
		HeroNode node2 = new HeroNode(2, "BB2");
		HeroNode node3 = new HeroNode(3, "CC3");
		HeroNode node4 = new HeroNode(4, "DD4");
		HeroNode node5 = new HeroNode(5, "EE5");
		
		root.setLeft(node2);
		root.setRight(node3);
		node3.setRight(node4);
		node3.setLeft(node5);
		binaryTree.setRoot(root);
		
		//binaryTree.preOrder();  //1,2,3,5,4
		//binaryTree.infixOrder();// 2,1,5,3,4
		//binaryTree.postOrder(); // 2,5,4,3,1
		
		//System.out.println(binaryTree.preOrderSearch(5).getName());
		//System.out.println(binaryTree.infixOrderSearch(5).getName());
		//System.out.println(binaryTree.postOrderSearch(5).getName());
		
		//binaryTree.preOrder(); //  1,2,3,5,4
		//binaryTree.delNode(5);
		//binaryTree.preOrder(); // 1,2,3,4
	}
}

class BinaryTree {
	private HeroNode root;

	public void setRoot(HeroNode root) {
		this.root = root;
	}
	
	public void delNode(int no) {
		if(root != null) {
			if(root.getNo() == no) {
				root = null;
			} else {
				root.delNode(no);
			}
		}else{
		}
	}
	
	/**
	 * 指先访问根，然后访问子树的遍历方式
	 */
	public void preOrder() {
		this.root.preOrder();
	}
	
	/**
	 * 指先访问左（右）子树，然后访问根，最后访问右（左）子树的遍历方式
	 */
	public void infixOrder() {
		this.root.infixOrder();
	}
	
	/**
	 * 指先访问子树，然后访问根的遍历方式
	 */
	public void postOrder() {
		this.root.postOrder();
	}
	
	public HeroNode preOrderSearch(int no) {
		return root.preOrderSearch(no);
	}
	public HeroNode infixOrderSearch(int no) {
		return root.infixOrderSearch(no);
	}
	public HeroNode postOrderSearch(int no) {
		return this.root.postOrderSearch(no);
	}
}

class HeroNode {
	private int no;
	private String name;
	private HeroNode left;
	private HeroNode right;
	public HeroNode(int no, String name) {
		this.no = no;
		this.name = name;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HeroNode getLeft() {
		return left;
	}
	public void setLeft(HeroNode left) {
		this.left = left;
	}
	public HeroNode getRight() {
		return right;
	}
	public void setRight(HeroNode right) {
		this.right = right;
	}
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + "]";
	}
	
	public void delNode(int no) {
		if(this.left != null && this.left.no == no) {
			this.left = null;
			return;
		}
		if(this.right != null && this.right.no == no) {
			this.right = null;
			return;
		}
		if(this.left != null) {
			this.left.delNode(no);
		}
		if(this.right != null) {
			this.right.delNode(no);
		}
	}
	
	public void preOrder() {
		System.out.println(this);
		if(this.left != null) {
			this.left.preOrder();
		}
		if(this.right != null) {
			this.right.preOrder();
		}
	}
	
	public void infixOrder() {
		if(this.left != null) {
			this.left.infixOrder();
		}
		System.out.println(this);
		if(this.right != null) {
			this.right.infixOrder();
		}
	}
	
	public void postOrder() {//left-> right-> middle
		if(this.left != null) {
			this.left.postOrder();
		}
		if(this.right != null) {
			this.right.postOrder();
		}
		System.out.println(this);
	}
	
	public HeroNode preOrderSearch(int no) {
		if(this.no == no) {
			return this;
		}
		HeroNode resNode = null;
		if(this.left != null) {
			resNode = this.left.preOrderSearch(no);
		}
		if(resNode != null) {
			return resNode;
		}
		if(this.right != null) {
			resNode = this.right.preOrderSearch(no);
		}
		return resNode;
	}
	
	public HeroNode infixOrderSearch(int no) {
		HeroNode resNode = null;
		if(this.left != null) {
			resNode = this.left.infixOrderSearch(no);
		}
		if(resNode != null) {
			return resNode;
		}
		if(this.no == no) {
			return this;
		}
		if(this.right != null) {
			resNode = this.right.infixOrderSearch(no);
		}
		return resNode;
		
	}
	
	public HeroNode postOrderSearch(int no) {
		HeroNode resNode = null;
		if(this.left != null) {
			resNode = this.left.postOrderSearch(no);
		}
		if(resNode != null) {
			return resNode;
		}
		if(this.right != null) {
			resNode = this.right.postOrderSearch(no);
		}
		if(this.no == no) {
			return this;
		}
		return resNode;
	}
}