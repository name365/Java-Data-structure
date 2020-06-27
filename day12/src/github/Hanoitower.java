package github;

public class Hanoitower {
	public static void main(String[] args) {
		Htower(10,'A','P','Q');
	}
	//汉诺塔算法
	//用分治算法求解
	public static void Htower(int num,char a,char b,char c){
		//如果只有一个盘
		if(num==1){
			System.out.println("第一个盘从" + a + "->" + c);
		}else{
			//如果我们有 n >= 2 情况,可以假设是两个盘 1.最下边的一个盘 2.上面的所有盘
			//1.先把最上面的所有盘 A->B,移动过程会使用到 c
			Htower(num-1,a,c,b);
			//2.把最下边的盘 A->C
			System.out.println("第" + num + "个盘从 " + a + "->" + c);
			//3.把B塔的所有盘 从 B->C,移动过程使用到 a塔  
			Htower(num-1,b,a,c);
		}
	}
}
