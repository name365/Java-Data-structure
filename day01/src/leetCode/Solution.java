package leetCode;

public class Solution {
	public String longestPalindrome(String s) {

		if (null == s || s.length() < 2) {
			return s;
		}
		int start = 0; // 记录回文子串的开始位置
		int maxLen = 1; // 记录字符串的长度
		int end = 0;

		// 定义二维数组记录原字符串 i 到 j 区间是否为回文子串。
		boolean[][] df = new boolean[s.length()][s.length()];
		// 遍历元素并得到包含当前元素之前字符串的最大回文子串。
		for (int i = 1; i < s.length(); i++) {
			for (int j = 0; j < i; j++) {
				// 状态转移，判断记录 i 到 j 位置是否为回文子串。
				if (s.charAt(j) == s.charAt(i) && (i - j <= 2 || df[i - 1][j + 1])) {
					df[i][j] = true;
					// 判断更新记录遍历过的最长回文子串。
					if (i - j + 1 > maxLen) {
						maxLen = i - j + 1;
						start = j;
						end = i;
					}
				}
			}
		}
		return s.substring(start, end + 1);
	}
}
