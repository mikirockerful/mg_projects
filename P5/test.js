function setup(){
createCanvas(windowWidth,windowHeight);
}

function windowResized() {
  resizeCanvas(windowWidth, windowHeight);
}

function draw(){
var r=0;
var g=0;
var b=0;
 if (mouseIsPressed) {
 	r=Math.random()*255;
 	g=Math.random()*255;
 	b=Math.random()*255;
 	var c = color(r,g,b)
    fill(c);
    ellipse(mouseX, mouseY, 80, 80);
  }
}