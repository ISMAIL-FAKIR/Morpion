package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

import application.data.DataManager;

public class GameModeController {

    @FXML
    public Button idSingleplayer;
    @FXML
    public Button idMultiplayer;
    @FXML
    public ImageView user;
    @FXML
    public ImageView users;
    @FXML
    public Pane idTitlePane;
    @FXML
    public ImageView Title;
    @FXML
    public Button idBackButton;
    @FXML
    public ImageView backIcon;

   
    @FXML
    private void initialize(){




        Image backImage = new Image("file:resources/images/left-arrow.png");

        ImageView backView = new ImageView(backImage);

        backView.setFitHeight(23.0);
        backView.setFitWidth(18.0);
        backView.setX(18.0);
        backView.setY(22.0);
        backView.setPreserveRatio(true);

        idBackButton.setGraphic(backView);



        Image userImage = new Image("file:resources/images/person.png");

        ImageView userView = new ImageView(userImage);

        userView.setFitHeight(23.0);
        userView.setFitWidth(35.0);
        userView.setX(231.0);
        userView.setY(228.0);
        userView.setPreserveRatio(true);

        idSingleplayer.setGraphic(userView);


        Image usersImage = new Image("file:resources/images/2users.png");

        ImageView usersView = new ImageView(usersImage);

        usersView.setFitHeight(23.0);
        usersView.setFitWidth(35.0);
        usersView.setX(231.0);
        usersView.setY(366.0);
        usersView.setPreserveRatio(true);

        idMultiplayer.setGraphic(usersView);

    }

   
    // Méthode appelée lorsqu'on clique sur le bouton du "SinglePlayer"
    public void SinglePlayerAction(ActionEvent actionEvent) throws IOException {
        DataManager.gameMode = "SinglePlayer";
        Parent root = FXMLLoader.load(getClass().getResource("../vue/modeJeu.fxml"));
        Scene gameScene = new Scene(root);
        Stage window = (Stage) idBackButton.getScene().getWindow();
        window.setScene(gameScene);
        window.show();
        window.setTitle("Tic-Tac-Toe");
    }
        
    // Méthode appelée lorsqu'on clique sur le bouton du "SinglePlayer"
    public void MultiplayerAction(ActionEvent actionEvent) throws IOException {
        DataManager.gameMode = "MultiPlayer";
        Parent gameRoot = FXMLLoader.load(getClass().getResource("../vue/playerInfo.fxml"));
        Scene gameScene = new Scene(gameRoot);
        Stage window = (Stage) idBackButton.getScene().getWindow();
        window.setScene(gameScene);
        window.show();
        window.setTitle("Tic-Tac-Toe");
    }
    
    public void BackButtonAction(ActionEvent actionEvent) throws IOException {

        Parent gameRoot = FXMLLoader.load(getClass().getResource("../vue/mainWindow.fxml"));
        Scene gameScene = new Scene(gameRoot);

        Stage window = (Stage) idBackButton.getScene().getWindow();

        window.setScene(gameScene);
        window.show();
        window.setTitle("Tic-Tac-Toe");
    }
}
