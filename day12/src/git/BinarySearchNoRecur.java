package git;

public class BinarySearchNoRecur {

	public static void main(String[] args) {
		int arr[] = {1,3, 8, 10, 11, 67, 100};
		int index = bSart(arr, 100);
		System.out.println("index:" + index);
	}
	
	//二分查找算法
	/**
	  * 
	  * @Description 
	  * @author subei
	  * @date 2020年6月20日下午3:41:33
	  * @param arr 待查找的数组, arr是升序排序
	  * @param target 需要查找的数
	  * @return 返回对应下标,-1表示没有找到
	 */
	public static int bSart(int[] arr, int target) {
		int left =0;
		int right = arr.length - 1;
		while(left <= right){	//继续进行查找
			int mid = (left+right)/2;
			if(arr[mid] == target){
				return mid;
			}else if(arr[mid] > target){
				right = mid - 1;	//向左查找
			}else{
				left = mid + 1;	//向右查找
			}
		}
		return -1;	//没找到
	}
}
