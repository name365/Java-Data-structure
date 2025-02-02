package github3;

import java.util.Arrays;

public class InsertSort {

	public static void main(String[] args) {
		int[] arr = {101, 34, 119, 1,-1,89,25};
		
		inserSort(arr);
		
	}
	//插入排序
	public static void inserSort(int[] arr){
		
		//代码的简化
				
		for(int i = 1;i < arr.length;i++){
			int insertVal = arr[i];
			int insertIndex = i - 1;	//即arr[i]的前面这个数的下标
			//给insertVal 找到插入位置
			//说明:
			//1.insertVal >= 0 保证在找到相应位置时,不会越界
			//2.insertVal < arr[insertIndex] 待插入的数未找到合适位置
			//3.就需要将 arr[insertIndex] 后移
			while(insertIndex >= 0 && insertVal < arr[insertIndex]){
				arr[insertIndex + 1] = arr[insertIndex];	// arr[insertIndex]
				insertIndex--;
			}
			//当退出while循环时，说明找到要插入的位置, insertIndex + 1
			arr[insertIndex + 1] = insertVal;
			
			System.out.print("第"+ i +"轮插入:");
			System.out.println(Arrays.toString(arr));
		}
		
		/*
		//使用逐步推到的过程
		
		//第1轮{101, 34, 119, 1}; =》 {34, 101, 119, 1};
		
		//定义待插入的数
		int insertVal = arr[1];
		int insertIndex = 1 - 1;	//即arr[1]的前面这个数的下标
		
		//给insertVal 找到插入位置
		//说明:
		//1.insertVal >= 0 保证在找到相应位置时,不会越界
		//2.insertVal < arr[insertIndex] 待插入的数未找到合适位置
		//3.就需要将 arr[insertIndex] 后移
		while(insertIndex >= 0 && insertVal < arr[insertIndex]){
			arr[insertIndex + 1] = arr[insertIndex];	// arr[insertIndex]
			insertIndex--;
		}
		//当退出while循环时，说明找到要插入的位置, insertIndex + 1
		arr[insertIndex + 1] = insertVal;
		
		System.out.print("第1轮插入:");
		System.out.println(Arrays.toString(arr));
		
		// 第2轮
		insertVal = arr[2];
		insertIndex = 2 - 1;	//即arr[2]的前面这个数的下标
		
		while(insertIndex >= 0 && insertVal < arr[insertIndex]){
			arr[insertIndex + 1] = arr[insertIndex];	// arr[insertIndex]
			insertIndex--;
		}
		//当退出while循环时，说明找到要插入的位置, insertIndex + 1
		arr[insertIndex + 1] = insertVal;
		
		System.out.print("第2轮插入:");
		System.out.println(Arrays.toString(arr));
		
		//第3轮
		insertVal = arr[3];
		insertIndex = 3 - 1;	//即arr[3]的前面这个数的下标
		
		while(insertIndex >= 0 && insertVal < arr[insertIndex]){
			arr[insertIndex + 1] = arr[insertIndex];	// arr[insertIndex]
			insertIndex--;
		}
		//当退出while循环时，说明找到要插入的位置, insertIndex + 1
		arr[insertIndex + 1] = insertVal;
		
		System.out.print("第3轮插入:");
		System.out.println(Arrays.toString(arr));
		*/
	}
}
