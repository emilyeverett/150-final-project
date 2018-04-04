
import java.io.*;

public class Pacman {
	private double x,y;
	private String image;
	private char dir;
	private int score, lives;
	private boolean alive;

	public Pacman(double x, double y, String image) {
		this.x = x;
		this.y = y;
		this.image = image;
		score = 0;
		lives = 3;
		alive = true;
	}
	public void move(Grid grid, IntroSong wakaWaka)throws FileNotFoundException, IOException{

		Tile t = grid.getTile(getRow(),getColumn());
		if(getRow() == 14 && getColumn() == 0 || getRow() == 14 && getColumn() == 27)
			x = -(x + 1);
		if(dir == 'U')
			goUp(grid, t, wakaWaka);
		else if (dir == 'L'){
			goLeft(grid, t, wakaWaka);
		}
		else if (dir == 'D'){
			goDown(grid, t, wakaWaka);
		}

		else if (dir == 'R'){
			goRight(grid, t, wakaWaka);
		}
		
	}
	public void goLeft(Grid grid, Tile t, IntroSong wakaWaka) throws FileNotFoundException, IOException{
		if(x >= t.getX())
        	x -= 1;
       	else if(x < t.getX() && grid.getTile(getRow(),getColumn()-1).getLegalSpace())
        	x -= 1;
    	else
        	dir = ' ';
    	if(grid.getTile(getRow(),getColumn()).getHasFood()) {
        	score += 100;
        	//wakaWaka.play();
    	}
    	grid.getTile(getRow(),getColumn()).setHasFood();
		image = "pacmanL.png";
	}
	public void goRight(Grid grid, Tile t, IntroSong wakaWaka) throws FileNotFoundException, IOException{
		if(x <= t.getX())
        	x += 1;
    	else if(x > t.getX() && grid.getTile(getRow(),getColumn()+1).getLegalSpace())
        	x += 1;
    	else
        	dir = ' ';
    	if(grid.getTile(getRow(),getColumn()).getHasFood()){
        	score += 100;
        	//wakaWaka.play();
    	}
    	grid.getTile(getRow(),getColumn()).setHasFood();
		image = "pacmanR.png";
	}
	public void goUp(Grid grid, Tile t, IntroSong wakaWaka) throws FileNotFoundException, IOException{
		if(y <= t.getY())
        	y += 1;
    	else if(y > t.getY() && grid.getTile(getRow()-1,getColumn()).getLegalSpace())
        	y += 1;
    	else
        	dir = ' ';
     	if(grid.getTile(getRow(),getColumn()).getHasFood()){
        	score += 100;
        	//wakaWaka.play();
     	}
      	grid.getTile(getRow(),getColumn()).setHasFood();
		image = "pacmanU.png";
	}
	public void goDown(Grid grid, Tile t, IntroSong wakaWaka) throws FileNotFoundException, IOException{
		if(y >= t.getY())
    		y -= 1;
    	else if(y < t.getY() && grid.getTile(getRow()+1,getColumn()).getLegalSpace())
        	y -= 1;
    	else
        	dir = ' ';
    	if(grid.getTile(getRow(),getColumn()).getHasFood()){
        	score += 100;
        	//wakaWaka.play();
    	}
    	grid.getTile(getRow(),getColumn()).setHasFood();
		image = "pacmanD.png";
	}

	public void draw() {
		if(alive)
			StdDraw.picture(x, y, image, 6, 6);
	}
	public char getDir(){return dir;}

	public void setDir(char d){
		dir = d;
	}
	public int getColumn(){
		return (int)(x + 112) / 8;
	}
	public int getRow(){
		return (int)(120 - y) / 8;
	}
	public int getScore(){return score;}

	public int getLives(){return lives;}

	public void setLives(){
		lives = 3;
	}

	public void die(){
		--lives;
		x = 0;
		y = -68;
		if (lives == 0)
			alive = false;
	}
}