public class Tile {
	private boolean legalSpace, hasFood;
	private double x,y;

	public Tile(double x, double y, boolean legalSpace, boolean hasFood) {
		this.x = x;
		this.y = y;
		this.legalSpace = legalSpace;
		this.hasFood = hasFood;
	}
	//return x position
	public double getX(){return x;}
	//return y position
	public double getY(){return y;}
	//accessor for legalSpace
	public boolean getLegalSpace(){return legalSpace;}
	//accessor for hasFood
	public boolean getHasFood(){return hasFood;}
	//changes space to false if food is eaten
	public void setHasFood(){hasFood = false;}

}