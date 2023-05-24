package application.controller;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


public class ModelsController extends Application {

// Déclaration des champs pour les éléments de la vue
@FXML
private TableView<Model> modelsTable;
@FXML
private Button deleteBtn;
@FXML
private TableColumn<Model, String> models;
@FXML
private TableColumn select;

// Déclaration de la liste observale contenant les éléments du tableau
private ObservableList<Model> listItems;

// Méthode initialize appelée au démarrage de l'application
@FXML
public void initialize(){
    List<File> Listfiles = loadModels();
    // Initialisation de la liste observale listItems
    listItems = FXCollections.observableArrayList();
    for (File f : Listfiles){
        listItems.add(new Model(f.getPath(),f.getName()));
    }

    // Initialisation des colonnes du tableau
    models = new TableColumn<Model, String>("Models");
    select = new TableColumn("");

    // Définition de la colonne Select avec une case à cocher pour chaque élément
    select.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Model, CheckBox>, ObservableValue<CheckBox>>() {
        @Override
        public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<Model, CheckBox> modelCheckBoxCellDataFeatures) {
            Model model = modelCheckBoxCellDataFeatures.getValue();

            CheckBox checkBox = new CheckBox();

            checkBox.selectedProperty().setValue(model.isSelected());

            checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                // Méthode appelée lorsqu'une case à cocher est cochée ou décochée
                public void changed(ObservableValue<? extends Boolean> ov,
                                    Boolean old_val, Boolean new_val) {
                    // On met à jour la valeur isSelected de l'élément Model correspondant
                    model.setSelected(new_val);
                }
            });

            return new SimpleObjectProperty<CheckBox>(checkBox);
        }
    });

    // Permettre l'édition du tableau
    modelsTable.setEditable(true);

    // Définition de la colonne Models qui affiche le nom des modèles
    models.setCellValueFactory(new PropertyValueFactory<Model,String>("name"));

    // Ajout des colonnes au tableau et des éléments de listItems
    modelsTable.setItems(listItems);
    modelsTable.getColumns().addAll(models,select);
}

    // Méthode appelée lors du clic sur le bouton deleteBtn pour supprimer le model choisi 
    @FXML
    private void deleteAction(ActionEvent action){
        listItems.forEach((model) -> {
            if(model.isSelected()) {
                try {
                    boolean result = Files.deleteIfExists(Paths.get(model.getPath()));
                    if (result) {
                        System.out.println("Model supprime!");

                    } else {
                        System.out.println("Désolé, impossible de supprimer le(s) model(s)!");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            Stage stage = (Stage) deleteBtn.getScene().getWindow();
            stage.close();

        });

        List<File> Listfiles = loadModels();
        listItems = FXCollections.observableArrayList();
        for (File f : Listfiles){
            System.out.println(f.getName());
            listItems.add(new Model(f.getPath(),f.getName()));
        }

        modelsTable.setItems(listItems);


    }

    public List<File> loadModels(){
        String dirLocation = "resources/models";

        try {
            List<File> files = Files.list(Paths.get(dirLocation))
                    .map(Path::toFile)
                    .collect(Collectors.toList());

            return files;
        } catch (IOException e) {
            System.out.println("directory doesn't exist");
        }
        return null;
    }



    @Override
    public void start(Stage stage) throws Exception {

    }
}
