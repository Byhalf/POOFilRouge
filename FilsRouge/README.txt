Féaux de Lacroix Martin 2704479 L2 3B Informatique
Ferdinand Mathieu 21809458 L2 4A Informatique
Lafay Gareth 21705720 L2 4A Informatique

Groupe 24 

Chargé de Tp: Alec Céline

Les arguments du programmes sont les suivant:
-jeux: nim, morpion, puissance4 (attaché)

-joueurs: humain, random, robot

L'ordre des arguments n'importe pas
exemple: nim humain robot est équivalent à humain nim robot

mais le premier joueur est toujours le premier argument de type joueur
exemple: nim random humain on à random en premier joueur et humain en second
	et nim humain random on à humain en premier joueur et random en second


Compiler toutes les classes du répertoire « FilsRouge » et placer les fichiers compilés (.class) dans le répertoire « build » :
javac -d "build" Main.java

exécuter:
java -cp "build" Main

suivit obligatoirement par les arguments, par exemple : java -cp "build" Main morpion humain robot
