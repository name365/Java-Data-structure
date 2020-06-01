package github;
//注意:使用二分查找的数组,必须是有序的。
public class BinarySearch {

	public static void main(String[] args) {
		int arr[] = {1,8, 10, 89, 1000, 1234};
		int serch = binarySerch(arr, 0, arr.length - 1, 11);
		System.out.println("serch = " + serch);
	}
	
	/**
	  * 
	  * @Description 二分查找算法
	  * @author subei
	  * @date 2020年5月31日下午4:59:59
	  * @param arr 数组
	  * @param left 左边的索引
	  * @param right 右边的索引
	  * @param findVal 要查找的值
	  * @return 如果找到返回下标，反之，返回 -1
	 */
	public static int binarySerch(int arr[],int left,int right,int findVal){
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
}
