package git;

public class SeqSearch {

	public static void main(String[] args) {
		int arr[] = { 1, 9, 11, -1, 34, 89 };	//无序序列
//		int arr[] = {1,8, 10, 89, 1000, 1234};	//有序序列
		
		int index = seqSearch(arr, 34);
		if(index == -1){
			System.out.println("没有找到");
		}else{
			System.out.println("找到了:" + index);
		}
	}
	
	/**
	  * 
	  * @Description 此处实现的线性查找是找到一个满足条件的值,就返回
	  * @author subei
	  * @date 2020年5月30日下午6:30:16
	  * @param arr
	  * @param value
	  * @return
	 */
	public static int seqSearch(int[] arr,int value){
		//线性查找是逐一比对,发现相同值,返回下标
		for(int i = 0;i < arr.length;i++){
			if(arr[i] == value){
				return i;
			}
		}
		return -1;
	}

}
