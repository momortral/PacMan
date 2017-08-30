import edu.princeton.cs.introcs.StdDraw;
import java.awt.Font;

public class Dessin {
	static Font font = new Font("New Times Roman", Font.BOLD, 40);
	//static Font début = new Font("");

	public static void dessinLabyrinthe(){
		
		
		for (int i=0;i<Labyrinthe.laby[0].length;i++){
			for (int j=0; j<Labyrinthe.laby.length;j++){
				switch (Labyrinthe.laby[30-j][i]){
				case 0: // case vide + pastille
					StdDraw.setPenColor(StdDraw.YELLOW);
					StdDraw.filledCircle(i,j,0.15);
					break;
				case 1: // case mur
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.filledSquare(i,j,0.51);
					break;
				case 2: // pac-man
					StdDraw.picture(i, j, "pacmangauche.jpg", 1,1);
					break;
				case 3: // case affichage
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.filledSquare(i, j, 0.5);
					break;
				case 8: // case vide
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.filledSquare(i,j,0.5);
					break;
				case 9: // case libération des fantomes
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.filledRectangle(i,j,0.5,0.1);
					break;				
				}
			}
			
		}
		StdDraw.setPenColor(StdDraw.WHITE);
		Font font = new Font("New Times Roman", Font.BOLD, 40);
		StdDraw.setFont(font);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(3.5, 13.5, "Score");
		StdDraw.text(3.5, 12.5, String.valueOf(0));
		StdDraw.text(26.5, 13.5, "Vies");
		StdDraw.text(26.5, 12.5, String.valueOf(3));
	}
	
public static void actuLabyrinthe(){
		
		for (int i=0;i<Labyrinthe.laby[0].length;i++){
			for (int j=0; j<Labyrinthe.laby.length;j++){
				switch (Labyrinthe.laby[30-j][i]){
				case 0:
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.filledSquare(i, j, 0.5);
					if (Main.fantomeB.getX() == i && Main.fantomeB.getY() == 30 - j){
						StdDraw.picture(i, j, "fantomeB.png", 1,1);}
					else if (Main.fantomeV.getX() == i && Main.fantomeV.getY() == 30 - j){
						StdDraw.picture(i, j, "fantomeV.png", 1,1);}
					else if (Main.fantomeR.getX() == i && Main.fantomeR.getY() == 30 - j){
						StdDraw.picture(i, j, "fantomeR.png", 1,1);}
					else{
						StdDraw.setPenColor(StdDraw.YELLOW);
						StdDraw.filledCircle(i,j,0.15);}								
					break;
				case 2:
					if (Pacman.getDirection() == 1){
						StdDraw.picture(i, j, "pacmanbas.jpg", 1,1);
					}
					if (Pacman.getDirection() == 2){
						StdDraw.picture(i, j, "pacmanhaut.jpg", 1,1);
					}
					if (Pacman.getDirection() == 3){
						StdDraw.picture(i, j, "pacmangauche.jpg", 1,1);
					}
					if (Pacman.getDirection() == 4){
						StdDraw.picture(i, j, "pacmandroite.jpg", 1,1);
					}
					break;
				case 3:
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.filledSquare(i, j, 0.5);
					break;
				case 8:
					if (Main.fantomeB.getX() == i && Main.fantomeB.getY() == 30 - j){
						StdDraw.picture(i, j, "fantomeB.png", 1,1);}
					else if (Main.fantomeV.getX() == i && Main.fantomeV.getY() == 30 - j){
						StdDraw.picture(i, j, "fantomeV.png", 1,1);}
					else if (Main.fantomeR.getX() == i && Main.fantomeR.getY() == 30 - j){
						StdDraw.picture(i, j, "fantomeR.png", 1,1);}
					else{
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.filledSquare(i,j,0.5);}
					break;
				case 9:
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.filledRectangle(i, j, 0.5, 0.1);
					break;				
				}
			}			
		}
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.setFont(font);
		StdDraw.text(3.5, 13.5, "Score");
		StdDraw.text(3.5, 12.3, String.valueOf(Main.points));
		StdDraw.text(26.5, 13.5, "Vies");
		StdDraw.text(26.5, 12.3, String.valueOf(Main.nbVies));
	}
}
