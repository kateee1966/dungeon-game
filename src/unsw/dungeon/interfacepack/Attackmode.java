package unsw.dungeon.interfacepack;

import java.util.List;

import unsw.dungeon.entity.Entity;
import unsw.dungeon.entity.Player;

public interface Attackmode {
	public void attack(List<Entity> entitylist, int playerxx, int playeryy);
	public Attackmode becomearmed(Player player);
	public Attackmode becomeunarmed(Player player);
	public Attackmode becomeinvicible(Player player);
}
