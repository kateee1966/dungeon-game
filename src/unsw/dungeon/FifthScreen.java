package unsw.dungeon;


import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FifthScreen {

    private Stage stage;
    private String title;
    private DungeonController controller;
    
    private Scene scene;
    
    public FifthScreen(Stage stage) throws IOException {
    	this.stage = stage;
    	title ="Dungeon";
    	
    	DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("fifth.json");

        controller = dungeonLoader.loadController();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        
        Parent root = loader.load();
        scene = new Scene(root);
        root.requestFocus();
        
        int row = controller.getDungeon().getWidth();
        int column = controller.getDungeon().getHeight();
        
        Label goal = new Label("· Goal:  [ Arrive Exit And Collect All Treasure]");
    	controller.getGridPane().add(goal, 0, row+1, column +2, row+1);
    	goal.setTextFill(Color.web("#40210a"));
    	goal.setFont(new Font("Arial Black", 16));
    }
    
    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    
    public DungeonController getController() {
    	return controller;
    }
}
