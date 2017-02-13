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

void setup() {
  size(500, 500, P3D);
  background(0);
  noFill();
  stroke(255);
  strokeWeight(2);

  for (int i = 0; i < AMOUNT; i++) {
    x[i] = int(random(-150, 150));
    y[i] = int(random(-150, 150));
    z[i] = int(random(-150, 150));
    x2[i] = int(random(-150, 150));
    y2[i] = int(random(-150, 150));
    z2[i] = int(random(-150, 150));
  }

}

void draw() {
  background(0,0.1);
  if (mousePressed == true) {
    for (int i = 0; i < AMOUNT; i++) {
        x[i] = int(random(-150, 150));
        y[i] = int(random(-150, 150));
        z[i] = int(random(-150, 150));
        x2[i] = int(random(-150, 150));
        y2[i] = int(random(-150, 150));
        z2[i] = int(random(-150, 150));
    }
  }
  translate(width/2, height/2);
  rotateY(mouseX / 200.0);
  rotateX(mouseY / 200.0);
  rotateZ((mouseY * mouseX) / 20000.0);

  for (int i = 0; i < AMOUNT; i++) {

    color1[i] = int(random(0, 255));
    color2[i] = int(random(0, 255));
    color3[i] = int(random(0, 255));
    stroke(color1[i],color2[i],color3[i]);
    //point(x[i], y[i], z[i]);
    line(x[i], y[i], z[i],x2[i], y2[i], z2[i]);
  }
}