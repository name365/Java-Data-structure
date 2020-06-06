package github;

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
		
		//前序遍历查找
		//前序遍历查找的次数 :4 
		System.out.println("前序遍历查找方式:");
		HeroNode node = binaryTree.preOrderSearch(5);
		if(node != null){
			System.out.printf("找到了,信息为 id = %d name = %s\n",node.getId(),node.getName());
		} else  {
			System.out.printf("没有找到,相关信息的人物。\n");
		}
		
		//中序遍历查找
		//中序遍历查找次数 :3
		System.out.println("中序遍历查找方式:");
		HeroNode node2 = binaryTree.infoxSearch(5);
		if(node2 != null){
			System.out.printf("找到了,信息为 id = %d name = %s\n",node2.getId(),node2.getName());
		} else  {
			System.out.printf("没有找到,相关信息的人物。\n");
		}
		
		//后序遍历查找
		//后序遍历查找次数 :2
		System.out.println("中序遍历查找方式:");
		HeroNode node3 = binaryTree.postSearch(5);
		if(node3 != null){
			System.out.printf("找到了,信息为 id = %d name = %s\n",node3.getId(),node3.getName());
		} else  {
			System.out.printf("没有找到,相关信息的人物。\n");
		}
		
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
	
	//前序遍历查找
	/**
	  * 
	  * @Description 
	  * @param id 查找的id
	  * @return 如果找到就返回该Node,如果没有找到返回 null
	 */
	public HeroNode preOrdersearch(int id){
//		System.out.println("进入前序查找:");	//用于测试查找次数
		//比较当前节点
		if(this.id == id){
			return this;
		}
		//1.判断当前结点的左子节点是否为空,如果不为空,则递归前序查找
		//2.如果左送归前序查找,找到结点,则返回
		HeroNode res = null;
		if(this.left != null){
			res = this.left.preOrdersearch(id);
		}
		if(res != null){	//找到了左子树
			return res;
		}
		//3.否则继续判断,当前的结点的右子节点是否为空
		//4.如果不空,则维续向右递归前序查找。
		if(this.right != null){
			res = this.right.preOrdersearch(id);
		}
		return res;
	}
	
	//中序遍历查找
	public HeroNode infoxSearch(int id){
		//1.判断当前结点的左子节点是否为空,如果不为空,则递归中序查找
		HeroNode res = null;
		if(this.left != null){
			res = this.left.infoxSearch(id);
		}
		if(res != null){	//找到了左子树
			return res;
		}
//		System.out.println("进入中序查找:"); 	//用于测试查找次数
		//2.如果找到,则返回,如果没有找到,就和当前结点比较,
		//如果是则返回当前结点,否则继续进行右递归的中序查找
		if(this.id == id){
			return this;
		}
		//3.如果右递归中序查找,找到就返回,否则返回null
		if(this.right != null){
			res = this.right.infoxSearch(id);
		}
		return res;
	}
	
	//后序遍历查找
	public HeroNode postSearch(int id){
		//1.判断当前节点的左子节点是否为空,如果不为空,则递归后序查找
		HeroNode res = null;
		if(this.left != null){
			res = this.left.postSearch(id);
		}
		if(res != null){	//找到了左子树
			return res;
		}
		//2.如果左子树没有找到,则向右递归进行后序查找,如果找到,就返回
		if(this.right != null){
			res = this.right.postSearch(id);
		}
		if(res != null){	//找到了右子树
			return res;
		}
//		System.out.println("进入后序查找:"); 	//用于测试查找次数
		//3.如果左右子树都没有找到,就比较当前节点是不是
		if(this.id == id){
			return this;
		}
		return res;
	}
}

//定义一个二叉树
class BinaryTree{
	private HeroNode root;

	public void setRoot(HeroNode root) {
		this.root = root;
	}
	
	//前序遍历
	public HeroNode preOrderSearch(int id){
		if(root != null){
			return root.preOrdersearch(id);
		}else{
			return null;
		}
	}
	
	//中序遍历
	public HeroNode infoxSearch(int id){
		if(root != null){
			return root.infoxSearch(id);
		}else{
			return null;
		}
	}
	
	//后序遍历
	public HeroNode postSearch(int id){
		if(root != null){
			return root.postSearch(id);
		}else{
			return null;
		}
	}
}




