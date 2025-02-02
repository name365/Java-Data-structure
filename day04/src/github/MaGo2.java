package github;

public class MaGo2 {

	public static void main(String[] args) {
		//先创建一个二维数组,用于模拟迷宫
		//地图
		int[][] map = new int[8][7];
		//使用1表示
		//上下全置为1
		for(int i = 0;i <7 ;i++){
			map[0][i] = 1;
			map[7][i] = 1;
		}
		//左右全置为1
		for(int i = 0;i < 8;i++){
			map[i][0] = 1;
			map[i][6] = 1;
		}
		//设置挡板,1表示
		map[3][1] = 1;
		map[3][2] = 1;
		//输出地图
		System.out.println("地图的初始情况:");
		for(int i = 0;i < 8;i++){
			for(int j = 0;j < 7;j++){
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		
		//使用递归回溯给小球找路
		setWay(map, 1, 1);
		setWay2(map, 1, 1);
				
		//输出新的地图, 小球走过,并标识过的递归
		System.out.println("小球走过,并标识走过的地图的情况:");
		for(int i = 0;i < 8;i++){
			for(int j = 0;j < 7;j++){
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	/**
	  * 
	  * @Description 使用递归来给小球找路
	  * @author subei
	  * @date 2020年5月28日上午9:52:07
	  * @param map 表示地图
	  * @param i 从哪个位置开始找
	  * @param j
	  * @return 如果找到通路，就返回true, 否则返回false
	 */
	public static boolean setWay(int[][] map,int i,int j){
		if(map[6][5] == 2){	//通路已经找到
			return true;
		}else{
			if(map[i][j] == 0){	//如果当前的点未走过
				//按步骤做 下->右->上->左  走
				map[i][j] = 2;	//假设此点可以通向终点
				if(setWay(map, i+1, j)) {	//先向下走
					return true;
				} else if (setWay(map, i, j+1)) { //再向右走
					return true;
				} else if(setWay(map, i-1, j)) { //再向上
					return true;
				} else if (setWay(map, i, j-1)){ //再向左走
					return true;
				} else {
					//最后说明该点是无法通向终点,pass
					map[i][j] = 3;
					return false;
				}
			}else{	//如果map[i][j] != 0 , 可能是 1， 2， 3
				return false;
			}
		}
	}
	
	//修改找路策略,改为 上 --》 右 --》 下 --》 左
	public static boolean setWay2(int[][] map,int i,int j){
		if(map[6][5] == 2){	//通路已经找到
			return true;
		}else{
			if(map[i][j] == 0){	//如果当前的点未走过
				//按步骤做 上 --》 右 --》 下 --》 左 走
				map[i][j] = 2;	//假设此点可以通向终点
				if(setWay2(map, i-1, j)) {	//先向上走
					return true;
				} else if (setWay2(map, i, j+1)) { //再向右走
					return true;
				} else if(setWay2(map, i+1, j)) { //再向下
					return true;
				} else if (setWay2(map, i, j-1)){ //再向左走
					return true;
				} else {
					//最后说明该点是无法通向终点,pass
					map[i][j] = 3;
					return false;
				}
			}else{	//如果map[i][j] != 0 , 可能是 1， 2， 3
				return false;
			}
		}
	}
}
