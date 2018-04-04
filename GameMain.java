/**
 * Basic main function. Keep main simple. Do not overload it with much. Recall
 * that you can always have the Game constructor take args[0] or even all of
 * args as a parameter.
 */

import java.io.*;


public class GameMain {
  public static void main(String[] args) throws FileNotFoundException, IOException {
    Game g = new Game(args); //Make a new application (mine is just called Game)
    g.run();                 //Run the application
    System.exit(0);          //Clean exit after finished execution
  }
}
