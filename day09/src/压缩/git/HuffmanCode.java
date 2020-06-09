package 压缩.git;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanCode {

	public static void main(String[] args) {
		String str = "i like like like java do you like a java";
		byte[] bytes = str.getBytes();
		System.out.println("字符串长度:" + bytes.length);	//40
		
		List<Node> nodes = getNodes(bytes);
		System.out.println("nodes = " + nodes);
		
		//创建的二叉树
		System.out.println("赫夫曼树");
		Node hfTree = createHFTree(nodes);
		System.out.println("前序遍历赫夫曼树:");
		hfTree.preOrder();
	}
	
	/**
	  * 
	  * @Description 
	  * @author subei
	  * @date 2020年6月9日下午4:56:13
	  * @param bytes 接受字符数组
	  * @return 返回的就是List形式  [Node[date=97 ,weight = 5], Node[date=32,weight = 9]......]
	 */
	private static List<Node> getNodes(byte[] bytes){
		//1.创建一个ArrayList
		ArrayList<Node> nodes = new ArrayList<Node>();
		
		//遍历bytes,统计每个byte出现的次数,使用map[key,value]
		Map<Byte, Integer> counts = new HashMap<>();
		for(byte b: bytes){
			Integer count = counts.get(b);
			if(count == null){	//Map中没有这个字符,初次检索
				counts.put(b, 1);
			}else{
				counts.put(b, count+1);
			}
		}
		
		//将每个键值对转成一个Node对象,并加入nodes集合
		//遍历map
		for(Map.Entry<Byte, Integer> entry : counts.entrySet()){
			nodes.add(new Node(entry.getKey(),entry.getValue()));
		}
		
		return nodes;
	}
	
	//通过List 创建对应的赫夫曼树
	private static Node createHFTree(List<Node> nodes){
		while(nodes.size() > 1){
			//排序:从小到大
			Collections.sort(nodes);
			//取出第一颗最小的二叉树
			Node leftNode = nodes.get(0);
			//取出第二颗最小的二叉树
			Node rightNode = nodes.get(1);
			//创建一颗新的二叉树,它的根节点没有data,只有权值
			Node parent = new Node(null, leftNode.weight + rightNode.weight);
			parent.left = leftNode;
			parent.right = rightNode;
			
			//将已经处理的两颗二叉树从nodes删除
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			
			//将新的二叉树,加入到nodes
			nodes.add(parent);
		}
		//nodes 最后的结点,就是赫夫曼树的根结点
		return nodes.get(0);
	}
	
	//前序遍历方法
	private static void preOrder(Node root){
		if(root != null){
			root.preOrder();
		}else{
			System.out.println("赫夫曼树为空！！！");
		}
	}

}
//创建Node,存放数据和权值
class Node implements Comparable<Node>{
	Byte date;	//存放数据(字符)本身,比如‘a’ =》 97 ; ' ' =》 32
	int weight;	//权值,表示字符
	Node left;	//指向左子树
	Node right;	//指向右子树
	
	public Node(Byte date, int weight) {
		super();
		this.date = date;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) {	//按从小到大排序
		return this.weight - o.weight;
	}

	@Override
	public String toString() {
		return "Node [date=" + date + ", weight=" + weight + "]";
	}
	
	//前序遍历方法
	public void preOrder(){
		System.out.println(this);
		if(this.left != null){
			this.left.preOrder();
		}
		if(this.right != null){
			this.right.preOrder();
		}
	}
}