package unsw.dungeon.unittest;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Bag;
import unsw.dungeon.entity.Bomb;
import unsw.dungeon.entity.Boulder;
import unsw.dungeon.entity.Enemy;
import unsw.dungeon.entity.Entity;
import unsw.dungeon.entity.LockedDoor;
import unsw.dungeon.entity.OpenKey;
import unsw.dungeon.entity.Player;
import unsw.dungeon.entity.Portal;
import unsw.dungeon.entity.Potion;
import unsw.dungeon.entity.SwitchFloor;
import unsw.dungeon.entity.Sword;
import unsw.dungeon.entity.Wall;
import unsw.dungeon.goal.ExitGoal;
import unsw.dungeon.interfacepack.GoalComponent;

public class PlayerTest {

	private Dungeon dungeon;
	
	@Before
	public void before() {
		// create a dungeon
		dungeon = new Dungeon(10,10);
		
		// set wall
		for(int i = 0; i < 10; i++) {
			dungeon.addEntity(new Wall(0, i, "wall"));
		}
		for(int i = 1; i < 10; i++) {
			dungeon.addEntity(new Wall(i, 0, "wall"));
		}
		for(int i = 1; i < 9; i++) {
			dungeon.addEntity(new Wall(9, i, "wall"));
		}
		for(int i = 1; i< 9; i++) {
			dungeon.addEntity(new Wall(i, 9, "wall"));
		}
				
		// set player
		Player player = new Player(dungeon, 1, 1, "player");
		dungeon.addEntity(player);
		dungeon.setPlayer(player);
		
		Bag bag = new Bag();
		player.setBag(bag);
		GoalComponent a = new ExitGoal(0);
		dungeon.setGoal(a);
	}
	
	@Test
	public void testWall() {
		// player want to move up, from (1,1) to (1,0)
		// it should not work, because (1,0) is wall
		// so player's position should still (1,1);
		dungeon.getPlayer().moveUp();	
		Assert.assertEquals(1, dungeon.getPlayer().getX());
		Assert.assertEquals(1, dungeon.getPlayer().getY());
	}

	@Test
	public void testPickUp() {
		// -------test pick up one sword -------
		// set Sword
		Sword sword = new Sword(1, 2, "sword");
		dungeon.addEntity(sword);
		// move down to pick up sword
		dungeon.getPlayer().moveDown();
		// should from (1,1) to (1,2)
		// test position of player
		Assert.assertEquals(1, dungeon.getPlayer().getX());
		Assert.assertEquals(2, dungeon.getPlayer().getY());
		// there is a sword on (1,2)
		
		
		// ------- test pick up one key ----------
		// set one key
		OpenKey key = new OpenKey(1, 3, "key");
		dungeon.addEntity(key);
		// continue move down to pick up key
		dungeon.getPlayer().moveDown();
		
		
		
		// -------test pick up one bomb --------
		// set one bomb
		Bomb bomb = new Bomb(1, 4, "bomb");
		dungeon.addEntity(bomb);
		// continue move down to pick up bomb
		dungeon.getPlayer().moveDown();
		
		
		
		// ------test pick up one potion --------
		// set one potion
		Potion potion = new Potion(1, 5, "invincibility potion");
		dungeon.addEntity(potion);
		// continue move down to pick up potion
		dungeon.getPlayer().moveDown();
				
		// ------test pick up successfully
		Assert.assertEquals(5, dungeon.getPlayer().getBag().getSwordattacktimes());
		Assert.assertEquals(1, dungeon.getPlayer().getBag().getKey());
		Assert.assertEquals(1, dungeon.getPlayer().getBag().getBomb());
		Assert.assertEquals(30, dungeon.getPlayer().getBag().getInviciblepotion());
		
		
	}
	
	@Test
	public void testPickUpUnderPotion() {
		Potion potion = new Potion(1, 2, "invincibility potion");
		dungeon.addEntity(potion);
		Sword sword = new Sword(1, 3, "sword");
		dungeon.addEntity(sword);
		dungeon.getPlayer().moveDown();// now livincibe status
		dungeon.getPlayer().moveDown();
		Assert.assertEquals(5, dungeon.getPlayer().getBag().getSwordattacktimes());
	//	Assert.assertEquals(29, dungeon.getPlayer().getBag().getInviciblepotion());
		
	}
	
