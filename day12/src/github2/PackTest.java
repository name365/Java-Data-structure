package github2;

public class PackTest {
	public static void main(String[] args) {
		int[] arr = {1,4,3};	//物品重量
		int[] var = {1500,3000,2000};	//物品的价值
		int m = 4;	//包的容量
		int n = var.length;	//物品的个数
		
		//创建二维数组,表
		//v[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大价值
		int[][] v = new int[n+1][m+1]; 
		int[][] path = new int[n+1][m+1];	//记录放入商品的情况
		
		//初始化第一行和第一列,本程序可不做此步
		for(int i = 0;i < v.length;i++){
			v[i][0]=0;	//将第一列设为0
		}
		for(int i = 0;i < v[0].length;i++){
			v[0][i]=0;	//将第一列设为0
		}
		
		//根据相关公式进行动态处理
		for(int i = 1;i < v.length;i++){	//不处理第一行
			for(int j = 1;j < v[0].length;j++){	//不处理第一列
				if(arr[i-1]> j) { // 因为程序i是从1开始的,因此原来公式中的 arr[i],修改成 arr[i-1]
					v[i][j]=v[i-1][j];
				} else {
					//说明:
					//因为i是从1开始的,因此公式需要调整成
					//v[i][j]=Math.max(v[i-1][j], var[i-1]+v[i-1][j-w[i-1]]);
					//v[i][j] = Math.max(v[i - 1][j], var[i - 1] + v[i - 1][j - w[i - 1]]);
					if(v[i - 1][j] < var[i - 1] + v[i - 1][j - arr[i - 1]]) {
						v[i][j] = var[i - 1] + v[i - 1][j - arr[i - 1]];
						//把当前的情况记录到path
						path[i][j] = 1;
					} else {
						v[i][j] = v[i - 1][j];
					}
				}
			}
		}
		
		//输出
		for(int i = 0;i < v.length;i++){
			for(int j = 0;j < v[i].length;j++){
				System.out.print(v[i][j] + " ");
			}
			System.out.println();
		}
		
		//输出最后放入的哪些商品
//		for(int i=0;i< path.length;i++){
//			for(int j=0;j< path[i].length;j++){
//				if(path[i][j] == 1){
//					System.out.printf("第%d个商品放入到背包\n", i);
//				}
//			}
//		}
		
		int i = path.length - 1; //行的最大下标
		int j = path[0].length - 1;  //列的最大下标
		while(i > 0 && j > 0 ) { //从path的最后开始找
			if(path[i][j] == 1) {
				System.out.printf("第%d个商品放入到背包\n", i); 
				j -= arr[i-1]; //arr[i-1]
			}
			i--;
		}
	}
}
