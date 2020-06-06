package github2;

public class BTreeTest {

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
		//创建一个 BTree
		BTree bTree = new BTree(arr);
		bTree.preOrder();
	}

}

//编写一个BTree,实现顺序存储二叉树遍历
class BTree{
	private int[] arr;	//存储数据结点的数组

	public BTree(int[] arr) {
		super();
		this.arr = arr;
	}
	
	//重载方法
	public void preOrder(){
		this.preOrder(0);
	}
	
	//编写方法,实现顺序存储二叉树的前序遍历
	//index:数组的下标
	public void preOrder(int index){
		//如果数组为空,或arr.length = 0
		if(arr == null || arr.length == 0){
			System.out.println("数组为空,无法遍历查找。");
		}
		//输出当前数组的元素
		System.out.println(arr[index]);
		//向左递归遍历
		if((index * 2 + 1) < arr.length){			
			preOrder(2 * index + 1);
		}
		//向右递归遍历
		if((index * 2 + 2) < arr.length) {
			preOrder(2 * index + 2);
		}
	}
}