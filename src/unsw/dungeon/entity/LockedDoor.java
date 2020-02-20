package unsw.dungeon.entity;

public class LockedDoor extends Entity{
	public LockedDoor(int x, int y, String type) {
		super(x, y, type);
    }
	
	@Override
	public void update(Player player) {
		int playerx = player.getX();
		int playery = player.getY();
		if ( (playerx == this.getX()) && (playery == this.getY())  ) {
			if(player.getBag().getHaskey() && (player.getBag().getKey()==1)) {
				player.getBag().setKey(0);
				player.getBag().setHaskey(false);
				for (Entity e : player.getEntityList()) {
					if( (e.gettype().equals("openeddoor")) && (e.getX() == 0) && (e.getY() == 0)) {
						e.x().set(this.getX());
						e.y().set(this.getY());
					}
				}
				this.x().set(0);
				this.y().set(0);
			}
		}
	}
}
