package github2;

import java.util.Arrays;

public class SelectSort {

	public static void main(String[] args) {
		int[] arr = { 101, 34, 119, 1 };
		System.out.print("排序前:");
		System.out.println(Arrays.toString(arr));

		selectSort(arr); // 调用排序
		
		System.out.print("排序后:");
		System.out.println(Arrays.toString(arr));
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

			System.out.print("第" + (i+1) + "轮后:");
			System.out.println(Arrays.toString(arr));
		}

		// 逐步推导过程
		// 第1轮
		// 原始数组: 101, 34, 119, 1
		// 第一轮排序: 1, 34, 119, 101
		// 算法思维:先简单--》再复杂,即复杂的算法拆分为简单的问题,再逐步解决
/*
		// 第一轮
		int minIndex = 0;
		int min = arr[0]; // 假设某个为最小值
		for (int j = 0 + 1; j < arr.length; j++) {
			if (min > arr[j]) { // 说明假定的最小值,并不是最小
				min = arr[j]; // 重置最小值
				minIndex = j; // 重置minIndex
			}
		}

		// 将最小值放在arr[0],即交换
		if (minIndex != 0) {
			arr[minIndex] = arr[0];
			arr[0] = min;
		}

		System.out.print("第一轮后:");
		System.out.println(Arrays.toString(arr)); // 第一轮后:[1, 34, 119, 101]

		// 第二轮
		minIndex = 1;
		min = arr[1]; // 假设某个为最小值
		for (int j = 1 + 1; j < arr.length; j++) {
			if (min > arr[j]) { // 说明假定的最小值,并不是最小
				min = arr[j]; // 重置最小值
				minIndex = j; // 重置minIndex
			}
		}

		// 将最小值放在arr[1],即交换
		if (minIndex != 1) {
			arr[minIndex] = arr[1];
			arr[1] = min;
		}

		System.out.print("第二轮后:");
		System.out.println(Arrays.toString(arr)); // [1, 34, 119, 101]

		// 第三轮
		minIndex = 2;
		min = arr[2]; // 假设某个为最小值
		for (int j = 1 + 2; j < arr.length; j++) {
			if (min > arr[j]) { // 说明假定的最小值,并不是最小
				min = arr[j]; // 重置最小值
				minIndex = j; // 重置minIndex
			}
		}

		// 将最小值放在arr[2],即交换
		if (minIndex != 2) {
			arr[minIndex] = arr[2];
			arr[2] = min;
		}

		System.out.print("第三轮后:");
		System.out.println(Arrays.toString(arr)); // [1, 34, 101, 119]
		*/
	}

}