	@Test
	public void testPickUpVarious() {
		Sword sword = new Sword(1, 2, "sword");
		dungeon.addEntity(sword);
		Bomb bomb = new Bomb(1, 2, "bomb");
		dungeon.addEntity(bomb);
		dungeon.getPlayer().moveDown();
		Assert.assertEquals(5, dungeon.getPlayer().getBag().getSwordattacktimes());
		Assert.assertEquals(1, dungeon.getPlayer().getBag().getBomb());
		
	}
	@Test
	public void testPickUpFail() {
		
		//------ test cannot duplicate pick up sword
		// set a sword just can use one time
		Sword sword = new Sword(1, 2, "sword");
		sword.setUsedtimes(1);
		dungeon.addEntity(sword);
		// move down player to pick up
		dungeon.getPlayer().moveDown();

		// set a new sword which can use 5 times
		Sword sword1 = new Sword(1, 3, "sword");
		dungeon.addEntity(sword1);
		dungeon.getPlayer().moveDown();
		Assert.assertEquals(1, dungeon.getPlayer().getBag().getSwordattacktimes());
		
		
		
		//-------test cannot duplicate pick up key
		// set one key
		OpenKey key = new OpenKey(1, 4, "key");
		dungeon.addEntity(key);
		// continue move down to pick up key
		dungeon.getPlayer().moveDown();
		
		// cannot duplicate pick up,
		// set another key
		OpenKey key1 = new OpenKey(1, 5, "key");
		dungeon.addEntity(key1);
		// continue move down to pick up key
		dungeon.getPlayer().moveDown();
		// cannot duplicate pick up, key number should still 1
		Assert.assertEquals(1, dungeon.getPlayer().getBag().getKey());
		
		
		
		// ----- test cannot duplicate pick up bomb
		// set one bomb
		Bomb bomb = new Bomb(1, 6, "bomb");
		dungeon.addEntity(bomb);
		// continue move down to pick up bomb
		dungeon.getPlayer().moveDown();
		
		Bomb bomb1 = new Bomb(1, 7, "bomb");
		dungeon.addEntity(bomb1);
		dungeon.getPlayer().moveDown();
		// cannot duplicate pick up, bomb number should still 1
		Assert.assertEquals(1, dungeon.getPlayer().getBag().getBomb());
		
		
		// ------ test cannot duplicate pick up potion
		// set one potion
		Potion potion = new Potion(1, 8, "invincibility potion");
		dungeon.addEntity(potion);
		// continue move down to pick up potion
		dungeon.getPlayer().moveDown();
		
		Potion potion1 = new Potion(2, 8, "invincibility potion");
		dungeon.addEntity(potion1);
		// cannot duplicate pick up, because potion still work on player
		dungeon.getPlayer().moveRight();
		Assert.assertEquals(2, potion1.getX());
		Assert.assertEquals(8, potion1.getY());
	}
	
	
	@Test
	public void testEnemyMoveToPlayer() {
		System.out.println("------- Test Enemy Move to Player ---------");
		Enemy enemy = new Enemy(8, 8, "enemy");
		dungeon.addEntity(enemy);
		System.out.println("The initial position of player is " + dungeon.getPlayer().getX() + " " + dungeon.getPlayer().getY() + " " + "the initial position of enemy is " + enemy.getX() + " " + enemy.getY());
		dungeon.getPlayer().moveDown();
		System.out.println("After Player move one square, the position of player is " + dungeon.getPlayer().getX() + " " + dungeon.getPlayer().getY() + " " + "the position of enemy is " + enemy.getX() + " " + enemy.getY());
		dungeon.getPlayer().moveRight();
		System.out.println("After Player move one square, the position of player is " + dungeon.getPlayer().getX() + " " + dungeon.getPlayer().getY() + " " + "the position of enemy is " + enemy.getX() + " " + enemy.getY());
		dungeon.getPlayer().moveRight();
		System.out.println("After Player move one square, the position of player is " + dungeon.getPlayer().getX() + " " + dungeon.getPlayer().getY() + " " + "the position of enemy is " + enemy.getX() + " " + enemy.getY());
	}
	
