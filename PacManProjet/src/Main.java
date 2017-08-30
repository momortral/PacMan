import java.awt.Font;
import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

//plugin egit pour gerer git sur eclipse directement
//tuto sur OC pour l'installation


public class Main {
	static Fantomes fantomeR;
	static Fantomes fantomeB;
	static Fantomes fantomeV;
	static Pacman pacman;
	private static boolean Victoire = false;
	private static boolean Defaite = false;
	private static boolean Rejouer = true;
	private static int caseVide;
	static int nbVies = 3;
	static int points;
	static int Score;
	
	public static void pageAccueil(){
		StdDraw.setCanvasSize(900,1000);
		StdDraw.setXscale(0,30);
		StdDraw.setYscale(0,33);
		StdDraw.clear(StdDraw.BLACK);
		
		while(StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE)!= true){				
				StdDraw.setFont(Dessin.font);
				StdDraw.picture(15, 20, "pacman.jpg",12,12);
				StdDraw.setPenColor(StdDraw.RED);
				StdDraw.text(15, 12, "Voulez-vous jouer ? Si oui, appuyez sur entrée");
				StdDraw.text(15, 10, "Sinon, appuyez sur Echap");
				StdDraw.text(15, 7, "Utilisez les flèches du clavier pour jouer");
				StdDraw.show(10);
				if (StdDraw.isKeyPressed(KeyEvent.VK_ENTER) == true){
					break;
				}
				if (StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE) == true){
					Rejouer = false;
					break;
				}
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		pageAccueil();
		
		StdDraw.setCanvasSize(900,900);
		StdDraw.setXscale(0,30);
		StdDraw.setYscale(0,30);
		StdDraw.clear(StdDraw.BLACK);
		pacman = new Pacman();
		fantomeR = new Fantomes("rouge");
		fantomeV = new Fantomes("vert");
		fantomeB = new Fantomes("bleu");
		
		while(!Victoire && !Defaite && Rejouer){
			StdDraw.enableDoubleBuffering();
			Dessin.dessinLabyrinthe();
			StdDraw.show(200);
			
			pacman.deplacement();
			Fantomes.deplacementF(fantomeR);
			Fantomes.deplacementF(fantomeV);
			Fantomes.deplacementF(fantomeB);
			
			testVictoire();
			testDefaite(pacman);
			
			Dessin.actuLabyrinthe();
			StdDraw.show();
		
			StdDraw.setPenRadius(100);
			Font font = new Font("Rockwell Extra Bold", Font.BOLD, 40);
			StdDraw.setFont(font);
			if (Victoire){
				StdDraw.clear(StdDraw.WHITE);
				StdDraw.setPenColor(StdDraw.RED);
				StdDraw.picture(15, 20.5, "victoire.jpg", 27,27);
				StdDraw.text(15, 6.5, "Bravo! Vous avez gagné!!");
				StdDraw.text(15,5,"Avec " + nbVies + " vies!");
				StdDraw.text(15, 3.5, "Entrée pour rejouer");
				StdDraw.show();
				while(Victoire){
					if (StdDraw.isKeyPressed(KeyEvent.VK_ENTER) == true){
						Victoire = false;
						Labyrinthe.laby = Labyrinthe.resetLaby;
						pacman.setX(15); pacman.setY(23);
						fantomeV.setX(15); fantomeV.setY(14);
						fantomeB.setX(17); fantomeB.setY(14);
						fantomeR.setX(13); fantomeR.setY(14);
						Labyrinthe.laby[pacman.getY()][pacman.getX()]=2;
						nbVies = 3;
						StdDraw.clear(StdDraw.BLACK);
					}
					if (StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE) == true){
						Rejouer = false;
					}
				}
			}			
			if (Defaite){
				StdDraw.clear(StdDraw.WHITE);
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.picture(15, 20, "defaite.png", 25,25);
				StdDraw.text(15, 9, "Vous avez perdu...");
				StdDraw.text(15, 7.5, "Votre score est de " + points + ", dommage!");
				StdDraw.text(15, 3.5, "Entrée pour rejouer");
				StdDraw.show();
				while(Defaite){
					if (StdDraw.isKeyPressed(KeyEvent.VK_ENTER) == true){
						Defaite = false;
						Labyrinthe.laby = Labyrinthe.resetLaby;
						pacman.setX(15); pacman.setY(23);
						fantomeV.setX(15); fantomeV.setY(14);
						fantomeB.setX(17); fantomeB.setY(14);
						fantomeR.setX(13); fantomeR.setY(14);
						Labyrinthe.laby[pacman.getY()][pacman.getX()]=2;
						nbVies = 3;
						StdDraw.clear(StdDraw.BLACK);
					}
					if (StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE) == true){
						Rejouer = false;
					}
				}
			}	
		}
	}
	
	public static void testVictoire(){
		//Test toutes les cases du jeu, si aucune des cases contient un fromage, fin de la partie
		caseVide = 0;
		for (int i=0;i<Labyrinthe.laby[0].length;i++){
			for (int j=0; j<Labyrinthe.laby.length;j++){
				if (Labyrinthe.laby[j][i] == 8){
					caseVide = caseVide + 1;
				}
			}
		}
		if (caseVide == 325){ //il y a 325 cases au total (80 cases vides de base pour tester) moins case joueur et fantome
			Victoire = true;
		}
		points = (caseVide-79) * 10;
	}
	
	public static void testDefaite(Pacman joueur){
		boolean reset = false;
		
		if(joueur.getY() == fantomeB.getY() && joueur.getX() == fantomeB.getX()){
			reset = true;
		}
		else if(joueur.getY() == fantomeR.getY() && joueur.getX() == fantomeR.getX()){
			reset = true;
		}
		else if(joueur.getY() == fantomeV.getY() && joueur.getX() == fantomeV.getX()){
			reset = true;
		}
		
		if (reset){
			nbVies = nbVies - 1;
			Labyrinthe.laby[joueur.getY()][joueur.getX()]=8;
			joueur.setX(15); joueur.setY(23);
			fantomeV.setX(15); fantomeV.setY(14);
			fantomeB.setX(17); fantomeB.setY(14);
			fantomeR.setX(13); fantomeR.setY(14);
			Labyrinthe.laby[joueur.getY()][joueur.getX()]=2;
		}
		
		if (nbVies == 0){
			Defaite = true;
		}
		
	}
}