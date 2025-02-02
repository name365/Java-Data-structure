package github6;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MergetSort {

	public static void main(String[] args) {
		
		int[] arr = new int[8000000];
		for(int i = 0;i < 8000000;i++){
			arr[i] = (int)(Math.random() * 8000000); //自动生成[0,800000)之间的随机数
		}

		// 排序前的时间:
		Date data = new Date();
		SimpleDateFormat simt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateS = simt.format(data);
		System.out.println("排序前的时间是:" + dateS);
		
		int temp[] = new int[arr.length]; //归并排序需要一个额外空间
		mergeSort(arr, 0, arr.length - 1, temp);
 				
		// 排序后的时间:
		Date data2 = new Date();
		SimpleDateFormat simt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateS2 = simt2.format(data2);
		System.out.println("排序后的时间是:" + dateS2);
	}
	
	//分+和的方法
	public static void mergeSort(int[] arr,int left,int right,int[] tem){
		if(left < right){
			int mid = (left + right) / 2;	//中间索引
			//向左进行递归
			mergeSort(arr, left, mid, tem);
			//向右递归
			mergeSort(arr, mid + 1, right, tem);
			//合并
			marge(arr, left, mid, right, tem);
		}
	}

	//合并的方法
	/**
	  * 
	  * @Description 
	  * @author subei
	  * @date 2020年5月29日下午5:46:23
	  * @param arr 排序的原始数组
	  * @param left 左边有序序列的初始索引
	  * @param mid 中间索引
	  * @param right 右边索引
	  * @param temp 做中转的数组
	 */
	public static void marge(int[] arr,int left,int mid,int right,int[] temp){
		int i = left; // 初始化i, 左边有序序列的初始索引
		int j = mid + 1; // 初始化j, 右边有序序列的初始索引
		int t = 0; // 指向temp数组的当前索引
		
		//一、
		//先把左右两边(有序)的数据按照规则填充到temp数组
		//直到左右两边的有序序列,有一边处理完毕为止
		while(i <= mid && j <= right){	//继续
			//如果左边的有序序列的当前元素,小于等于右边有序序列的当前元素
			//即,将左边的当前元素,填充到 temp数组 
			//然后 t++, i++ ==》 后移
			if(arr[i] <= arr[j]){
				temp[t] = arr[i];
				t += 1;
				i += 1;
			} else {	//反之,将右边有序序列的当前元素,填充到temp数组
				temp[t] = arr[j];
				t += 1;
				j += 1;
			}
		}
		
		//二、
		//把有剩余数据的一边的数据依次全部填充到temp
		while(i <= mid){	//左边的有序序列还有剩余的元素,就全部填充到temp
			temp[t] = arr[i];
			t += 1;
			i += 1;
		}
		while(j <= right){	//右边的有序序列还有剩余的元素,就全部填充到temp
			temp[t] = arr[j];
			t += 1;
			j += 1;
		}
		
		//三、
		//将temp数组的元素拷贝到arr,注意,并不是每次都拷贝所有数据
		t = 0;
		int tempLeft = left;
		//第一次合并 tempLeft = 0 , right = 1 
		//第二次合并 tempLeft = 2  right = 3 
		//第三次合并 tempLeft = 0 right=3
		//最后一次合并 tempLeft = 0  right = 7
		while(tempLeft <= right){
			arr[tempLeft] = temp[t];
			t += 1;
			tempLeft += 1; 
		}
	}
}
