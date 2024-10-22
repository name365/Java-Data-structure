package github3;

public class Queue8 {

	// 定义一个max表示共有多少个皇后
	int max = 8;
	// 定义一个数组array,保存皇后的地址,如 arr = {0 , 4, 7, 5, 2, 6, 1, 3}
	int[] array = new int[max];
	static int count = 0; // 统计解法数目
	static int judgeCount = 0; // 统计冲突次数

	public static void main(String[] args) {
		// 具体测试:
		Queue8 Q8 = new Queue8();
		Q8.Qnum(0);
		System.out.printf("一共有%d解法", count);
		System.out.printf("一共判断冲突的次数%d次", judgeCount); // 15720次
	}

	// 自定义一个方法,放置n个皇后
	// 注意: Qnum 是每一次递归时,进入到Qnum中都有 for(int i = 0; i < max; i++),因此会有回溯.
	public void Qnum(int n) {
		if (n == max) { // n = 8;等同于在放置第九个皇后,等同于结束
			print(); // 输出结果
			return;
		}
		// 依次放入皇后,并进行相关判断
		for (int i = 0; i < max; i++) {
			// 先把当前的皇后,放到该行的第1列
			array[n] = i;
			// 判断当放置的第n个皇后到i列时,是否冲突
			if (judge(n)) { // 如果不冲突
				// 继续放n+1个皇后,即开始递归
				Qnum(n + 1);
			}
			// 如果冲突,就继续执行 array[n] = i; 即将第n个皇后,放置在本行的皇后,后移的一个位置
		}
	}

	// 定义一个方法,判断放置的皇后之间是否产生冲突
	// n 表示第n个皇后
	private boolean judge(int n) {
		// 详细说明
		// 1.array[i] == array[n] 表示判断:第n个皇后是否和前面的n-1个皇后在同一列
		// 2.Math.abs(n-i) == Math.abs(array[n] - array[i])
		// 表示判断:第n个皇后是否和第i皇后是否在同一斜线
		// n = 1 放置第 2列 1 n = 1 array[1] = 1
		// Math.abs(1-0) == 1 Math.abs(array[n] - array[i]) = Math.abs(1-0) = 1
		// 3.表示判断:是否在同一行, 没有必要,n 每次都在递增
		judgeCount++;
		for (int i = 0; i < n; i++) {
			if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
				return false;
			}
		}
		return true;
	}

	// 定义一个方法,将皇后摆放的位置输出
	public void print() {
		count++;
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}
