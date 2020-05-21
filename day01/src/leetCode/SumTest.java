package leetCode;

import java.util.HashMap;
import java.util.Map;
/*
 * 1.创建一个map
 * 2.for循环遍历nums数组
 * 3.用target减nums[i],以计算哪个数能和当前的数相加可得到target
 * 4.判断map里是否有这个数
 * 		如果有，返回结果；
 * 		如果没有，则把nums[i]当作key,i当作value放入map中。
 */
public class SumTest {
    public int[] twoSum(int[] nums, int target) {
    	Map<Integer, Integer> map = new HashMap<>();
    	for(int i = 0;i < nums.length;i++){
    		// 如果 map 存在此差值，则返回
    		if(map.containsKey(target - nums[i])){
    			return new int[]{i,map.get(target - nums[i])};
    		}
    		map.put(nums[i], i);	// 将该数组的值存入 map
    	}
		return null;
    }
}
