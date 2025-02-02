package github5;

import java.util.Arrays;

public class QuackSort {

	public static void main(String[] args) {
		int[] arr = { -9, 78, 0, 23, -567, 70 };

		quackSort(arr, 0, arr.length - 1);

		System.out.println("arr排序的结果是:" + Arrays.toString(arr));
	}

	//
	public static void quackSort(int[] arr, int left, int right) {
		int l = left; // 左索引
		int r = right; // 右索引
		int pivot = arr[(left + right) / 2]; // pivot 中轴
		int temp = 0; // 临时变量,作为交换时使用
		// while循环的目的:让,比pivot 值小的放到左边,比pivot 值大的放到右边
		while (l < r) {
			// 在pivot左边一直找,找到一个大于等于pivot的值,才退出
			while (arr[l] < pivot) {
				l += 1;
			}
			// 在pivot右边一直找,找到一个小于等于pivot的值,才退出
			while (arr[r] > pivot) {
				r -= 1;
			}
			// 如果l >= r,则说明pivot 的左右两的值，已经按照左边全部是
			// 小于等于pivot值,右边全部是大于等于pivot值.
			if (l >= r) {
				break;
			}
			// 数据交换
			temp = arr[l];
			arr[l] = arr[r];
			arr[r] = temp;
			
			// 如果交换完后,发现这个arr[l] == pivot值,相等 r--, 前移
			if (arr[l] == pivot) {
				r -= 1;
			}
			// 如果交换完后,发现这个arr[r] == pivot值,相等 l++, 后移
			if (arr[r] == pivot) {
				l += 1;
			}
		}
		//如果 l == r, 必须l++, r--, 否则为出现栈溢出
		if(l == r){
			l += 1;
			r -= 1;
		}
		//向左递归
		if(left < r){
			quackSort(arr, left, r);
		}
		//向右递归
		if(right > l){
			quackSort(arr, l, right);
		}
	}
}
