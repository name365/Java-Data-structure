package github;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffTreeTest {

	public static void main(String[] args) {
		int arr[] = { 13, 7, 8, 3, 29, 6, 1 };
		Node root = creatHFTree(arr);

		preOrder(root);
	}

	// 前序遍历方法
	public static void preOrder(Node root) {
		if (root != null) {
			root.preOrder();
		} else {
			System.out.println("这是一个空树。无法遍历");
		}
	}

	// 创建赫夫曼树的方法
	public static Node creatHFTree(int[] arr) {
		// 第一步,为了操作方法
		// 1.遍历 arr 数组
		// 2.将arr的每个元素构成一个Node
		// 3.将Node 放入到ArrayList中
		List<Node> nodes = new ArrayList<Node>();
		for (int value : arr) {
			nodes.add(new Node(value));
		}

		// int count = 0; //统计处理次数

		// 处理的过程是循环的过程
		while (nodes.size() > 1) {

			// 排序:从小到大排序
			Collections.sort(nodes);

			// System.out.println("第" + count + "次排序后的结果:nodes = " + nodes);

			// 取出根节点权值最小的两颗二叉树
			// (1)取出权值最小的结点(二叉树)
			Node leftNode = nodes.get(0);
			// (1)取出权值另一个最小的结点(二叉树)
			Node rightNode = nodes.get(1);

			// (3)构建一个新的二叉树
			Node parents = new Node(leftNode.value + rightNode.value);
			parents.left = leftNode;
			parents.right = rightNode;

			// (4)从ArrayList删除处理过的二叉树
			nodes.remove(leftNode);
			nodes.remove(rightNode);

			// (5)将parent加入到nodes
			nodes.add(parents);

			// count++;
			// System.out.println("第" + count + "次处理后的结果:" + nodes);
		}

		// 返回赫夫曼树的root结点
		return nodes.get(0);
	}

}

// 创建结点类
// 为了让Node 对象持续排序Collections集合排序
// 让Node 实现Comparable接口
class Node implements Comparable<Node> {
	int value; // 结点权值
	Node left; // 指向左子结点
	Node right; // 指向右子结点

	public Node(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}

	@Override
	public int compareTo(Node o) {
		// 表示从小到大排序
		return this.value - o.value;
	}

	// 前序遍历
	public void preOrder() {
		// 当前结点
		System.out.println(this);
		// 左子结点
		if (this.left != null) {
			this.left.preOrder();
		}
		// 右子结点
		if (this.right != null) {
			this.right.preOrder();
		}
	}
}
