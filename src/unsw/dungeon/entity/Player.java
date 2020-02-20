package unsw.dungeon.entity;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.dungeon.Dungeon;
import unsw.dungeon.attackmode.Invicibleplayer;
import unsw.dungeon.attackmode.Unarmed;
import unsw.dungeon.interfacepack.Attackmode;
import unsw.dungeon.interfacepack.GoalComponent;
import unsw.dungeon.interfacepack.Moving;
import unsw.dungeon.interfacepack.Playersubject;

/**
 * The player entity
 *
 */
public class Player extends Entity implements Moving, Playersubject {

    private Dungeon dungeon;
    private Attackmode attackmode;
    private Bag bag;
    private List<Entity> EntityList;
    private String LastMove;
    private int exit;
    private String status;
    private int result;
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y, String type) {
        super(x, y, type);
        this.dungeon = dungeon;
        this.bag = new Bag();
        this.EntityList = dungeon.getEntity();
        this.attackmode = new Unarmed(this);
        this.LastMove = "Stop";
        this.exit = 0;
        this.status = type;
        this.result = 0;	// when result = 1, win
        					// when result = 2, dead
    }
    
    public int getExit() {
		return exit;
	}

	public void setExit(int exit) {
		this.exit = exit;
	}

    
    public String getLastMove() {
		return LastMove;
	}

	public void setLastMove(String lastMove) {
		LastMove = lastMove;
	}

	public Bag getBag() {
		return bag;
	}
    
	public void setBag(Bag bag) {
		this.bag = bag;
	}
	
	public int getResult() {
		return this.result;
	}
	
	public void setResult(int result) {
		this.result = result;
	}
	public String getplayerStatus() {
		return this.status;
	}
	
	public void settype(String status) {
		this.status = status;
	}
