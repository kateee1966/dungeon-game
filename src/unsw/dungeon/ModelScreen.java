package unsw.dungeon;

import java.io.IOException;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class ModelScreen {

    private Stage stage;
    private String title;
    private ModelController controller;
    private Scene scene;

    public ModelScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Welcome to Dungeon";

        controller = new ModelController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("model.fxml"));
        loader.setController(controller);

        // load into a Parent node called root

		
        Parent root = loader.load();
        scene = new Scene(root);
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    
    public ModelController getController() {
    	return controller;
    }
}
