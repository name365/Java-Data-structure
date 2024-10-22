package github;

import java.util.Scanner;

public class ArrayQueueDemo {

	public static void main(String[] args) {
		
		//测试代码
		//创建一个队列
		ArrayQueue queue = new ArrayQueue(3);
		char key = ' ';	//接收用户输入
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		//输出一个菜单
		while(loop){
			System.out.println("s(show):显示队列");
			System.out.println("e(exit):退出程序");
			System.out.println("a(add):添加数据到队列");
			System.out.println("g(get):从队列中取出数据");
			System.out.println("h(head):查看队列头的数据");
			key = scanner.next().charAt(0);	//接收一个字符
			switch(key){
			case 's':
				queue.showQueue();
				break;
			case 'a':
				System.out.println("输出一个数");
				int value = scanner.nextInt();
				queue.addQueue(value);
				break;
			case 'h':	//查看队列头的数据
				try {
					int res = queue.headQueue();
					System.out.printf("队列头的数据是%d\n",res);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}				
				break;
			case 'g':	//取出数据
				try {
					int res = queue.getQueue();
					System.out.printf("取出的数据是%d\n",res);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'e':	//退出
				scanner.close();
				loop = false;
				break;
			default:
				break;
			}
		}
		System.out.println("程序退出-------");
	}

}

// 使用数组模拟队列---》编写一个ArrayQueue类
class ArrayQueue {
	private int maxSize; // 表示数组的最大容量
	private int front; // 队列头
	private int rear; // 队列尾
	private int[] arr; // 该数组用于存放数据，模拟队列

	// 创建队列的构造器
	public ArrayQueue(int arrMaxSize) {
		maxSize = arrMaxSize;
		arr = new int[maxSize];
		front = -1; // 指向队列头部,分析出front是指向队列头的前一个位置
		rear = -1; // 指向队列尾部,指向队列尾的数据(即包含队列尾部的最后一个数据)
	}

	// 判断队列是否满
	public boolean isFull() {
		return rear == maxSize - 1; // 满为true,不满为false
	}

	// 判断队列是否为空
	public boolean isEmpty() {
		return rear == front; // 为空即true,不空为false
	}

	// 添加数据到队列
	public void addQueue(int n) {
		// 判断队列是否满,满了不加入，未满则加入数据
		if (isFull()) { // 队列满了
			System.out.println("队列满了，不能再加了！！！");
			return;
		}
		// 队列未满
		rear++; // 让rear后移
		arr[rear] = n; // 添加数据
	}

	// 获取队列的数据,出队列
	public int getQueue() {
		// 判断队列是否空
		if (isEmpty()) { // 队列为空
			// 错误的写法:
			// return -1; //当要出的数据为-1时，此处不正确，应当通过如下方法
			// 正确的写法:通过抛出异常来处理
			throw new RuntimeException("队列空了，不能取数据。");
		}
		// 队列不空，返回数据
		front++; // 让front后移
		return arr[front]; // 出队列
	}

	// 显示队列的所有数据
	public void showQueue() {
		// 遍历
		if (isEmpty()) { // 队列为空
			System.out.println("队列空的，没有数据。");
			return;
		}
		// 队列不空
		for (int i = 0; i < arr.length; i++) {
			System.out.printf("arr[%d]=%d\n", i, arr[i]);
		}
	}

	// 显示队列的头数据。注意:不是取出数据
	public int headQueue() {
		// 判断
		if (isEmpty()) {
			throw new RuntimeException("队列空了，不能取数据。");
		}
		return arr[front + 1];
	}
}