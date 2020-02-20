package unsw.dungeon;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class InformationController {

	@FXML
	private Button go;
	private ModelScreen modelScreen;
	@FXML
	private BorderPane back;
	@FXML
	public void initialize() {
		go.setStyle("-fx-background-color: transparent;");
		Image ground = new Image("/information.png");
		BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
		Background background2 = new Background(new BackgroundImage(ground,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundPosition.CENTER,
	            bSize));
		back.setBackground(background2);
        
	}
	@FXML
	void handleGo(ActionEvent event) {
		modelScreen.start();
	}
	
	public void setModelScreen(ModelScreen dungeonScreen) {
		this.modelScreen = dungeonScreen;
	}
	
}
