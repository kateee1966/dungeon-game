package unsw.dungeon.unittest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Bag;
import unsw.dungeon.entity.Boulder;
import unsw.dungeon.entity.Enemy;
import unsw.dungeon.entity.Exit;
import unsw.dungeon.entity.Player;
import unsw.dungeon.entity.SwitchFloor;
import unsw.dungeon.entity.Sword;
import unsw.dungeon.entity.Treasure;
import unsw.dungeon.entity.Wall;
import unsw.dungeon.goal.AndGoal;
import unsw.dungeon.goal.BouldersGoal;
import unsw.dungeon.goal.EnemiesGoal;
import unsw.dungeon.goal.ExitGoal;
import unsw.dungeon.goal.OrGoal;
import unsw.dungeon.goal.TreasureGoal;
import unsw.dungeon.interfacepack.GoalComponent;

public class GoalTest {

	private Dungeon dungeon;
	
	@Before
	public void Before() {
		
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
		
	}
	
	@Test
	public void testExitGoal() {
		System.out.println("--------- Test Arrival Exit To Get Win ");
		GoalComponent goal = new ExitGoal(0);
		dungeon.setGoal(goal);
		
		Exit exit = new Exit(1, 3, "exit");
		dungeon.addEntity(exit);
		
		dungeon.getPlayer().moveDown();
		dungeon.getPlayer().moveDown();
		Assert.assertEquals(1, dungeon.getPlayer().getX());
		Assert.assertEquals(3, dungeon.getPlayer().getY());
		
	}
	
	
	@Test
	public void testTreasureGoal() {
		System.out.println("--------- Test Collect All Treasure To Get Win ");
		GoalComponent goal = new TreasureGoal(0);
		dungeon.setGoal(goal);
		Treasure treasure = new Treasure(1, 2, "treasure");
		dungeon.addEntity(treasure);
		Treasure treasure1 = new Treasure(1, 3, "treasure");
		dungeon.addEntity(treasure1);
		Treasure treasure2 = new Treasure(1, 4, "treasure");
		dungeon.addEntity(treasure2);
		
		dungeon.getPlayer().moveDown();
		dungeon.getPlayer().moveDown();
		dungeon.getPlayer().moveDown();
		Assert.assertEquals(0, treasure.getX());
		Assert.assertEquals(0, treasure.getY());
		Assert.assertEquals(0, treasure1.getX());
		Assert.assertEquals(0, treasure1.getY());
		Assert.assertEquals(0, treasure2.getX());
		Assert.assertEquals(0, treasure2.getY());
	}
	
	
	@Test
	public void testEnemyGoal() {
		System.out.println("--------- Test Kill All Enemy To Get Win ");
		GoalComponent goal = new EnemiesGoal(0);
		dungeon.setGoal(goal);
		Enemy enemy = new Enemy(4, 1, "enemy");
		dungeon.addEntity(enemy);
		Sword sword = new Sword(2, 1, "sword");
		dungeon.addEntity(sword);
		
		dungeon.getPlayer().moveRight();
	//	System.out.println(enemy.getX());
	//	System.out.println(enemy.getY());
		
		
		dungeon.getPlayer().moveRight();
	//	System.out.println(enemy.getX());
	//	System.out.println(enemy.getY());
		Assert.assertEquals(0, enemy.getX());
		Assert.assertEquals(0, enemy.getY());
	}
	
	@Test
	public void testBoulderTest() {
		System.out.println("--------- Test Cover All Floor Switch To Get Win ");
		GoalComponent goal = new BouldersGoal(0);
		dungeon.setGoal(goal);
		Boulder boulder = new Boulder(2, 1, "boulder");
		dungeon.addEntity(boulder);
		Boulder boulder1 = new Boulder(1, 2, "boulder");
		dungeon.addEntity(boulder1);
		SwitchFloor switchs = new SwitchFloor(3, 1, "switch floor");
		dungeon.addEntity(switchs);
		SwitchFloor switchs1 = new SwitchFloor(1, 3, "switch floor");
		dungeon.addEntity(switchs1);
		
		
		
		dungeon.getPlayer().moveRight();
		/*System.out.println(dungeon.getPlayer().getX());
		System.out.println(dungeon.getPlayer().getY());
		System.out.println(boulder.getX());
		System.out.println(boulder.getY());
		System.out.println(switchs.getCovered());*/
		Assert.assertEquals(1, switchs.getCovered());

		
		dungeon.getPlayer().moveLeft();
		dungeon.getPlayer().moveDown();
		Assert.assertEquals(1, switchs1.getCovered());
	}
	
	
	@Test
	public void testExitAndEnemy() {
		System.out.println("--------- Test Kill All Enemy And Exit To Get Win ");
		GoalComponent goal = new AndGoal(1);
		GoalComponent exitgoal = new ExitGoal(0);
		GoalComponent enemygoal = new EnemiesGoal(0);
		goal.add(exitgoal);
		goal.add(enemygoal);
		
		dungeon.setGoal(goal);
		
		Exit exit = new Exit(3, 3, "exit");
		dungeon.addEntity(exit);
		
		Enemy enemy = new Enemy(4, 1, "enemy");
		dungeon.addEntity(enemy);
		Sword sword = new Sword(2, 1, "sword");
		dungeon.addEntity(sword);
		
		dungeon.getPlayer().moveRight();
		
		dungeon.getPlayer().moveRight();
		
		//System.out.println(enemy.getX());
     	//System.out.println(enemy.getY());
		dungeon.getPlayer().moveDown();
		dungeon.getPlayer().moveDown();
		Assert.assertEquals(3, dungeon.getPlayer().getX());
		Assert.assertEquals(3, dungeon.getPlayer().getY());
		Assert.assertEquals(0, enemy.getX());
		Assert.assertEquals(0, enemy.getY());
		//System.out.println(dungeon.getPlayer().getX());
		//System.out.println(dungeon.getPlayer().getY());
		
	}
	
	
	
