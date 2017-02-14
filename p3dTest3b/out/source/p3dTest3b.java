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

public class p3dTest3b extends PApplet {

final int AMOUNT = 33;

int[] x = new int[AMOUNT];
int[] y = new int[AMOUNT];
int[] z = new int[AMOUNT];
int[] x2 = new int[AMOUNT];
int[] y2 = new int[AMOUNT];
int[] z2 = new int[AMOUNT];
int[] color1 = new int[AMOUNT];
int[] color2 = new int[AMOUNT];
int[] color3 = new int[AMOUNT];
int angle;

public void setup() {
  
  background(0);
  
  noStroke();
  //strokeWeight(2);
  angle = 0;

  for (int i = 0; i < AMOUNT; i++) {
    color1[i] = PApplet.parseInt(random(0, 255));
    color2[i] = PApplet.parseInt(random(0, 255));
    color3[i] = PApplet.parseInt(random(0, 255));
    x[i] = PApplet.parseInt(random(-250, 250));
    y[i] = PApplet.parseInt(random(-250, 250));
    z[i] = PApplet.parseInt(random(-250, 250));
    x2[i] = PApplet.parseInt(random(-150, 150));
    y2[i] = PApplet.parseInt(random(-150, 150));
    z2[i] = PApplet.parseInt(random(-150, 150));
  }

}

public void draw() {
  background(0);
  if (mousePressed == true) {

    pointLight(random(0, 255), random(0, 255), random(0, 255), random(-150, 150), random(-150, 150), random(-150, 150));
    for (int i = 0; i < AMOUNT; i++) {
        x[i] = PApplet.parseInt(random(-250, 250));
        y[i] = PApplet.parseInt(random(-250, 250));
        z[i] = PApplet.parseInt(random(-250, 250));
        x2[i] = PApplet.parseInt(random(-250, 250));
        y2[i] = PApplet.parseInt(random(-250, 250));
        z2[i] = PApplet.parseInt(random(-250, 250));
    color1[i] = PApplet.parseInt(random(0, 255));
    color2[i] = PApplet.parseInt(random(0, 255));
    color3[i] = PApplet.parseInt(random(0, 255));
    stroke(color1[i],color2[i],color3[i]);
    }
  }else{
      background(0);
  }
  angle +=1;
  if(angle > 360) angle = 0; 
  translate(width/2, height/2);
  rotateY(mouseX / 200.0f +radians(angle));
  //rotateX(mouseY / 200.0);
  rotateX(radians(angle));
  rotateZ((mouseY * mouseX) / 20000.0f + radians(angle));
ambientLight(51, 102, 126);
pointLight(51, 102, 126, 35, 40, 36);
  for (int i = 0; i < AMOUNT; i++) {
    fill(color1[i],color2[i],color3[i]);
    beginShape();
      vertex(x[i], y[i], z[i]);
      vertex(y2[i], z2[i], x[i]);
      vertex(z[i], z2[i], x2[i]);
      vertex(x2[i], x[i], z[i]);
    endShape();
    //point(x[i], y[i], z[i]);
    //line(x[i], y[i], z[i],x2[i], y2[i], z2[i]);
    //stroke(color1[i],color2[i],color3[i]);
  }
}
  public void settings() {  size(500, 500, P3D);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "p3dTest3b" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
