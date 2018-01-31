import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;


public class Enemigo {
	int x;
	int y;
	int vx;
	int vy;
	int xfinal;
	int yfinal;
	public Enemigo(){
		posicioninicial();
		this.vx=10;
		this.vy=10;
	}
	public void pintar(Graphics g){
		g.setColor(new Color(0,0,255));
		g.fillOval(this.x, this.y, 10, 10);
		
	}
	public void mover(ArrayList<Rectangulos> listarect){
		xfinal=this.x+vx;
		yfinal=this.y+vy;
			if (this.x<xfinal){
				if (this.y<yfinal){
					this.x=this.x+1;
					

						while (this.x<xfinal){
							if (this.colisionrect(listarect)){
								this.x=this.x-1;
								this.vx=-10;
								break;
							}
							this.x=this.x+1;
						}
						this.y=this.y+1;
						while (this.y<yfinal){
							if (this.colisionrect(listarect)){
								this.y=this.y-1;
								this.vy=-10;
								break;
							}
							this.y=this.y+1;
						}

				}else{
					this.x=this.x+1;
					
					while (this.x<xfinal){
						if (this.colisionrect(listarect)){
							this.x=this.x-1;
							this.vx=-10;
							break;
						}
						this.x=this.x+1;
					}
					this.y=this.y-1;
					while (this.yfinal<this.y){
						if (this.colisionrect(listarect)){
							this.y=this.y+1;
							this.vy=10;
							break;
						}
						this.y=this.y-1;
					}
					
				}
			}else{
				if (this.y<yfinal){
					this.x=this.x-1;
					
					
					while (this.xfinal<this.x){
						if (this.colisionrect(listarect)){
							this.x=this.x+1;
							this.vx=+10;
							break;
						}
						this.x=this.x-1;
					}
					this.y=this.y+1;
					while (this.y<yfinal){
						if (this.colisionrect(listarect)){
							this.y=this.y-1;
							this.vy=-10;
							break;
						}
						this.y=this.y+1;
					}
					
				}else{
					this.x=this.x-1;
					
					while (this.xfinal<this.x){
						if (this.colisionrect(listarect)){
							this.x=this.x+1;
							this.vx=10;
							break;
						}
						this.x=this.x-1;
					}
					this.y=this.y-1;
					while (this.yfinal<this.y){
						if (this.colisionrect(listarect)){
							this.y=this.y+1;
							this.vy=10;
							break;
						}
						this.y=this.y-1;
					}
				}
			}
			
		
	}
	public void posicioninicial(){
		this.x=((int)(Math.random()*763+0.5))+6;
		this.y=((int)(Math.random()*550+0.5))+6;
	}
	public boolean colisionrect(ArrayList<Rectangulos> listarect){
		for (int i=0;i<listarect.size();i++){
			Rectangle rect1= new Rectangle(listarect.get(i).getx(),listarect.get(i).gety(),listarect.get(i).getwidth(),listarect.get(i).getheight());
			Rectangle rect2= new Rectangle(this.x+5,this.y+5,1,1);
			if (rect1.intersects(rect2)){
				return true;
			}
		}
		return false;
	}
	public int getx(){
		return this.x;
	}
	public int gety(){
		return this.y;
	}
}
