package github2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

	public static void main(String[] args) {

		// 完成将一个中缀表达式转成后缀表达式的功能
		// 说明
		// 1.1+((2+3)×4)-5 => 转成 1 2 3 + 4 × + 5 –
		// 2.因为直接对str 进行操作，不方便，因此 先将 "1+((2+3)×4)-5" =》 中缀的表达式对应的List
		// 即 "1+((2+3)×4)-5" => ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]

		String exception = "1+((2+3)*4)-5";
		List<String> inList = toInList(exception);
		System.out.println("中缀表达式对应的List = " + inList);
		System.out.println("+++++++++++++++++++++++++++");

		// 3.将得到的中缀表达式对应的List => 后缀表达式对应的List
		// 即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5] =》 ArrayList
		// [1,2,3,+,4,*,+,5,–]
		List<String> parList = parList(inList);
		System.out.println("后缀表达式对应的List = " + parList);
		
		System.out.printf("expression=%d", calculate(parList));

		// //先定义一个逆波兰表达式
		// //测试1:(3+4)x5-6 =》 3 4 + 5 x 6 - =》 29
		// //测试2:(30+4)x5-6 =》 30 4 + 5 x 6 - =》 164
		// //中缀表达式:4 * 5 - 8 + 60 + 8 / 2 =》后缀表达式:4 5 * 8 - 60 + 8 2 / +
		// //说明:为了方便,逆波兰表达式的数字和符号使用空格隔开
		// String suffiException = "3 4 + 5 * 6 -";
		// //具体思路:
		// //1.先将“ 3 4 + 5 x 6 - ” =》 放到ArrayList中
		// //2.将ArrayList传递给一个方法,遍历ArrayList配合栈完成计算
		//
		// List<String> rpnList = getListString(suffiException);
		// System.out.println("rpnList = " + rpnList);
		//
		// int res = calculate(rpnList);
		// System.out.println("计算的结果是:" + res);
	}

	// 即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5] =》 ArrayList [1,2,3,+,4,*,+,5,–]
	// 方法：将得到的中缀表达式对应的List => 后缀表达式对应的List
	public static List<String> parList(List<String> ls) {
		// 定义两个栈
		Stack<String> s1 = new Stack<String>(); // 符号栈
		// 说明：因为s2 这个栈,在整个转换过程中,没有pop操作,而且后面还需要逆序输出
		// 因此比较麻烦,这里就不用 Stack<String> 直接使用 List<String> s2
		// Stack<String> s2 = new Stack<String>(); // 储存中间结果的栈s2
		List<String> s2 = new ArrayList<String>(); // 储存中间结果的Lists2

		// 遍历ls
		for (String item : ls) {
			// 如果是一个数,加入s2
			if (item.matches("\\d+")) {
				s2.add(item);
			} else if (item.equals("(")) {
				s1.push(item);
			} else if (item.equals(")")) {
				// 如果是右括号“)”,则依次弹出s1栈顶的运算符，并压入s2,直到遇到左括号为止,此时将这一对括号丢弃
				while (!s1.peek().equals("(")) {
					s2.add(s1.pop());
				}
				s1.pop();// 将 ( 弹出 s1栈，,消除小括号
			} else {
				// 当item的优先级小于等于s1栈顶运算符,
				// 将s1栈顶的运算符弹出并加入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较
				// 问题：我们缺少一个比较优先级高低的方法
				while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
					s2.add(s1.pop());
				}
				// 还需要将item压入栈
				s1.push(item);
			}
		}
		// 将s1中剩余的运算符依次弹出并加入s2
		while (s1.size() != 0) {
			s2.add(s1.pop());
		}
		return s2; // 注意因为是存放到List, 因此按顺序输出就是对应的后缀表达式对应的List
	}

	// 自定义方法:将中缀表达式转成对应的List
	public static List<String> toInList(String s) {
		// 定义一个List,存放中缀表达式对应的内容
		List<String> ls = new ArrayList<String>();
		int i = 0; // 这是一个指针,用于遍历中缀表达式字符串
		String str; // 多位数拼接
		char c; // 每遍历一个字符，就存入c
		do {
			// 如果是非数字,需要加入ls
			if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
				ls.add("" + c);
				i++; // i后移
			} else { // 如果是一个数,需要考虑多位数
				str = ""; // 将str 制成 ""
				while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
					str += c; // 拼接
					i++; // 后移
				}
				ls.add(str);
			}
		} while (i < s.length());
		return ls;
	}

	// 将一个逆波兰表达式,一次将数据和运算符放入ArrayList中
	public static List<String> getListString(String suffiException) {
		// 将suffiException分割
		String[] split = suffiException.split(" ");
		List<String> list = new ArrayList<String>();
		for (String ele : split) {
			list.add(ele);
		}
		return list;
	}

	// 完成对逆波兰表达式的计算
	/*
	 * 1.从左至右扫描，将3和4压入堆栈； 2.遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
	 * 3.将5入栈； 4.接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈； 5.将6入栈；
	 * 6.最后是-运算符，计算出35-6的值，即29，由此得出最终结果
	 */
	public static int calculate(List<String> ls) {
		// 创建一个栈
		Stack<String> stack = new Stack<String>();
		// 遍历
		for (String item : ls) {
			// 使用正则表达式取值
			if (item.matches("\\d+")) { // 匹配多位数的正则表达式
				// 入栈
				stack.push(item);
			} else {
				// pop出两个数,并运算,再入栈
				int num2 = Integer.parseInt(stack.pop()); // 将字符串转成整数
				int num = Integer.parseInt(stack.pop());
				int res = 0; // 存放判断结果
				if (item.equals("+")) {
					res = num + num2;
				} else if (item.equals("-")) {
					res = num - num2;
				} else if (item.equals("*")) {
					res = num * num2;
				} else if (item.equals("/")) {
					res = num / num2;
				} else {
					throw new RuntimeException("运算符有问题！！！");
				}
				// 将res入栈
				stack.push(res + ""); // 整数转字符串
			}
		}
		// 最后留在stack中的数据即为结果
		return Integer.parseInt(stack.pop()); // 字符串转整数
	}
}

// 编写一个类 Operation 可以返回一个运算符 对应的优先级
class Operation {
	private static int ADD = 1;
	private static int SUB = 1;
	private static int MUL = 2;
	private static int DIV = 2;

	// 写一个方法,返回对应的优先级数字
	public static int getValue(String operation) {
		int result = 0;
		switch (operation) {
		case "+":
			result = ADD;
			break;
		case "-":
			result = SUB;
			break;
		case "*":
			result = MUL;
			break;
		case "/":
			result = DIV;
			break;
		default:
			System.out.println("不存在该运算符:" + operation);
			break;
		}
		return result;
	}
}