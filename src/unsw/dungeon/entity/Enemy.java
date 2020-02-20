package unsw.dungeon.entity;

import java.util.List;

import unsw.dungeon.Dungeon;
import unsw.dungeon.attackmode.Invicibleplayer;
import unsw.dungeon.interfacepack.Moving;

public class Enemy extends Entity implements Moving{
	public Enemy(int x, int y, String type) {
        super(x, y, type);
    }
	
	@Override
	public void attack(Player player, int x, int y) {
		if ( (this.getX()!=0)  && (this.getY()!=0)) {
			if(!(player.getattackmode() instanceof Invicibleplayer)) {
				if ( (player.getX() == this.getX()) && (player.getY() == this.getY())) {
					player.getattackmode().attack(player.getEntityList(), player.getX(), player.getY());
					return;
				}
				if(player.getX() <= x) {
					if(whethercouldmoveleft(player.getDungeon(), this.getX(), this.getY())) {
						moveLeft();
					}
					else if (player.getY() <= y){
						if(whethercouldmoveup(player.getDungeon(), this.getX(), this.getY())) {
							moveUp();
						}
					}
					else if(whethercouldmovedown(player.getDungeon(), this.getX(), this.getY())) {
						moveDown();
					}
					else if(whethercouldmoveright(player.getDungeon(), this.getX(), this.getY())){
						moveRight();
					}
				}
				else if (player.getY() <= y){
					if(whethercouldmoveup(player.getDungeon(), this.getX(), this.getY())) {
						moveUp();
					}
					else if (player.getX() <= x) {
						if(whethercouldmoveleft(player.getDungeon(), this.getX(), this.getY())) {
							moveLeft();
						}
					}
					else if(whethercouldmoveright(player.getDungeon(), this.getX(), this.getY())) {
						moveRight();
					}
					else if(whethercouldmovedown(player.getDungeon(), this.getX(), this.getY())){
						moveDown();
					}
				}
				else if(player.getX() >= x) {
					if(whethercouldmoveright(player.getDungeon(), this.getX(), this.getY())) {
						moveRight();
					}
					else if (player.getY() >= y) {
						if(whethercouldmovedown(player.getDungeon(), this.getX(), this.getY())) {
							moveDown();
						}
					}
					else if(whethercouldmoveup(player.getDungeon(), this.getX(), this.getY())) {
						moveUp();
					}
					else if(whethercouldmoveleft(player.getDungeon(), this.getX(), this.getY())){
						moveLeft();
					}
				}
				else if (player.getY() >= y){
					if(whethercouldmovedown(player.getDungeon(), this.getX(), this.getY())) {
						moveDown();
					}
					else if (player.getX() >= x) {
						if(whethercouldmoveright(player.getDungeon(), this.getX(), this.getY())) {
							moveRight();
						}
					}
					else if(whethercouldmoveleft(player.getDungeon(), this.getX(), this.getY())) {
						moveLeft();
					}
					else if(whethercouldmoveup(player.getDungeon(), this.getX(), this.getY())){
						moveUp();
					}
				}
				if ( (player.getX() == this.getX()) && (player.getY() == this.getY())) {
					player.getattackmode().attack(player.getEntityList(), player.getX(), player.getY());
				}
			}	
			else {
				if ( (player.getX() == this.getX()) && (player.getY() == this.getY())) {
					player.getattackmode().attack(player.getEntityList(), player.getX(), player.getY());
					return;
				}
				if(player.getX() >= x) {
					if(whethercouldmoveleft(player.getDungeon(), this.getX(), this.getY())) {
						moveLeft();
					}
					else if (player.getY() >= y){
						if(whethercouldmoveup(player.getDungeon(), this.getX(), this.getY())) {
							moveUp();
						}
					}
					else if(whethercouldmovedown(player.getDungeon(), this.getX(), this.getY())) {
						moveDown();
					}
					else if(whethercouldmoveright(player.getDungeon(), this.getX(), this.getY())){
						moveRight();
					}
				}
				else if (player.getY() >= y){
					if(whethercouldmoveup(player.getDungeon(), this.getX(), this.getY())) {
						moveUp();
					}
					else if(player.getX() >= x) {
						if(whethercouldmoveleft(player.getDungeon(), this.getX(), this.getY())) {
							moveLeft();
						}
					}
					else if(whethercouldmoveright(player.getDungeon(), this.getX(), this.getY())) {
						moveRight();
					}
					else if(whethercouldmovedown(player.getDungeon(), this.getX(), this.getY())){
						moveDown();
					}
				}
				else if (player.getX() <= x){
					if(whethercouldmoveright(player.getDungeon(), this.getX(), this.getY())) {
						moveRight();
					}
					else if(player.getY() <= y) {
						if(whethercouldmovedown(player.getDungeon(), this.getX(), this.getY())) {
							moveDown();
						}
					}
					else if(whethercouldmoveup(player.getDungeon(), this.getX(), this.getY())) {
						moveUp();
					}
					else if(whethercouldmoveleft(player.getDungeon(), this.getX(), this.getY())){
						moveLeft();
					}
				}
				else if (player.getY() <= y){
					if(whethercouldmovedown(player.getDungeon(), this.getX(), this.getY())) {
						moveDown();
					}
					else if(player.getX() <= x) {
						if(whethercouldmoveright(player.getDungeon(), this.getX(), this.getY())) {
							moveRight();
						}
					}
					else if(whethercouldmoveleft(player.getDungeon(), this.getX(), this.getY())) {
						moveLeft();
					}
					else if(whethercouldmoveup(player.getDungeon(), this.getX(), this.getY())){
						moveUp();
					}
				}
				if ( (player.getX() == this.getX()) && (player.getY() == this.getY())) {
					player.getattackmode().attack(player.getEntityList(), player.getX(), player.getY());
				}
			}
		}
	}

