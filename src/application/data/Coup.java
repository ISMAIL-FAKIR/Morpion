package application.data;

import java.util.Arrays;

public class Coup{

//CHAMPS ...
public String game; 
public double[] in;
public double[] out;
//
//
public static int X = -1;
public static int O = 1;
//
public static int EMPTY = 0;
//
public int joueurCourant = 0;
//
public int nbCoups;
//
public boolean partieGagne = false;

//Constructeur de la classe Coup.

public Coup(int size, String game) {
    if (size > 0) {
        in = new double[size];
        out = new double[size];
        //
        this.nbCoups = 0;
    }
    this.game = game;
}

//Vérifie si la cellule à la position donnée est disponible.
 
public boolean cellAvailable(int position) {
    return in[position] == Coup.EMPTY;
}

//Retourne la prochaine pièce à jouer pour le joueur suivant.

public int getNextTurnPiece(int piece) {
    return piece == Coup.X ? Coup.O : Coup.X;
}

//Ajoute le tableau de doubles à l'intérieur du plateau de jeu.

public void addInBoard(double[] board) {
    for (int i = 0; i < board.length; i++) {
        in[i] = board[i];
    }
}

//Retourne une chaîne de caractères représentant l'objet Coup.

@Override
public String toString() {
    return "Coup [game=" + this.game + " in=" + Arrays.toString(in) + ", out=" + Arrays.toString(out)
            + ", joueurCourant=" + joueurCourant + ", nbCoups=" + nbCoups + ", partieGagne=" + partieGagne + "]";
}
}