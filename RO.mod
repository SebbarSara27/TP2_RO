/*********************************************
 * OPL 12.6.0.0 Model
 * Author: serradj
 * Creation Date: 31 mars 2022 at 19:43:10
 *********************************************/
int n = ...; //d�clarer le nombre des emplacement

range emplacement = 1..n; // un intervalle d'entiers de 1 � n emplacement 
int rue[1..53][1..2]=...; //l'ensembe des rues (53 rue et dans chaque rue on a 2 arr�te)
dvar boolean x[emplacement] ; // D�clarer les variables de d�cisions



// function objectif
minimize  sum(i in emplacement)  x[i];
// contraintes
subject to
{
// les contraintes (Xi + Xj >= 1) pour tout (i,j) appartient � R (l'ensemble des rues entre emplacements)
forall(i in emplacement)
 forall(j in emplacement)
 if(rue[j][1]==i||rue[j][2]==j)
  
  x[i]+x[j]>=1; 
  
 
      
    
}
