package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import unsw.dungeon.attackmode.Armed;
import unsw.dungeon.attackmode.Invicibleplayer;
import unsw.dungeon.attackmode.Unarmed;
import unsw.dungeon.entity.*;
/**
 * A JavaFX controller for the dungeon.
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;
 

    @FXML
    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;
    
    private DungeonSystem dungeonsystem;

    
    private ModelScreen modelScreen;
    
    Label status;
    Label bombtime;
    Label swordtime;
    Label potiontime;
    Label keytime;
    
    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.player = dungeon.getPlayer();
        this.dungeon = this.player.getDungeon();
        this.dungeonsystem = new DungeonSystem(this.dungeon, this.player);
        this.initialEntities = new ArrayList<>(initialEntities);
    }
    public void setStartScreen(ModelScreen startScreen) {
        this.modelScreen = startScreen;
    }
    @FXML
    public void initialize() {
        //System.out.println("-----");
        Image ground = new Image("/dirt_0_new.png");
        int column = 0;
        int row = 0;
        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight()+2; y++) {
                squares.add(new ImageView(ground), x, y);
                column = x;
                row = y;
            }
        }
        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
        
        
        Image player = new Image("/human_new.png");
        squares.add(new ImageView(player), 0, row-1);
        status = new Label("Normal");
        squares.add(status, 1, row-2, 2, row-2);
        status.setTextFill(Color.web("white"));
        
        
        Image bomb = new Image("/bomb_unlit.png");
        squares.add(new ImageView(bomb), 3, row-1);
        bombtime = new Label("0");
        squares.add(bombtime, 4, row-1);
        bombtime.setTextFill(Color.web("white"));
        
        
        Image sword = new Image("/greatsword_1_new.png");
        squares.add(new ImageView(sword), 5, row-1);
        swordtime = new Label("0");
        squares.add(swordtime, 6, row-1);
        swordtime.setTextFill(Color.web("white"));
        
        Image key = new Image("/key.png");
        squares.add(new ImageView(key), 7, row-1);
        keytime = new Label("0");
        squares.add(keytime, 8, row-1);
        keytime.setTextFill(Color.web("white"));
        
        Button exit = new Button("BACK");
        
        squares.add(exit, column-1, row-2, column, row-2);
        exit.setStyle("-fx-background-color: Transparent");
        exit.setTextFill(Color.web("white"));
        exit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                modelScreen.start();
            }
        }); 
         
    }   
    
    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case W:
            if(player.getResult() == 1) {
                winGame();
            }
            if(player.getResult() == 2) {
            	status.setText("Normal");
                bombtime.setText(Integer.toString(0));
                swordtime.setText(Integer.toString(0));
                dieGame();
                break;
            }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                	status.setText("Normal");
                	if(player.getattackmode() instanceof Armed) {
                		status.setText("Sword!");
                	}
                    if(player.getattackmode() instanceof Invicibleplayer) {
                    	status.setText("Invincible");
                    }
                    
                    bombtime.setText(Integer.toString(player.getBag().getBomb()));
                    swordtime.setText(Integer.toString(player.getBag().getSwordattacktimes()));
                    if(player.getBag().getHaskey()) {
                    	keytime.setText(Integer.toString(1));
                    }else {
                    	keytime.setText(Integer.toString(0));
                    }
                }
            });
            dungeonsystem.playermoveup();
            break;
        case S:
            if(player.getResult() == 1) {
                winGame();
            }
            if(player.getResult() == 2) {
            	status.setText("Normal");
                bombtime.setText(Integer.toString(0));
                swordtime.setText(Integer.toString(0));
                dieGame();
                break;
            }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                	status.setText("Normal");
                	if(player.getattackmode() instanceof Armed) {
                		status.setText("Sword!");
                	}
                    if(player.getattackmode() instanceof Invicibleplayer) {
                    	status.setText("Invincible");
                    }
                    
                    bombtime.setText(Integer.toString(player.getBag().getBomb()));
                    swordtime.setText(Integer.toString(player.getBag().getSwordattacktimes()));
                    
                    if(player.getBag().getHaskey()) {
                    	keytime.setText(Integer.toString(1));
                    }else {
                    	keytime.setText(Integer.toString(0));
                    }
                }
            });
            dungeonsystem.playermovedown();
            break;
        case A:
            if(player.getResult() == 1) {
                winGame();
            }
            if(player.getResult() == 2) {
            	status.setText("Normal");
                bombtime.setText(Integer.toString(0));
                swordtime.setText(Integer.toString(0));
                dieGame();
                break;
            }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                	status.setText("Normal");
                	if(player.getattackmode() instanceof Armed) {
                		status.setText("Sword!");
                	}
                    if(player.getattackmode() instanceof Invicibleplayer) {
                    	status.setText("Invincible");
                    }
                    
                    bombtime.setText(Integer.toString(player.getBag().getBomb()));
                    swordtime.setText(Integer.toString(player.getBag().getSwordattacktimes()));
                    if(player.getBag().getHaskey()) {
                    	keytime.setText(Integer.toString(1));
                    }else {
                    	keytime.setText(Integer.toString(0));
                    }
                }
            });
            dungeonsystem.playermoveleft();
            break;
        case D:
            if(player.getResult() == 1) {
                winGame();
            }
            if(player.getResult() == 2) {
            	status.setText("Normal");
                bombtime.setText(Integer.toString(0));
                swordtime.setText(Integer.toString(0));
                dieGame();
                break;
            }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                	status.setText("Normal");
                	if(player.getattackmode() instanceof Armed) {
                		status.setText("Sword!");
                	}
                    if(player.getattackmode() instanceof Invicibleplayer) {
                    	status.setText("Invincible");
                    }
                    bombtime.setText(Integer.toString(player.getBag().getBomb()));
                    swordtime.setText(Integer.toString(player.getBag().getSwordattacktimes()));
                    if(player.getBag().getHaskey()) {
                    	keytime.setText(Integer.toString(1));
                    }else {
                    	keytime.setText(Integer.toString(0));
                    }
                }
            });
            dungeonsystem.playermoveright();
            break;
        case G:
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    status.setText(dungeonsystem.playerStatus());
                    bombtime.setText(Integer.toString(player.getBag().getBomb()));
                    swordtime.setText(Integer.toString(player.getBag().getSwordattacktimes()));
                }
            });
            dungeonsystem.playerthrowsword();
            break;
        case J:
            if(dungeon.getPlayer().getBag().getBomb()>0) {
                dungeonsystem.playerplacebomb();
                for(Entity i:dungeon.getEntity()) {
                	if(i.gettype().equals("bomb") && i.getBombStatus() == 1 ) {
                		int x = player.getX();
                        int y = player.getY();
                        Timer time = new Timer();
                        TimerTask task = new TimerTask() {
                            private int count = 4;
                            public void run() {
                                if(count == 4) {
                                    Image ground = new Image("/bomb_lit_1.png");
                                    for(Entity i:dungeon.getEntity()) {
                                        if(i.gettype().equals("bomb") && i.getX() == x+1 && i.getY() == y) {
                                            Platform.runLater(new Runnable() {
                                                @Override
                                                public void run() {
                                                    squares.add(new ImageView(ground), i.getX(), i.getY());
                                                }
                                            });
                                            break;
                                        }
                                    }
                                    
                                    count --;
                                }else if(count == 3) {
                                    Image ground = new Image("/bomb_lit_2.png");
                                    
                                    for(Entity i:dungeon.getEntity()) {
                                        if(i.gettype().equals("bomb") && i.getX() == x+1 && i.getY() == y) {
                                            Platform.runLater(new Runnable() {
                                                @Override
                                                public void run() {
                                                    squares.add(new ImageView(ground), i.getX(), i.getY());
                                                }
                                            });
                                            break;
                                        }
                                    }
                                    count --;
                                }else if(count == 2) {
                                    Image ground = new Image("/bomb_lit_3.png");
                                    
                                    for(Entity i:dungeon.getEntity()) {
                                        if(i.gettype().equals("bomb") && i.getX() == x+1 && i.getY() == y) {
                                            Platform.runLater(new Runnable() {
                                                @Override
                                                public void run() {
                                                    squares.add(new ImageView(ground), i.getX(), i.getY());
                                                }
                                            });
                                            break;
                                        }
                                    }
                                    count --;
                                }else if(count == 1) {
                                    Image ground = new Image("/bomb_lit_4.png");
                                    for(Entity i:dungeon.getEntity()) {
                                        if(i.gettype().equals("bomb") && i.getX() == x+1 && i.getY() == y) {
                                            Platform.runLater(new Runnable() {
                                                @Override
                                                public void run() {
                                                    squares.add(new ImageView(ground), i.getX(), i.getY());
                                                    
                                                }
                                            });
                                            break;
                                        }
                                    }
                                    count --;
                                }else if(count == 0) {
                                	Image key = new Image("/key.png");
                                	Image redkey = new Image("/redkey.png");
                                	Image bluekey = new Image("/bluekey.png");
                                	Image portal = new Image("/portal.png");
                                    for(Entity i:dungeon.getEntity()) {
                                    	Node back = null;
                                    	
                                    	for(Node n : squares.getChildren()) {
                                            if(n instanceof ImageView && squares.getRowIndex(n) == y && squares.getColumnIndex(n) == x+1) {
                                                back = n;
                                                break;
                                            }
                                        }
                                        for(Node n : squares.getChildren()) {
                                            if(n instanceof ImageView && squares.getRowIndex(n) == y && squares.getColumnIndex(n) == x+1) {
                                                if(n != back) {
                                                    n.setVisible(false);
                                                }
                                            }
                                        
                                        }
                                        
                                    }
                                    cancel();
                                }

                            }
                        };
                        time.schedule(task, 1000L, 1000L);
                        break;
                	}
                }
                
            }
            
        default:
            break;
        }
    }
    

    public void winGame() {
        Label wingame = new Label("You Win!!!");
        wingame.setStyle("-fx-background-color: #00FF00");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                squares.add(wingame, dungeon.getWidth()/2 -1,dungeon.getHeight(),dungeon.getWidth()/2 + 1,dungeon.getHeight());
            }
        });
    }


    public void dieGame() {
        Label wingame = new Label("You Dead!!!");
        wingame.setStyle("-fx-background-color: red");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                squares.add(wingame, dungeon.getWidth()/2-1,dungeon.getHeight(),dungeon.getWidth()/2 + 1,dungeon.getHeight());
                status.setText("Die");
                bombtime.setText(Integer.toString(0));
                swordtime.setText(Integer.toString(0));
            }
        });
    }
    
    public GridPane getGridPane() {
    	return squares;
    }
    
    public Dungeon getDungeon() {
    	return dungeon;
    }
}

