

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Grid {
	Tile[][] grid = new Tile[31][28];

	//read in file and create grid
	public Grid() throws FileNotFoundException{
		Scanner in = new Scanner(new File("grid.txt"));
		for (int x = 0; x < 31; ++x) {
			for (int y = 0; y < 28; y++) {
				grid[x][y] = new Tile(in.nextInt(), in.nextInt(), in.nextBoolean(), in.nextBoolean());
			}
		}
	}
	//return tile from array
	public Tile getTile(int x, int y){
		return grid[x][y];
	}
	//draw food on maze
	public void draw(){		
		StdDraw.setPenColor(StdDraw.RED);
		for(int r = 0; r < 31; ++r) {
			for (int c = 0; c < 28; ++c) {
				if(grid[r][c].getLegalSpace()) {
					StdDraw.setPenColor(StdDraw.BLACK);
				}
				else {
					StdDraw.setPenColor(StdDraw.GREEN);
				}
				if(grid[r][c].getHasFood()){
					StdDraw.setPenColor(StdDraw.WHITE);
					if(r == 3 && c == 1 || r == 3 && c == 26 || r == 23 && c == 1 || r == 23 && c == 26){
						StdDraw.filledCircle(grid[r][c].getX(), grid[r][c].getY(),3);
					}					
					else{

						StdDraw.filledCircle(grid[r][c].getX(),grid[r][c].getY(), 1);
					}
				}
			}
		}
	}

}