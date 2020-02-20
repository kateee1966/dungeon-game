package unsw.dungeon.unittest;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import unsw.dungeon.*;
import unsw.dungeon.entity.*;

public class AddEntityTest {
	
	
	private Dungeon dungeon;
	
	
	@Before
	public void before() {
		// create a dungeon
		dungeon = new Dungeon(10,10);	
		
	}
	
	@Test
	public void buildWall() {
		dungeon.addEntity(new Wall(0, 0, "wall"));
		for(Entity e : dungeon.getEntity()) {
			if(e.gettype() == "wall") {
				Assert.assertEquals(0, e.getX());
				Assert.assertEquals(0, e.getY());
				System.out.println("Add a wall on column: " + e.getX() + " row: " + e.getY());
			}
		}
	}

	@Test
	public void addSword() {
		dungeon.addEntity(new Sword(2, 3, "sword"));
		for(Entity e : dungeon.getEntity()) {
			if(e.gettype() == "sword") {
				Assert.assertEquals(2, e.getX());
				Assert.assertEquals(3, e.getY());
				System.out.println("Add a Sword on column: " + e.getX() + " row: " + e.getY());
			}
		}
		
	}
	
	@Test
	public void addPotion() {
		dungeon.addEntity(new Potion(6, 7, "invincibility potion"));
		for(Entity e : dungeon.getEntity()) {
			if(e.gettype() == "invincibility potion") {
				Assert.assertEquals(6, e.getX());
				Assert.assertEquals(7, e.getY());
				System.out.println("Add a invincibility potion on column: " + e.getX() + " row: " + e.getY());
			}
		}
	}
	
	@Test
	public void addBomb() {
		dungeon.addEntity(new Bomb(3 ,3, "bomb"));
		for(Entity e : dungeon.getEntity()) {
			if(e.gettype() == "bomb") {
				Assert.assertEquals(3, e.getX());
				Assert.assertEquals(3, e.getY());
				System.out.println("Add a bomb on column: " + e.getX() + " row: " + e.getY());
			}
		}
	}
	
	@Test
	public void addKey() {
		dungeon.addEntity(new Bomb(3 ,4, "key"));
		for(Entity e : dungeon.getEntity()) {
			if(e.gettype() == "key") {
				Assert.assertEquals(3, e.getX());
				Assert.assertEquals(4, e.getY());
				System.out.println("Add a key on column: " + e.getX() + " row: " + e.getY());
			}
		}
	}
	@Test
	public void addDoor() {
		dungeon.addEntity(new LockedDoor(5, 5, "door"));
		for(Entity e : dungeon.getEntity()) {
			if(e.gettype() == "door") {
				Assert.assertEquals(5, e.getX());
				Assert.assertEquals(5, e.getY());
				System.out.println("Add a door on column: " + e.getX() + " row: " + e.getY());
			}
		}
	}
}
