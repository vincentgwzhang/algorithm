package algorithm.xishushuzu;

import org.junit.Test;

/**
 * 稀疏数组 的实现
 */
public class XishushuzuApplication {
	
	private int[][] coreAlgorithm(int [][] inputArray, int sum) {
		//创建对应的稀疏数组
		int sparseArray[][] = new int[sum+1][3];
		//给稀疏数组赋值
		sparseArray[0][0] = inputArray.length;
		sparseArray[0][1] = inputArray[0].length;
		sparseArray[0][2] = sum;
		
		//遍历二维数组
		int loop = 1;
		for(int x = 0; x < inputArray.length; x++ ) {
			for(int y = 0 ; y < inputArray[0].length; y++) {
				if (inputArray[x][y] != 0) {
					sparseArray[loop][0] = x;
					sparseArray[loop][1] = y;
					sparseArray[loop][2] = inputArray[x][y];
					loop++;
				}
				if(loop == sum + 1) {
					break;
				}
			}
			if(loop == sum + 1) {
				break;
			}
		}
		return sparseArray;
	}
	
	@Test
	public void test01() {
		
		int[][] xishushuzu = new int[11][11];
		xishushuzu[1][2] = 1;
		xishushuzu[2][3] = 2;
		
		//先遍历二维数组，得到非0数据的个数
		int sum = 0;
		for(int[] row : xishushuzu) {
			for(int data : row) {
				if (data != 0) {
					sum++;
				}
			}
		}
		
		printArray(xishushuzu);
		int sparseArray[][] = coreAlgorithm(xishushuzu, sum);
		printArray(sparseArray);
		
		//恢复
		int huifu[][] = new int[sparseArray[0][0]][sparseArray[0][1]];
		for(int i = 1; i < sparseArray.length; i ++) {
			huifu[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
		}
		
		printArray(huifu);
		
	}
	
	private void printArray(int[][] array) {
		System.out.println();
		System.out.println("========================================================");
		for(int[] row : array) {
			for(int data : row) {
				System.out.printf("%d\t", data);
			}
			System.out.println();
		}
	}
	
}