	@Test
	public void testEnemyMoveAwayFromPlayer() {
		System.out.println("------- Test Enemy Move Away From Invincibe Player ---------");
		Enemy enemy = new Enemy(5, 5, "enemy");
		dungeon.addEntity(enemy);
		System.out.println("The initial position of player is " + dungeon.getPlayer().getX() + " " + dungeon.getPlayer().getY() + " " + "the initial position of enemy is " + enemy.getX() + " " + enemy.getY());
		Potion potion = new Potion(1, 2, "invincibility potion");
		dungeon.addEntity(potion);
		dungeon.getPlayer().moveDown();
		System.out.println("After Player move one square, the position of player is " + dungeon.getPlayer().getX() + " " + dungeon.getPlayer().getY() + " " + "the position of enemy is " + enemy.getX() + " " + enemy.getY());
		dungeon.getPlayer().moveRight();
		System.out.println("After Player move one square, the position of player is " + dungeon.getPlayer().getX() + " " + dungeon.getPlayer().getY() + " " + "the position of enemy is " + enemy.getX() + " " + enemy.getY());
		dungeon.getPlayer().moveRight();
		System.out.println("After Player move one square, the position of player is " + dungeon.getPlayer().getX() + " " + dungeon.getPlayer().getY() + " " + "the position of enemy is " + enemy.getX() + " " + enemy.getY());
	}
	
	
	
	@Test
	public void testSwordAttack() {
		Sword sword = new Sword(1, 2, "sword");
		dungeon.addEntity(sword);
		dungeon.getPlayer().moveDown();
		Enemy enemy = new Enemy(2, 2, "enemy");
		dungeon.addEntity(enemy);
		dungeon.getPlayer().moveRight();		
		Assert.assertEquals(0, enemy.getX());
		Assert.assertEquals(0, enemy.getY());
		Assert.assertEquals(4, dungeon.getPlayer().getBag().getSwordattacktimes());
	}
	
	
	@Test
	public void testPotionAttack() {
		Potion potion = new Potion(1, 2, "invincibility potion");
		dungeon.addEntity(potion);
		Sword sword = new Sword(2, 5, "sword");
		dungeon.addEntity(sword);
		dungeon.getPlayer().moveDown();	// now invincibe status
	
		dungeon.getPlayer().moveDown();
	
		dungeon.getPlayer().moveDown();

		dungeon.getPlayer().moveDown();

		dungeon.getPlayer().moveRight();
		// now at (2,5)
		
		dungeon.getPlayer().moveRight();
		// now at (3,5)
		
		Enemy enemy = new Enemy(3, 6, "enemy");
		dungeon.addEntity(enemy);
		dungeon.getPlayer().moveDown();
		Assert.assertEquals(0, enemy.getX());
		Assert.assertEquals(0, enemy.getY());

	}

