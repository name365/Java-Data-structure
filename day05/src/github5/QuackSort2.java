package github5;

import java.text.SimpleDateFormat;
import java.util.Date;
//测试快排的执行速度
public class QuackSort2 {

	public static void main(String[] args) {
		
		int[] arr = new int[8000000];
		for(int i = 0;i < 8000000;i++){
			arr[i] = (int)(Math.random() * 8000000); //自动生成[0,8000000)之间的随机数
		}

		// 排序前的时间:
		Date data = new Date();
		SimpleDateFormat simt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateS = simt.format(data);
		System.out.println("排序前的时间是:" + dateS);

		quackSort(arr, 0, arr.length - 1);
		
		// 排序后的时间:
		Date data2 = new Date();
		SimpleDateFormat simt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateS2 = simt2.format(data2);
		System.out.println("排序后的时间是:" + dateS2);
	}

	//快速排序
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
