package github;

import java.util.Arrays;

public class BubbleSort {

	public static void main(String[] args) {
		int arr[] = { 3, 9, -1, 10, -2 };

		// 第一趟排序,将最大的数排在最后
		// 冒泡排序 的时间复杂度 O(n^2)
		int temp = 0; // 创建临时变量,用于数据交换
		for (int j = 0; j < arr.length - 1; j++) {
			for (int i = 0; i < arr.length - 1 - j; i++) {
				// 如果, 前一个数 > 后一个数
				if (arr[i] > arr[i + 1]) {
					temp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = temp;
				}
			}
			System.out.print("第" + (j + 1) + "趟排序后:");
			System.out.println(Arrays.toString(arr));
		}

		/*
		 * //第二趟排序,将第二大的数排在倒数第二位 for(int i = 0;i < arr.length - 1 - 1;i++){
		 * //如果, 前一个数 > 后一个数 if(arr[i] > arr[i+1]){ temp = arr[i]; arr[i] =
		 * arr[i + 1]; arr[i + 1] = temp; } } System.out.print("第二趟排序后:");
		 * System.out.println(Arrays.toString(arr));
		 * 
		 * //第三趟排序,将第三大的数排在倒数第三位 for(int i = 0;i < arr.length - 1 - 2;i++){
		 * //如果, 前一个数 > 后一个数 if(arr[i] > arr[i+1]){ temp = arr[i]; arr[i] =
		 * arr[i + 1]; arr[i + 1] = temp; } } System.out.print("第三趟排序后:");
		 * System.out.println(Arrays.toString(arr));
		 * 
		 * //第四趟排序,将第四大的数排在倒数第四位 for(int i = 0;i < arr.length - 1 - 3;i++){
		 * //如果, 前一个数 > 后一个数 if(arr[i] > arr[i+1]){ temp = arr[i]; arr[i] =
		 * arr[i + 1]; arr[i + 1] = temp; } } System.out.print("第四趟排序后:");
		 * System.out.println(Arrays.toString(arr));
		 */
	}
}
