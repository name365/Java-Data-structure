package github2;

import java.text.SimpleDateFormat;
import java.util.Date;
//选择排序算法速度测试
public class SelectSort2 {

	public static void main(String[] args) {
		//测试一下冒泡排序的速度:O(n^2),给80000个的数据,测试
		int[] arr = new int[80000];
		for(int i = 0;i < 80000;i++){
			arr[i] = (int)(Math.random() * 80000);	//自动生成[0,80000)之间的随机数
		}
		
		//排序前的时间:
		Date data = new Date();
		SimpleDateFormat simt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateS = simt.format(data);
		System.out.println("排序前的时间是:" + dateS);

		selectSort(arr); // 调用排序
		
		//排序后的时间:
		Date data2 = new Date();
		SimpleDateFormat simt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateS2 = simt2.format(data2);
		System.out.println("排序后的时间是:" + dateS2);
	}

	// 选择排序
	public static void selectSort(int[] arr) {

		// 在推导过程中,通过规律直接循环解决
		// 选择排序的时间复杂度O(n^2)
		for (int i = 0; i < arr.length - 1; i++) {
			int minIndex = i;
			int min = arr[i]; // 假设某个为最小值
			for (int j = i + 1; j < arr.length; j++) {
				if (min > arr[j]) { // 说明假定的最小值,并不是最小
					min = arr[j]; // 重置最小值
					minIndex = j; // 重置minIndex
				}
			}

			// 将最小值放在arr[i],即交换
			if (minIndex != i) {
				arr[minIndex] = arr[i];
				arr[i] = min;
			}

		}
	}

}
