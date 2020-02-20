package unsw.dungeon.entity;

public class BlackHole extends Entity{


/*
 * black hole is an entity which whatever entity steps in it gets removed
 * this includes bomb, player and enemy
 */


	public BlackHole(int x, int y, String type) {
		super(x, y, type);		
	}
	
	
	@Override
	//whenever a change in dungeon happens it should call this
	public void update (Player player) {
		if (player.getX() == this.getX() && player.getY() == this.getY()) {
			player.makedead();
			player.x().set(0);
			player.y().set(0);
		}
		for (Entity e: player.getEntityList()) {
			if(e.getX() == this.getX() && e.getY() == this.getY()) {
				if(e instanceof BlackHole) {
					break;
				}else {
					e.x().set(0);
					e.y().set(0);
				}
				
			}
			
			
		}
	}

}
