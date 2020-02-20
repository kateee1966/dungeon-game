package unsw.dungeon.entity;

public class OpenKeyRed extends Entity{
	private int useTime;
	public OpenKeyRed(int x, int y, String type) {
		super(x, y, type);
		this.useTime = 1;
	}
	
	@Override
	public int getUsedtimes() {
		return useTime;
	}

	@Override
	public void setUsedtimes(int valueTime) {
		this.useTime = valueTime;
	}
	
	
	public void useKey() {
		this.useTime = 0;
	}
	
	@Override
	public void update(Player player) {
		int playerx = player.getX();
		int playery = player.getY();
		if ( (playerx == this.getX()) && (playery == this.getY())  ) {
			if(player.getBag().getHaskey()) {
			}
			else {
				player.getBag().setRedkey(1);;
				player.getBag().setHaskey(true);
				this. y().set(0);
				this. x().set(0);
			}
		}
	}
}
