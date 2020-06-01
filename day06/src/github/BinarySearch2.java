package github;

import java.util.ArrayList;
import java.util.List;

//注意:使用二分查找的数组,必须是有序的。
public class BinarySearch2 {

	public static void main(String[] args) {
//		int arr[] = {1,8, 10, 89, 1000,1000, 1234};
		int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 , 11, 12, 13,14,15,16,17,18,19,20 };
		
		List<Integer> reget = binarySerch2(arr, 0, arr.length - 1, 1);
		System.out.println("reget = " + reget);
	}
	
	//{1,8, 10, 89, 1000, 1000，1234} 
	//当一个有序数组中，有多个相同的数值时，如何将所有的数值都查找到，比如这里的 1000.
	//思路分析:
	//1.在找到mid时,不马上返回
	//2.向mid索引值的左边扫描,将所有满足 1000的元素的下标，加入到集合ArrayList中
	//3.向mid索引值的右边扫描,将所有满足 1000的元素的下标，加入到集合ArrayList中
	//4.返回ArrayList集合
	public static List<Integer>  binarySerch2(int arr[],int left,int right,int findVal){
		System.out.println("nums----");
		//当left > right时,整个数组都没有
		if(left > right){	//没有这个判断,会造成死递归！！！！
			return new ArrayList<Integer>();
		}
		int mid = (left + right) / 2;
		int midVal = arr[mid];
		if(findVal > midVal){	//向右递归
			return binarySerch2(arr, mid + 1, right, findVal);
		}else if(findVal < midVal){	//向左递归
			return binarySerch2(arr, left, mid-1, findVal);
		}else{
			
			List<Integer> reget = new ArrayList<Integer>();
			//向mid索引值的左边扫描,将所有满足 1000的元素的下标，加入到集合ArrayList中
			int temp = mid - 1;
			while(true){
				if(temp < 0 || arr[temp] != findVal){	//已经将最左边都扫描完成
					break;
				}
				//否则,就将temp放入集合
				reget.add(temp);
				temp -= 1;	//向左移动temp
			}
			reget.add(mid);	//放入中间值
			
			//向mid索引值的右边扫描,将所有满足 1000的元素的下标，加入到集合ArrayList中
			temp = mid + 1;
			while(true){
				if(temp > arr.length - 1 || arr[temp] != findVal){	//已经将最右边都扫描完成
					break;
				}
				//否则,就将temp放入集合
				reget.add(temp);
				temp += 1;	//向右移动temp
			}
			return reget;
		}
	}
}
