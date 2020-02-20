package unsw.dungeon.attackmode;

import java.util.List;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Bag;
import unsw.dungeon.entity.Entity;
import unsw.dungeon.entity.Player;
import unsw.dungeon.interfacepack.Attackmode;

public class Unarmed implements Attackmode{
	private Player player;
	private Dungeon presentDungeon;
	private List<Entity> attackedEntities;
	private Bag playerbag;
	private int playerx;
	private int playery;
	
	public Unarmed(Player player) {
		this.player = player;
		this.presentDungeon = player.getDungeon();
		this.attackedEntities = this.presentDungeon.getEntity();
		this.playerbag = player.getBag();
		this.playerx = player.getX();
		this.playery = player.getY();
	}
	@Override
	public void attack(List<Entity> entitylist,  int playerx, int playery) {
		for(Entity i : entitylist) {
			if ( (i.gettype().equals("enemy")) &&  (i.getX() == playerx) && (i.getY() == playery)) {
    			player.makedead();
    			break;
			}
		}
	}
	@Override
	public Attackmode becomearmed(Player player) {
		return new Armed(player);
	}
	@Override
	public Attackmode becomeunarmed(Player player) {
		return new Unarmed(player);
	}
	@Override
	public Attackmode becomeinvicible(Player player) {
		return new Invicibleplayer(player);
	}
	
}
