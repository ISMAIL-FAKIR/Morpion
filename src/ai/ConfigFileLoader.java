package ai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ConfigFileLoader {

	// Map qui stockera les configurations pour chaque niveau de difficulté
	private HashMap<String, Config> mapConfig;

	
	public void loadConfigFile(String name) {
		try {
			// Création d'un objet File pour vérifier l'existence et la validité du fichier
			File f = new File(name);
			// Initialisation de la map des configurations
			this.mapConfig = new HashMap<String, Config>();
			if (f.exists() && f.isFile()) {
				// Lecture du fichier ligne par ligne
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
				String s = "";
				while ((s = br.readLine()) != null) {
					String[] t = s.split(":");
					if (t.length == 4) {
						// Récupération de chaque paramètre de configuration
						String level = t[0];
						int hiddenLayerSize = Integer.parseInt(t[1]);
						double learningRate = Double.parseDouble(t[2]);
						int numberOfhiddenLayers = Integer.parseInt(t[3]);
						// Création d'un objet Config avec les paramètres récupérés
						Config c = new Config(level, hiddenLayerSize, numberOfhiddenLayers, learningRate);
						// Ajout de la configuration à la map en utilisant le niveau de difficulté comme clé
						mapConfig.put(level, c);
					}
				}
				br.close();
			}
		} catch (Exception e) {
			System.out.println("Config.loadConfigFile()");
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public Config get(String level) {
		try {
			// Vérification de l'existence du niveau de difficulté dans la map
			if (mapConfig != null && mapConfig.containsKey(level))
				return mapConfig.get(level);
		} catch (Exception e) {
			System.out.println("ConfigFileLoader.get()");
			e.printStackTrace();
			System.exit(-1);
		}
		return null;
	}

	@Override
	public String toString() {
		return "ConfigFileLoader [mapConfig=" + mapConfig + "]";
	}

}
