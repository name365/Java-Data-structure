package 压缩.github;

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
		
		//验证是否生成对应的赫夫曼编码
		Map<Byte, String> hfCodes = getCodes(hfTree);
		System.out.println("生成的赫夫曼编码表:" + hfCodes);
	}
	
	//生成赫夫曼树对应的赫夫曼编码
	//思路:
	//1.将赫夫曼编码表存放在 Map<Byte,String> 形式:
	//  生成的赫夫曼编码表{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
	static Map<Byte, String> huffCodes = new HashMap<Byte,String>();
	//2.在生成赫夫曼编码表示,需要去拼接路径,定义一个StringBuilder 存储某个叶子结点的路径
	static StringBuilder stringBuilder = new StringBuilder();
	
	/**
	  * 
	  * @Description 功能:将传入的node结点的所有叶子结点的赫夫曼编码得到,并放入到huffCodes集合
	  * @author subei
	  * @date 2020年6月9日下午5:28:28
	  * @param nodes 传入结点
	  * @param code 路径:左子结点是 0,右子结点 1
	  * @param stringBuilder 用于拼接路径
	 */
	private static void getCodes(Node nodes,String code,StringBuilder stringBuilder){
		StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
		
		//将code 加入到 stringBuilder2
		stringBuilder2.append(code);
		
		if(nodes != null){	//如果nodes == null不处理
			//判断当前nodes是叶子结点,还是非叶子结点
			if(nodes.date == null) { //非叶子结点
				//递归处理,即先向左递归处理
				getCodes(nodes.left,"0",stringBuilder2);
				//再向右递归
				getCodes(nodes.right,"1",stringBuilder2);
			}else{	//否则,说明是一个叶子结点,就表示找到某个叶子结点的最后
				huffCodes.put(nodes.date, stringBuilder2.toString());
			}
		}
	}
	
	//为了调用方便,进行重载 getCodes
	private static Map<Byte, String> getCodes(Node root) {
		if(root == null) {
			return null;
		}
		//处理root的左子树
		getCodes(root.left, "0", stringBuilder);
		//处理root的右子树
		getCodes(root.right, "1", stringBuilder);
		return huffCodes;
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