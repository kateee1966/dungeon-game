package unsw.dungeon.entity;

public class Sword extends Entity{
	private int attacktimes;
	
	public Sword(int x, int y, String type) {
		super(x, y, type);
		this.attacktimes = 5;
    }
	
	@Override
	public int getUsedtimes() {
		return attacktimes;
	}

	@Override
	public void setUsedtimes(int attacktimes) {
		this.attacktimes = attacktimes;
	}
	
	@Override
	public void update(Player player) {
		int playerx = player.getX();
		int playery = player.getY();
		if ( (playerx == this.getX()) && (playery == this.getY())  ) {
			if ( player.getBag().getSwordattacktimes() > 0) {
			}
			else {
				player.getBag().setSwordattacktimes(this.getUsedtimes());
				if(player.getBag().getInviciblepotion() > 0) {
					player.getBag().setSwordattacktimes(this.getUsedtimes());
					this. x().set(0);
					this. y().set(0);
				}else {
					player.makearmed();
					player.getBag().setSwordattacktimes(this.getUsedtimes());
					this. x().set(0);
					this. y().set(0);
				}
			}
		}
	}
}
