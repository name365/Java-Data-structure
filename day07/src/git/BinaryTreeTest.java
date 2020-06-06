package git;

public class BinaryTreeTest {

	public static void main(String[] args) {
		//先创建一棵二叉树
		BinaryTree binaryTree = new BinaryTree();
		//创建需要的节点
		HeroNode root = new HeroNode(1,"宋江");
		HeroNode hero2 = new HeroNode(2,"吴用");
		HeroNode hero3 = new HeroNode(3,"卢俊义");
		HeroNode hero4 = new HeroNode(4,"关胜");
		HeroNode hero5 = new HeroNode(5,"林冲");
		
		//说明,先手动创建二叉树,再递归方式创建二叉树
		root.setLeft(hero2);
		root.setRight(hero3);
		hero3.setLeft(hero5);
		hero3.setRight(hero4);		
		binaryTree.setRoot(root);
		
		//测试
		System.out.println("前序遍历:");	//1,2,3,5,4
		binaryTree.perOrder();
		
		//测试2
		System.out.println("中序遍历:");	//2,1,5,3,4
		binaryTree.infixOrder();
		
		//测试3
		System.out.println("后序遍历:");	//2,5,4,3,1
		binaryTree.postOrder();
	}
}

// 创建一个HeroNode结点
class HeroNode {
	private int id;
	private String name;
	private HeroNode left; // 默认为null,左子节点
	private HeroNode right; // 默认为null,右子节点

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

	@Override
	public String toString() {
		return "HeroNode [id=" + id + ", name=" + name + "]";
	}

	// 编写前序遍历的方法
	public void perOrder() {
		System.out.println(this); // 输出父结点
		// 递归向左子树前序遍历
		if (this.left != null) {
			this.left.perOrder();
		}
		// 递归向右子树前序遍历
		if (this.right != null) {
			this.right.perOrder();
		}
	}

	// 编写中序遍历的方法
	public void infixOrder(){
		//递归向左子树中序遍历
		if(this.left != null){
			this.left.infixOrder();
		}
		//输出父节点
		System.out.println(this);
		//递归向右子树中序遍历 
		if(this.right != null){
			this.right.infixOrder();
		}
	}

	// 编写后序遍历的方法
	public void postOrder(){
		//递归向左子树后序遍历
		if(this.left != null){
			this.left.postOrder();
		}
		//递归向右子树后序遍历
		if(this.right != null){
			this.right.postOrder();
		}
		//输出父节点
		System.out.println(this);
	}
}

//定义一个二叉树
class BinaryTree{
	private HeroNode root;

	public void setRoot(HeroNode root) {
		this.root = root;
	}
	
	//前序遍历
	public void perOrder() {
		if(this.root != null){
			this.root.perOrder();
		}else{
			System.out.println("二叉树为空,无法遍历");
		}
	}
	
	//中序遍历
	public void infixOrder(){
		if(this.root != null){
			this.root.infixOrder();
		}else{
			System.out.println("二叉树为空,无法遍历");
		}
	}
	
	//后序遍历
	public void postOrder(){
		if(this.root != null){
			this.root.postOrder();
		}else{
			System.out.println("二叉树为空,无法遍历");
		}
	}
}




