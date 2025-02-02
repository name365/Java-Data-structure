package github2;

public class Calculator2 {

	public static void main(String[] args) {
		// 测试一下
		String exception = "7*2*2-5+1-5+3-4";
		// 创建两个栈,一个数栈,一个符号栈
		ArrayStack2 numS = new ArrayStack2(10);
		ArrayStack2 operS = new ArrayStack2(10);
		// 定义两个相关变量
		int index = 0; // 用于扫描
		int num = 0;
		int num2 = 0;
		int oper = 0;
		int res = 0;
		char ch = ' '; // 每次的扫描结果保存到ch
		String keepNum = ""; // 用于拼接 多位数
		// k=开始用while语句循环扫描exception
		while (true) {
			// 依次得到exception 的每一个字符
			ch = exception.substring(index, index + 1).charAt(0);
			// 判断ch,做出相应的处理
			if (operS.isOper(ch)) {
				// 判断当前的符号栈是否为空,为空则入栈
				if (!operS.isEmpty()) {
					// 如果符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符,就需要从数栈中pop出两个数,
					// 在从符号栈中pop出一个符号,进行运算,将得到结果,入数栈,然后将当前的操作符入符号栈
					if (operS.priority(ch) <= operS.priority(operS.peek())) {
						num = numS.pop();
						num2 = numS.pop();
						oper = operS.pop();
						res = numS.cal(num, num2, oper);
						// 将运算结果入数栈
						numS.push(res);
						// 将当前的操作符入符号栈
						operS.push(ch);
					} else {
						// 如果当前的优先级大于栈中的操作符,就直接入符号栈
						operS.push(ch);
					}
				} else {
					// 如果为空,则直接入符号栈
					operS.push(ch); // 1 + 3
				}
			} else {// 如果是数,则直接入数栈
				// numS.push(ch); //错误的写法:读取的是字符不是数字
				// numS.push(ch - 48);
				// 分析思路如下:
				// 1.当处理多位数时，不能发现是一个数就立即入栈,因为他可能是多位数
				// 2.在处理数，需要向exception的表达式的index 后再看一位,如果是数就进行扫描,如果是符号才入栈
				// 3.因此我们需要定义一个变量 字符串，用于拼接

				// 处理多位数
				keepNum += ch;

				// 如果ch已经是exception的最后一位,就直接入栈
				if (index == exception.length() - 1) {
					numS.push(Integer.parseInt(keepNum));
				} else {
					// 判断下一个字符是不是数字,如果是数字,就继续扫描,如果是运算符,则入栈
					// 注意是看后一位,不是index++
					if (operS.isOper(exception.substring(index + 1, index + 2).charAt(0))) {
						// 如果后一位是运算符,则入栈 keepNum = "1" 或者 "123"
						numS.push(Integer.parseInt(keepNum));
						// 注意!!!!!!,keepNum清空
						keepNum = "";

					}
				}
			}
			// 让index+1,并判断是否扫描到exception的最后
			index++;
			if (index >= exception.length()) {
				break;
			}
		}
		// 当表达式扫描完毕,就顺序的从 数栈和符号栈中pop出相应的数和符号,并运行.
		while (true) {
			// 如果符号栈为空，则计算到最后的结果,数栈中只有一个数字[结果]
			if (operS.isEmpty()) {
				break;
			}
			num = numS.pop();
			num2 = numS.pop();
			oper = operS.pop();
			res = numS.cal(num, num2, oper);
			numS.push(res); // 入栈
		}
		// 将数栈的最后数，pop出，就是结果
		int res2 = numS.pop();
		System.out.printf("表达式 %s = %d", exception, res2);
	}

}

// 先创建一个栈,直接使用前面的
// 定义一个类,表示栈
class ArrayStack2 {
	private int maxSize; // 栈的大小
	private int[] stack; // 数组,模拟栈,数据就放在该数组
	private int top = -1; // top表示栈,初始化为-1

	// 构造器
	public ArrayStack2(int maxSize) {
		this.maxSize = maxSize;
		stack = new int[this.maxSize];
	}

	// 自定义一个方法,可以返回当前栈顶的值 ,但不是真的出栈
	public int peek() {
		return stack[top];
	}

	// 栈满
	public boolean isFull() {
		return top == maxSize - 1;
	}

	// 栈空
	public boolean isEmpty() {
		return top == -1;
	}

	// 入栈
	public void push(int value) {
		// 先判断栈是否满
		if (isFull()) {
			System.out.println("栈满了！！！");
			return;
		}
		top++;
		stack[top] = value;
	}

	// 出栈-pop,将栈顶的数据返回
	public int pop() {
		// 先判断栈是否空
		if (isEmpty()) {
			// 抛出异常
			throw new RuntimeException("栈空,没有数据！！！");
		}
		int value = stack[top];
		top--;
		return value;
	}

	// 显示栈的情况[便利栈],遍历时,需要从栈顶开始显示数据
	public void list() {
		if (isEmpty()) {
			System.out.println("栈空,没有数据！！！");
			return;
		}
		for (int i = top; i >= 0; i--) {
			System.out.printf("stack[%d]=%d\n", i, stack[i]);
		}
	}

	// 返回运算符的优先级,优先级是程序员来确定,优先级使用数字表示
	// 数字越大,则优先级就越高
	public int priority(int oper) {
		if (oper == '*' || oper == '/') {
			return 1;
		} else if (oper == '+' || oper == '-') {
			return 0;
		} else {
			return -1; // 假设目前表达式只有+ - * /
		}
	}

	// 判断是不是一个运算符
	public boolean isOper(char val) {
		return val == '+' || val == '-' || val == '*' || val == '/';
	}

	// 计算方法
	public int cal(int num, int num2, int oper) {
		int res = 0; // 用于存放反计算的结果
		switch (oper) {
		case '+':
			res = num + num2;
			break;
		case '-':
			res = num2 - num; // 注意顺序
			break;
		case '*':
			res = num * num2;
			break;
		case '/':
			res = num2 / num;
			break;
		default:
			break;
		}
		return res;
	}
}