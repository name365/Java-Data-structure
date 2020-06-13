package github;

public class BinarySortTreeTest {

	public static void main(String[] args) {
		int arr[] = {7,3,10,12,5,1,9};
		BinaryTree tree = new BinaryTree();
		//循环的添加结点到二叉排序树
		for(int i = 0 ;i< arr.length;i++){
			tree.add(new Node(arr[i]));
		}
		
		//中序遍历二叉排序树
		System.out.println("中序遍历此树:");
		tree.infixOrder(); 	//1,3,5,7,9,10,12
		
		//测试一下删除叶子节点
		tree.delNode(2);
		tree.delNode(5);
		tree.delNode(9);
		System.out.println("删除后的节点:");
		tree.infixOrder();
	}

}

//创建Node结点
class Node{
	int value;
	Node left;
	Node right;
	
	public Node(int value) {
		super();
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}

	//添加节点的方法
	//递归的形式添加结点,注意需要满足二叉排序树的要求
	public void add(Node node){
		if(node == null){
			return;
		}
		
		//判断传入的结点的值,和当前子树的根结点的值的关系
		if(node.value < this.value){
			if(this.left == null){	//如果当前结点左子结点为null
				this.left = node;
			}else{
				//递归的向左子树添加
				this.left.add(node);
			}
		}else{	//添加的节点的值大于当前结点的值
			if(this.right == null){
				this.right = node;
			}else{
				//递归的向右子树添加
				this.right.add(node);
			}
		}
	}
	
	//中序遍历
	public void infixOrder(){
		if(this.left != null){
			this.left.infixOrder();
		}
		System.out.println(this);
		if(this.right != null){
			this.right.infixOrder();
		}
	}
	
	//查找要删除的节点
	/**
	  * 
	  * @Description 
	  * @author subei
	  * @date 2020年6月13日上午8:43:01
	  * @param value 希望删除的结点的值
	  * @return 如果找到该值返回,未找到返回null
	 */
	public Node search(int value){
		if(value == this.value){	//说明找到了
			return this;
		}else if(value < this.value){	//查找的值小于当前结点的值,向左子树查找
			if(this.left == null){	//左子结点为空
				return null;
			}
			return this.left.search(value);
		}else{	//查找的值不小于当前结点的值,向右子树查找
			if(this.right == null){
				return null;
			}
			return this.right.search(value);
		}
	}
	

	//查找要删除结点的父结点
	/**
	 * 
	 * @param value 希望删除的结点的值
	 * @return 返回的是要删除的结点的父结点,如果没有就返回null
	 */
	public Node searchP(int value){
		//如果当前结点是要删除的结点的父结点,如果没有就返回null
		if((this.left != null && this.left.value == value)||
				(this.right != null && this.right.value == value)){
			return this;
		}else{
			//如果查找的值小于当前结点的值,且当前结点的左子结点不为空
			if(value < this.value && this.left != null){
				return this.left.searchP(value);	//向左子树查找
			}else if(value >= this.value && this.right != null){
				return this.right.searchP(value);	//向右子树递归查找
			}else {
				return null;	//未找到父结点
			}
		}
	}
	
	
}

//创建二叉排序树
class BinaryTree{
	private Node root;
	//添加结点的方法
	public void add(Node node){
		if(root == null){
			root = node;	//如果root为空则直接让root指向node
		}else{
			root.add(node);
		}
	}
	//遍历方法
	public void infixOrder(){
		if(root != null){
			root.infixOrder();
		}else{
			System.out.println("二叉排序树为空！！！");
		}
	}
	//查找要刪除的结点
	public Node search(int value){
		if(root == null){
			return null;
		}else{
			return root.search(value);
		}
	}
	//查找要删除的节点的父节点
	public Node searchP(int value){
		if(root == null){
			return null;
		}else{
			return root.searchP(value);
		}
	}
	
	//删除节点
	public void delNode(int value){
		if(root == null){
			return;
		}else{
			//1.需求先去找到要删除的结点  targetNode
			Node targetNode = search(value);
			//如果没有找到要删除的结点
			if(targetNode ==null){
				return;
			}
			//如果我们发现当前这颗二叉排序树只有一个结点
			if(root.left == null && root.right == null) {
				root = null;
				return;
			}
			//去找到targetNode的父结点
			Node parent = searchP(value);
			//如果要删除的节点为叶子节点
			if(targetNode.left == null && targetNode.right == null){
				//判断targetNode是父节点的左子结点,还是右子节点
				if(parent.left != null && parent.left.value == value){	//左子节点
					parent.left = null;
				}else if(parent.right != null && parent.right.value == value){	//右子节点
					parent.right = null;
				}
			}
		}
	}
}
