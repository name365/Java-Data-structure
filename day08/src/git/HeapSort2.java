package git;

import java.text.SimpleDateFormat;
//import java.util.Arrays;
import java.util.Date;

public class HeapSort2 {

	public static void main(String[] args) {
		//要求:将数组进行升序排序
//		int arr[] = {4,6,8,5,9};
//		headSort(arr);
		
		//创建要给80000个的随机的数组
		int[] arr = new int[8000000];
		for (int i = 0; i < 8000000; i++) {
			arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
		}

		System.out.println("排序前");
		Date data1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(data1);
		System.out.println("排序前的时间是=" + date1Str);
		
		headSort(arr);
		
		Date data2 = new Date();
		String date2Str = simpleDateFormat.format(data2);
		System.out.println("排序后的时间是=" + date2Str);
		//System.out.println("排序后=" + Arrays.toString(arr));
	}
	
	//编写一个堆排序的方法
	public static void headSort(int arr[]){
		System.out.println("堆排序:");
		int temp = 0;
		//完成我们最终代码
		//1.将无序序列构建成一个堆,根据升序降序需求选择大顶堆或小顶堆
		for(int i=arr.length / 2 - 1;i >= 0;i--){
			adHeap(arr,i,arr.length);
		}
//		System.out.println("步骤一的数组排序结果=" + Arrays.toString(arr));
		
		//2.将堆顶元素与末尾元素交换,将最大元素"沉"到数组末端;
		//3.重新调整结构,使其满足堆定义,然后继续交换堆顶元素与当前末尾元素,反复执行调整+交换步骤,直到整个序列有序。
		for(int j=arr.length - 1;j >0 ;j--){
			//交换
			temp=arr[j];
			arr[j]=arr[0];
			arr[0]=temp;
			adHeap(arr, 0, j); 
		}
//		System.out.println("最后的数组排序结果=" + Arrays.toString(arr));
	}
	
	//将一个数组(二叉树),调整成一个大顶堆
	/**
	  * 功能: 完成 将 以 i 对应的非叶子结点的树调整成大顶堆
	  * 举例: int arr[] = {4, 6, 8, 5, 9}; => i = 1 => adHeap => 得到 {4, 9, 8, 5, 6}
	  * 如果我们再次调用  adHeap 传入的是 i = 0 => 得到 {4, 9, 8, 5, 6} => {9,6,8,5,4}
	  * @Description 
	  * @author subei
	  * @date 2020年6月8日上午10:29:31
	  * @param arr 待调整的数组
	  * @param i 非叶子结点的索引
	  * @param length 表示对多少个元素进行调整
	 */
	public static void adHeap(int arr[],int i,int length){
		int temp = arr[i];	//先取出当前元素的值,保存在临时变量
		//说明:
		//1. j = i * 2 + 1 j 是 i结点的左子结点
		for(int j = i * 2 + 1;j < length;j = j * 2 + 1){
			if(j+1 < length && arr[j] < arr[j+1]){	//说明左子节点的值小于右子节点的值
				j++;	//j指向右子节点
			}
			if(arr[j] > temp){ //如果子节点大于父结点
				arr[i] = arr[j];	//将较大的值赋给当前节点
				i = j;	//让i指向j,继续循环
			}else{
				break;
			}
			//当for 循环结束后,已经将以 i 为父结点的树的最大值,放在了最顶(局部)
			arr[i] = temp;	//将temp值放到调整后的位置
		}
	}
}
