package unsw.dungeon.entity;

public class Bag {
	private int swordattacktimes;
	private int bomb;
	private int inviciblepotion;
	private int key;
	private int redkey;
	private int bluekey;
	private boolean haskey;
	
	public Bag() {
		swordattacktimes = 0;
		bomb = 0;
		inviciblepotion = 0;
		key = 0;
		redkey = 0;
		bluekey = 0;
		haskey = false;
	}

	public boolean getHaskey() {
		return haskey;
	}

	public void setHaskey(boolean haskey) {
		this.haskey = haskey;
	}

	public int getRedkey() {
		return redkey;
	}

	public void setRedkey(int redkey) {
		this.redkey = redkey;
	}

	public int getBluekey() {
		return bluekey;
	}

	public void setBluekey(int bluekey) {
		this.bluekey = bluekey;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	
	public int getSwordattacktimes() {
		return swordattacktimes;
	}

	public void setSwordattacktimes(int swordattacktimes) {
		this.swordattacktimes = swordattacktimes;
	}

	public int getBomb() {
		return bomb;
	}

	public void setBomb(int bomb) {
		this.bomb = bomb;
	}

	public int getInviciblepotion() {
		return inviciblepotion;
	}

	public void setInviciblepotion(int inviciblepotion) {
		this.inviciblepotion = inviciblepotion;
		
		
	}
}