	@Test
	public void testPotionDisappear() {
		Potion potion = new Potion(1, 2, "invincibility potion");
		dungeon.addEntity(potion);
		dungeon.getPlayer().moveDown();
		
		for(int i = 31; i >= 1; i--) {
			try {
				//System.out.println(i);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Enemy enemy = new Enemy(1, 3, "enemy");
		dungeon.addEntity(enemy);
		dungeon.getPlayer().moveDown();
		Assert.assertEquals(1, enemy.getX());
		Assert.assertEquals(3, enemy.getY());
		
	}
	@Test
	public void testBombAttack() {
		// set bomb and all bomb status
		Bomb bomb = new Bomb(1, 2, "bomb");
		dungeon.addEntity(bomb);
		// player get bomb
		dungeon.getPlayer().moveDown();
		Assert.assertEquals(1, dungeon.getPlayer().getBag().getBomb());
		dungeon.getPlayer().moveRight();	// now player at 2,2
		dungeon.getPlayer().moveRight();    // now player at 3,2
		dungeon.getPlayer().moveRight();    // now player at 4,2
		dungeon.getPlayer().moveDown();    // now player at 4,3
		dungeon.getPlayer().moveDown();    // now player at 4,4
		
		// set a boulder on 5,5
		Boulder boulder = new Boulder(6, 4, "boulder");
		dungeon.addEntity(boulder);
		
		// player use bomb
		dungeon.getPlayer().placebomb();
		System.out.println(dungeon.getPlayer().getX());
		System.out.println(dungeon.getPlayer().getY());
		Enemy enemy = new Enemy(5, 4, "enemy");
		dungeon.addEntity(enemy);
		
		for(int i = 5; i >= 1; i--) {
			try {
				//System.out.println(i);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Assert.assertEquals(0, enemy.getX());
		Assert.assertEquals(0, enemy.getY());
		Assert.assertEquals(0, boulder.getX());
		Assert.assertEquals(0, boulder.getY());
		
		
	}
	
	
	@Test
	public void testPushBoulder() {
		SwitchFloor switchs = new SwitchFloor(1, 3, "switch floor");
		
		Boulder boulder = new Boulder(1, 2, "boulder");
		dungeon.addEntity(boulder);
		dungeon.addEntity(switchs);
		
		dungeon.getPlayer().moveDown();
		Assert.assertEquals(1, boulder.getX());
		Assert.assertEquals(3, boulder.getY());
		for(Entity i : dungeon.getEntity()) {
			if(i.gettype().equals("switch floor")) {
				Assert.assertEquals(1, i.getCovered());
			}
		}
	}
	
	
	@Test
	public void testCannotPushTwoBoulders() {
		Boulder boulder = new Boulder(1, 2, "boulder");
		dungeon.addEntity(boulder);
		Boulder boulder1 = new Boulder(1, 3, "boulder");
		dungeon.addEntity(boulder1);
		
		dungeon.getPlayer().moveDown();
		Assert.assertEquals(1, dungeon.getPlayer().getX());
		Assert.assertEquals(1, dungeon.getPlayer().getY());
	}
	
	
	@Test
	public void testCannotPushBoulderNearbyDoor() {
		Boulder boulder = new Boulder(1, 2, "boulder");
		dungeon.addEntity(boulder);
		LockedDoor door = new LockedDoor(1, 3, "lockeddoor");
		dungeon.addEntity(door);
		
		dungeon.getPlayer().moveDown();
		Assert.assertEquals(1, boulder.getX());
		Assert.assertEquals(2, boulder.getY());
		Assert.assertEquals(1, dungeon.getPlayer().getX());
		Assert.assertEquals(1, dungeon.getPlayer().getY());
	}
	
	
	@Test
	public void testCannotPushBoulderNearbyWall() {
		Boulder boulder = new Boulder(1, 2, "boulder");
		dungeon.addEntity(boulder);
		dungeon.getPlayer().moveRight();
		dungeon.getPlayer().moveDown();
		dungeon.getPlayer().moveLeft();
		Assert.assertEquals(1, boulder.getX());
		Assert.assertEquals(2, boulder.getY());
		Assert.assertEquals(2, dungeon.getPlayer().getX());
		Assert.assertEquals(2, dungeon.getPlayer().getY());
		
	}
	
	
	@Test
	public void testThrow() {
		Sword sword = new Sword(1, 2, "sword");
		dungeon.addEntity(sword);
		// pick up sword;
		dungeon.getPlayer().moveDown();
		Assert.assertEquals(5, dungeon.getPlayer().getBag().getSwordattacktimes());
		// if no wall or boulder, player can throw his sword
		dungeon.getPlayer().throwsword();
		// test no sword in this bag(useTime become 0)
		Assert.assertEquals(0, dungeon.getPlayer().getBag().getSwordattacktimes());
	}
	
	
	
	@Test
	public void testThrowFailByWall() {
		Sword sword = new Sword(1, 2, "sword");
		dungeon.addEntity(sword);
		// pick up sword
		dungeon.getPlayer().moveDown();
		Assert.assertEquals(5, dungeon.getPlayer().getBag().getSwordattacktimes());
		// if there is a wall on the right of the player
		Wall awall = new Wall(dungeon.getPlayer().getX() + 1, dungeon.getPlayer().getY(), "wall");
		dungeon.addEntity(awall);
		dungeon.getPlayer().throwsword();
		Assert.assertEquals(5, dungeon.getPlayer().getBag().getSwordattacktimes());
	}
	
	
	
	@Test 
	public void testThrowFailByBoulder() {
		Sword sword = new Sword(1, 2, "sword");
		dungeon.addEntity(sword);
		// pick up sword
		dungeon.getPlayer().moveDown();
		Assert.assertEquals(5, dungeon.getPlayer().getBag().getSwordattacktimes());
		// if there is a wall on the right of the player
		Boulder aboulder = new Boulder(dungeon.getPlayer().getX() + 1, dungeon.getPlayer().getY(), "boulder");
		dungeon.addEntity(aboulder);
		dungeon.getPlayer().throwsword();
		Assert.assertEquals(5, dungeon.getPlayer().getBag().getSwordattacktimes());
	}
	
	
	
	@Test
	public void testPlayerDeadByEnemy() {
		System.out.println("--------Test Dead By Enemy----------");
		Enemy enemy = new Enemy(1, 2, "enemy");
		dungeon.addEntity(enemy);
		dungeon.getPlayer().moveDown();
	}
	
	
	
	@Test
	public void testPlayerDeadByBomb() {
		System.out.println("--------Test Dead By Bomb----------");
		Bomb bomb = new Bomb(1, 2, "bomb");
		dungeon.addEntity(bomb);
		
		
		
		dungeon.getPlayer().moveDown();
		dungeon.getPlayer().placebomb();

		for(int i = 5; i >= 1; i--) {
			try {
				//System.out.println(i);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testInvincibePlayerCannotDeadByBomb() {
		Bomb bomb = new Bomb(1, 2, "bomb");
		dungeon.addEntity(bomb);
		
		
		Potion potion = new Potion(1, 3, "invincibility potion");
		dungeon.addEntity(potion);
		
		dungeon.getPlayer().moveDown();
		dungeon.getPlayer().moveDown();// now player at (1,3)
		
		dungeon.getPlayer().placebomb();
		
		for(int i = 5; i >= 1; i--) {
			try {
				//System.out.println(i);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Assert.assertEquals(1, dungeon.getPlayer().getX());
		Assert.assertEquals(3, dungeon.getPlayer().getY());
	}
	
	@Test
	public void testLockedDoor() {
		LockedDoor door = new LockedDoor(4, 1, "lockeddoor");
		dungeon.addEntity(door);
		
		dungeon.getPlayer().moveRight(); // 2,1
		dungeon.getPlayer().moveRight();//3,1
		dungeon.getPlayer().moveRight();//3,1

		Assert.assertEquals(3, dungeon.getPlayer().getX());
		Assert.assertEquals(1, dungeon.getPlayer().getY());
		
		Enemy enemy = new Enemy(5, 1, "enemy");
		dungeon.addEntity(enemy);
		dungeon.getPlayer().moveLeft();

	}

	
	
	@Test
	public void testOpenDoor() {
		// test player and enemy can pass open door
		OpenKey key = new OpenKey(3, 1, "openkey");
		dungeon.addEntity(key);
		
		LockedDoor door = new LockedDoor(5, 1, "lockeddoor");
		dungeon.addEntity(door);
		
		
		dungeon.getPlayer().moveRight();

		
		dungeon.getPlayer().moveRight();
		// get Key
		
		dungeon.getPlayer().moveRight();
		
		Enemy enemy = new Enemy(2, 1, "enemy");
		dungeon.addEntity(enemy);
		
		dungeon.getPlayer().moveRight();
		//System.out.println(enemy.getX());
		//System.out.println(enemy.getY());
		Assert.assertEquals(5, dungeon.getPlayer().getX());
		Assert.assertEquals(1, dungeon.getPlayer().getY());
		
		dungeon.getPlayer().moveRight();
		Assert.assertEquals(6, dungeon.getPlayer().getX());
		Assert.assertEquals(1, dungeon.getPlayer().getY());
		// key has been used, key number should be 0
		Assert.assertEquals(0, dungeon.getPlayer().getBag().getKey());
		
		dungeon.getPlayer().moveRight();


		Assert.assertEquals(7, dungeon.getPlayer().getX());
		Assert.assertEquals(1, dungeon.getPlayer().getY());
		
		dungeon.getPlayer().moveRight();

		Assert.assertEquals(8, dungeon.getPlayer().getX());
		Assert.assertEquals(1, dungeon.getPlayer().getY());
	}
	
	@Test
	public void testPortal() {
		Portal portal = new Portal(1, 2, "portal", 1);
		dungeon.addEntity(portal);
		Portal portal1= new Portal(6, 6, "portal", 1);
		dungeon.addEntity(portal1);
		
		Portal portal2 = new Portal(6, 8, "portal", 2);
		dungeon.addEntity(portal2);
		Portal portal3= new Portal(9, 9, "portal", 2);
		dungeon.addEntity(portal3);
		
		dungeon.getPlayer().moveDown();
		Assert.assertEquals(6, dungeon.getPlayer().getX());
		Assert.assertEquals(6, dungeon.getPlayer().getY());
		dungeon.getPlayer().moveDown();
		dungeon.getPlayer().moveUp();
		Assert.assertEquals(1, dungeon.getPlayer().getX());
		Assert.assertEquals(2, dungeon.getPlayer().getY());
		dungeon.getPlayer().moveUp();
		dungeon.getPlayer().moveDown();
		Assert.assertEquals(6, dungeon.getPlayer().getX());
		Assert.assertEquals(6, dungeon.getPlayer().getY());
		dungeon.getPlayer().moveDown();
		dungeon.getPlayer().moveDown();
		Assert.assertEquals(9, dungeon.getPlayer().getX());
		Assert.assertEquals(9, dungeon.getPlayer().getY());
		
	}

}
