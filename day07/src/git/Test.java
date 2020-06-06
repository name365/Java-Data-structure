package git;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		//以ArrayList为例,演示数组扩容
		@SuppressWarnings({ "rawtypes", "unused" })
		//ArrayList 维护了transient Object[] elementData;
		/*
		 * ArrayList 底层仍是进行了数组扩容
		 */
		ArrayList arrayList = new ArrayList();
		
	}

}
