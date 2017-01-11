import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class test extends PApplet {

public void setup(){
  
  
  background(255);
}

public void draw(){

  float rnd1 = random(125);
  float rnd2 = random(200,255);
  float rnd3 = random(10,50);
  float rnd4 = random(10,50);

  fill(rnd2);
  ellipse(mouseX + rnd3,mouseY+rnd4,rnd1,rnd1);
}
  public void settings() {  size(500,500);  smooth(8); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "test" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
