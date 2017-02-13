float rot=0;

void setup(){
  size(400,400,OPENGL);
}

void draw(){
  background(0);
  translate(width/2,height/2);
  rotateX(mouseY/50.0);
  rotateY(mouseX/50.0);
  box(150,150,150);
  println(mouseX,mouseY);
}