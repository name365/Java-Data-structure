package github;

import java.util.Scanner;

public class CircleArrayQueueDemo {

	public static void main(String[] args) {
		
		// 测试代码
		System.out.println("测试数组模拟环形队列-------");
		// 创建一个环形队列
		CircleArray queue = new CircleArray(6);	//这里设置的6，是其队列的有效数据最大是5
		char key = ' '; // 接收用户输入
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		// 输出一个菜单
		while (loop) {
			System.out.println("s(show):显示队列");
			System.out.println("e(exit):退出程序");
			System.out.println("a(add):添加数据到队列");
			System.out.println("g(get):从队列中取出数据");
			System.out.println("h(head):查看队列头的数据");
			key = scanner.next().charAt(0); // 接收一个字符
			switch (key) {
			case 's':
				queue.showQueue();
				break;
			case 'a':
				System.out.println("输出一个数");
				int value = scanner.nextInt();
				queue.addQueue(value);
				break;
			case 'h': // 查看队列头的数据
				try {
					int res = queue.headQueue();
					System.out.printf("队列头的数据是%d\n", res);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'g': // 取出数据
				try {
					int res = queue.getQueue();
					System.out.printf("取出的数据是%d\n", res);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'e': // 退出
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
class CircleArray {
	private int maxSize; // 表示数组的最大容量

	// front 队列头,front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
	// front 的初始值为0
	private int front;

	// rear 队列尾,rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
	// rear 的初始值 = 0
	private int rear;
	private int[] arr; // 该数组用于存放数据，模拟队列

	// 创建队列的构造器
	public CircleArray(int arrMaxSize) {
		maxSize = arrMaxSize;
		arr = new int[maxSize];
	}

	// 判断队列是否满
	public boolean isFull() {
		return (rear + 1) % maxSize == front; // 满为true,不满为false
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
		arr[rear] = n; // 直接添加数据
		rear = (rear + 1) % maxSize; // 将 rear 后移, 这里必须考虑取模
	}

	// 获取队列的数据,出队列
	public int getQueue() {
		// 判断队列是否空
		if (isEmpty()) { // 队列为空
			// 通过抛出异常来处理
			throw new RuntimeException("队列空了，不能取数据。");
		}
		// 队列不空，返回数据
		// 1. 先把 front 对应的值保留到一个临时变量
		// 2. 将 front 后移, 考虑取模
		// 3. 将临时保存的变量返回
		int value = arr[front];
		front = (front + 1) % maxSize;
		return value;
	}

	// 显示队列的所有数据
	public void showQueue() {
		// 遍历
		if (isEmpty()) { // 队列为空
			System.out.println("队列空的，没有数据。");
			return;
		}
		// 队列不空
		// 思路：从front开始遍历，遍历多少个元素
		for (int i = front; i < front + size(); i++) {
			System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
		}
	}

	// 求出当前队列有效数据的个数
	public int size() {
		// rear = 2
		// front = 1
		// maxSize = 3
		return (rear + maxSize - front) % maxSize;
	}

	// 显示队列的头数据。注意:不是取出数据
	public int headQueue() {
		// 判断
		if (isEmpty()) {
			throw new RuntimeException("队列空了，不能取数据。");
		}
		return arr[front];
	}
}