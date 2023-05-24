package ai;

import java.io.Serializable;

public class Layer implements Serializable{

	//Déclaration des champs
	public Neuron 	Neurons[]; //tableau de neurones
	public int 		Length; //nombre de neurones dans la couche

	//Constructeur
	public Layer(int l, int prev){
		Length = l; //initialisation de la taille de la couche
		Neurons = new Neuron[l]; //initialisation du tableau de neurones

		//Création de chaque neurone dans la couche
		for(int j = 0; j < Length; j++)
			Neurons[j] = new Neuron(prev);
	}
}

class Neuron implements Serializable {

	//Déclaration des champs
	public double		value; //valeur de sortie du neurone
	public double[]		weights; //poids pour chaque connexion entrante
	public double		bias; //biais pour le neurone
	public double		delta; //erreur calculée pour le neurone

	//Constructeur
	public Neuron(int prevLayerSize){
		weights = new double[prevLayerSize]; //initialisation des poids avec la taille de la couche précédente

		//Initialisation des champs avec des valeurs aléatoires très petites
		bias = Math.random() / 10000000000000.0;
		delta = Math.random() / 10000000000000.0;
		value = Math.random() / 10000000000000.0;

		//Initialisation des poids avec des valeurs aléatoires très petites
		for(int i = 0; i < weights.length; i++)
			weights[i] = Math.random() / 10000000000000.0;
	}
}
