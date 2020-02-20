package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

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
import unsw.dungeon.goal.AndGoal;
import unsw.dungeon.goal.BouldersGoal;
import unsw.dungeon.goal.EnemiesGoal;
import unsw.dungeon.goal.ExitGoal;
import unsw.dungeon.goal.OrGoal;
import unsw.dungeon.goal.TreasureGoal;
import unsw.dungeon.interfacepack.GoalComponent;
/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        
        JSONObject goal_conditions = json.getJSONObject("goal-condition");
        String initial_game_type = goal_conditions.getString("goal");
        
        GoalComponent firstlayout = new AndGoal(1);
        
        if(initial_game_type.equals("AND")) {
        	JSONArray jsonSubgoals = goal_conditions.getJSONArray("subgoals");
        	for(int i=0; i<jsonSubgoals.length();i++) {
        		if(jsonSubgoals.getJSONObject(i).getString("goal").equals("AND")) {
        			GoalComponent secondlayout = new AndGoal(1);
        			JSONArray jsonSubgoalssecond = jsonSubgoals.getJSONObject(i).getJSONArray("subgoals");
        			for(int j=0; j<jsonSubgoalssecond.length();j++) {
        				if(jsonSubgoalssecond.getJSONObject(j).getString("goal").equals("AND")) {
        					GoalComponent thirdlayout = new AndGoal(1);
        					JSONArray jsonSubgoalthird = jsonSubgoalssecond.getJSONObject(i).getJSONArray("subgoals");
        					for (int k=0; k<jsonSubgoalthird.length(); k++) {
        						String fourth_game_type = jsonSubgoalthird.getJSONObject(k).getString("goal");
        						thirdlayout.add(createnewgoal(fourth_game_type));
        					}
        					secondlayout.add(thirdlayout);
        				}
        				else if(jsonSubgoalssecond.getJSONObject(j).getString("goal").equals("OR")) {
        					GoalComponent thirdlayout = new OrGoal(0);
        					JSONArray jsonSubgoalthird = jsonSubgoalssecond.getJSONObject(i).getJSONArray("subgoals");
        					for (int k=0; k<jsonSubgoalthird.length(); k++) {
        						String fourth_game_type = jsonSubgoalthird.getJSONObject(k).getString("goal");
        						thirdlayout.add(createnewgoal(fourth_game_type));
        					}
        					secondlayout.add(thirdlayout);
        				}
        				else {
        					String third_game_type = jsonSubgoalssecond.getJSONObject(j).getString("goal");
        					secondlayout.add(createnewgoal(third_game_type));
        				}
        			}
        			firstlayout.add(secondlayout);
        		}
        		else if(jsonSubgoals.getJSONObject(i).getString("goal").equals("OR")) {
        			GoalComponent secondlayout = new OrGoal(0);
        			JSONArray jsonSubgoalssecond = jsonSubgoals.getJSONObject(i).getJSONArray("subgoals");
        			for(int j=0; j<jsonSubgoalssecond.length();j++) {
        				if(jsonSubgoalssecond.getJSONObject(j).getString("goal").equals("AND")) {
        					GoalComponent thirdlayout = new AndGoal(1);
        					JSONArray jsonSubgoalthird = jsonSubgoalssecond.getJSONObject(i).getJSONArray("subgoals");
        					for (int k=0; k<jsonSubgoalthird.length(); k++) {
        						String fourth_game_type = jsonSubgoalthird.getJSONObject(k).getString("goal");
        						thirdlayout.add(createnewgoal(fourth_game_type));
        					}
        					secondlayout.add(thirdlayout);
        				}
        				else if(jsonSubgoalssecond.getJSONObject(j).getString("goal").equals("OR")) {
        					GoalComponent thirdlayout = new OrGoal(0);
        					JSONArray jsonSubgoalthird = jsonSubgoalssecond.getJSONObject(i).getJSONArray("subgoals");
        					for (int k=0; k<jsonSubgoalthird.length(); k++) {
        						String fourth_game_type = jsonSubgoalthird.getJSONObject(k).getString("goal");
        						thirdlayout.add(createnewgoal(fourth_game_type));
        					}
        					secondlayout.add(thirdlayout);
        				}
        				else {
        					String third_game_type = jsonSubgoalssecond.getJSONObject(j).getString("goal");
        					secondlayout.add(createnewgoal(third_game_type));
        				}
        			}
        			firstlayout.add(secondlayout);
        		}
        		else {
        			String second_game_type = jsonSubgoals.getJSONObject(i).getString("goal");
        			firstlayout.add(createnewgoal(second_game_type));
        		}
        	}
        }
        else if (initial_game_type.equals("OR")) {
        	firstlayout = new OrGoal (0); 
        	JSONArray jsonSubgoals = goal_conditions.getJSONArray("subgoals");
        	for(int i=0; i<jsonSubgoals.length();i++) {
        		if(jsonSubgoals.getJSONObject(i).getString("goal").equals("AND")) {
        			GoalComponent secondlayout = new AndGoal(1);
        			JSONArray jsonSubgoalssecond = jsonSubgoals.getJSONObject(i).getJSONArray("subgoals");
        			for(int j=0; j<jsonSubgoalssecond.length();j++) {
        				if(jsonSubgoalssecond.getJSONObject(j).getString("goal").equals("AND")) {
        					GoalComponent thirdlayout = new AndGoal(1);
        					JSONArray jsonSubgoalthird = jsonSubgoalssecond.getJSONObject(i).getJSONArray("subgoals");
        					for (int k=0; k<jsonSubgoalthird.length(); k++) {
        						String fourth_game_type = jsonSubgoalthird.getJSONObject(k).getString("goal");
        						thirdlayout.add(createnewgoal(fourth_game_type));
        					}
        					secondlayout.add(thirdlayout);
        				}
        				else if(jsonSubgoalssecond.getJSONObject(j).getString("goal").equals("OR")) {
        					GoalComponent thirdlayout = new OrGoal(0);
        					JSONArray jsonSubgoalthird = jsonSubgoalssecond.getJSONObject(i).getJSONArray("subgoals");
        					for (int k=0; k<jsonSubgoalthird.length(); k++) {
        						String fourth_game_type = jsonSubgoalthird.getJSONObject(k).getString("goal");
        						thirdlayout.add(createnewgoal(fourth_game_type));
        					}
        					secondlayout.add(thirdlayout);
        				}
        				else {
        					String third_game_type = jsonSubgoalssecond.getJSONObject(j).getString("goal");
        					secondlayout.add(createnewgoal(third_game_type));
        				}
        			}
        			firstlayout.add(secondlayout);
        		}
        		else if(jsonSubgoals.getJSONObject(i).getString("goal").equals("OR")) {
        			GoalComponent secondlayout = new OrGoal(0);
        			JSONArray jsonSubgoalssecond = jsonSubgoals.getJSONObject(i).getJSONArray("subgoals");
        			for(int j=0; j<jsonSubgoalssecond.length();j++) {
        				if(jsonSubgoalssecond.getJSONObject(j).getString("goal").equals("AND")) {
        					GoalComponent thirdlayout = new AndGoal(1);
        					JSONArray jsonSubgoalthird = jsonSubgoalssecond.getJSONObject(i).getJSONArray("subgoals");
        					for (int k=0; k<jsonSubgoalthird.length(); k++) {
        						String fourth_game_type = jsonSubgoalthird.getJSONObject(k).getString("goal");
        						thirdlayout.add(createnewgoal(fourth_game_type));
        					}
        					secondlayout.add(thirdlayout);
        				}
        				else if(jsonSubgoalssecond.getJSONObject(j).getString("goal").equals("OR")) {
        					GoalComponent thirdlayout = new OrGoal(0);
        					JSONArray jsonSubgoalthird = jsonSubgoalssecond.getJSONObject(i).getJSONArray("subgoals");
        					for (int k=0; k<jsonSubgoalthird.length(); k++) {
        						String fourth_game_type = jsonSubgoalthird.getJSONObject(k).getString("goal");
        						thirdlayout.add(createnewgoal(fourth_game_type));
        					}
        					secondlayout.add(thirdlayout);
        				}
        				else {
        					String third_game_type = jsonSubgoalssecond.getJSONObject(j).getString("goal");
        					secondlayout.add(createnewgoal(third_game_type));
        				}
        			}
        			firstlayout.add(secondlayout);
        		}
        		else {
        			String second_game_type = jsonSubgoals.getJSONObject(i).getString("goal");
        			firstlayout.add(createnewgoal(second_game_type));
        		}
        	}
        }
        else {
        	firstlayout.add(createnewgoal(initial_game_type));
        }
        
        dungeon.setGoal(firstlayout);

        return dungeon;
    }    

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        
        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y, "player");
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y, "wall");
            onLoad(wall);
            entity = wall;
            break;
        case "exit":
        	Exit exit = new Exit(x, y, "exit");
        	onLoad(exit);
        	entity = exit;
        	break;
        case "enemy":
        	Enemy enemy = new Enemy(x,y, "enemy");
        	onLoad(enemy);
        	entity = enemy;
        	break;
        case "bomb":
        	Bomb bomb = new Bomb(x,y, "bomb");
        	onLoad(bomb);
        	entity = bomb;
        	break;
        case "treasure":
        	Treasure treasure = new Treasure(x,y, "treasure");
        	onLoad(treasure);
        	entity = treasure;
        	break;
        case "lockeddoor":
        	LockedDoor door = new LockedDoor(x,y, "lockeddoor");
        	onLoad(door);
        	entity = door;
        	break;
        case "lockeddoorred":
        	LockedDoorRed lockeddoorred = new LockedDoorRed(x,y, "lockeddoorred");
        	onLoad(lockeddoorred);
        	entity = lockeddoorred;
        	break;
        case "lockeddoorblue":
        	LockedDoorBlue lockeddoorblue = new LockedDoorBlue(x,y, "lockeddoorblue");
        	onLoad(lockeddoorblue);
        	entity = lockeddoorblue;
        	break;
        case "openeddoor":
        	Openeddoor openeddoor = new Openeddoor(x,y, "openeddoor");
        	onLoad(openeddoor);
        	entity = openeddoor;
        	break;
        case "openeddoorred":
        	OpeneddoorRed openeddoorred = new OpeneddoorRed(x,y, "openeddoorred");
        	onLoad(openeddoorred);
        	entity = openeddoorred;
        	break;
        case "openeddoorblue":
        	OpeneddoorBlue openeddoorblue = new OpeneddoorBlue(x,y, "openeddoorblue");
        	onLoad(openeddoorblue);
        	entity = openeddoorblue;
        	break;
        case "openkey":
        	OpenKey key = new OpenKey(x,y, "openkey");
        	onLoad(key);
        	entity = key;
        	break;
        case "openkeyred":
        	OpenKeyRed openkeyred = new OpenKeyRed(x,y, "openkeyred");
        	onLoad(openkeyred);
        	entity = openkeyred;
        	break;
        case "openkeyblue":
        	OpenKeyBlue openkeyblue = new OpenKeyBlue(x,y, "openkeyblue");
        	onLoad(openkeyblue);
        	entity = openkeyblue;
        	break;
        case "boulder":
        	Boulder boulder = new Boulder(x,y, "boulder");
        	onLoad(boulder);
        	entity = boulder;
        	break;
        case "switch":
        	SwitchFloor floorswitch = new SwitchFloor(x,y, "switch floor");
        	onLoad(floorswitch);
        	entity = floorswitch;
        	break;
        case "sword":
        	Sword sword = new Sword(x,y, "sword");
        	onLoad(sword);
        	entity = sword;
        	break;
        case "invincibility":
        	Potion potion = new Potion(x, y, "invincibility potion");
        	onLoad(potion);
        	entity = potion;
        	break;
        case "blackhole":
        	BlackHole hole = new BlackHole(x, y, "blackhole");
        	onLoad(hole);
        	entity = hole;
        	break;
        case "portal":
            int id = json.getInt("id");
        	Portal portal = new Portal(x, y, "portal", id);
        	onLoad(portal);
        	entity = portal;
        	break;
        //["player", "wall", "exit", "treasure", "door", "key", "boulder", "switch", "bomb", "enemy", "sword", "invincibility"]
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Player player);

    public abstract void onLoad(Wall wall);

    public abstract void onLoad(Exit exit);

    public abstract void onLoad(Treasure treasure);
    
    public abstract void onLoad(LockedDoor door);
    
    public abstract void onLoad(LockedDoorBlue doorblue);
    
    public abstract void onLoad(LockedDoorRed doorred);
    
    public abstract void onLoad(OpenKey key);
    
    public abstract void onLoad(OpenKeyBlue keyblue);
    
    public abstract void onLoad(OpenKeyRed keyred);
    
    public abstract void onLoad(Boulder boulder);

    public abstract void onLoad(SwitchFloor switchfloor);

    public abstract void onLoad(Bomb bomb);
    
    public abstract void onLoad(Enemy enemy);

    public abstract void onLoad(Sword sword);

    public abstract void onLoad(Potion potion);
    
    public abstract void onLoad(Openeddoor openeddoor);
    
    public abstract void onLoad(OpeneddoorBlue openeddoorBlue);
    
    public abstract void onLoad(OpeneddoorRed openeddoorRed);
    
    public abstract void onLoad(BlackHole hole);
    
    public abstract void onLoad(Portal portal);
    
    public GoalComponent createnewgoal(String goaltype) {
    	if(goaltype.equals("exit")) {
    		return new ExitGoal(0);
    	}
    	else if (goaltype.equals("enemies")) {
    		return new EnemiesGoal(0);
    	}
    	else if (goaltype.equals("boulders")) {
    		return new BouldersGoal(0);
    	}
    	else if (goaltype.equals("treasure")) {
    		return new TreasureGoal(0);
    	}
    	else {
    		return new AndGoal(1);
    	}
    }

}
