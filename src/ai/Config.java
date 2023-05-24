package ai;

public class Config {
	
	// Constructeur de la classe Config
	public Config(String level, int hiddenLayerSize, int numberOfhiddenLayers, double learningRate) {
		super();
		// Initialisation des champs de la classe avec les valeurs passées en paramètres
		this.level = level;
		this.hiddenLayerSize = hiddenLayerSize;
		this.numberOfhiddenLayers = numberOfhiddenLayers;
		this.learningRate = learningRate;
	}
	
	// Méthode pour afficher les valeurs des champs de la classe
	@Override
	public String toString() {
		return "Config [level=" + level + ", hiddenLayerSize=" + hiddenLayerSize + ", numberOfhiddenLayers="
				+ numberOfhiddenLayers + ", learningRate=" + learningRate + "]";
	}

	// Champs de la classe Config
	public String level ;
	public int hiddenLayerSize;
	public int numberOfhiddenLayers;
	public double learningRate;
}