	@Override
	public void moveUp() {
		this.y().set(this.getY()-1);
		
	}

	@Override
	public void moveDown() {
		this.y().set(this.getY()+1);
		
	}

	@Override
	public void moveLeft() {
		this.x().set(this.getX()-1);
		
	}

	@Override
	public void moveRight() {
		this.x().set(this.getX()+1);
		
	}

	@Override
	public boolean whethercouldmoveleft(Dungeon dungeon, int x, int y) {
		boolean could = true;
    	List<Entity> EntityList = dungeon.getEntity();
    	for (Entity i: EntityList) {
    		if( ((i.getX()==x-1) && (i.getY() == y) && (i.gettype().equals("wall"))) || ((i.getX()==x-1) && (i.getY() == y) && (i.gettype().equals("boulder"))) || ((i.getX()==x-1) && (i.getY() == y) && ((i.gettype().equals("lockeddoor"))|| (i.gettype().equals("enemy"))||(i.gettype().equals("lockeddoorred")) || (i.gettype().equals("lockeddoorblue"))))){
    			could = false;
    		}
    	}
    	return could;
	}

	@Override
	public boolean whethercouldmoveright(Dungeon dungeon, int x, int y) {
		boolean could = true;
    	List<Entity> EntityList = dungeon.getEntity();
    	for (Entity i: EntityList) {
    		if( ((i.getX()==x+1) && (i.getY() == y) && (i.gettype().equals("wall"))) || ((i.getX()==x+1) && (i.getY() == y) && (i.gettype().equals("boulder"))) || ((i.getX()==x+1) && (i.getY() == y) && ((i.gettype().equals("lockeddoor"))|| (i.gettype().equals("enemy"))||(i.gettype().equals("lockeddoorred")) || (i.gettype().equals("lockeddoorblue"))))){
    			could = false;
    		}
    	}
    	return could;
	}

	@Override
	public boolean whethercouldmoveup(Dungeon dungeon, int x, int y) {
		boolean could = true;
    	List<Entity> EntityList = dungeon.getEntity();
    	for (Entity i: EntityList) {
    		if( ((i.getX()==x) && (i.getY() == y-1) && (i.gettype().equals("wall"))) || ((i.getX()==x) && (i.getY() == y-1) && (i.gettype().equals("boulder"))) || ((i.getX()==x) && (i.getY() == y-1) && ((i.gettype().equals("lockeddoor"))|| (i.gettype().equals("enemy"))||(i.gettype().equals("lockeddoorred")) || (i.gettype().equals("lockeddoorblue"))))){
    			could = false;
    		}
    	}
    	return could;
	}

	@Override
	public boolean whethercouldmovedown(Dungeon dungeon, int x, int y) {
		boolean could = true;
    	List<Entity> EntityList = dungeon.getEntity();
    	for (Entity i: EntityList) {
    		if( ((i.getX()==x) && (i.getY() == y+1) && (i.gettype().equals("wall"))) || ((i.getX()==x) && (i.getY() == y+1) && (i.gettype().equals("boulder"))) || ((i.getX()==x) && (i.getY() == y+1) && ((i.gettype().equals("lockeddoor"))|| (i.gettype().equals("enemy"))||(i.gettype().equals("lockeddoorred")) || (i.gettype().equals("lockeddoorblue"))))){
    			could = false;
    		}
    	}
    	return could;
	}
	
	@Override
	public void update(Player player) {
		this.attack(player, this.getX(), this.getY());
	}
}
