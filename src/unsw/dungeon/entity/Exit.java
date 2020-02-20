package unsw.dungeon.entity;

public class Exit extends Entity{
	private int exitreached;
	public Exit(int x, int y, String type) {
        super(x, y, type);
        exitreached = 0;
    }
	public int getExitreached() {
		return exitreached;
	}
	public void setExitreached(int exitreached) {
		this.exitreached = exitreached;
	}
	
	public void update(Player player) {
		if (  (player.getX() == this.getX()) &&  (player.getY() == this.getY()) ) {
			player.setExit(1);
		}
		else {
			player.setExit(0);
		}
	}

}
