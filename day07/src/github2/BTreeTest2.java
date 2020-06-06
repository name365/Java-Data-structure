package github2;

public class BTreeTest2 {

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
		//创建一个 BTree
		BTree2 bTree = new BTree2(arr);
		bTree.infixOrder(0);	//中序
		System.out.println();
		bTree.postOrder(0);	//后序
	}

}

//编写一个BTree,实现顺序存储二叉树遍历
class BTree2{
	private int[] arr;	//存储数据结点的数组

	public BTree2(int[] arr) {
		super();
		this.arr = arr;
	}
	
	//编写方法,实现顺序存储二叉树的中序遍历
	public void infixOrder(int index){
		//向左递归遍历
		if((index * 2 + 1) < arr.length){			
			infixOrder(2 * index + 1);
		}
		//如果数组为空,或arr.length = 0
		if(arr == null || arr.length == 0){
			System.out.println("数组为空,无法遍历查找。");
		}
		//输出当前数组的元素
		System.out.print("中序遍历:" + arr[index] + " ");
		//向右递归遍历
		if((index * 2 + 2) < arr.length) {
			infixOrder(2 * index + 2);
		}
	}
	
	//编写方法,实现顺序存储二叉树的后序遍历
	public void postOrder(int index){
		//向左递归遍历
		if((index * 2 + 1) < arr.length){			
			postOrder(2 * index + 1);
		}
		//向右递归遍历
		if((index * 2 + 2) < arr.length) {
			postOrder(2 * index + 2);
		}
		//如果数组为空,或arr.length = 0
		if(arr == null || arr.length == 0){
			System.out.println("数组为空,无法遍历查找。");
		}
		//输出当前数组的元素
		System.out.print("后序遍历:" + arr[index] + " ");
	}
}