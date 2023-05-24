package ai;

import application.controller.modeJeuController;
import application.data.DataManager;
import application.data.Player;
import java.util.Arrays;

public class ClassAI {
    public static int play(int availableTiles) {

        // Charge le modèle de l'IA à partir du fichier spécifié dans le contrôleur de mode de jeu
        MultiLayerPerceptron net = MultiLayerPerceptron.load("./resources/models/"+modeJeuController.fileModel);

        // Prépare les entrées pour le réseau de neurones artificiels
        double[] inputs = new double[9];
        Arrays.fill(inputs, 0); // Initialise tous les éléments du tableau d'entrées à zéro
        for (int i = 0; i < DataManager.gameBoard.getTiles().size(); i++) {
            for (int j = 0; j < DataManager.gameBoard.getTiles().get(0).size(); j++) {

                // Si la case appartient au joueur, on assigne la valeur -1 à l'entrée correspondante
                Player tileOwner = DataManager.gameBoard.getTiles().get(i).get(j).getPlayer();
                if (tileOwner == DataManager.playerOne) {
                    inputs[i*3 + j] = -1;
                }
                // Sinon, s'il appartient à l'IA, on assigne la valeur 1 à l'entrée correspondante
                else if (tileOwner == DataManager.playerTwo) {
                    inputs[i*3 + j] = 1;
                }
            }
        }

        // Calcule les sorties à partir des entrées fournies
        assert net != null; // Vérifie que le modèle a bien été chargé
        double[] outputs = net.forwardPropagation(inputs);

        // Choisis la case ayant la plus grande valeur de sortie, sauf si elle est déjà prise
        int chosenTileIndex;
        int availableTileIndex = -1; // Initialise l'indice de la case choisie à -1 pour entrer dans la boucle while
        do {
            chosenTileIndex = 0; // Initialise l'indice de la case choisie à 0
            double chosenTileWeight = outputs[0]; // Initialise le poids de la case choisie au premier élément de la sortie
            for (int i = 1; i < outputs.length; i++) {
                if (outputs[i] > chosenTileWeight) {
                    // Si la sortie de la case courante est supérieure à la sortie de la case choisie, on met à jour l'indice et le poids
                    chosenTileIndex = i;
                    chosenTileWeight = outputs[i];
                }
            }

            // Si la case choisie est déjà prise, on la retire des sorties et on recommence la boucle
            if (inputs[chosenTileIndex] != 0) {
                outputs[chosenTileIndex] = 0;
            }
            else {
                // Sinon, on récupère l'indice de la case disponible
                availableTileIndex = chosenTileIndex;
                for (int i = 0; i < chosenTileIndex; i++) {
                    if (inputs[i] != 0) {
                        availableTileIndex--;
                    }
                }
            }
        } while (availableTileIndex == -1);


        return availableTileIndex;
    }

}
