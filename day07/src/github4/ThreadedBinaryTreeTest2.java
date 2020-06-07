package github4;

public class ThreadedBinaryTreeTest2 {

	public static void main(String[] args) {
		// 测试前、后序线索二叉树的功能
		HeroNode2 root = new HeroNode2(1, "Z1");
		HeroNode2 node2 = new HeroNode2(3, "S5");
		HeroNode2 node3 = new HeroNode2(6, "R6");
		HeroNode2 node4 = new HeroNode2(8, "G4");
		HeroNode2 node5 = new HeroNode2(10, "P8");
		HeroNode2 node6 = new HeroNode2(14, "Q1");

		// 二叉树，后面需要要递归创建,所以先简单处理使用手动创建
		root.setLeft(node2);
		root.setRight(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		node3.setLeft(node6);
		
		//添加父节点
		node2.setParent(root);
		node3.setParent(root);
		node4.setParent(node2);
		node5.setParent(node2);
		node6.setParent(node3);

		// 测试后序线索化
		ThreadTree2 threadTree = new ThreadTree2();
		threadTree.setRoot(root);
		threadTree.endTreadNodes(root);

		// 测试:以10号节点测试
		HeroNode2 left = node5.getLeft();
		HeroNode2 right = node5.getRight();
		System.out.println("10号节点的前驱是:" + left);
		System.out.println("10号节点的后继是:" + right);

		System.out.println("使用线索化的方式遍历线索化二叉树:");
		threadTree.threadedList(); //
	}
}

// 定义一个ThreadTree2二叉树,实现线索化功能
class ThreadTree2 {
	private HeroNode2 root;

	// 为了实现线索化,需要创建要给指向当前结点的前驱结点的指针
	// 在递归进行线索化时,pre 总是保留前一个结点
	private HeroNode2 pre = null;

	public void setRoot(HeroNode2 root) {
		this.root = root;
	}

	// 后序遍历线索化二叉树的方法
	public void threadedList() {
		//定义一个变量,用于|存储当前遍历的结点,从root开始
		HeroNode2 node = root;
		while(node != null && node.getLeftType() != 1){
				node = node.getLeft();
		}
		HeroNode2 pre = null;
		while(node != null){
			if(node.getRightType()!= 0){
				System.out.println(node);
				pre = node;
				node = node.getRight();
			}else{
				if(node.getRight() == pre){
					System.out.println(node);
					if(node == root){
						return;
					}
					pre = node;
					node = node.getParent();
				}else{
					node = node.getRight();
					while(node.getLeftType() != 1){
						node = node.getLeft();
					}
				}
			}
		}
	}

	// 编写对二叉树进行后序线索化的方法
	// node 即当前需要线索化的结点
	public void endTreadNodes(HeroNode2 node) {
		// 如果node==null,无法线索化
		if (node == null) {
			return;
		}
		
		// 1.线索化左子树
		endTreadNodes(node.getLeft());
		
		// 2.再线索化右子树
		endTreadNodes(node.getRight());
		
		// 3.线索化当前结点
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
	}
}

//创建一个HeroNode2结点
class HeroNode2 {
	private int id;
	private String name;
	private HeroNode2 left; // 默认为null,左子节点
	private HeroNode2 right; // 默认为null,右子节点
	private HeroNode2 parent;	//父结点指针

	// 说明:
	// 1.如果leftType == 0 表示指向的是左子树, 如果 1 则表示指向前驱结点
	// 2.如果rightType == 0 表示指向是右子树, 如果 1表示指向后继结点
	private int leftType = 0;
	private int rightType = 0;

	public HeroNode2() {
		super();
	}

	public HeroNode2(int id, String name) {
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

	public HeroNode2 getLeft() {
		return left;
	}

	public void setLeft(HeroNode2 left) {
		this.left = left;
	}

	public HeroNode2 getRight() {
		return right;
	}

	public void setRight(HeroNode2 right) {
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
	
	public HeroNode2 getParent() {
		return parent;
	}

	public void setParent(HeroNode2 parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "HeroNode [id=" + id + ", name=" + name + "]";
	}
}