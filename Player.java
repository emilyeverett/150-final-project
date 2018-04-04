public class Player{
	private String name;

	public Player(){
		name = "";
	}
	//add letters to username
	public void addLetter(char l){
		name += l;
	}
	//delete letters from username
	public void subtractLetter(){
		name = name.substring(0,name.length()-1);
	}
	//return username
	public String getName(){
		return name;
	}
}