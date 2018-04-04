/**
 * Main application object.
 * Contains the core loop: process input, update, clear, draw, show.
 */

import java.io.*;


public class Game {

  /** All of the various states of the games. */
  public enum GameState {Title, Play, Win, Lose, Quit}; //enums are like integers. Except I
                                             //rename 0 to be Title, 1 to be
                                             //Play, and 2 to be Quit. You can
                                             //have as many state as you want.

  /** State of the application */
  GameState gameState; //Store the state of the game

  //////////////////////////////////////////////////////////////////////////////
  //SPECIFIC TO THIS APPLICATION. YOU MAY OR MAY NOT NEED THESE
  /** Last key pressed */
  char key;
  //////////////////////////////////////////////////////////////////////////////

  /**
   * Constructor to initialize instance variables.
   * Remember you can add whatever parameters you want, passed through GameMain
   * @param args Command-line arguments
   */
  public Game(String args[]) {
    gameState = GameState.Title;
  }

  /**
   * Basic game loop (process input, update, draw, show). This is like the real
   * main function.
   */
  public void run()
  throws FileNotFoundException, IOException{

    Pacman p = new Pacman(0,-68,"pacmanL.png");
    Blinky b = new Blinky("blinkyL.png");
    Pinky y = new Pinky("pinkyL.png");
    Inky i = new Inky("inkyL.png");
    Clyde c = new Clyde("clydeL.png");
    Grid grid = new Grid();
    Player player = new Player();
    IntroSong introSong = new IntroSong("pacman_beginning.wav");
    IntroSong wakaWaka = new IntroSong("pacman_chomp.wav");

    initialize(p,b,y,i,c,grid,player);

    while(gameState != GameState.Quit) {
      //Process any and all user input, i.e., change the internal state of the
      //game through external influence (user) (e.g., keyboard presses)
      processInput(p, b, y, i, c, grid, player, introSong);

      //update the internal state of the game automatically (e.g., physics)
      update(b,y,i,c,p, grid, wakaWaka);

      //draw the game
      draw(p, b, y, i, c, grid, player);

      //Show and pause
      show();
    }
  }

  /**
   * Initialization of the GUI for the game - Setup GUI canvas, scale, and
   * double buffering.
   */
  public void initialize(Pacman p, Blinky b, Pinky y, Inky i, Clyde c, Grid grid, Player player) throws FileNotFoundException{
    StdDraw.setCanvasSize(575, 700);
    StdDraw.setXscale(-112, 112);
    StdDraw.setYscale(-144, 144);
    StdDraw.enableDoubleBuffering();
  }

  /**
   * Process mouse and key presses - user input.
   * Note how to use mouse here in different ways. Click actions are defined by
   * a press and release of the left mouse button without moving the mouse in
   * between. Mouse press events can be used to determine if the left mouse
   * button is down. Mouse movement can also be tracked without pressing the
   * button. Keyboard button presses are also shown. Here you write code
   * corresponding to altering the state of the game based upon these actions.
   */
  public void processInput(Pacman p, Blinky b, Pinky y, Inky i, Clyde d, Grid grid, Player player, IntroSong song) 
  throws FileNotFoundException, IOException{

    //Only register if a click occured (click - mouse down and up without any
    //movement inbetween). Note here, we can do different responses to the mouse
    //click based on the gameState variable.
    if(StdDraw.hasNextMouseClicked()) {
      StdDraw.MouseClick m = StdDraw.nextMouseClicked(); //Make sure to always
                                                         //process mouseClick
      switch(gameState) {
        case Title:
          song.play();
          gameState = GameState.Play;
          break;
        default:
          break;
      }
    }
    if(p.getScore() == 24400)
      gameState = GameState.Win;
    else if(p.getLives() == 0)
      gameState = GameState.Lose;

    //Registering if a keyboard button is pressed. It is not designed to handle
    //holding keyboard buttons down, so you may get unexpected behavior if this
    //occurs. See me for a more detailed explanation.
    if(StdDraw.hasNextKeyTyped()) {
      key = StdDraw.nextKeyTyped(); //Make sure to always process the key
      if(gameState == GameState.Play) {
        if(key == 'q' || key == 'Q')
          gameState = GameState.Quit;
        if(key == 'w' || key == 'W') p.setDir('U');
        if(key == 'a' || key == 'A') p.setDir('L');
        if(key == 's' || key == 'S') p.setDir('D');
        if(key == 'd' || key == 'D') p.setDir('R');
      }
      else if(gameState == GameState.Title){
        if(key == '\b')
          player.subtractLetter();
        else
          player.addLetter(key);
      }
      else if(gameState == GameState.Win || gameState == GameState.Lose){
        if(key == 'q' || key == 'Q') gameState = GameState.Quit;
        if(key != 'q' && key != 'Q')
          gameState = GameState.Play;
      }
    }
  }

