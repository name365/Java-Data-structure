package git;

public class RunTest {

	public static void main(String[] args) {
		//打印问题,递归调用
		test(4);
		
		//阶乘问题,递归方法
		int res = factorial(2);
		System.out.println("res = " + res);
	}

	//打印问题
	public static void test(int n) {
		if (n > 2) {
			test(n - 1);
		} //else{			
			System.out.println("n=" + n);
		//}
	}
	
	//阶乘
	public static int factorial(int n) {
		if (n == 1) {
			return 1;
		} else {
			return factorial(n - 1) * n;
		}
	}

}
