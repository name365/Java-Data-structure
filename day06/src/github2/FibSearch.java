package github2;

import java.util.Arrays;

public class FibSearch {

	public static int maxSize = 20;

	public static void main(String[] args) {
		int arr[] = { 1, 8, 10, 89, 1000, 1234 };
		System.out.println("index = " + fibS(arr, 189));
	}

	// 因为后面需要使用公式 mid=low+F(k-1)-1,因此需要先获取到一个斐波那契数列
	// 用非递归方法得到一个斐波那契数列
	public static int[] fib() {
		int f[] = new int[maxSize];
		f[0] = 1;
		f[1] = 1;
		for (int i = 2; i < maxSize; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}
		return f;
	}

	/**
	 * 
	 * @Description 使用非递归的方式编写斐波那契算法
	 * @author subei
	 * @date 2020年5月31日下午10:20:37
	 * @param a
	 *            数组
	 * @param key
	 *            需要查找的关键码(值)
	 * @return 返回对应的下标,如果没有-1
	 */
	public static int fibS(int[] a, int key) {
		int low = 0;
		int hight = a.length - 1;
		int k = 0; // 斐波那契数列的下标
		int mid = 0; // 存放mid值
		int f[] = fib(); // 获取到斐波那契数列
		// 获取到斐波那契分割数值的下标
		while (hight > f[k] - 1) {
			k++;
		}
		// 因为 f[k] 值 可能大于 a 的 长度,因此我们需要使用Arrays类,构造一个新的数组,并指向temp[]
		// 不足的部分会使用0填充
		int[] temp = Arrays.copyOf(a, f[k]);
		// 实际上需求使用a数组最后的数填充 temp
		// 举例:
		// temp = {1,8, 10, 89, 1000, 1234, 0, 0} =》 {1,8, 10, 89, 1000, 1234,
		// 1234, 1234,}
		for (int i = hight + 1; i < temp.length; i++) {
			temp[i] = a[hight];
		}
		// 利用循环查找key
		while (low <= hight) { // 满足这个条件,即可以找到
			mid = low + f[k - 1] - 1;
			if (key < temp[mid]) { // 继续向左边查找
				hight = mid - 1;
				// 使用k--的原因
				// 说明:
				// 1.全部元素 = 前面的元素 + 后边元素
				// 2.f[k] = f[k-1] + f[k-2]
				// 因为 前面有 f[k-1]个元素,所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
				// 即 在 f[k-1] 的前面继续查找 k--
				// 即下次循环 mid = f[k-1-1]-1
				k--;
			} else if (key > temp[mid]) { // 继续向右边查找
				low = mid + 1;
				// 使用k -= 2 的原因
				// 说明
				// 1.全部元素 = 前面的元素 + 后边元素
				// 2.f[k] = f[k-1] + f[k-2]
				// 3.因为后面我们有f[k-2] 所以可以继续拆分 f[k-1] = f[k-3] + f[k-4]
				// 4.即在f[k-2] 的前面进行查找 k -= 2
				// 5.即下次循环 mid = f[k - 1 - 2] - 1
				k -= 2;
			} else { // 找到了！！！
				// 需要确定,返回的是哪个下标
				if (mid <= hight) {
					return mid;
				} else {
					return hight;
				}
			}
		}
		return -1;
	}
}
