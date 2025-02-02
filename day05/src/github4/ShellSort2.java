package github4;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort2 {

	public static void main(String[] args) {
//		int[] arr = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };

		 int[] arr = new int[80000];
		 for(int i = 0;i < 80000;i++){
		 arr[i] = (int)(Math.random() * 80000); //自动生成[0,80000)之间的随机数
		 }

		// 排序前的时间:
		Date data = new Date();
		SimpleDateFormat simt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateS = simt.format(data);
		System.out.println("排序前的时间是:" + dateS);

//		shellSort(arr); // 调用[交换式]排序
		shellSort2(arr); // 调用[移位式]排序

		// 排序后的时间:
		Date data2 = new Date();
		SimpleDateFormat simt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateS2 = simt2.format(data2);
		System.out.println("排序后的时间是:" + dateS2);
		
	}

    //[交换式]排序
	public static void shellSort(int[] arr) {

		// 使用循环处理
		int temp = 0;
		int count = 0; // 统计排序次数
		for (int num = arr.length / 2; num > 0; num /= 2) {
			for (int i = num; i < arr.length; i++) {
				// 遍历各组中所有的元素(共num组,每组?个元素),步长是num
				for (int j = i - num; j >= 0; j -= num) {
					// 如果当前的元素大于加上步长后的元素,即交换
					if (arr[j] > arr[j + num]) {
						temp = arr[j];
						arr[j] = arr[j + num];
						arr[j + num] = temp;
					}
				}
			}
			System.out.println("第" + (++count) + "轮排序:" + Arrays.toString(arr));
		}
	}

	// 对交换式的希尔排序进行优化->移位法
	public static void shellSort2(int[] arr) {

		for (int num = arr.length / 2; num > 0; num /= 2) {
			// 从num个元素,逐个对其所在的组进行直接插入排序
			for (int i = num; i < arr.length; i++) {
				int j = i;
				int temp = arr[j];
				if (arr[j] < arr[j - num]) {
					while (j - num >= 0 && temp < arr[j - num]) {
						//移动
						arr[j] = arr[j - num];
						j -= num;
					}
					//当退出while循环后,就为temp找到相应的位置
					arr[j] = temp;
				}
			}
		}
	}
}