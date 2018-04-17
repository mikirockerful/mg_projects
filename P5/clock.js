function setup(){
createCanvas(400,400);
angleMode(DEGREES);
}

function draw(){
background(0);
translate(200,200);


var hr=hour();
var mn=minute();
var sc=second();

strokeWeight(1);

stroke(255);
text(hr + ':' + mn + ':' + sc, -25, 100);
var r=165;

for (i=1;i<=12; i++){
var angle=-i*30;
var x=r*sin(angle);
var y=r*cos(angle);
text(i, x-5, -y+2);
}
rotate(-90);


var color1=color(255,200,123);
var color2=color(100,100,255);
var color3=color(200,80,150);



//noFill();
//strokeWeight(8);
//stroke(255);
//ellipse(200,200,300,300);

strokeWeight(4);
noFill();

let end3=map(hr % 12, 0, 12, 0, 360);
stroke(color3);
arc(0,0,260,260,  0, -end3);

push();
rotate(-end3);
stroke(color3);
line(0,0, 70, 0);
pop();

let end2=map(mn, 0, 60, 0, 360);
stroke(color2);
arc(0,0,280,280,  0, -end2);

push();
rotate(-end2);
stroke(color2);
line(0,0, 90, 0);
pop();

let end=map(sc, 0, 60, 0, 360);
stroke(color1);
arc(0,0,300,300, 0, -end);

push();
rotate(-end);
stroke(color1);
line(0,0, 110, 0);
pop();

}