package git;

public class Sort {

	//比如计算1-100所有数字之和, 设计如下两种算法：
	public static void main(String[] args) {
		//方法一:
		int total = 0;
		int end = 100;
		for(int i = 1;i <= end;i++){
			total += i;
		}
		System.out.println("total = " + total);	//T(n)=n+1;
		
		//方法二:
		int total2 = 0;
		int end2 = 100;
		total2 = (1 + end2)*end2/2;
		System.out.println("total2 = " + total2); //T(n)=1
	}
}
