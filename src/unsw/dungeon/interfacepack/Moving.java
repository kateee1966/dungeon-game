package unsw.dungeon.interfacepack;

import unsw.dungeon.Dungeon;

public interface Moving {
	public void moveUp();
	public void moveDown();
	public void moveLeft();
	public void moveRight();
	public boolean whethercouldmoveleft(Dungeon dungeon, int x, int y);
	public boolean whethercouldmoveright(Dungeon dungeon, int x, int y);
	public boolean whethercouldmoveup(Dungeon dungeon, int x, int y);
	public boolean whethercouldmovedown(Dungeon dungeon, int x, int y);
}
