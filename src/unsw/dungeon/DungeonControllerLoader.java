package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import unsw.dungeon.attackmode.Armed;
import unsw.dungeon.attackmode.Unarmed;
import unsw.dungeon.entity.BlackHole;
import unsw.dungeon.entity.Bomb;
import unsw.dungeon.entity.Boulder;
import unsw.dungeon.entity.Enemy;
import unsw.dungeon.entity.Entity;
import unsw.dungeon.entity.Exit;
import unsw.dungeon.entity.LockedDoor;
import unsw.dungeon.entity.LockedDoorBlue;
import unsw.dungeon.entity.LockedDoorRed;
import unsw.dungeon.entity.OpenKey;
import unsw.dungeon.entity.OpenKeyBlue;
import unsw.dungeon.entity.OpenKeyRed;
import unsw.dungeon.entity.Openeddoor;
import unsw.dungeon.entity.OpeneddoorBlue;
import unsw.dungeon.entity.OpeneddoorRed;
import unsw.dungeon.entity.Player;
import unsw.dungeon.entity.Portal;
import unsw.dungeon.entity.Potion;
import unsw.dungeon.entity.SwitchFloor;
import unsw.dungeon.entity.Sword;
import unsw.dungeon.entity.Treasure;
import unsw.dungeon.entity.Wall;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image invicibleplayerImage;
    private Image swordplayerImage;
    private Image wallImage;
    private Image boulderImage;
    private Image potionImage;
    private Image switchImage;
    private Image enemyImage;
    private Image swordImage;
    private Image keyImage;
    private Image keyblueImage;
    private Image keyredImage;
    private Image closeddoorImage;
    private Image closeddoorblueImage;
    private Image closeddoorredImage;
    private Image openeddoorImage;
    private Image openeddoorblueImage;
    private Image openeddoorredImage;
    private Image bombunlitImage;
    private Image bomblit1Image;
    private Image bomblit2Image;
    private Image bomblit3Image;
    private Image bomblit4Image;
    private Image treasureImage;
    private Image exitImage;
    private Image blackholeImage;
    private Image portalImage;
    
    
    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image("/human_new.png");
        invicibleplayerImage = new Image("/hound.png");
        swordplayerImage = new Image("/gnome.png");
        wallImage = new Image("/brick_brown_0.png");
        boulderImage = new Image("/boulder.png");
        potionImage = new Image("/brilliant_blue_new.png");
        switchImage = new Image("/pressure_plate.png");
        enemyImage = new Image("/deep_elf_master_archer.png");
        swordImage = new Image("/greatsword_1_new.png");
        keyImage = new Image("/key.png");
        keyblueImage = new Image("/bluekey.png");
        keyredImage = new Image("/redkey.png");
        closeddoorImage = new Image("/closed_door.png");
        closeddoorblueImage = new Image("/closed_bluedoor.png");
        closeddoorredImage = new Image("/closed_reddoor.png");
        openeddoorImage = new Image("/open_door.png");
        openeddoorblueImage = new Image("/open_bluedoor.png");
        openeddoorredImage = new Image("/open_reddoor.png");
        bombunlitImage = new Image("/bomb_unlit.png");
        bomblit1Image = new Image("/bomb_lit_1.png");
        bomblit2Image = new Image("/bomb_lit_2.png");
        bomblit3Image = new Image("/bomb_lit_3.png");
        bomblit4Image = new Image("/bomb_lit_4.png");
        treasureImage = new Image("/gold_pile.png");
        exitImage = new Image("/exit.png");
        blackholeImage = new Image("/blackHole.png");
        portalImage = new Image("/portal.png");
    }

    @Override
    public void onLoad(LockedDoorBlue doorblue) {
        ImageView view = new ImageView(closeddoorblueImage);
        addEntity(doorblue, view);  
    }

    @Override
    public void onLoad(LockedDoorRed doorred) {
        ImageView view = new ImageView(closeddoorredImage);
        addEntity(doorred, view);   
        
    }

    @Override
    public void onLoad(OpenKeyBlue keyblue) {
        ImageView view = new ImageView(keyblueImage);
        addEntity(keyblue, view);
    }

    @Override
    public void onLoad(OpenKeyRed keyred) {
        ImageView view = new ImageView(keyredImage);
        addEntity(keyred, view);
    }

    @Override
    public void onLoad(OpeneddoorBlue openeddoorBlue) {
        ImageView view = new ImageView(openeddoorblueImage);
        addEntity(openeddoorBlue, view);
    }

    @Override
    public void onLoad(OpeneddoorRed openeddoorRed) {
        ImageView view = new ImageView(openeddoorredImage);
        addEntity(openeddoorRed, view);
    }

    @Override
    public void onLoad(Player player) {
        if(player.getattackmode() instanceof Unarmed) {
            ImageView view = new ImageView(playerImage);
            addEntity(player, view);
        }
        else if(player.getattackmode() instanceof Armed) {
            ImageView view = new ImageView(swordplayerImage);
            addEntity(player, view);
        }
        else {
            ImageView view = new ImageView(invicibleplayerImage);
            addEntity(player, view);
        }
    }
    
    
    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }
    
    @Override
    public void onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        addEntity(exit, view);
    }
    
    @Override
    public void onLoad(Treasure treasure) {
        ImageView view = new ImageView(treasureImage);
        addEntity(treasure, view);
    }
    
    @Override
    public void onLoad(LockedDoor door) {
        ImageView view = new ImageView(closeddoorImage);
        addEntity(door, view);
    }

    @Override
    public void onLoad(OpenKey key) {
        ImageView view = new ImageView(keyImage);
        addEntity(key, view);
    }
    
    @Override
    public void onLoad(Boulder boulder) {
        ImageView view = new ImageView(boulderImage);
        addEntity(boulder, view);
    }

    @Override
    public void onLoad(SwitchFloor switchfloor) {
        ImageView view = new ImageView(switchImage);
        addEntity(switchfloor, view);
    }
    
    @Override
    public void onLoad(Bomb bomb) {
        ImageView view = new ImageView(bombunlitImage);
        addEntity(bomb, view);
    }

    @Override
    public void onLoad(Enemy enemy) {
        ImageView view = new ImageView(enemyImage);
        addEntity(enemy, view);
    }
    
    @Override
    public void onLoad(Sword sword) {
        ImageView view = new ImageView(swordImage);
        addEntity(sword, view);
    }

    @Override
    public void onLoad(Potion potion) {
        ImageView view = new ImageView(potionImage);
        addEntity(potion, view);
    }
    
    @Override
    public void onLoad(BlackHole hole) {
    	ImageView view = new ImageView(blackholeImage);
        addEntity(hole, view);
    }

    @Override
    public void onLoad(Openeddoor openeddoor) {
        ImageView view = new ImageView(openeddoorImage);
        addEntity(openeddoor, view);
    }
    
    @Override
    public void onLoad(Portal portal) {
    	ImageView view = new ImageView(portalImage);
        addEntity(portal, view);
    }
    
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }
    
    

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if(newValue.intValue()!= 0) {
                    node.setVisible(true);
                    GridPane.setColumnIndex(node, newValue.intValue());
                }
                else {
                    node.setVisible(false);
                }
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if(newValue.intValue()!= 0) {
                    node.setVisible(true);
                    GridPane.setRowIndex(node, newValue.intValue());
                }
                else {
                    node.setVisible(false);
                }
            }
        });
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }


}
