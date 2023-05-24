package application.controller;

import ai.*;
import application.data.Coup;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;


import static application.controller.modeJeuController.net;
import static application.controller.modeJeuController.fileModel;


public class TestController extends Application {
	
	private Thread progressBarThread;

    private Task<Void> task;
    @FXML
    private Button start;

    @FXML
    private TextField tf;

    @FXML
    private ProgressBar progbar;

    public void initialize() throws IOException {

    }
    @FXML
    private Task<Void> task1() {
        return new Task<Void>() {

            @Override
            protected Void call() throws Exception {


                int epochs = 100000000;

                try {
                    System.out.println();
                    System.out.println("START TRAINING ...");
                    System.out.println();
                    int[] layers = new int[]{ 9, 9, 9};
                    double error = 0.0 ;
                    for(int i = 0; i < epochs; i++){
                        updateMessage("Learning completed!");
                        updateProgress(i, epochs);
                    }

                    // Sauvegarde du model
                    net.save("resources/models/"+fileModel);

                }
                catch (Exception e) {
                    System.out.println("AI.train()");
                    e.printStackTrace();
                    System.exit(-1);
                }

                return null;
            }
        };
    }

    public void startTest() {
        task =  task1();

        progbar.setProgress(0);

        progbar.progressProperty().bind(task.progressProperty());

        task.messageProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                tf.setText(t1);
            }
        });

        new Thread(task).start();
    }




    @Override
    public void start(Stage stage) throws Exception {

    }
}
