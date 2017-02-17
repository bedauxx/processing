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

public class sp extends PApplet {

public void setup(){
  
}

public void draw(){
  background(0, 15, 30);
  
  translate(width/2, height/2, 0);
  rotateX(frameCount*0.01f);
  rotateY(frameCount*0.01f);
  
  //println("frameCount: "+frameCount);


  // \u4e00\u3064\u524d\u306e\u5ea7\u6a19\u3092\u683c\u7d0d\u3059\u308b
  float lastX = 0, lastY = 0, lastZ = 0;
  float radius = 200;
  float s = 0, t = 0;


  while(s <= 180){
    float radianS = radians(s);
    float radianT = radians(t);
    // \u73fe\u5728\u306e\u5ea7\u6a19\u3092\u66f4\u65b0
    float x = radius * sin(radianS) * cos(radianT);
    float y = radius * sin(radianS) * sin(radianT);
    float z = radius * cos(radianS);

//println(sin(frameCount));

float rnd = random(1);
  
  pushMatrix();  
  /*
  applyMatrix(1.0,  0.0, 0.0, 0.0,  
              0.0, -1.0, 2.0, 0.0,  
              0.0,  -3.0, -1.0, 0.0,  
              0.0,  0.0, 0.0, 1.0); 
  */
  applyMatrix(1.0f,  0.0f, 0.0f, 0.0f,  
              0.0f, -1.0f, 0.0f, 0.0f,  
              sin(frameCount/20),  0.0f, -1.0f, 0.0f,  
              0.0f,  0.0f, 0.0f, 1.0f); 
if(mousePressed==true){
    scale( sin(s/50) + sin(frameCount*0.01f));   
}else{
    scale( sin(s/50) * sin(frameCount*0.01f));  
}
    float col1 = random(255);
    float col2 = random(255);
    float col3 = random(255);

    stroke(col1, col2, col3);
    if(lastX != 0){
      strokeWeight(1);
      // \u73fe\u5728\u306e\u5ea7\u6a19\u304b\u3089\u4e00\u3064\u524d\u306e\u5ea7\u6a19\u306b\u7dda\u3092\u5f15\u304f
      line(x, y, z, lastX, lastY, lastZ);
    }
    strokeWeight(15);
    point(x, y, z);
    


    // \u4e00\u3064\u524d\u306e\u5ea7\u6a19\u3092\u66f4\u65b0
    lastX = x;
    lastY = y;
    lastZ = z;
    

    popMatrix();
    // s\u3068t\u3092\u540c\u6642\u306b\u66f4\u65b0
    s++;
    t+=10;
  }
}
  public void settings() {  size(960, 540, P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sp" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
