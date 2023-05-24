package application.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

import ai.MultiLayerPerceptron;
import ai.SigmoidalTransferFunction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class modeJeuController {
	 @FXML
	    public Pane idAnchorPane;
	    @FXML
	    private Button easyBtn;
	    @FXML
	    private Button hardBtn;
	    @FXML
	    private Button nextBtn;
	    @FXML
	    private Button idBackButton;

	    @FXML
	    private Button mediumBtn;
	    public static String fileModel;
	    public static MultiLayerPerceptron net;
	    public static String SelectedMode;
	    @FXML
	    public static Label  choiceLabel;
	    
	    public static int numColumn = 3;

	    public static int numRow = 3;
	    @FXML
	    public Button idSaveButton;

	    @FXML
	    public void initialize(){
	    	 Image titleImage = new Image("file:resources/images/logolabel.jpg");

	         ImageView titletView = new ImageView(titleImage);

	         titletView.setFitHeight(95.0);
	         titletView.setFitWidth(431.0);
	         titletView.setX(173.0);
	         titletView.setY(72.0);
	         titletView.setPreserveRatio(true);

	         Animator.animateTitle(titletView);

	         idAnchorPane.getChildren().add(titletView);



	         Image backImage = new Image("file:resources/images/left-arrow.png");

	         ImageView backView = new ImageView(backImage);
	         backView.setFitHeight(23.0);
	         backView.setFitWidth(18.0);
	         backView.setX(18.0);
	         backView.setY(22.0);
	         backView.setPreserveRatio(true);


	    }

	    public MultiLayerPerceptron getNet() {
	        return net;
	    }


	    public void setNet(MultiLayerPerceptron net) {
	        this.net = net;
	    }
	    

	    public void StartPlaying(String mode) throws IOException {
			//lire le fichier config.txt
	        FileReader fin = new FileReader("resources/config.txt");

	        BufferedReader bin = new BufferedReader(fin);

	        String facile = bin.readLine();
	        String moyen = bin.readLine();
	        String diffcile = bin.readLine();

	        String [] facileSplit = facile.split(":");
	        String [] moyenSplit = moyen.split(":");
	        String [] difficileSplit = diffcile.split(":");

	        int hf = Integer.parseInt(facileSplit[1]);
	        double lrf =  new Double(facileSplit[2]);
	        int lf = Integer.parseInt(facileSplit[3]);

	        int hm = Integer.parseInt(moyenSplit[1]);
	        double lrm =  new Double(moyenSplit[2]);
	        int lm = Integer.parseInt(moyenSplit[3]);

	        int hd = Integer.parseInt(difficileSplit[1]);
	        double lrd = new Double(difficileSplit[2]);
	        int ld =Integer.parseInt(difficileSplit[3]);

			//un nom de fichier modèle est créé en concaténant des chaînes avec des variables.
	        if (mode.equals("Facile")){
	            fileModel = "mlp_"+hf+"_"+lrf+"_"+lf+".srl";
	        }
	        if (mode.equals("Moyen")) {
	            fileModel = "mlp_"+hm+"_"+lrm+"_"+lm+".srl";
	        }
	        if (mode.equals("Difficile")) {
	            fileModel = "mlp_"+hd+"_"+lrd+"_"+ld+".srl";
	        }

			//Si un fichier avec ce nom existe déjà
	        if(new File("resources/models/"+fileModel).exists()){
	            System.out.println("Le model existe déjà");
	            MultiLayerPerceptron net = MultiLayerPerceptron.load("resources/models/"+fileModel);
	        }

	        else{

	            int[] layers = new int[lf+2];
	            layers[0] = 9;
	            for (int i = 0; i < lf; i++){
	                layers[i+1] = hf;
	            }
	            layers[layers.length-1] = 9;

	            setNet(new MultiLayerPerceptron(layers, lf, new SigmoidalTransferFunction()));

	        }
	        bin.close();


	    }

		//boutton Easy
	    @FXML
	    public void EasyBtnActionPerformed(ActionEvent actionEvent) throws IOException {
	        SelectedMode = "Facile";
	        launchAiTraining(SelectedMode);
	    }

		//boutton Medium
	    @FXML
	    public void MediumBtnActionPerformed(ActionEvent actionEvent) throws IOException {
	        SelectedMode = "Moyen";
	        launchAiTraining(SelectedMode);
	    }

		//boutton Hard
	    @FXML
	    public void HardBtnActionPerformed(ActionEvent actionEvent) throws IOException {
	        SelectedMode = "Difficile";
	        launchAiTraining(SelectedMode);
	    }
	    @FXML
	    public void nextBtnActionPerformed(ActionEvent actionEvent) throws IOException {
	        Parent root = FXMLLoader.load(getClass().getResource("../vue/PlayerInfo.fxml"));
	        Scene gameScene = new Scene(root);
	        Stage window = (Stage) nextBtn.getScene().getWindow();
	        window.setScene(gameScene);
	        window.show();
	        window.setTitle("Tic-Tac-Toe");
	    }
	    
	    public void BackButtonAction(ActionEvent actionEvent) throws IOException {
	        Parent gameRoot = FXMLLoader.load(getClass().getResource("../vue/gameMode.fxml"));
	        Scene gameScene = new Scene(gameRoot);

	        Stage window = (Stage) idBackButton.getScene().getWindow();

	        window.setScene(gameScene);
	        window.show();
	        window.setTitle("Tic-Tac-Toe");
	    }

		//lancer la progBar pour le Learning si le model choisi n'existe pas 
	    public void launchAiTraining(String mode) throws IOException {
	        if(SelectedMode != null){
	            StartPlaying(mode);
	            if(!new File("resources/models/"+fileModel).exists()){
	            	Parent root = FXMLLoader.load(getClass().getResource("../vue/progBar.fxml"));	                
	            	Stage stage = new Stage();
	                stage.setTitle("Tic Tac Toe");
	                stage.setScene(new Scene(root));
	                stage.show();
	            }
	        }
	    }

}
