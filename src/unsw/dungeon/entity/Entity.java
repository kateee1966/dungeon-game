package unsw.dungeon.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.dungeon.interfacepack.Entityobserver;

/**
 * An entity in the dungeon.
 *
 */
public class Entity implements Entityobserver{

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    private String type;
    private int usedtimes;
    private int changed;
    private int covered;
    private int bombstatus;
    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y, String type) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.type = type;
        this.usedtimes = 0;
        this.changed = 0;
        this.bombstatus = 0;
    }

    public int getCovered() {
		return covered;
	}
        
	public void setCovered(int covered) {
		this.covered = covered;
	}

    public int getBombStatus() {
    	return this.bombstatus;
    }
    
    public void setBombStatus(int i) {
    	this.bombstatus = i;
    }
    
	public int isChanged() {
		return changed;
	}

	public void setChanged(int changed) {
		this.changed = changed;
	}

	public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public String gettype() {
    	return this.type;
    }
    
    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }

    
	public int getUsedtimes() {
		return usedtimes;
	}

	public void setUsedtimes(int usedtimes) {
		this.usedtimes = usedtimes;
	}
	
	public void attack(Player player, int x, int y) {
		
	}

	@Override
	public void update(Player player) {
		
	}
}