	@Test
	public void testBoulderAndExit() {
		System.out.println("--------- Test Covered All Floor Switch And Exit To Get Win ");
		GoalComponent goal = new AndGoal(1);
		GoalComponent bouldersgoal = new BouldersGoal(0);
		GoalComponent exitgoal = new ExitGoal(0);
		goal.add(bouldersgoal);
		goal.add(exitgoal);
		
		dungeon.setGoal(goal);
		
		Boulder boulder = new Boulder(2, 1, "boulder");
		dungeon.addEntity(boulder);
		SwitchFloor switchs = new SwitchFloor(3, 1, "switch floor");
		dungeon.addEntity(switchs);
		
		Exit exit = new Exit(2, 2, "exit");
		dungeon.addEntity(exit);
		
		dungeon.getPlayer().moveRight();
		Assert.assertEquals(1, switchs.getCovered());
		
		dungeon.getPlayer().moveDown();
		Assert.assertEquals(2, dungeon.getPlayer().getX());
		Assert.assertEquals(2, dungeon.getPlayer().getY());
	}
	
	
	
	@Test
	public void testTreasureOrBoulder() {
		System.out.println("--------- Test Covered All Floor Switch Or Collect All Treasure To Get Win ");
		GoalComponent goal = new OrGoal(0);
		GoalComponent bouldergoal = new BouldersGoal(0);
		GoalComponent treasuregoal = new TreasureGoal(0);
		goal.add(bouldergoal);
		goal.add(treasuregoal);
		
		dungeon.setGoal(goal);
		
		Boulder boulder = new Boulder(2, 1, "boulder");
		dungeon.addEntity(boulder);
		SwitchFloor switchs = new SwitchFloor(3, 1, "switch floor");
		dungeon.addEntity(switchs);
		
		Treasure treasure = new Treasure(1, 2, "treasure");
		dungeon.addEntity(treasure);
		
		dungeon.getPlayer().moveRight();
		Assert.assertEquals(1, switchs.getCovered());
		
	}
	
	@Test
	public void testTreasureOrBoulderandExit() {
		System.out.println("--------- Test Collect All Treasure Or (Covered All Floor Switch And Exit) To Get Win ");
		GoalComponent goal = new OrGoal(0);
		
		
		GoalComponent goal1 = new AndGoal(1);
		GoalComponent bouldersgoal = new BouldersGoal(0);
		GoalComponent exitgoal = new ExitGoal(0);
		goal1.add(bouldersgoal);
		goal1.add(exitgoal);
		
		
		
		GoalComponent treasuregoal = new TreasureGoal(0);
		
		goal.add(goal1);
		goal.add(treasuregoal);
		dungeon.setGoal(goal);
		
		Treasure treasure = new Treasure(1, 2, "treasure");
		dungeon.addEntity(treasure);
		
		Boulder boulder = new Boulder(2, 1, "boulder");
		dungeon.addEntity(boulder);
		SwitchFloor switchs = new SwitchFloor(3, 1, "switch floor");
		dungeon.addEntity(switchs);
		
		Exit exit = new Exit(2, 2, "exit");
		dungeon.addEntity(exit);
		
		dungeon.getPlayer().moveRight();
		Assert.assertEquals(1, switchs.getCovered());
		
		dungeon.getPlayer().moveDown();
		Assert.assertEquals(2, dungeon.getPlayer().getX());
		Assert.assertEquals(2, dungeon.getPlayer().getY());
	//	System.out.println(dungeon.getPlayer().getX());
	//	System.out.println(dungeon.getPlayer().getY());
	//	System.out.println(treasure.getX());
	//	System.out.println(treasure.getY());
	}
	
	
	
	@Test
	public void testExitAndBoulderOrTreasure() {
		System.out.println("--------- Test Exit And (Boulder Or Treasure) To Get Win ");
		GoalComponent goal = new AndGoal(1);
		
		GoalComponent goals = new OrGoal(0);
		GoalComponent bouldergoal = new BouldersGoal(0);
		GoalComponent treasuregoal = new TreasureGoal(0);
		goals.add(treasuregoal);
		goals.add(bouldergoal);
		
		GoalComponent exitgoal = new ExitGoal(0);
		
		goal.add(exitgoal);
		goal.add(goals);
		
		dungeon.setGoal(goal);
		
		Exit exit = new Exit(2, 2, "exit");
		dungeon.addEntity(exit);
		
		Treasure treasure = new Treasure(1, 2, "treasure");
		dungeon.addEntity(treasure);
		
		Boulder boulder = new Boulder(2, 1, "boulder");
		dungeon.addEntity(boulder);
		SwitchFloor switchs = new SwitchFloor(3, 1, "switch floor");
		dungeon.addEntity(switchs);
		
		// cover floor switch and exit
		//dungeon.getPlayer().moveRight();
		//Assert.assertEquals(1, switchs.getCovered());
		//dungeon.getPlayer().moveDown();
		
		// collect treasure and exit
		dungeon.getPlayer().moveDown();
		Assert.assertEquals(0, treasure.getX());
		Assert.assertEquals(0, treasure.getY());
		dungeon.getPlayer().moveRight();
		Assert.assertEquals(2, dungeon.getPlayer().getX());
		Assert.assertEquals(2, dungeon.getPlayer().getY());
		
	}
}
