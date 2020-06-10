package 解压.github6;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanCode {

	public static void main(String[] args) {
		//测试压缩文件
		String srcFile = "D://subei.xml";
		String dstFile = "D://Uninstall.zip";
		
		zipFile(srcFile, dstFile);
		System.out.println("压缩文件ok~~");
				
	}
	
	//编写一个方法进行文件压缩
	/**
	  * 
	  * @Description 
	  * @author subei
	  * @date 2020年6月10日上午9:44:35
	  * @param srcFile 传入需要压缩文件的路径
	  * @param dstFile 压缩后文件的保存路径
	 */
	public static void zipFile(String srcFile,String dstFile){
		//创建输入流
		FileInputStream is = null;
		//创建输出流
		OutputStream os = null;
		ObjectOutputStream oos = null;
		try {			
			//创建文件的输入流
			is = new FileInputStream(srcFile);
			//创建一个和源文件大小一样的byte[]
			byte[] b = new byte[is.available()];
			//读取文件
			is.read(b);
			//直接对源文件压缩
			byte[] hfBytes = hfZip(b);
			//创建文件的输出流
			os = new FileOutputStream(dstFile);
			//创建一个和文件输出流关联的ObjectOutputStream
			oos = new ObjectOutputStream(os);
			//把赫夫曼编码后的字节数组写入压缩文件
			oos.writeObject(hfBytes); 
			//这里我们以对象流的方式写入赫夫曼编码,是为了以后我们恢复源文件时使用
			//注意一定要把赫夫曼编码 写入压缩文件
			oos.writeObject(huffCodes);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally{
			try {
				is.close();
				oos.close();
				os.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	//使用一个方法,将前面的方法封装起来,便于我们的调用.
	/**
	  * 
	  * @Description 
	  * @author subei
	  * @date 2020年6月9日下午6:50:14
	  * @param bytes 原始的字符串对应的字节数组
	  * @return 经过赫夫曼编码处理后的字节数组(即压缩后的数组)
	 */
	private static byte[] hfZip(byte[] bytes){
		List<Node> nodes = getNodes(bytes);
		//根据 nodes 创建的赫夫曼树
		Node hfTree = createHFTree(nodes);
		//对应的赫夫曼编码(根据 赫夫曼树)
		Map<Byte, String> hfCodes = getCodes(hfTree);
		//根据生成的赫夫曼编码,压缩得到压缩后的赫夫曼编码字节数组
		byte[] hfCodeBytes = zip(bytes, hfCodes);
		return hfCodeBytes;
	}
	
	//编写一个方法,将字符串对应的byte[]数组,通过生成的赫夫曼编码表,返回一个赫夫曼编码压缩后的byte[]
	/**
	  * 
	  * @Description 
	  * @author subei
	  * @date 2020年6月9日下午5:55:21
	  * @param bytes 原始的字符串对应的byte[]
	  * @param hfCodes 生成的赫夫曼编码map
	  * @return 返回赫夫曼编码处理后的 byte[] 
	  * 
	  * 举例: String content = "i like like like java do you like a java"; 
	  * 	 =》 byte[] contentBytes = content.getBytes();
	  * 
	  * 返回的是字符串 "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
	  * 	 => 对应的 byte[] hfCodeBytes ,即 8位数字对应一个 byte,放入到 hfCodeBytes
	  * 
	  * hfCodeBytes[0] =  10101000(补码) 
	  * 	 => byte  [推导  10101000=> 10101000 - 1 => 10100111(反码)=> 11011000= -88 ]
	  * hfCodeBytes[1] = -88
	 */
	private static byte[] zip(byte[] bytes,Map<Byte,String> hfCodes){
		
		//1.利用 hfCodes 将  bytes 转成赫夫曼编码对应的字符串
		StringBuilder stringBuilder = new StringBuilder();
		//遍历bytes数组
		for(byte b : bytes){
			stringBuilder.append(hfCodes.get(b));
		}
		
//		System.out.println("测试 stringBuilder=" + stringBuilder.toString());
		
		//将 "1010100010111111110..." 转成 byte[]
		//统计返回  byte[] hfCodeBytes 长度
		//即 int len = (stringBuilder.length() + 7) / 8;
		int len;
		if(stringBuilder.length() % 8 == 0){	
			len = stringBuilder.length() / 8;
		}else{
			len = stringBuilder.length() / 8 + 1;
		}
		
		//创建存储压缩后的 byte数组
		byte[] hfCodeBytes = new byte[len];
		int index = 0;//记录是第几个byte
		for (int i = 0; i < stringBuilder.length(); i += 8) { //因为是每8位数字对应一个byte,所以步长 +8
			String strByte;
			if(i+8 > stringBuilder.length()) {//不够8位
				strByte = stringBuilder.substring(i);
			}else{
				strByte = stringBuilder.substring(i, i + 8);
			}	
			//将strByte 转成一个byte,放入到 hfCodeBytes
			hfCodeBytes[index] = (byte)Integer.parseInt(strByte, 2);
			index++;
		}
		
		return hfCodeBytes;
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