//--------------------------Strategy Pattern-----------------------------------------
	@Override
    public boolean whethercouldmoveup(Dungeon dungeon, int x, int y) {
    	boolean could = true;
    	List<Entity> EntityList = dungeon.getEntity();
    	for (Entity i: EntityList) {
    		if( (i.getX()==x) && (i.getY() == y-1) && (i.gettype().equals("lockeddoor"))){
    			
    			if(this.bag.getKey() == 0) {
    				could = false;
    			}
    		}
    		if( (i.getX()==x) && (i.getY() == y-1) && (i.gettype().equals("lockeddoorred"))){
    			
    			if(this.bag.getRedkey() == 0) {
    				could = false;
    			}
    		}
    		if( (i.getX()==x) && (i.getY() == y-1) && (i.gettype().equals("lockeddoorblue"))){
    			
    			if(this.bag.getBluekey() == 0) {
    				could = false;
    			}
    		}
    		if( (i.getX()==x) && (i.getY() == y-1) && (i.gettype().equals("boulder"))) {
    			for (Entity e: EntityList) {
    				if ( (e.getX()==x) && (e.getY() == y-2) && ( (e.gettype().equals("wall")) || (e.gettype().equals("boulder")) || (e.gettype().equals("lockeddoor"))|| (e.gettype().equals("enemy"))|| (e.gettype().equals("lockeddoorred")) || (e.gettype().equals("lockeddoorblue")))) {
    					could = false;
    				}
    			}
    		}
    		if( (i.getX()==x) && (i.getY() == y-1) && (i.gettype().equals("wall"))){
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
    		if( (i.getX()==x) && (i.getY() == y+1) && (i.gettype().equals("wall"))){
    			could = false;
    		}
    		if( (i.getX()==x) && (i.getY() == y+1) && (i.gettype().equals("boulder"))) {
    			for (Entity e: EntityList) {
    				if ( (e.getX()==x) && (e.getY() == y+2) && ( (e.gettype().equals("wall")) || (e.gettype().equals("boulder")) || (e.gettype().equals("lockeddoor")) || (e.gettype().equals("enemy")) || (e.gettype().equals("lockeddoorred")) || (e.gettype().equals("lockeddoorblue")))) {
    					could = false;
    				}
    			}
    		}
    		if( (i.getX()==x) && (i.getY() == y+1) && (i.gettype().equals("lockeddoor"))){
    			
    			if(this.bag.getKey() == 0) {
    				could = false;
    			}
    		}
    		if( (i.getX()==x) && (i.getY() == y+1) && (i.gettype().equals("lockeddoorred"))){
    			
    			if(this.bag.getRedkey() == 0) {
    				could = false;
    			}
    		}
    		if( (i.getX()==x) && (i.getY() == y+1) && (i.gettype().equals("lockeddoorblue"))){
    			
    			if(this.bag.getBluekey() == 0) {
    				could = false;
    			}
    		}
    	}
    	return could;
    }
    
    @Override
    public boolean whethercouldmoveleft(Dungeon dungeon, int x, int y) {
    	boolean could = true;
    	List<Entity> EntityList = dungeon.getEntity();
    	for (Entity i: EntityList) {
    		if( (i.getX()==x-1) && (i.getY() == y) && (i.gettype().equals("wall"))){
    			could = false;
    		}
    		if( (i.getX()==x-1) && (i.getY() == y) && (i.gettype().equals("boulder"))) {
    			for (Entity e: EntityList) {
    				if ( (e.getX()==x-2) && (e.getY() == y) && ( (e.gettype().equals("wall")) || (e.gettype().equals("boulder")) || (e.gettype().equals("lockeddoor")) || (e.gettype().equals("enemy"))|| (e.gettype().equals("lockeddoorred")) || (e.gettype().equals("lockeddoorblue")))) {
    					could = false;
    				}
    			}
    		}
    		if( (i.getX()==x-1) && (i.getY() == y) && (i.gettype().equals("lockeddoor"))){
    			
    			if(this.bag.getKey() == 0) {
    				could = false;
    			}
    		}
    		if( (i.getX()==x-1) && (i.getY() == y) && (i.gettype().equals("lockeddoorred"))){
    			
    			if(this.bag.getRedkey() == 0) {
    				could = false;
    			}
    		}
    		if( (i.getX()==x-1) && (i.getY() == y) && (i.gettype().equals("lockeddoorblue"))){
    			
    			if(this.bag.getBluekey() == 0) {
    				could = false;
    			}
    		}
    	}
    	return could;
    }
    
    @Override
    public boolean whethercouldmoveright(Dungeon dungeon, int x, int y) {
    	boolean could = true;
    	List<Entity> EntityList = dungeon.getEntity();
    	for (Entity i: EntityList) {
    		if( (i.getX()==x+1) && (i.getY() == y) && (i.gettype().equals("wall"))){
    			
    			could = false;
    			
    		}
    		if( (i.getX()==x+1) && (i.getY() == y) && (i.gettype().equals("boulder"))) {
    			for (Entity e: EntityList) {
    				if ( (e.getX()==x+2) && (e.getY() == y) && ( (e.gettype().equals("wall")) || (e.gettype().equals("boulder")) || (e.gettype().equals("lockeddoor")) || (e.gettype().equals("enemy"))|| (e.gettype().equals("lockeddoorred")) || (e.gettype().equals("lockeddoorblue")))) {
    					could = false;
    				}
    			}
    		}
    		if( (i.getX()==x+1) && (i.getY() == y) && (i.gettype().equals("lockeddoor"))){
    			if(this.bag.getKey() == 0) {
    				could = false;
    			}
    		}
    		if( (i.getX()==x+1) && (i.getY() == y) && (i.gettype().equals("lockeddoorred"))){
    			
    			if(this.bag.getRedkey() == 0) {
    				could = false;
    			}
    		}
    		if( (i.getX()==x+1) && (i.getY() == y) && (i.gettype().equals("lockeddoorblue"))){
    			
    			if(this.bag.getBluekey() == 0) {
    				could = false;
    			}
    		}
    	}
    	return could;
    }
    
    @Override
    public void moveUp() {
    	int flag = 0;
    	for(Entity i: EntityList) {
        	if(i.getX()==this.getX() && i.getY() == (this.getY() - 1) && (i instanceof Portal)) {
        		Portal p = (Portal) i;
        		p.setStatus(1);
        		for(Entity a: EntityList) {
                	if(a instanceof Portal && ((Portal) a).getStatus() == 0 && ((Portal) a).getID() == p.getID()) {
                		Portal portal = (Portal) a;
            			x().set(portal.getX());
            			y().set(portal.getY());
            			for(Entity x : EntityList) {
            				if(x instanceof Portal && ((Portal) x).getStatus() == 1 && ((Portal) x).getID() == p.getID()) {
            					Portal portals = (Portal) x;
            					portals.setStatus(0);
            				}
            			}
            			flag = 1;
            			break;
                	}
                }
        		break;
        	}
        }

        if (flag == 0 && getY() > 0 && whethercouldmoveup(this.getDungeon(), this.getX(), this.getY())) {
            y().set(getY() - 1);
            this.setLastMove("Up");
            if(this.attackmode instanceof Invicibleplayer) {
                if(this.bag.getInviciblepotion()==0) {
                	if (this.bag.getSwordattacktimes()>0) {
                		int times = this.bag.getSwordattacktimes();
                		this.makearmed();
                		this.bag.setSwordattacktimes(times);
                	}
                	else {
                		this.makeunarmed();
                	}
                }  
            }
            notifyPickEntityObservers();
            notifyPickEntityObservers();
            GoalComponent a = this.dungeon.getGoal();
            boolean b = a.checkwhethercomplete(this, a.getAndor(), a.isWhethercomplete());
            if(b) {
            	this.setResult(1);	
            	System.out.println("Win");
            }
        }
    }
    
    @Override
    public void moveDown() {
    	int flag = 0;
    	for(Entity i: EntityList) {
        	if(i.getX()==this.getX() && i.getY() == (this.getY() + 1) && (i instanceof Portal)) {
        		Portal p = (Portal) i;
        		p.setStatus(1);
        		for(Entity a: EntityList) {
                	if(a instanceof Portal && ((Portal) a).getStatus() == 0 && ((Portal) a).getID() == p.getID()) {
                		Portal portal = (Portal) a;
            			x().set(portal.getX());
            			y().set(portal.getY());
            			for(Entity x : EntityList) {
            				if(x instanceof Portal && ((Portal) x).getStatus() == 1 && ((Portal) x).getID() == p.getID()) {
            					Portal portals = (Portal) x;
            					portals.setStatus(0);
            				}
            			}
            			flag = 1;
            			break;
                	}
                }
        		break;
        	}
        }

        if (flag == 0 && getY() < dungeon.getHeight() - 1 && whethercouldmovedown(this.getDungeon(), this.getX(), this.getY())) {
            y().set(getY() + 1);
            this.setLastMove("Down");
            if(this.attackmode instanceof Invicibleplayer) {
                if(this.bag.getInviciblepotion()==0) {
                	if (this.bag.getSwordattacktimes()>0) {
                		int times = this.bag.getSwordattacktimes();
                		this.makearmed();
                		this.bag.setSwordattacktimes(times);
                	}
                	else {
                		this.makeunarmed();
                	}
                }
            }
            notifyPickEntityObservers();
            notifyPickEntityObservers();
            GoalComponent a = this.dungeon.getGoal();
            boolean b = a.checkwhethercomplete(this, a.getAndor(), a.isWhethercomplete());
            if(b) {
            	this.setResult(1);
            	System.out.println("Win");
            }
        }
    }
    
    @Override
    public void moveLeft() {
    	int flag = 0;
    	for(Entity i: EntityList) {
        	if(i.getX()==this.getX()-1 && i.getY() == (this.getY()) && (i instanceof Portal)) {
        		Portal p = (Portal) i;
        		p.setStatus(1);
        		for(Entity a: EntityList) {
                	if(a instanceof Portal && ((Portal) a).getStatus() == 0 && ((Portal) a).getID() == p.getID()) {
                		Portal portal = (Portal) a;
            			x().set(portal.getX());
            			y().set(portal.getY());
            			for(Entity x : EntityList) {
            				if(x instanceof Portal && ((Portal) x).getStatus() == 1 && ((Portal) x).getID() == p.getID()) {
            					Portal portals = (Portal) x;
            					portals.setStatus(0);
            				}
            			}
            			flag = 1;
            			break;
                	}
                }
        		break;
        	}
        }

        if (flag == 0 && getX() > 0 && whethercouldmoveleft(this.getDungeon(), this.getX(), this.getY())) {
        	x().set(getX() - 1);
        	this.setLastMove("Left");
        	 if(this.attackmode instanceof Invicibleplayer) {
                 if(this.bag.getInviciblepotion()==0) {
                 	if (this.bag.getSwordattacktimes()>0) {
                 		int times = this.bag.getSwordattacktimes();
                 		this.makearmed();
                 		this.bag.setSwordattacktimes(times);
                 	}
                 	else {
                 		this.makeunarmed();
                 	}
                 }
             }
         
        	notifyPickEntityObservers();
        	notifyPickEntityObservers();
        	GoalComponent a = this.dungeon.getGoal();
            boolean b = a.checkwhethercomplete(this, a.getAndor(), a.isWhethercomplete());
            if(b) {
            	this.setResult(1);
            	System.out.println("Win");
            }
        }
    }

    @Override
    public void moveRight() {
    	int flag = 0;
    	for(Entity i: EntityList) {
        	if(i.getX()==this.getX()+1 && i.getY() == (this.getY()) && (i instanceof Portal)) {
        		Portal p = (Portal) i;
        		p.setStatus(1);
        		for(Entity a: EntityList) {
                	if(a instanceof Portal && ((Portal) a).getStatus() == 0 && ((Portal) a).getID() == p.getID()) {
                		Portal portal = (Portal) a;
            			x().set(portal.getX());
            			y().set(portal.getY());
            			for(Entity x : EntityList) {
            				if(x instanceof Portal && ((Portal) x).getStatus() == 1 && ((Portal) x).getID() == p.getID()) {
            					Portal portals = (Portal) x;
            					portals.setStatus(0);
            				}
            			}
            			flag = 1;
            			break;
                	}
                }
        		break;
        	}
        }

        if (flag == 0 && getX() < dungeon.getWidth() - 1 && whethercouldmoveright(this.getDungeon(), this.getX(), this.getY())){
            x().set(getX() + 1);
            this.setLastMove("Right");
            if(this.attackmode instanceof Invicibleplayer) {
                if(this.bag.getInviciblepotion()==0) {
                	if (this.bag.getSwordattacktimes()>0) {
                		int times = this.bag.getSwordattacktimes();
                		this.makearmed();
                		this.bag.setSwordattacktimes(times);
                	}
                	else {
                		this.makeunarmed();
                	}
                }
            }
            notifyPickEntityObservers();
            notifyPickEntityObservers();
            GoalComponent a = this.dungeon.getGoal();
            boolean b = a.checkwhethercomplete(this, a.getAndor(), a.isWhethercomplete());
            if(b) {
            	this.setResult(1);
            	System.out.println("Win");
            	
            }
        }
    }
 
 //--------------------------Strategy Pattern-----------------------------------------
    public void throwsword() {
    	boolean throwable = true;
    	if(this.bag.getSwordattacktimes() == 0) {
    		throwable = false;
    	}
    	for (Entity e : this.EntityList) {
    		if(  (e.getX() == this.getX()+1) && (e.getY() == this.getY()) && ( (e.gettype().equals("lockeddoor"))|| (e.gettype().equals("lockeddoorred")) || (e.gettype().equals("lockeddoorblue")) || (e.gettype().equals("wall")) || (e.gettype().equals("boulder"))) ) {
    			throwable = false;
    		}
    	}
    	if(throwable) {
    		for(Entity i : this.EntityList) {
    			if( (i.gettype().equals("sword")) && (i.getX()==0) && (i.getY()==0)) {
    				i.x().set(this.getX()+1);
    				i.y().set(this.getY());
    				i.setUsedtimes(this.bag.getSwordattacktimes());
    			}
    		}
    		this.bag.setSwordattacktimes(0);

    		if(this.attackmode instanceof Invicibleplayer) {

    		}
    		else {
    			this.makeunarmed();
    		}
    	

    	}
    	
    }
    
    public Attackmode getAttackmode() {
		return attackmode;
	}

	public void setAttackmode(Attackmode attackmode) {
		this.attackmode = attackmode;
	}

	public void placebomb() {
    	
    	boolean placeable = true;
    	for (Entity e : this.EntityList) {
    		if((e.getX() == this.getX()+1) && (e.getY() == this.getY()) && ( (e.gettype().equals("lockeddoor"))|| (e.gettype().equals("lockeddoorred")) || (e.gettype().equals("lockeddoorblue")) || (e.gettype().equals("wall")) || (e instanceof Portal)||(e instanceof OpenKey) ||(e instanceof OpenKeyRed) || (e instanceof OpenKeyBlue)||(e.gettype().equals("boulder"))) ) {
    			placeable = false;
    		}
    	}
    	if(placeable && this.bag.getBomb() == 1) {
    		for(Entity i : this.EntityList) {
    			if( (i.gettype().equals("bomb")) && (i.getX()== 0) && (i.getY()== 0)) {
    				this.getBag().setBomb(0);
    				i.x().set(this.getX()+1);
    				i.y().set(this.getY());
    				i.setBombStatus(1);
    				Bomb b = (Bomb) i;
    				b.setX(i.getX());
    				b.setY(i.getY());
    		    	Timer time = new Timer();
    		        TimerTask task = new TimerTask() {
    		            private int count = 4;
    		            public void run() {
    		                if(count > 1) {
    		                	count --;
    		                	
    		                }else if(count == 1) {
    		            		b.attack(getPlayer(), i.getX(), i.getY());
    		            		count --;
    		                }else{
    		                	i.setBombStatus(0);
    		                	i.x().set(0);
    		                	i.y().set(0);
    		                	cancel();
    		                }
    		                
    		            }
    		        };
    		        time.schedule(task, 1000L, 1000L);
    		        
    		        break;
    			}
    			
    		}
    	}
    	
    }
    
    public Dungeon getDungeon() {
    	return this.dungeon;
    }
    
    public Player getPlayer() {
    	return this;
    }
    public List<Entity> getEntityList() {
    	return this.EntityList;
    }
   
//--------------------------Observer Pattern--------------------------------------
    @Override
	public void registerEntityObserver(Entity e) {
    	EntityList.add(e);
		
	}

	@Override
	public void removeEntityObserver(Entity e) {
		EntityList.remove(e);
		
	}

	@Override
	public void notifyPickEntityObservers() {
		for(Entity i:EntityList) {
			i.setChanged(0);
		}
		for(Entity e: EntityList) {
			e.update(this);
		}
	}
	
//--------------------------Observer Pattern--------------------------------------
	
	
	
//--------------------------State Pattern-----------------------------------------
	public void makeunarmed() {
    	this.attackmode = attackmode.becomeunarmed(this);
    }
    
    public void makearmed() {
    	this.attackmode = attackmode.becomearmed(this);
    }
    
    public void makeinvicible() {
    	this.attackmode = attackmode.becomeinvicible(this);
    	Timer timer = new Timer();
    	TimerTask task = new TimerTask() {
    		private int count = 30;
    		public void run() {
    			if(count > 0) {
    				timedown();
    				count--;
    			}else {
    				cancel();
    			}
    			 
    		}
    	};
    	timer.schedule(task, 1000L, 1000L);
    	
    }
    public void timedown() {
    	this.bag.setInviciblepotion(this.bag.getInviciblepotion() - 1);
    }
    
    public Attackmode getattackmode() {
    	return this.attackmode;
    }
    

    public void attack() {
    	this.attackmode.attack(this.getEntityList(), this.getX(), this.getY());
    }
 //--------------------------State Pattern-----------------------------------------
    public void makedead() {

    	this.setResult(2);
    	this.makeunarmed();
    	this.settype("Normal");
    	this.bag.setSwordattacktimes(0);
    	System.out.println("Game Over, You Died");
    }
}
