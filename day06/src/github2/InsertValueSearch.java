package github2;

//import java.util.Arrays;

public class InsertValueSearch {

	public static void main(String[] args) {
//		int arr[] = new int[100];
//		for(int i = 1;i < 100;i++){
//			arr[i] = i + 1;
//		}
		
		int arr[] = {1,8, 10, 89, 1000, 1234};
		
		int index = insertValue(arr, 0, arr.length - 1, 1234);
		System.out.println("index = " + index);
		
		int index2 = binarySerch(arr, 0, arr.length, 1234);
		System.out.println("index2 = " + index2);
		
//		System.out.println(Arrays.toString(arr));
	}
	
	public static int binarySerch(int arr[],int left,int right,int findVal){
		System.out.println("二分查找的调用:");
		//当left > right时,整个数组都没有
		if(left > right){	//没有这个判断,会造成死递归！！！！
			return -1;
		}
		int mid = (left + right) / 2;
		int midVal = arr[mid];
		if(findVal > midVal){	//向右递归
			return binarySerch(arr, mid + 1, right, findVal);
		}else if(findVal < midVal){	//向左递归
			return binarySerch(arr, left, mid-1, findVal);
		}else{
			return mid;
		}
	}
	
	/**
	  * 插值查找算法,也要求数组是有序的!!!
	  * @Description 插值查找算法
	  * @author subei
	  * @date 2020年5月31日下午9:43:59
	  * @param arr 数组
	  * @param left 左边的索引
	  * @param right 右边的索引
	  * @param findVal 查找的值
	  * @return 如果找到,就返回对应的下标,如果没有找到,返回-1
	 */
	public static int insertValue(int arr[],int left,int right,int findVal){
		System.out.println("查找的调用:");
		
		//注意：findVal < arr[0]  和  findVal > arr[arr.length - 1] 必须需要
		//否则将会得到的 mid可能越界
		if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
			return -1;
		}
		//计算mid,自适应写法
		int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
		int midVal = arr[mid];
		if(findVal > midVal){	//向右查找
			return insertValue(arr, mid + 1, right, findVal);
		}else if(findVal < midVal){	//向左查找
			return insertValue(arr, left, mid - 1, findVal);
		}else{	//找到了！！！
			return mid;
		}
	}
}
