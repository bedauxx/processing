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

public class p3dTest2 extends PApplet {

final int AMOUNT = 100;

int[] x = new int[AMOUNT];
int[] y = new int[AMOUNT];
int[] z = new int[AMOUNT];
int[] x2 = new int[AMOUNT];
int[] y2 = new int[AMOUNT];
int[] z2 = new int[AMOUNT];
int[] color1 = new int[AMOUNT];
int[] color2 = new int[AMOUNT];
int[] color3 = new int[AMOUNT];

public void setup() {
  
  background(0);
  noFill();
  stroke(255);
  strokeWeight(2);

  for (int i = 0; i < AMOUNT; i++) {
    x[i] = PApplet.parseInt(random(-150, 150));
    y[i] = PApplet.parseInt(random(-150, 150));
    z[i] = PApplet.parseInt(random(-150, 150));
    x2[i] = PApplet.parseInt(random(-150, 150));
    y2[i] = PApplet.parseInt(random(-150, 150));
    z2[i] = PApplet.parseInt(random(-150, 150));
  }

}

public void draw() {
  background(0,0.1f);
  if (mousePressed == true) {
    for (int i = 0; i < AMOUNT; i++) {
        x[i] = PApplet.parseInt(random(-150, 150));
        y[i] = PApplet.parseInt(random(-150, 150));
        z[i] = PApplet.parseInt(random(-150, 150));
        x2[i] = PApplet.parseInt(random(-150, 150));
        y2[i] = PApplet.parseInt(random(-150, 150));
        z2[i] = PApplet.parseInt(random(-150, 150));
    }
  }
  translate(width/2, height/2);
  rotateY(mouseX / 200.0f);
  rotateX(mouseY / 200.0f);
  rotateZ((mouseY * mouseX) / 20000.0f);

  for (int i = 0; i < AMOUNT; i++) {

    color1[i] = PApplet.parseInt(random(0, 255));
    color2[i] = PApplet.parseInt(random(0, 255));
    color3[i] = PApplet.parseInt(random(0, 255));
    stroke(color1[i],color2[i],color3[i]);
    //point(x[i], y[i], z[i]);
    line(x[i], y[i], z[i],x2[i], y2[i], z2[i]);
  }
}
  public void settings() {  size(500, 500, P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "p3dTest2" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
