var angle=0.6;
var step=0.7;
function setup(){
createCanvas(800,800);
frameRate(1);
}

function draw(){
background(51);
translate(400, height);
branch(200);
}

function branch(len){
	stroke(Math.random()*255,Math.random()*255,Math.random()*255);

	line(0, 0, 0, -len);
	translate(0,-len);
	if (len > 2){
	push();
	rotate(angle);
	branch(len*step);
	pop();
	push();
	rotate(-angle);
	branch(len*step);
	pop();
}


}