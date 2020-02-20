package unsw.dungeon;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ModelController {

	
	@FXML
	private GridPane squares;
	
	private MazeScreen mazeScreen;
	
	private DungeonScreen dungeonScreen;
	
	private BoulderScreen boulderScreen;
	
	private InformationScreen informationScreen;
	
	private FourthScreen fourthScreen;
	
	private FifthScreen fifthScreen;
	
	private SixthScreen sixthScreen;

	@FXML
	public void initialize() {
		Image ground = new Image("/dirt_0_new.png");
		Image ground1 = new Image("/brick_brown_0.png");
	
        // Add the ground first so it is below all other entities
        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
            	if(x == 0 || x == 19 || x == 6 || x == 12|| y == 0 || y == 19 || y == 16|| y ==8) {
            		if(x == 6 && (y == 17 || y == 18)) {
            			squares.add(new ImageView(ground), x, y);
            		}else if(x == 12 && (y == 17 || y == 18)){
            			squares.add(new ImageView(ground), x, y);
            		}else {
            			squares.add(new ImageView(ground1), x, y);
            		}
            	}else{
            		squares.add(new ImageView(ground), x, y);
            	}
                
            }
        }
		
        Button back = new Button("BACK");
        squares.add(back, 18,19,19, 19);
        back.setStyle("-fx-background-color: transparent;");
        back.setTextFill(Color.web("white"));
        back.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
            	informationScreen.start();
            }
        }); 
        // for maze part

        Button maze = new Button("START");    
        squares.add(maze, 2, 4, 5, 4);
       // maze.setPrefSize(100, 20);
        maze.setStyle("-fx-background-color: #40210a;-fx-font-size: 24;");
        maze.setTextFill(Color.web("white"));
        maze.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
            	mazeScreen.start();
            }
        }); 
        Label text = new Label("First");
        text.setFont(new Font("Arial Black", 24));
        text.setTextFill(Color.web("#40210a"));
        squares.add(text, 2, 2,6,2);
        
        
        // for complex part
        Button dungeon = new Button("START");    
        squares.add(dungeon, 14, 4, 16, 4);
        dungeon.setStyle("-fx-background-color: #40210a;-fx-font-size: 24;");
        dungeon.setTextFill(Color.web("white"));
        dungeon.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
            	dungeonScreen.start();
            }
        });
        
        Label comp = new Label("Third");
        comp.setFont(new Font("Arial Black", 26));
        comp.setTextFill(Color.web("#40210a"));
        squares.add(comp, 14,2,16,2);
        
        
        // for second part
        Button hard = new Button("START");    
        squares.add(hard, 8, 4, 10, 4);
        hard.setStyle("-fx-background-color:#40210a;-fx-font-size: 24;-fx-font-name:Arial Black");
        hard.setTextFill(Color.web("white"));
        hard.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
            	boulderScreen.start();
            }
        }); 
        Label bouler = new Label("Second");
        bouler.setFont(new Font("Arial Black", 26));
        bouler.setTextFill(Color.web("#40210a"));
        squares.add(bouler, 8,2,10,2);
        

        // for Fourth part
        Button fourth = new Button("START");    
        squares.add(fourth, 2, 9, 4, 9);
        fourth.setStyle("-fx-background-color:#40210a;-fx-font-size: 24;-fx-font-name:Arial Black");
        fourth.setTextFill(Color.web("white"));
        fourth.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
            	fourthScreen.start();
            }
        }); 
        Label fourthh = new Label("Fourth");
        fourthh.setFont(new Font("Arial Black", 26));
        fourthh.setTextFill(Color.web("#40210a"));
        squares.add(fourthh, 2,7,4,7);
        

        // for Fifth part
        Button fifth = new Button("START");    
        squares.add(fifth, 8, 9, 10, 9);
        fifth.setStyle("-fx-background-color:#40210a;-fx-font-size: 24;-fx-font-name:Arial Black");
        fifth.setTextFill(Color.web("white"));
        fifth.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
            	fifthScreen.start();
            }
        }); 
        Label fifthh = new Label("Fifth");
        fifthh.setFont(new Font("Arial Black", 26));
        fifthh.setTextFill(Color.web("#40210a"));
        squares.add(fifthh, 8,7,10,7);
        

        // for Sixth part
        Button sixth = new Button("START");    
        squares.add(sixth, 14, 9, 16, 9);
        sixth.setStyle("-fx-background-color:#40210a;-fx-font-size: 24;-fx-font-name:Arial Black");
        sixth.setTextFill(Color.web("white"));
        sixth.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
            	sixthScreen.start();
            }
        }); 
        Label sixthh = new Label("Sixth");
        sixthh.setFont(new Font("Arial Black", 26));
        sixthh.setTextFill(Color.web("#40210a"));
        squares.add(sixthh, 14,7,16,7);
        
        
        
        Label explain = new Label("W: MoveUp   S: MoveDown   A: MoveLeft   D: MoveRight   " + "\n" + "J: ThrowBomb   G: ThrowSword   BACK: QuitGame");
        explain.setTextFill(Color.web("#641E16"));
        explain.setFont(new Font("Arial Black", 16));
        squares.add(explain, 1, 16, 18, 16);
	}
	
	public void setMazeScreen(MazeScreen dungeonScreen) {
		this.mazeScreen = dungeonScreen;
	}
	
	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
	}
	
	public void setBoulderScreen(BoulderScreen boulderScreen) {
		this.boulderScreen = boulderScreen;
	}
	
	public void setInformationScreen(InformationScreen informationScreen) {
		this.informationScreen = informationScreen;
	}
	
	public void setFourthScreen(FourthScreen fourthScreen) {
		this.fourthScreen = fourthScreen;
	}
	
	public void setFifthScreen(FifthScreen fifthScreen) {
		this.fifthScreen = fifthScreen;
	}
	
	public void setSixthScreen(SixthScreen sixthScreen) {
		this.sixthScreen = sixthScreen;
	}
}
