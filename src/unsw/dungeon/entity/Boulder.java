package unsw.dungeon.entity;

public class Boulder extends Entity{
	public Boulder(int x, int y, String type) {
        super(x, y, type);
    }
	
	@Override
	public void update(Player player) {
		int playerx = player.getX();
		int playery = player.getY();
		if ( (playerx == this.getX()) && (playery == this.getY())  ) {
			switch(player.getLastMove()) {
				case "Up":
					this. y().set(getY()-1);
					break;
				case "Down":
					this. y().set(getY()+1);
					break;
				case "Left":
					this. x().set(getX()-1);
					break;
				case "Right":
					this. x().set(getX()+1);
					break;
				default:
					break;
			}
		}
	}
}
