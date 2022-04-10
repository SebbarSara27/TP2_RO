/*********************************************
 * OPL 12.6.0.0 Model
 * Author: serradj
 * Creation Date: 31 mars 2022 at 19:43:10
 *********************************************/
int n = ...; //déclarer le nombre des emplacement

range emplacement = 1..n; // un intervalle d'entiers de 1 à n emplacement 
int rue[1..53][1..2]=...; //l'ensembe des rues (53 rue et dans chaque rue on a 2 arréte)
dvar boolean x[emplacement] ; // Déclarer les variables de décisions



// function objectif
minimize  sum(i in emplacement)  x[i];
// contraintes
subject to
{
// les contraintes (Xi + Xj >= 1) pour tout (i,j) appartient à R (l'ensemble des rues entre emplacements)
forall(i in emplacement)
 forall(j in emplacement)
 if(rue[j][1]==i||rue[j][2]==j)
  
  x[i]+x[j]>=1; 
  
 
      
    
}
