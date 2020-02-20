package unsw.dungeon.entity;

public class Potion extends Entity{
	private int valueTime;
	public Potion(int x, int y, String type) {
		super(x, y, type);		// value time of potion is 15s.
    }
	
	@Override
	public int getUsedtimes() {
		return valueTime;
	}
	
	@Override
	public void setUsedtimes(int valueTime) {
		this.valueTime = valueTime;
	}
	
	@Override
	public void update(Player player) {
		int playerx = player.getX();
		int playery = player.getY();
		if ( (playerx == this.getX()) && (playery == this.getY())  ) {
			if ( player.getBag().getInviciblepotion() > 0) {
			}
			else {
				player.getBag().setInviciblepotion(30);
				player.makeinvicible();
				this. y().set(0);
				this. x().set(0);
			}
		}
	}
}
