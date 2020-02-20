package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonApplication extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {
        StartScreen startScreen = new StartScreen(primaryStage);
        DungeonScreen dungeonScreen = new DungeonScreen(primaryStage);
        MazeScreen mazeScreen = new MazeScreen(primaryStage);
        BoulderScreen boulderScreen = new BoulderScreen(primaryStage);
        ModelScreen modelScreen = new ModelScreen(primaryStage);
        InformationScreen informationScreen = new InformationScreen(primaryStage);
        SixthScreen sixthScreen = new SixthScreen(primaryStage);
        FourthScreen fourthScreen = new FourthScreen(primaryStage);
        FifthScreen fifthScreen = new FifthScreen(primaryStage);
        startScreen.getController().setInformationScreen(informationScreen);
        informationScreen.getController().setModelScreen(modelScreen);
        modelScreen.getController().setMazeScreen(mazeScreen);
        modelScreen.getController().setDungeonScreen(dungeonScreen);
        modelScreen.getController().setBoulderScreen(boulderScreen);
        modelScreen.getController().setInformationScreen(informationScreen);
        modelScreen.getController().setFourthScreen(fourthScreen);
        modelScreen.getController().setFifthScreen(fifthScreen);
        modelScreen.getController().setSixthScreen(sixthScreen);
        
        
        
        dungeonScreen.getController().setStartScreen(modelScreen);
        mazeScreen.getController().setStartScreen(modelScreen);
        boulderScreen.getController().setStartScreen(modelScreen);
        sixthScreen.getController().setStartScreen(modelScreen);
        fourthScreen.getController().setStartScreen(modelScreen);
        fifthScreen.getController().setStartScreen(modelScreen);
        
        
        
        startScreen.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
