import java.awt.event.KeyEvent;
import edu.princeton.cs.introcs.StdDraw;

public class Pacman {
	private int x;
	private int y;
	private int ancienX;
	private int ancienY;
	public boolean Nord, Ouest, Sud, Est;
	public int mouvement;
	private static int direction;
	
	public static int getDirection() {
		return direction;
	}

	public static void setDirection(int direction) {
		Pacman.direction = direction;
	}

	public Pacman(){
		this.x = 15;
		this.y = 23;
		Labyrinthe.laby[y][x] = 2;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void deplacement(){
		ancienX = this.x;
		ancienY = this.y;
		mouvement = 0;
		testMur();
		
		if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN) && Sud ) { //Si la mouvement est activée et que la case en dessous n'est pas un mur
            this.y = this.y + 1;
            mouvement = 1;
            setDirection(1);
        }
		else if (StdDraw.isKeyPressed(KeyEvent.VK_UP) && Nord) {
            this.y = this.y - 1;
            mouvement = 1;
            setDirection(2);
        }
		else if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT) && Ouest) {
            if (this.x ==  1  && this.y == 14){
            	this.x = 29 ;}
            else{
			this.x = this.x - 1;}
            mouvement = 1;
            setDirection(3);
        }
		else if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT) && Est) {
            if (this.x == 29 && this.y == 14){
            	this.x = 1;}
            else{
			this.x = this.x + 1;}
            mouvement = 1; 
            setDirection(4);
        }
        
        if (mouvement == 1){
        	Labyrinthe.laby[this.y][this.x] = 2;
        	Labyrinthe.laby[ancienY][ancienX] = 8;
        }
	}
	
	public void testMur(){

		//test si les cases à cotés du joueur sont des murs ou vides
		Nord = false;
		Sud = false;
		Est = false;
		Ouest = false;
		
		if (this.x == 29 && this.y == 14){
            Est = true;}
		if (this.x == 1 && this.y == 14){
			Ouest = true;}
		
		if (Labyrinthe.laby[this.y + 1][this.x]==0 || Labyrinthe.laby[this.y + 1][this.x]==8 /*|| Labyrinthe.laby[this.y + 1][this.x]==9*/){
			Sud = true;
			}
		if (Labyrinthe.laby[this.y - 1][this.x]==0 || Labyrinthe.laby[this.y - 1][this.x]==8){
			Nord = true;
		}
		if (Labyrinthe.laby[this.y][this.x + 1]==0 || Labyrinthe.laby[this.y][this.x + 1]==8 /*|| Labyrinthe.laby[this.y][this.x + 1]==9*/){
			Est = true;
			}
		if (Labyrinthe.laby[this.y][this.x - 1]==0 || Labyrinthe.laby[this.y][this.x - 1]==8){
			Ouest = true;
		}
	}
}
