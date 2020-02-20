package unsw.dungeon;
import unsw.dungeon.entity.*;
public class DungeonSystem {
	private Dungeon dungeon;
	private Player player;
	public DungeonSystem (Dungeon dungeon, Player player){
		this.dungeon = dungeon;
		this.player = player;
	}
	
	public void playermoveup() {
		if(player.getResult() == 0) {
			player.moveUp();
		}
		//player.moveUp();
	}
	
	public void playermovedown() {
		if(player.getResult() == 0) {
			player.moveDown();
		}
	}
	
	public void playermoveleft() {
		if(player.getResult() == 0) {
			player.moveLeft();
		}
	}
	
	public void playermoveright() {
		if(player.getResult() == 0) {
			player.moveRight();
		}
	}
	
	public void playerthrowsword() {
		player.throwsword();
	}
	
	public void playerplacebomb() {
		player.placebomb();
	}
	
	public String playerStatus() {
		if(player.getBag().getSwordattacktimes() > 0 && player.getBag().getInviciblepotion() == 0) {
			System.out.println("Sowrd");
			return "Sword!";
		}
		if(player.getBag().getInviciblepotion() > 0) {
			return "Invincibe!!!";
		}
		return "Normal";
	}
}
