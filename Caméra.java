/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2_ro;
import ilog.concert.*;
import ilog.cplex.*;

public class Caméra {
        private static boolean mAdj[][];
	private int n ;
        public Caméra(int noeud) { //pour creer une graphe et une matrice d'adjacent
		this.n  = noeud;
		mAdj = new boolean[noeud][noeud];
	}
	public static void Creer_Arete(int i, int j) { //pour creer les arrete entre les emplacement 
		mAdj[i-1][j-1] = true;
	}

	public static boolean Verifie_adjacent(int i, int j) { //verifier est-ce-que l'ensemble (i,j) adjacent
		return mAdj[i][j];
	}
	public static void main(String[] args) {
              // creation une graphe G et matrice d'adjacence
		Caméra G = new Caméra(49);
                // ajouter des arretes entre les emplacements 
		G.Creer_Arete(1, 3);		
                G.Creer_Arete(1, 2);
		G.Creer_Arete(2, 41);
		G.Creer_Arete(2, 39);
		G.Creer_Arete(3, 11);
		G.Creer_Arete(4, 5);
		G.Creer_Arete(4, 6);
		G.Creer_Arete(4, 9);
		G.Creer_Arete(6, 7);
		G.Creer_Arete(6, 8);
		G.Creer_Arete(9, 10);
		G.Creer_Arete(11, 21);
		G.Creer_Arete(12, 13);
		G.Creer_Arete(12, 15);
		G.Creer_Arete(13, 14);
		G.Creer_Arete(14, 15);
		G.Creer_Arete(14, 18);
		G.Creer_Arete(15, 19);
		G.Creer_Arete(16, 20);
		G.Creer_Arete(17, 18);
		G.Creer_Arete(18, 19);
		G.Creer_Arete(19, 20);
		G.Creer_Arete(20, 21);
		G.Creer_Arete(21, 22);
		G.Creer_Arete(22, 25);
		G.Creer_Arete(22, 23);
		G.Creer_Arete(23, 32);
		G.Creer_Arete(24, 25);
		G.Creer_Arete(25, 26);
		G.Creer_Arete(25, 30);
		G.Creer_Arete(26, 27);
		G.Creer_Arete(26, 28);
		G.Creer_Arete(28, 29);
		G.Creer_Arete(30, 31);
		G.Creer_Arete(31, 32);
		G.Creer_Arete(31, 33);
		G.Creer_Arete(32, 38);
		G.Creer_Arete(33, 34);
		G.Creer_Arete(33, 37);
		G.Creer_Arete(34, 35);
		G.Creer_Arete(35, 36);
		G.Creer_Arete(37, 43);
		G.Creer_Arete(37, 38);
		G.Creer_Arete(38, 40);
		G.Creer_Arete(39, 40);
		G.Creer_Arete(40, 41);
		G.Creer_Arete(41, 42);
		G.Creer_Arete(43, 44);
		G.Creer_Arete(44, 49);
		G.Creer_Arete(44, 45);
		G.Creer_Arete(45, 47);
		G.Creer_Arete(47, 48);
		G.Creer_Arete(46, 46); //car l'emplacement 46 doit avoir une caméra
	
		Solve(G); //appel au fonction solve pour resoudre notre probleme
		
	}

	public static void Solve(Caméra adj) {
		try {
			int n = 49; // le nombre d'emplacement
			IloCplex simplexe = new IloCplex ();
			IloNumVar[] X = new IloNumVar[n]; // déclaration des Variables de décision de type boolean
			for (int i=0;i<n;i++){
				X[i]= simplexe.boolVar();
			}
			IloLinearNumExpr obj = simplexe.linearNumExpr(); // declaration de la fonction objectif
			for (int i=0;i<n;i++){
				obj.addTerm(1, X[i]); // définition des coefficients de la fonction objectif
			}
			
			simplexe.addMinimize(obj); // Définir le type d'optimisation de la fonction (min)
			
			for(int i = 0; i < n; i++) { // les contraintes (Xi + Xj >= 1) pour tout (i,j) appartient à R 
				for(int j = 0; j < n; j++) {
					if(adj.Verifie_adjacent(i, j)) { // pour (i,j) appartient a l'ensemble des rues R
						IloLinearNumExpr c = simplexe.linearNumExpr();
						c.addTerm(1, X[i]);
						c.addTerm(1, X[j]);
						simplexe.addGe(c, 1);						
					}
				}
			}
			
			simplexe.solve(); 
			
			
			System.out.println("\n le nombre  minimal de caméras qu'on besoin pour surveiller toutes les rues de la zone d’activités est : "+ simplexe.getObjValue() + " Caméra\n");
			System.out.println("il faudra placer à: ") ;
                        
			for (int i=0;i<n;i++) {
				if(simplexe.getValue(X[i]) != 0.0) {
					int j = i + 1;
					System.out.println( "Emplacement "+j);
				}				
			}
		} catch (IloException e){
			System.out.print("Erreur" + e);
		}
	}
    
}
