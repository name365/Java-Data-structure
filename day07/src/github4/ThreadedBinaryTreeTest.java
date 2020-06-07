package github4;

public class ThreadedBinaryTreeTest {

	public static void main(String[] args) {
		// 测试前、后序线索二叉树的功能
		HeroNode root = new HeroNode(1, "Z1");
		HeroNode node2 = new HeroNode(3, "S5");
		HeroNode node3 = new HeroNode(6, "R6");
		HeroNode node4 = new HeroNode(8, "G4");
		HeroNode node5 = new HeroNode(10, "P8");
		HeroNode node6 = new HeroNode(14, "Q1");

		// 二叉树，后面需要要递归创建,所以先简单处理使用手动创建
		root.setLeft(node2);
		root.setRight(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		node3.setLeft(node6);

		// 测试前序线索化
		ThreadTree threadTree = new ThreadTree();
		threadTree.setRoot(root);
		threadTree.endTreadNodes(root);

		// 测试:以10号节点测试
		HeroNode left = node5.getLeft();
		HeroNode right = node5.getRight();
		System.out.println("10号节点的前驱是:" + left);
		System.out.println("10号节点的后继是:" + right);

		System.out.println("使用线索化的方式遍历线索化二叉树:");
		threadTree.threadedList(); // 1,3,8,10,6,14
	}
}

// 定义一个ThreadTree二叉树,实现线索化功能
class ThreadTree {
	private HeroNode root;

	// 为了实现线索化,需要创建要给指向当前结点的前驱结点的指针
	// 在递归进行线索化时,pre 总是保留前一个结点
	private HeroNode pre = null;

	public void setRoot(HeroNode root) {
		this.root = root;
	}

	// 前序遍历线索化二叉树的方法
	public void threadedList() {
		// 定义一个变量,用于|存储当前遍历的结点,从root开始
		HeroNode node = root;
		while (node != null) {
			// 循环的找到leftType == 1的结点,第一个找到就是8结点
			// 后面随着遍历而变化,因为当leftType==1时,说明该结点是按照线索化处理后的有效结点
			while (node.getLeftType() != 1) {
				System.out.println(node);
				node = node.getLeft();
			}
			System.out.println(node);
			// 替换这个遍历节点
			node = node.getRight();
		}
	}

	// 编写对二叉树进行前序线索化的方法
	// node 即当前需要线索化的结点
	public void endTreadNodes(HeroNode node) {

		// 2.再线索化左子树
		if (node == null) {
			return;
		}
		
		// 1.先线索化当前结点
		// 处理当前结点的前驱结点
		// 例如:8结点的.left = null , 8结点的.leftType = 1
		if (node.getLeft() == null) {
			// 让当前结点的左指针指向前驱结点
			node.setLeft(pre);
			// 修改当前结点的左指针的类型,指向前驱结点
			node.setLeftType(1);
		}

		// 处理后继结点
		if (pre != null && pre.getRight() == null) {
			pre.setRight(node); // 让前驱结点的右指针指向当前结点
			pre.setRightType(1); // 修改前驱结点的右指针类型
		}
		// 重要:每处理一个结点后,让当前结点是下一个结点的前驱结点
		pre = node;
		
		// 2.再线索化左子树
		if(node.getLeftType() != 1) {
			endTreadNodes(node.getLeft());
		}
		
		// 3.再线索化右子树
		if(node.getRightType() != 1) {
			endTreadNodes(node.getRight());
		}	
	}
}

// 创建一个HeroNode结点
class HeroNode {
	private int id;
	private String name;
	private HeroNode left; // 默认为null,左子节点
	private HeroNode right; // 默认为null,右子节点

	// 说明:
	// 1.如果leftType == 0 表示指向的是左子树, 如果 1 则表示指向前驱结点
	// 2.如果rightType == 0 表示指向是右子树, 如果 1表示指向后继结点
	private int leftType;
	private int rightType;

	public HeroNode() {
		super();
	}

	public HeroNode(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getLeftType() {
		return leftType;
	}

	public void setLeftType(int leftType) {
		this.leftType = leftType;
	}

	public int getRightType() {
		return rightType;
	}

	public void setRightType(int rightType) {
		this.rightType = rightType;
	}

	@Override
	public String toString() {
		return "HeroNode [id=" + id + ", name=" + name + "]";
	}
}