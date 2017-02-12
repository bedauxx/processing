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

public class p3dTest extends PApplet {

float rot=0;

public void setup(){
  
}

public void draw(){
  background(0);
  translate(width/2,height/2);
  rotateY(rot);
  box(150,150,150);
  rot += 0.06f;
}
  public void settings() {  size(400,400,P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "p3dTest" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
