package github;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BubbleSort2 {

	public static void main(String[] args) {
		int arr[] = { 3, 9, -1, 10, 20 };
		
		System.out.print("排序前的数组:");
		System.out.println(Arrays.toString(arr));
		
		//测试一下冒泡排序的速度:O(n^2),给80000个的数据,测试
//		int[] arr = new int[80000];
//		for(int i = 0;i < 80000;i++){
//			arr[i] = (int)(Math.random() * 80000);	//自动生成[0,80000)之间的随机数
//		}
//		System.out.print("排序前的数组:");
//		System.out.println(Arrays.toString(arr));
		
		//排序前的时间:
		Date data = new Date();
		SimpleDateFormat simt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateS = simt.format(data);
		System.out.println("排序前的时间是:" + dateS);

		//测试冒泡排序:
		bubbleSort(arr);
		
		//输出排序后的数组
		System.out.print("排序后的数组:");
		System.out.println(Arrays.toString(arr));
		
		//排序后的时间:
		Date data2 = new Date();
		SimpleDateFormat simt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateS2 = simt2.format(data2);
		System.out.println("排序后的时间是:" + dateS2);

	}

	// 将前面的冒泡排序封装成一个方法
	public static void bubbleSort(int[] arr) {
		// 冒泡排序 的时间复杂度 O(n^2)
		int temp = 0; // 创建临时变量,用于数据交换
		boolean falg = false; // 标识变量,表示是否进行过交换
		for (int j = 0; j < arr.length - 1; j++) {
			for (int i = 0; i < arr.length - 1 - j; i++) {
				// 如果, 前一个数 > 后一个数
				if (arr[i] > arr[i + 1]) {
					falg = true;
					temp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = temp;
				}
			}

			if (falg == false) { // 在一趟排序中,一次交换都没有,直接退出
				break;
			} else {
				falg = false; // 重置falg,方便进行二次判断
			}
		}
	}
}
