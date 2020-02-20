package unsw.dungeon.attackmode;

import java.util.List;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Bag;
import unsw.dungeon.entity.Entity;
import unsw.dungeon.entity.Player;
import unsw.dungeon.interfacepack.Attackmode;

public class Armed implements Attackmode{
	Player player;
	private Dungeon presentDungeon;
	private List<Entity> attackedEntities;
	private Bag playerbag;

	
	public Armed(Player player) {
		this.player = player;
		this.presentDungeon = player.getDungeon();
		this.attackedEntities = this.presentDungeon.getEntity();
		this.playerbag = player.getBag();
		playerbag.setSwordattacktimes(player.getBag().getSwordattacktimes());
	}
	
	@Override
	public void attack(List<Entity> entitylist, int playerx, int playery) {
		for(Entity i : entitylist) {
			if ( (i.gettype().equals("enemy")) &&  (i.getX() == playerx) && (i.getY() == playery)) {
				i. y().set(0);
    			i. x().set(0);
    			playerbag.setSwordattacktimes(playerbag.getSwordattacktimes() - 1);
    			if (playerbag.getSwordattacktimes() == 0) {
    				player.makeunarmed();
    			}
			}
		}
	}
	
	@Override
	public Attackmode becomearmed(Player player) {
		throw new UnsupportedOperationException("Armed can't become new armed");
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
