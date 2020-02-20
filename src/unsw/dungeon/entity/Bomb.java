package unsw.dungeon.entity;

import unsw.dungeon.attackmode.Invicibleplayer;

public class Bomb extends Entity{
	private int explosiontype;
	private int x;
	private int y; 
	public Bomb(int x, int y, String type) {
        super(x, y, type);
        this.x = x;
        this.y = y;
    }
	
	public int getExplosiontype() {
		return explosiontype;
	}
	
	public void setExplosiontype(int explosiontype) {
		this.explosiontype = explosiontype;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public void update(Player player) {
		int playerx = player.getX();
		int playery = player.getY();
		if ( (playerx == this.getX()) && (playery == this.getY())  ) {
			if ( player.getBag().getBomb() > 0) {
			}
			else if(this.getBombStatus() == 1){
			}else {
				player.getBag().setBomb(1);
				this. y().set(0);
				this. x().set(0);
			}
		}
	}
	
	@Override
	public void attack(Player player, int x, int y) {
		for(Entity i: player.getEntityList()) {
			if( (i.getX()== x-1) && (i.getY() == y) ) {
				if((i.gettype().equals("enemy")) || (i.gettype().equals("boulder"))) {
					i.x().set(0);
					i.y().set(0);
				}
			}
			else if( (i.getX()== x+1) && (i.getY() == y) ) {
				if( (i.gettype().equals("enemy")) || (i.gettype().equals("boulder")) ) {
					i.x().set(0);
					i.y().set(0);
				}
			}
			else if( (i.getX()== x) && (i.getY() == y-1) ) {
				if( (i.gettype().equals("enemy")) || (i.gettype().equals("boulder")) ) {
					i.x().set(0);
					i.y().set(0);
				}
			}
			else if( (i.getX()== x) && (i.getY() == y+1) ) {
				if( (i.gettype().equals("enemy")) || (i.gettype().equals("boulder")) ) {
					i.x().set(0);
					i.y().set(0);
				}
			}
			else if( (i.getX()== x) && (i.getY() == y) ) {
				if( (i.gettype().equals("enemy")) || (i.gettype().equals("boulder")) ) {
					i.x().set(0);
					i.y().set(0);
				}
			}
		}
		if( (player.getX()== x-1) && (player.getY() == y) ) {
			if(!(player.getattackmode() instanceof Invicibleplayer)) {
				player.makedead();
			}
		}
		else if( (player.getX()== x+1) && (player.getY() == y) ) {
			if(!(player.getattackmode() instanceof Invicibleplayer)) {
				player.makedead();
			}
		}
		else if( (player.getX()== x) && (player.getY() == y-1) ) {
			if(!(player.getattackmode() instanceof Invicibleplayer)) {
				player.makedead();
			}
		}
		else if( (player.getX()== x) && (player.getY() == y+1) ) {
			if(!(player.getattackmode() instanceof Invicibleplayer)) {
				player.makedead();
			}
		}
		else if( (player.getX()== x) && (player.getY() == y) ) {
			if(!(player.getattackmode() instanceof Invicibleplayer)) {
				player.makedead();
			}
		}
		
		for(Entity e: player.getEntityList()) {
			if((e.gettype().equals("switch floor")) && (e.getCovered()==1)) {
				int on = 0;
				for(Entity k:player.getEntityList()) {
					if(  (k.gettype().equals("boulder")) && (k.getX()==e.getX()) && (k.getY()==e.getY())) {
						on = 1;
					}
				}
				if(on==0) {
					e.setCovered(0);
				}
			}
		}
	}
}
