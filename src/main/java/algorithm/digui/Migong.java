package algorithm.digui;

import org.junit.Before;
import org.junit.Test;

public class Migong {
	
	private int[][] map;
	
	private int count = 0;
	
	@Before
	public void init() {
		map = new int[][] {
			{1,1,1,1,1,1,1},
			{1,0,0,0,0,0,1},
			{1,0,1,0,0,0,1},
			{1,1,1,0,0,0,1},
			{1,0,0,0,0,0,1},
			{1,0,1,1,1,1,1},
			{1,0,0,0,0,0,1},
			{1,1,1,1,1,1,1}
		};
	}
	
	@Test
	public void testDemo() {
		if (coreAlgorithm(1, 1)) {
			System.out.println("Found! Total go with " + count);
			printMigong();
		} else {
			System.out.println("Not found");
		}
		
	}
	
	private void printMigong() {
		for (int i = 0; i < map.length; i ++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * i, j stand for startup position
	 * m[i][j] == 0 stand for never access
	 * m[i][j] == 1 stand for wall
	 * m[i][j] == 2 already be
	 * n[i][j] == 3 stand for dead
	 * 要有策略，下，右，上，左
	 */
	private boolean coreAlgorithm(int i, int j) {
		if(map[6][5] == 2) {
			return true;
		} else {
			if(map[i][j] == 0) {
				//Go path according to stredge
				map[i][j] = 2;
				if(coreAlgorithm(i+1, j)) {
					count++;
					return true;
				} else if (coreAlgorithm(i, j+1)) {
					count++;
					return true;
				} else if (coreAlgorithm(i-1, j)) {
					count++;
					return true;
				} else if (coreAlgorithm(i, j-1)) {
					count++;
					return true;
				} else {
					map[i][j] = 3;
					return false;
				}
			} else {
				return false;
			}
		}
	}
}
