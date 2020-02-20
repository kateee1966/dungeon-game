/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import unsw.dungeon.entity.Bomb;
import unsw.dungeon.entity.Entity;
import unsw.dungeon.entity.Player;
import unsw.dungeon.interfacepack.GoalComponent;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private GoalComponent goal;
    
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.goal = null;
    }

    public GoalComponent getGoal() {
		return goal;
	}

	public void setGoal(GoalComponent goal) {
		this.goal = goal;
	}

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
    public List<Entity> getEntity(){
    	return this.entities;
    }
    
    public void useBomb() {
    	for(Entity i : entities) {
    		if(i.gettype().equals("bomb")) {
    			Bomb b = (Bomb) i;
    			b.attack(getPlayer(), b.getX(), b.getY());
    		}
    	}
    }
}
