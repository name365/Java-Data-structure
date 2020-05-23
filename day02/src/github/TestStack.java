package github;

import java.util.Stack;

//演示栈Stack的基本使用
public class TestStack {
	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		//入栈
		stack.add("jack");
		stack.add("Tom");
		stack.add("smith");
		
		//出栈
		//出栈顺序:smith-->Tom-->jack
		while(stack.size() > 0){
			System.out.println(stack.pop());	//pop()就是将栈顶的数据取出
		}
	}
}
