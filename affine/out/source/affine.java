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

public class affine extends PApplet {

MouseCamera mouseCamera;
public void setup() {
  
  mouseCamera = new MouseCamera(800.0f, 0, 0, (height/2.0f)/tan(PI*30.0f/180.0f), 0, 0, 0, 0, 1, 0); // MouseCamera\u306e\u751f\u6210
  draw();
}
public void draw() {
    mouseCamera.update(); // MouseCamera\u306e\u30a2\u30c3\u30d7\u30c7\u30fc\u30c8
    background(255);
    sphere(100);
    torus(250, 50, 60, 30);
}
public void torus(float R, float r, int countS, int countT) {
    for (int s=0; s<countS; s++) {
        float theta1 = map(s, 0, countS, 0, 2*PI);
        float theta2 = map(s+1, 0, countS, 0, 2*PI);
        beginShape(TRIANGLE_STRIP);
        // beginShape(QUAD_STRIP);
        for (int t=0; t<=countT; t++) {
            float phi = map(t, 0, countT, 0, 2*PI);
            vertex((R+r*cos(phi))*cos(theta1), (R+r*cos(phi))*sin(theta1), r*sin(phi));
            vertex((R+r*cos(phi))*cos(theta2), (R+r*cos(phi))*sin(theta2), r*sin(phi));
        }
        endShape();
    }
}
// \u30de\u30a6\u30b9\u64cd\u4f5c\u306b\u5fdc\u3058\u305fMouseCamera\u306e\u95a2\u6570\u3092\u547c\u3073\u51fa\u3059
public void mousePressed() {
    mouseCamera.mousePressed();
}
public void mouseDragged() {
    mouseCamera.mouseDragged();
}
public void mouseWheel(MouseEvent event) {
    mouseCamera.mouseWheel(event);
}
class MouseCamera {
    float eyeX, eyeY, eyeZ;
    float centerX, centerY, centerZ;
    float upX, upY, upZ;
    float radius; // \u4eee\u60f3\u7684\u306a\u7403\u306e\u534a\u5f84
    float[][] matrix; // \u884c\u5217
    PVector preVector; // PVector\u306fProcessing\u306e\u7d44\u307f\u8fbc\u307f\u30af\u30e9\u30b9
    MouseCamera(float radius) {
        this(radius, width/2.0f, height/2.0f, (height/2.0f)/tan(PI*30.0f/180.0f), width/2.0f, height/2.0f, 0, 0, 1, 0);
    }
    MouseCamera(float radius, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        this.radius = radius;
        this.eyeX = eyeX;
        this.eyeY = eyeY;
        this.eyeZ = eyeZ;
        this.centerX = centerX;
        this.centerY = centerY;
        this.centerZ = centerZ;
        this.upX = upX;
        this.upY = upY;
        this.upZ = upZ;
        matrix = getIdentityMatrix();
    }
    // \u6bce\u30d5\u30ec\u30fc\u30e0\u51e6\u7406
    public void update() {
        beginCamera();
        camera(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        applyMatrix(matrix[0][0], matrix[0][1], matrix[0][2], matrix[0][3],
                    matrix[1][0], matrix[1][1], matrix[1][2], matrix[1][3],
                    matrix[2][0], matrix[2][1], matrix[2][2], matrix[2][3],
                    matrix[3][0], matrix[3][1], matrix[3][2], matrix[3][3]);
        endCamera();
    }
    // \u30af\u30ea\u30c3\u30af\u3057\u305f\u3068\u304d\u306b\u547c\u3073\u51fa\u3059\u95a2\u6570
    public void mousePressed() {
        switch(mouseButton) {
            case RIGHT: { // \u53f3\u30dc\u30bf\u30f3\u3092\u30af\u30ea\u30c3\u30af\u3092\u3057\u305f\u3068\u304d\u306e\u51e6\u7406
                matrix = getIdentityMatrix();
            } break;
            case LEFT: { // \u5de6\u30dc\u30bf\u30f3\u3092\u30af\u30ea\u30c3\u30af\u3092\u3057\u305f\u3068\u304d\u306e\u51e6\u7406
                preVector = mouseOnSphere(mouseX-width/2, mouseY-height/2);
            } break;
            case CENTER: { // \u4e2d\u30dc\u30bf\u30f3\u3092\u30af\u30ea\u30c3\u30af\u3057\u305f\u3068\u304d\u306e\u51e6\u7406
                preVector = new PVector(mouseX-width/2, mouseY-height/2);
            } break;
        }
    }
    // \u30c9\u30e9\u30c3\u30b0\u3057\u305f\u3068\u304d\u306b\u547c\u3073\u51fa\u3059\u95a2\u6570
    public void mouseDragged() {
        switch(mouseButton) {
            case LEFT: { // \u5de6\u30dc\u30bf\u30f3\u3092\u30c9\u30e9\u30c3\u30b0\u3057\u305f\u3068\u304d\u306e\u51e6\u7406
                PVector v = mouseOnSphere(mouseX-width/2, mouseY-height/2);
                matrix = mult(getRotationMatrix(preVector, v), matrix);
                preVector = v;
            } break;
            case CENTER: { // \u4e2d\u30dc\u30bf\u30f3\u3092\u30c9\u30e9\u30c3\u30b0\u3057\u305f\u3068\u304d\u306e\u51e6\u7406
                PVector v = new PVector(mouseX-width/2, mouseY-height/2);
                matrix = mult(getTranslationMatrix(preVector, v), matrix);
                preVector = v;
            } break;
        }
    }
    // \u30de\u30a6\u30b9\u30db\u30a4\u30fc\u30eb\u3092\u52d5\u304b\u3057\u305f\u3068\u304d\u306b\u547c\u3073\u51fa\u3059\u95a2\u6570
    public void mouseWheel(MouseEvent event) {
        matrix = mult(getScaleMatrix(event.getCount()), matrix);
    }
    // \u5358\u4f4d\u884c\u5217\u306e\u53d6\u5f97
    public float[][] getIdentityMatrix() {
        return new float[][] {{1.0f, 0.0f, 0.0f, 0.0f},
                              {0.0f, 1.0f, 0.0f, 0.0f},
                              {0.0f, 0.0f, 1.0f, 0.0f},
                              {0.0f, 0.0f, 0.0f, 1.0f}};
    }
    // \u56de\u8ee2\u884c\u5217\u306e\u53d6\u5f97
    public float[][] getRotationMatrix(PVector v1, PVector v2) {
        PVector v = v1.cross(v2).normalize(); // \u56de\u8ee2\u8ef8
        float c = v1.dot(v2); // cos
        float s = v1.cross(v2).mag(); // sin
        return new float[][] {{c + v.x*v.x*(1-c), v.x*v.y*(1-c) - v.z*s, v.x*v.z*(1-c) + v.y*s, 0.0f},
                              {v.y*v.x*(1-c) + v.z*s, c + v.y*v.y*(1-c), v.y*v.z*(1-c) - v.x*s, 0.0f},
                              {v.z*v.x*(1-c) - v.y*s, v.z*v.y*(1-c) + v.x*s, c + v.z*v.z*(1-c), 0.0f},
                              {0.0f, 0.0f, 0.0f, 1.0f}};
    }
    // \u5e73\u884c\u79fb\u52d5\u884c\u5217\u306e\u53d6\u5f97
    public float[][] getTranslationMatrix(PVector v1, PVector v2) {
        return new float[][] {{1.0f, 0.0f, 0.0f, v2.x-v1.x},
                              {0.0f, 1.0f, 0.0f, v2.y-v1.y},
                              {0.0f, 0.0f, 1.0f, v2.z-v1.z},
                              {0.0f, 0.0f, 0.0f, 1.0f}};
    }
    // \u62e1\u5927\u7e2e\u5c0f\u884c\u5217\u306e\u53d6\u5f97
    public float[][] getScaleMatrix(float wheelCount) {
        float temp = 10.0f; // wheelCount\u306e\u5024\u304c\u5927\u304d\u3044\u306e\u3067\u5b9a\u6570\u3067\u5272\u308b
        return new float[][] {{exp(-wheelCount/temp), 0.0f, 0.0f, 0.0f},
                              {0.0f, exp(-wheelCount/temp), 0.0f, 0.0f},
                              {0.0f, 0.0f, exp(-wheelCount/temp), 0.0f},
                              {0.0f, 0.0f, 0.0f, 1.0f}};
    }
    // \u30de\u30a6\u30b9\u306e\u5ea7\u6a19\u304b\u3089\u7403\u9762\u4e0a\u306e\u4f4d\u7f6e\u30d9\u30af\u30c8\u30eb\u3092\u53d6\u5f97\u3059\u308b
    public PVector mouseOnSphere(float x, float y) {
        float _x = x/radius;
        float _y = y/radius;
        PVector res = new PVector(_x, _y, 0.0f);
        if (_x*_x + _y*_y > 1.0f) {
            res.normalize();
        } else {
            res.z = sqrt(1.0f - _x*_x - _y*_y);
        }
        return res;
    }
    // \u884c\u5217\u306e\u7a4d (m1 * m2)
    public float[][] mult(float[][] m1, float[][] m2) {
        assert(m1[0].length == m2.length);
        float[][] res = new float[m1.length][m2[0].length];
        for (int i=0; i<m1.length; i++) {
            for (int j=0; j<m2[0].length; j++) {
                float sum = 0;
                for (int k=0; k<m1[0].length; k++) {
                    sum += m1[i][k]*m2[k][j];
                }
                res[i][j] = sum;
            }
        }
        return res;
    }
}
  public void settings() {  size(800,800,P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "affine" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
