package github;

public class BinaryTreeTest2 {

	public static void main(String[] args) {
		//先创建一棵二叉树
		BinaryTree2 binaryTree = new BinaryTree2();
		//创建需要的节点
		HeroNode2 root = new HeroNode2(1,"宋江");
		HeroNode2 hero2 = new HeroNode2(2,"吴用");
		HeroNode2 hero3 = new HeroNode2(3,"卢俊义");
		HeroNode2 hero4 = new HeroNode2(4,"关胜");
		HeroNode2 hero5 = new HeroNode2(5,"林冲");
		
		//说明,先手动创建二叉树,再递归方式创建二叉树
		root.setLeft(hero2);
		root.setRight(hero3);
		hero3.setLeft(hero5);
		hero3.setRight(hero4);		
		binaryTree.setRoot(root);
		
		//测试删除
		System.out.println("删除前,前序遍历:");
		binaryTree.perOrder(); //  1,2,3,5,4
		binaryTree.delNode(5);
//		binaryTree.delNode(3);
		System.out.println("删除后，前序遍历:");
		binaryTree.perOrder(); // 1,2,3,4
		
	}
}

// 创建一个HeroNode结点
class HeroNode2 {
	private int id;
	private String name;
	private HeroNode2 left; // 默认为null,左子节点
	private HeroNode2 right; // 默认为null,右子节点

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

	@Override
	public String toString() {
		return "HeroNode [id=" + id + ", name=" + name + "]";
	}
	
	//前序遍历
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
	
	//递归删除结点
	//1.如果删除的节点是叶子节点,则删除该节点
	//2.如果删除的节点是非叶子节点,则删除该子树
	public void delNode(int id){
		/**
		 * 思路:
		 * 1.因为我们的二叉树是单向的，所以我们是判断当前结点的子结点是否需要删除结点，而不能去判断当前这个结点是不是需要删除结点.
		 * 2.如果当前结点的左子结点不为空，并且左子结点就是要删除结点，就将this.left = null;并且就返回(结束递归删除)
		 * 3.如果当前结点的右子结点不为空，并且右子结点就是要删除结点，就将this.right = null;并且就返回(结束递归删除)
		 * 4.如果第2和第3步没有删除结点，那么我们就需要向左子树进行递归删除
		 * 5.如果第4步也没有删除结点，则应当向右子树进行递归删除.
		 * 
		 */
		// 2.如果当前结点的左子结点不为空，并且左子结点就是要删除结点，就将this.left = null;并且就返回(结束递归删除)
		if(this.left != null && this.left.id == id){
			this.left = null;
			return;
		}
		//3.如果当前结点的右子结点不为空，并且右子结点就是要删除结点，就将this.right = null;并且就返回(结束递归删除)
		if(this.right != null && this.right.id == id){
			this.right = null;
			return;
		}
		//4.如果第2和第3步没有删除结点，那么我们就需要向左子树进行递归删除
		if(this.left != null){
			this.left.delNode(id);
		}
		//5.如果第4步也没有删除结点，则应当向右子树进行递归删除.
		if(this.right != null){
			this.right.delNode(id);
		}
	}
}

//定义一个二叉树
class BinaryTree2{
	private HeroNode2 root;

	public void setRoot(HeroNode2 root) {
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
	
	//删除节点
	public void delNode(int id){
		if(root != null){
			//如果只有一个root结点，则等价将二叉树置空
			if(root.getId() == id){
				root = null;
			}else{
				//递归删除
				root.delNode(id);
			}
		}else{
			System.out.println("空树,不能删除");
		}
	}
}