  /**
   * Update would be the simulation part only. Like applying gravity. Apply an
   * AI to a set of enemies. Anything related to changing the game without user
   * input.
   */
  public void update(Blinky b, Pinky y, Inky i, Clyde e, Pacman p, Grid grid, IntroSong wakaWaka) 
  throws FileNotFoundException, IOException{
    if(grid.getTile(p.getRow(), p.getColumn()) == grid.getTile(b.getRow(),b.getColumn()) ||
      grid.getTile(p.getRow(), p.getColumn()) == grid.getTile(y.getRow(),y.getColumn()) ||
      grid.getTile(p.getRow(), p.getColumn()) == grid.getTile(i.getRow(),i.getColumn()) ||
      grid.getTile(p.getRow(), p.getColumn()) == grid.getTile(e.getRow(),e.getColumn()))
        p.die();
    p.move(grid, wakaWaka);
    b.move(grid);
    y.move(grid);
    i.move(grid);
    e.move(grid);

  }

  /**
   * Clear resets the graphics. Add display of any background images here. Note
   * that with a switch on the game state you can vary the background.
   */
  public void clear() {
    StdDraw.clear(StdDraw.BLACK);
  }

  /**
   * Draw based on the gameState, i.e., call an appropriate draw function based
   * on the gameState variable.
   */
  public void draw(Pacman p, Blinky b, Pinky y, Inky i, Clyde d, Grid grid, Player player) 
  throws FileNotFoundException {
    //Start with a clean slate
    clear();

    //Draw the game based upon the screen it is on
    switch(gameState) {
      case Title:
        drawTitle(player);
        break;
      case Play:
        drawPlay(p, b, y, i, d, grid, player);
        break;
      case Win:
        drawWin(p);
      case Lose:
        drawLose(p, player);
      default:
        break;
    }
  }

  /**
   * Show and pause for 16 milliseconds. Aiming for 60 FPS. If your game is
   * extremely slow (can easily happen in Java) change the 16 to 50 or 100.
   */
  public void show() {
    StdDraw.show();
    StdDraw.pause(16);
  }

  /**
   * Title screen draw.
   */
  public void drawTitle(Player player) {
    StdDraw.setPenColor(StdDraw.WHITE);
    StdDraw.text(0, 75, "Enter username. Click to play Pac-Man.");
    StdDraw.text(0,15, "Enter Player Name: ");
    StdDraw.filledRectangle(0,-10,30,7);
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.text(0,-10,player.getName());
  }

  /**
   * Game play draw.
   */
  public void drawPlay(Pacman p, Blinky b, Pinky y, Inky i, Clyde d, Grid grid, Player player) {
    StdDraw.setPenColor(StdDraw.WHITE);
    StdDraw.text(-90, 135, "Score: " + p.getScore());
    StdDraw.text(75,135,"Player: " + player.getName());
    StdDraw.picture(0,-2, "maze.jpg", 220, 246);
    StdDraw.text(-90,-135, "Lives left: " + p.getLives());
    grid.draw();
    p.draw();
    b.draw();
    y.draw();
    i.draw();
    d.draw();
  }

  public void drawWin(Pacman p){
    StdDraw.clear(StdDraw.BLACK);
    StdDraw.text(0,0,"YOU WIN\nPress q to quit");
    p.setLives();
  }
  public void drawLose(Pacman p, Player player) throws FileNotFoundException{
    StdDraw.clear(StdDraw.BLACK);
    StdDraw.text(0,0,"YOU LOSE\nPress q to quit");
    p.setLives();
    char d = p.getDir();
    int r = p.getRow();
    int c = p.getColumn();
    PrintWriter pw = new PrintWriter("scoreboard.txt");
    pw.print("Score\n\n");
    pw.println(player.getName() + "   " + p.getScore());
    pw.println("Final Position: " + d + " row: " + r + " col: " + c);
    pw.flush();
    pw.close();
  }
}
