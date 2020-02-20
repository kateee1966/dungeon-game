package unsw.dungeon.interfacepack;

import unsw.dungeon.entity.Entity;

public interface Playersubject {
	public void registerEntityObserver(Entity e);
	public void removeEntityObserver(Entity e);
	public void notifyPickEntityObservers();
}
