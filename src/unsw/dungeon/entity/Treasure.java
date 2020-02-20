package unsw.dungeon.entity;

public class Treasure extends Entity{
	public Treasure(int x, int y, String type) {
		super(x, y, type);
    }
	
	@Override
	public void update(Player player) {
		int playerx = player.getX();
		int playery = player.getY();
		if ( (playerx == this.getX()) && (playery == this.getY())  ) {
			this. y().set(0);
			this. x().set(0);
		}
	}
}
