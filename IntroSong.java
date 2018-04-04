import java.io.*;
import sun.audio.*;


public class IntroSong{
  private String file;

  public IntroSong(String f){
  	this.file = f;
  }
  public void play() throws FileNotFoundException, IOException{
    // open the sound file as a Java input stream
    InputStream in = new FileInputStream(this.file);

    // create an audiostream from the inputstream
    AudioStream audioStream = new AudioStream(in);

    // play the audio clip with the audioplayer class
    AudioPlayer.player.start(audioStream);
  }
}
