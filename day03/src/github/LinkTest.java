package github;

import java.util.Scanner;

public class LinkTest {
	public static void main(String[] args) {
		// 测试代码如下:
		// 先创建一个对象---》表示栈
		LinkedListStack stack = new LinkedListStack();
		String key = ""; // 空串
		boolean loop = true; // 控制是否退出菜单
		Scanner scanner = new Scanner(System.in);

		while (loop) {
			System.out.println("show:表示显示栈");
			System.out.println("exit:退出程序");
			System.out.println("push:表示添加数据到栈(入栈)");
			System.out.println("pop:表示从栈取出数据(出栈)");
			System.out.println("请输入你的选择:");
			key = scanner.next();
			switch (key) {
			case "show":
				stack.list();
				break;
			case "push":
				System.out.println("请输入一个数:");
				int value = scanner.nextInt();
				LinkedList list = new LinkedList(value);
				stack.push(list);
				break;
			case "pop":
				try {
					LinkedList res = stack.pop();
					System.out.printf("取出的数据是%d\n", res);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case "exit":
				scanner.close();
				loop = false;
				break;
			default:
				break;
			}
		}
		System.out.println("程序退出！！！！");
	}
}

// 链表操作
class LinkedListStack {
	private LinkedList head = new LinkedList(-1); // 先初始化一个头结点，头结点不动
	private LinkedList top = null; // 定义一个节点，代表栈顶所指节点

	public LinkedList getHead() { // 创建一个获取头结点的方法
		return head;
	}

	// 判断栈空
	public boolean isEmpty() {
		return top == null;
	}

	public void push(LinkedList num) {
		// 因为头结点不能动，所以需要一个辅助节点来完成
		LinkedList temp = head;
		// 对链表进行遍历，遍历到最后一个节点，然后进行添加节点
		while (true) {
			// 判断是否为节点的最后
			if (temp.next == null) {
				// 当为空时，说明已经是最后一个节点
				break;
			}
			// 如果不为空，则指针向后移动
			temp = temp.next;
		}
		// 当退出while循环时，说明到了最后一个节点
		// 将节点添加到链表的最后
		temp.next = num;
		// 将top指向这个节点
		top = num;
	}

	// 删除节点从单向链表实现的栈中，即出栈
	public LinkedList pop() {
		// 将top指针指向的节点出栈
		// 因为出栈后，top指针需要向前移动，所以需要一个辅助指针完成出栈
		LinkedList tmp = top;
		// 将top指针向前移动一个
		// 需要重新遍历链表找到top节点的前一个节点，再由top指针指向这个节点
		LinkedList h = head;
		while (true) {
			// 找到top节点的前一个节点
			if (h.next == top) {
				// 说明找到了这个节点
				break;
			}
			// 如果不是，则指针向后移动
			h = h.next;
		}
		// 退出循环后，找到top节点的前一个节点，对top节点进行删除
		h.next = top.next;
		// 将top指针指向这个节点,完成top指针的前移动作
		top = h;
		return tmp;
	}

	// 显示栈的情况[遍历栈]，遍历时，需要从栈顶开始显示数据，即从链表尾遍历链表
	// 将链表进行反转，然后再打印链表
	public void list() {
		// 判断链表是否为空
		if (head.next == null) {
			System.out.println("链表为空！！！");
			return;
		}
		// 因为头结点不能动，需要辅助变量来遍历
		reverseList(head);
		LinkedList temp = head.next;
		while (true) {
			// 判断是否到链表最后
			if (temp == null) {
				break;
			}
			// 输出节点信息
			System.out.println(temp);
			// 将temp后移
			temp = temp.next;
		}
	}

	// 栈的遍历需要先将链表进行反转
	// 链表反转
	public void reverseList(LinkedList head) {
		// 如果当前链表为空，或者只有一个节点，无需反转，直接返回
		if (head.next == null || head.next.next == null) {
			return;
		}
		// 定义一个辅助的指针(变量),帮助遍历原来的链表
		LinkedList cur = head.next;
		// 定义一个变量存储当前节点的下个节点，即指向当前节点[cur]的下个节点
		LinkedList next = null;
		// 定义一个新链表的头结点
		LinkedList reverseHead = new LinkedList(-1);
		// 遍历原来的链表，每遍历一个节点就将其取出，并放在新的链表reverseHead的最前端
		while (cur != null) {
			// 暂时保存当前节点的下一个节点(后续需要使用)
			next = cur.next;
			// 将cur的下个节点指向新的链表的最前端
			cur.next = reverseHead.next;
			// 将cur连接到新的链表上
			reverseHead.next = cur;
			// 让cur后移
			cur = next;
		}
		// 将head.next指向reverseHead.next，实现单链表的反转
		head.next = reverseHead.next;
	}
}

// 节点
class LinkedList {
	public int val; // 存储的数据
	public LinkedList next; // 下一个节点

	public LinkedList(int val) {
		this.val = val;
	}

	@Override
	public String toString() {
		return "LinkedList [val=" + val + ", next=" + next + "]";
	}
}