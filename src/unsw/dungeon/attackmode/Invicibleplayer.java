package unsw.dungeon.attackmode;

import java.util.List;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Bag;
import unsw.dungeon.entity.Entity;
import unsw.dungeon.entity.Player;
import unsw.dungeon.interfacepack.Attackmode;

public class Invicibleplayer implements Attackmode{
	private Player player;
	private Dungeon presentDungeon;
	private List<Entity> attackedEntities;
	private Bag playerbag;
	private int playerx;
	private int playery;
	
	public Invicibleplayer(Player player) {
		this.player = player;
		this.presentDungeon = player.getDungeon();
		this.attackedEntities = this.presentDungeon.getEntity();
		this.playerbag = player.getBag();
		this.playerx = player.getX();
		this.playery = player.getY();
		playerbag.setInviciblepotion(30);
	}
	
	@Override
	public void attack(List<Entity> entitylist, int playerx, int playery) {
		for(Entity i : entitylist) {
			if ( (i.gettype().equals("enemy")) &&  (i.getX() == playerx) && (i.getY() == playery)) {
				i. y().set(0);
    			i. x().set(0);
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
		throw new UnsupportedOperationException("Invicible can't become invicible again");
	}
}	
