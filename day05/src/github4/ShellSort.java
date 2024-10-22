package github4;

import java.util.Arrays;

public class ShellSort {

	public static void main(String[] args) {
		int[] arr = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };
		
		shellSort(arr);	//调用排序
	}

	// 逐步推导的方式——希尔排序
	public static void shellSort(int[] arr) {
		
		//使用循环处理
		int temp = 0;
		int count = 0;	//统计排序次数
		for(int num = arr.length / 2;num > 0;num /= 2){
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
			System.out.println("第"+ (++count) +"轮排序:" + Arrays.toString(arr));
		}
		
		/*
		int temp = 0;
		// 第1轮排序: 将10个数据分为5组
		for (int i = 5; i < arr.length; i++) {
			// 遍历各组中所有的元素(共5组,每组两个元素),步长是5
			for (int j = i - 5; j >= 0; j -= 5) {
				// 如果当前的元素大于加上步长后的元素,即交换
				if (arr[j] > arr[j + 5]) {
					temp = arr[j];
					arr[j] = arr[j + 5];
					arr[j + 5] = temp;
				}
			}
		}
		System.out.println("第1轮排序:" + Arrays.toString(arr));
		
		//第2轮排序,将10个数据分成了 5/2 = 2组
		for (int i = 2; i < arr.length; i++) {
			// 遍历各组中所有的元素(共2组,每组5个元素),步长是2
			for (int j = i - 2; j >= 0; j -= 2) {
				// 如果当前的元素大于加上步长后的元素,即交换
				if (arr[j] > arr[j + 2]) {
					temp = arr[j];
					arr[j] = arr[j + 2];
					arr[j + 2] = temp;
				}
			}
		}
		System.out.println("第2轮排序:" + Arrays.toString(arr));
		
		//第3轮排序,将10个数据分成了 2/2 = 1组
		for (int i = 1; i < arr.length; i++) {
			// 遍历各组中所有的元素(共1组,每组10个元素),步长是1
			for (int j = i - 1; j >= 0; j -= 1) {
				// 如果当前的元素大于加上步长后的元素,即交换
				if (arr[j] > arr[j + 1]) {
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		System.out.println("第3轮排序:" + Arrays.toString(arr));
		*/
	}
}
