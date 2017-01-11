void setup(){
  size(500,500);
  smooth(8);
  background(255);
}

void draw(){

  float rnd1 = random(125);
  float rnd2 = random(200,255);
  float rnd3 = random(10,50);
  float rnd4 = random(10,50);

  fill(rnd2);
  ellipse(mouseX + rnd3,mouseY+rnd4,rnd1,rnd1);
}
