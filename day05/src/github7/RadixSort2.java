package github7;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RadixSort2 {
	
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
		
		radSort(arr);	//调用基数排序
		
		// 排序后的时间:
		Date data2 = new Date();
		SimpleDateFormat simt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateS2 = simt2.format(data2);
		System.out.println("排序后的时间是:" + dateS2);
		
	}
	
	//基数排序方法
	public static void radSort(int[] arr){
		
		//1.得到数组中的最大的位数
		int max = arr[0];	//假设第一个数即为最大数
		for(int i = 1;i < arr.length;i++){
			if(arr[i] > max){
				max = arr[i];
			}
		}
		//得到最大数是几位数
		int maxLen = (max + "").length();
		
		//定义一个二维数组,表示10个桶,每个桶即为一个一维数组
		//说明:
		//1.二维数组包含10个一维数组
		//2.为了防止在放入数的时候,数据溢出,则每个一维数组(桶),大小定为arr.length
		//3.需要明确:基数排序是使用空间换时间的经典算法
		int[][] bucket = new int[10][arr.length];
		
		//为了记录每个桶中,实际存放了多少个数据,需要定义一个一维数组来记录各个桶的每次放入的数据个数
		//可以这里理解
		//比如：bucketNums[0] , 记录的就是  bucket[0] 桶的放入数据个数
		int[] bucketNums = new int[10];
		
		for(int i = 0,n = 1;i < maxLen;i++,n *= 10){
			//对每个元素的各位进行排序,第一次是个位,第二次是十位,第三次是百位.
			for(int j = 0;j < arr.length;j++){
				//取出每个元素的对应位的值
				int digt = arr[j] / n % 10;	// 748 / 1 % 10 => 8
				//放入到对应的桶中
				bucket[digt][bucketNums[digt]] = arr[j];
				bucketNums[digt]++;
			}
			//按照这个桶的顺序(将一维数组的下标依次取出数据,放入原来数组)
			int index = 0;
			//遍历每一个桶,并将桶中的数据,放入原数组中
			for(int k = 0;k < bucketNums.length;k++){
				//如果桶中有数据,才放入原数组
				if(bucketNums[k] != 0) {
					//循环该桶,即第k个桶(即第k个一维数组), 放入数据
					for(int p = 0;p < bucketNums[k];p++){
						//取出元素放入到arr中
						arr[index++] = bucket[k][p];
					}
				}
				//第i+1轮处理后,需要将每个 bucketNums[k] = 0 ！
				bucketNums[k] = 0;	//重置为0
			}
//			System.out.println("第"+ (i+1) +"轮排序:" + Arrays.toString(arr));
		}
	}
}
