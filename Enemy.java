public class Enemy {
	private double x,y;
	private String image;
	private int speed, dir;

	public Enemy(String image) {
		x = 0;
		y = 0;
		this.image = image;
		speed = 1;
		dir = 1;
	}
	//calls correct method based on direction
	public void move(Grid grid){
		Tile t = grid.getTile(getRow(),getColumn());
		if(dir == 1)
			goUp(grid, t);
		else if (dir == 2){
			goLeft(grid, t);
		}
		else if (dir == 3){
			goDown(grid, t);
		}

		else if (dir == 0){
			goRight(grid, t);
		}
	}
	//moves ghost left
	public void goLeft(Grid grid, Tile t) {
		if(x >= t.getX())
        	x -= speed;
       	else if(x < t.getX() && grid.getTile(getRow(),getColumn()-1).getLegalSpace())
        	x -= speed;
    	else
        	stop();
	}
	//moves ghost right
	public void goRight(Grid grid, Tile t) {
		if(x <= t.getX())
        	x += speed;
    	else if(x > t.getX() && grid.getTile(getRow(),getColumn()+1).getLegalSpace())
        	x += speed;
    	else
        	stop();
	}
	//moves ghost up
	public void goUp(Grid grid, Tile t) {
		if(y <= t.getY())
        	y += speed;
    	else if(y > t.getY() && grid.getTile(getRow()-1,getColumn()).getLegalSpace())
        	y += speed;
    	else
        	stop();
	}
	//moves ghost down
	public void goDown(Grid grid, Tile t) {
		if(y >= t.getY())
    		y -= speed;
    	else if(y < t.getY() && grid.getTile(getRow()+1,getColumn()).getLegalSpace())
        	y -= speed;
    	else
        	stop();
	}
	//stops ghost movement
	public void stop(){
		dir = (int)(Math.random()*4);
	}
	//gets ghost column location
	public int getColumn(){
		return (int)(x + 112) / 8;
	}
	//gets ghost row location
	public int getRow(){
		return (int)(120 - y) / 8;
	}
	//draw ghost image
	public void draw(){
		StdDraw.picture(x, y, image, 6, 6);

	}
	//make ghosts blue
	public void makeBlue() {
	}
	//ghosts get eaten
	public void dead() {
	}
	//increase ghost speed
	public void makeFaster(){
		++speed;
	}
}