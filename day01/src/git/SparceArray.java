package git;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 要求:
 * 	1.在前面的基础上，将稀疏数组保存到磁盘上，比如 map.data
 *  2.恢复原来的数组时，读取map.data 进行恢复
 */
public class SparceArray {

	public static void main(String[] args) throws Exception {
		
		//创建一个原始的二维数组 11*11
		// 0:表示没有棋子,1表示黑子,2表示白子
		int chessArr[][] = new int[11][11];
		chessArr[1][3] = 5;
		chessArr[2][1] = 17;
		chessArr[5][5] = 21;
		//输出原始二维数组
		System.out.println("原始的二维数组:");
		for(int[] row : chessArr){
			for(int data : row){
				System.out.printf("%d\t",data);
			}
			System.out.println();
		}
		
		//二维数组 转 稀疏数组
		//1.先遍历二维数组,得到非0数据的个数
		int sum = 0;
		for(int i = 0;i < 11;i++){
			for(int j = 0;j < 11;j++){
				if(chessArr[i][j] != 0){
					sum++;
				}
			}
		}
		
		//2.创建对应的稀疏数组
		int sparseArr[][] = new int[sum + 1][3];	//除去第一行
		//给稀疏数组赋值
		sparseArr[0][0] = 11;
		sparseArr[0][1] = 11;
		sparseArr[0][2] = sum;	//有效数据的个数,即非0的值
		
		//遍历二维数组,将非0的值存入到稀疏数组saprseArr中
		int count = 0;	//用于记录是第几个非0数据
		for(int i = 0;i < 11;i++){
			for(int j = 0;j < 11;j++){
				if(chessArr[i][j] != 0){
					count++;
					sparseArr[count][0] = i;	//第一列
					sparseArr[count][1] = j;	//第二列
					sparseArr[count][2] = chessArr[i][j];	//第三列
				}
			}
		}
		
		//保存稀疏数组
		File file = new File("F:\\java\\Data structure\\day01\\map.data");
		FileOutputStream fos = new FileOutputStream(file);

		OutputStreamWriter write = new OutputStreamWriter(fos, "UTF-8");
		
		//输出稀疏数组的形式
		System.out.println();
		System.out.println("得到的稀疏数组为: ");
		for(int i = 0;i < sparseArr.length;i++){
			System.out.printf("%d\t%d\t%d\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
			
			if (i == sparseArr.length - 1) {
				write.append(sparseArr[i][0] + "," + sparseArr[i][1] + "," + sparseArr[i][2]);
			} else {
				write.append(sparseArr[i][0] + "," + sparseArr[i][1] + "," + sparseArr[i][2] + ",");
			}

		}
		
		System.out.println("写入文件中...");
		write.close();
		fos.close();

		System.out.println("打开文件中...");
		Desktop.getDesktop().open(file);

		System.out.println("-------------先读取map.data-----------------");
		// 创建 FileReader 对象
		FileInputStream fis = new FileInputStream(file);

		InputStreamReader reader = new InputStreamReader(fis, "UTF-8");
		StringBuffer sb = new StringBuffer();
		while (reader.ready()) {
			sb.append((char) reader.read());// 转成char加到StringBuffer对象中
		}

		System.out.println(sb.toString());
		reader.close();// 关闭读取流
		fis.close();// 关闭输入流,释放系统资源

		System.out.println("-------------恢复成稀疏数组sparseArr-----------------");
		
		System.out.println();
		
		//将稀疏数组恢复为原始的二维数组
		/*
		 * 1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的  chessArr2 = int [11][11]
		 * 2.在读取稀疏数组后几行的数据，并赋给 原始的二维数组 即可.
		 */
		//1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
		int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
		
		//2.在读取稀疏数组后几行的数据(从第二行开始),并赋给 原始的二维数组
		for(int i = 1;i < sparseArr.length;i++){
			chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
		}
		
		//输出恢复后的二维数组
		System.out.println();
		System.out.println("恢复后的二维数组:");
		for(int[] row : chessArr2){
			for(int data : row){
				System.out.printf("%d\t",data);
			}
			System.out.println();
		}
	
	}
	
}
