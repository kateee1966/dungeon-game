package unsw.dungeon.entity;

public class Portal extends Entity{
	
	public int status;
	private int id;
	public Portal(int x, int y, String type, int i) {
        super(x, y, type);
        this.status = 0;
        this.id = i;
    }
	
	public int getStatus() {
		return this.status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}

	public int getID() {
		return this.id;
	}
}
