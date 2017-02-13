float rot=0;

void setup(){
  size(400,400,OPENGL);
}

void draw(){
  background(0);
  translate(width/2,height/2);
  ambientLight(61,31,31);
  pointLight(63, 127, 255, mouseX, mouseY, 200);
  rotateX(mouseY/50.0);
  rotateY(mouseX/50.0);
  sphere(150);
  //box(150,150,150);
  println(mouseX,mouseY);
}