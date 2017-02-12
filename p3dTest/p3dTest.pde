float rot=0;

void setup(){
  size(400,400,P3D);
}

void draw(){
  background(0);
  translate(width/2,height/2);
  rotateY(rot);
  box(150,150,150);
  rot += 0.06;
}