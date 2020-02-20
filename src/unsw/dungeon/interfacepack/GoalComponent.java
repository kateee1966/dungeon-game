package unsw.dungeon.interfacepack;

import unsw.dungeon.entity.Player;

public interface GoalComponent {
	
	public boolean checkwhethercomplete(Player player, int andor, boolean whethercomplete);
	public boolean add(GoalComponent child);
	public boolean remove(GoalComponent child);
	public int getAndor();
	public void setAndor(int andor);
	public boolean isWhethercomplete();
}
