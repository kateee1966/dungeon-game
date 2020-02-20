package unsw.dungeon.entity;

public class SwitchFloor extends Entity{
	private int covered;
	public SwitchFloor(int x, int y, String type) {
        super(x, y, type);
        this.covered = 0;
	}

	@Override
	public int getCovered() {
		return this.covered;
	}

	@Override
	public void setCovered(int covered) {
		this.covered = covered;
	}
	@Override
	public void update(Player player) {
		this.setCovered(0);
		for (Entity e : player.getEntityList()) {
			if( (e.gettype().equals("boulder")) && (e.getX() == this.getX()) && (e.getY() == this.getY())) {
				this.setCovered(1);
			}
		}
	}
	
	
}
