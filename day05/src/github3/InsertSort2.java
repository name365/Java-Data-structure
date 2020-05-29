package github3;

import java.text.SimpleDateFormat;
import java.util.Date;
//时间测试
public class InsertSort2 {

	public static void main(String[] args) {
		//测试一下插入排序的速度:O(n^2),给80000个的数据,测试
		int[] arr = new int[80000];
		for(int i = 0;i < 80000;i++){
			arr[i] = (int)(Math.random() * 80000);	//自动生成[0,80000)之间的随机数
		}
		
		//排序前的时间:
		Date data = new Date();
		SimpleDateFormat simt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateS = simt.format(data);
		System.out.println("排序前的时间是:" + dateS);	//2020-05-29 11:31:53
		
		inserSort(arr);	//调用插入排序
		
		//排序后的时间:
		Date data2 = new Date();
		SimpleDateFormat simt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateS2 = simt2.format(data2);
		System.out.println("排序后的时间是:" + dateS2);	//2020-05-29 11:31:54	
	}
	//插入排序
	public static void inserSort(int[] arr){
		int insertVal = 0;
		int insertIndex = 0;
		for(int i = 1;i < arr.length;i++){
			insertVal = arr[i];
			insertIndex = i - 1;	//即arr[i]的前面这个数的下标
			//给insertVal 找到插入位置
			//说明:
			//1.insertVal >= 0 保证在找到相应位置时,不会越界
			//2.insertVal < arr[insertIndex] 待插入的数未找到合适位置
			//3.就需要将 arr[insertIndex] 后移
			while(insertIndex >= 0 && insertVal < arr[insertIndex]){	//从小到大排序
//			while(insertIndex >= 0 && insertVal > arr[insertIndex]){	//从大到小排序
				arr[insertIndex + 1] = arr[insertIndex];	// arr[insertIndex]
				insertIndex--;
			}
			//当退出while循环时，说明找到要插入的位置, insertIndex + 1
			//算法的优化:判断是否需要赋值
			if(insertVal + 1 == i){	//满足则没必要执行
				arr[insertIndex + 1] = insertVal;
			}
		}
	}
}
