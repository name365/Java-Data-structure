package github;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {

	private ArrayList<String> vertexList; // 存储顶点集合
	private int[][] str; // 存储图对应的邻接矩阵
	private int numofEdges; // 表示边的数目
	// 定义一个数组boolean[], 记录某个结点是否被访问
	private boolean[] isNut;

	public static void main(String[] args) {

		// 测试图的创建
		int n = 5; // 结点的个数
		String Ver[] = { "A", "B", "C", "D", "E" };

		// 创建图对象
		Graph graph = new Graph(n);
		// 循环的添加顶点
		for (String vertex : Ver) {
			graph.insertVertex(vertex);
		}

		// 添加边
		// A-B A-C B-C B-D B-E
		graph.insertEdge(0, 1, 1); // A-B
		graph.insertEdge(0, 2, 1); // A-C
		graph.insertEdge(1, 2, 1); // B-C
		graph.insertEdge(1, 3, 1); // B-D
		graph.insertEdge(1, 4, 1); // B-E

		// 显示邻接矩阵
		graph.showGraph();

		//测试dfs
		System.out.println("深度遍历:");
		graph.dfs();	//A->B->C->D->E->
		
	}

	// 构造器
	public Graph(int n) {
		// 初始化矩阵和vertexList
		str = new int[n][n];
		vertexList = new ArrayList<String>(n);
		numofEdges = 0;
		isNut = new boolean[5];
	}

	// 插入结点
	public void insertVertex(String vertex) {
		vertexList.add(vertex);
	}

	// 添加边
	/**
	 * 
	 * @Description
	 * @author subei
	 * @date 2020年6月14日下午6:37:03
	 * @param v1
	 *            表示点的下标即第几个顶点 "A"-"B" "A"->0 "B"->1
	 * @param v2
	 *            第二个顶点对应的下标
	 * @param weight
	 *            表示
	 */
	public void insertEdge(int v1, int v2, int weight) {
		str[v1][v2] = weight;
		str[v2][v1] = weight;
		numofEdges++;
	}

	// 图中常用的方法
	// 返回结点的个数
	public int getNumber() {
		return vertexList.size();
	}

	// 得到边的个数
	public int getNumEdg() {
		return numofEdges;
	}

	// 返回结点i(下标)对应的值 0->"A" 1->"B" 2->"C"
	public String getValueByIndex(int i) {
		return vertexList.get(i);
	}

	// 返回v1和v2的权值
	public int getWeight(int v1, int v2) {
		return str[v1][v2];
	}

	// 返回结点的个数
	public int getNumOfVertex() {
		return vertexList.size();
	}

	// 显示图对应的矩阵
	public void showGraph() {
		for (int[] link : str) {
			System.err.println(Arrays.toString(link));
		}
	}

	// 得到第一个邻接结点的下标 w
	/**
	 * 
	 * @Description
	 * @author subei
	 * @date 2020年6月14日下午8:03:22
	 * @param index
	 * @return 如果存在就返回对应的下标，否则返回-1
	 */
	public int getFirstNeigh(int index) {
		for (int j = 0; j < vertexList.size(); j++) {
			if (str[index][j] > 0) {
				return j;
			}
		}
		return -1;
	}

	// 根据前一个邻接结点的下标来获取下一个邻接结点
	public int getNextNeigh(int v1, int v2) {
		for (int j = v2 + 1; j < vertexList.size(); j++) {
			if (str[v1][j] > 0) {
				return j;
			}
		}
		return -1;
	}

	// 深度优先算法
	public void dfs(boolean[] isNut, int i) {
		// 首先我们访问该结点,输出
		System.out.print(getValueByIndex(i) + "->");
		// 将结点设置为已经访问
		isNut[i] = true;
		// 查找结点i的第一个邻接结点w
		int w = getFirstNeigh(i);
		while (w != -1) {// 说明找到了
			if (!isNut[w]) {
				dfs(isNut, w);
			}
			// 如果w结点已经被访问过
			w = getNextNeigh(i, w);
		}
	}

	//对DFS进行重载,遍历所有的结点,并进行DFS
	public void dfs() {
		isNut = new boolean[vertexList.size()];
		//遍历所有的结点,进行dfs[回溯]
		for(int i = 0; i < getNumOfVertex(); i++) {
			if(!isNut[i]) {
				dfs(isNut, i);
			}
		}
	}
	
}
