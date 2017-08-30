import java.util.Random;

public class Fantomes {
	
	private int x;
	private int y;
	private int ancienneDirection;
	static int mouvement;
	private String couleur;
	private static boolean Nord, Sud, Est, Ouest;
	
	public int getAncienneDirection() {
		return ancienneDirection;
	}

	public void setAncienneDirection(int pancienneDirection) {
		ancienneDirection = pancienneDirection;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Fantomes(String pCouleur){
		
		this.couleur = pCouleur;
		
		
		if (this.couleur == "rouge"){
			this.x = 13;
			this.y = 14;
		}
		if (this.couleur == "vert"){
			this.x = 15;
			this.y = 14;
		}
		if (this.couleur == "bleu"){
			this.x = 17;
			this.y = 14;
		}
		this.ancienneDirection = 0;		
	}
	
	public static void deplacementF(Fantomes fantome){
		mouvement = 0;
		
		testMur(fantome);
		//Si le fantome se trouve à une certaine distance du joueur, il devient "intelligent"
		if ((Main.pacman.getX() - fantome.getX())*(Main.pacman.getX() - fantome.getX()) + (Main.pacman.getY() - fantome.getY())*(Main.pacman.getY() - fantome.getY()) < 36){
			if (Main.pacman.getY() - fantome.getY() < 0){
				if (Nord){
					fantome.y = fantome.y - 1;
					mouvement = 1;
				}
				else{
					if (Main.pacman.getX() - fantome.getX() < 0 && Ouest){
						fantome.x = fantome.x - 1;
						mouvement = 1;
					}
					else if (Main.pacman.getX() - fantome.getX() > 0 && Est){
						fantome.x = fantome.x + 1;
						mouvement = 1;
					}
					else{
						deplacementRandom(fantome);
					}
				}
			}
					
			else if (Main.pacman.getY() - fantome.getY() > 0){
				if (Sud){
					fantome.y = fantome.y + 1;
					mouvement = 1;
				}
				else{
					if (Main.pacman.getX() - fantome.getX() < 0 && Ouest){
						fantome.x = fantome.x - 1;
						mouvement = 1;
					}
					else if (Main.pacman.getX() - fantome.getX() > 0 && Est){
						fantome.x = fantome.x + 1;
						mouvement = 1;
					}
					else{
						deplacementRandom(fantome);
					}
				}
			}
		
			else{ //coordonées Y égales
				if (Main.pacman.getX() - fantome.getX() < 0 && Ouest){
					fantome.x = fantome.x - 1;
					mouvement = 1;	
				}
				else if (Main.pacman.getX() - fantome.getX() > 0 && Est){
					fantome.x = fantome.x + 1;
					mouvement = 1;
				}
				else {
					deplacementRandom(fantome);
				}
			}
		}
		else{ //sinon deplacement random
			deplacementRandom(fantome);
		}
		
		if (mouvement == 0){
			deplacementF(fantome);
		}
	}
	
	public static void deplacementRandom(Fantomes fantome) {
		Random y = new Random();
		
		int dep = y.nextInt(4);
		switch(dep){
		case 0: //deplacement en haut
			if (Labyrinthe.laby[fantome.y-1][fantome.x]==9) {
				fantome.y = fantome.y - 2;
				mouvement = 1;
			}
			else{
			if (Nord && fantome.getAncienneDirection() != 2 ) { //le fantome ne peux pas revenir sur ses pas
				fantome.y = fantome.y - 1;
				mouvement = 1;
				fantome.setAncienneDirection(0);//vers le haut
				} 
			}
			break;
		case 1: //deplacement a droite
			if (Est && fantome.getAncienneDirection() != 3){  
				if (fantome.x ==  29  && fantome.y == 14){
	            	fantome.x = 1 ;
	            	}
	            else{
				fantome.x = fantome.x + 1;
				}
				mouvement = 1;
				fantome.setAncienneDirection(1);
			}
			break;
		case 2: //deplacement en bas
			if (Sud && fantome.getAncienneDirection() != 0){
				fantome.y = fantome.y + 1;
				mouvement = 1;
				fantome.setAncienneDirection(2);
			}
			break;
		case 3: //deplacement a gauche
			if (Ouest && fantome.getAncienneDirection() != 1){
				if (fantome.x ==  1  && fantome.y == 14){
	            	fantome.x = 29 ;
	            	}
	            else{
				fantome.x = fantome.x - 1;
				}
				mouvement = 1;
				fantome.setAncienneDirection(3);
			}
			break;
		}
	}
	
	public static void testMur(Fantomes fantome){

		//test si les cases à cotés du joueur sont des murs ou vides
		Nord = false;
		Sud = false;
		Est = false;
		Ouest = false;
		
		if (fantome.x == 29 && fantome.y == 14){
            Est = true;}
		if (fantome.x == 1 && fantome.y == 14){
			Ouest = true;}
		
		if (Labyrinthe.laby[fantome.y + 1][fantome.x]==0 || Labyrinthe.laby[fantome.y + 1][fantome.x]==8 || Labyrinthe.laby[fantome.y + 1][fantome.x]==2){
			Sud = true;
			}
		if (Labyrinthe.laby[fantome.y - 1][fantome.x]==0 || Labyrinthe.laby[fantome.y - 1][fantome.x]==8 || Labyrinthe.laby[fantome.y - 1][fantome.x]==2){
			Nord = true;
		}
		if (Labyrinthe.laby[fantome.y][fantome.x + 1]==0 || Labyrinthe.laby[fantome.y][fantome.x + 1]==8 || Labyrinthe.laby[fantome.y][fantome.x + 1]==2){
			Est = true;
			}
		if (Labyrinthe.laby[fantome.y][fantome.x - 1]==0 || Labyrinthe.laby[fantome.y][fantome.x - 1]==8 || Labyrinthe.laby[fantome.y][fantome.x - 1]==2){
			Ouest = true;
		}
	}
}
