package unsw.dungeon.goal;

import java.util.ArrayList;
import unsw.dungeon.entity.Player;
import unsw.dungeon.interfacepack.GoalComponent;

public class OrGoal implements GoalComponent{
	private int andor;
	private boolean whethercomplete;
	private ArrayList<GoalComponent> subgoallist;
	
	public OrGoal(int andor) {
		//1 means and, 0 means or
		this.andor = andor;
		this.whethercomplete = false;
		this.subgoallist =new ArrayList<GoalComponent>();
	}
	
	@Override 
	public boolean checkwhethercomplete(Player player, int andor, boolean whethercomplete) {
		boolean completeornot = whethercomplete;
		
		completeornot = false;
		
		//This session is and
		if(this.andor == 1) {
			for(GoalComponent g : subgoallist) {
				completeornot = completeornot & g.checkwhethercomplete(player, g.getAndor(), false);
			}
		}
		else {
			for(GoalComponent g : subgoallist) {
				completeornot = completeornot | g.checkwhethercomplete(player, g.getAndor(), false);
			}
		}
		return completeornot;
	}
	
	@Override
	public int getAndor() {
		return andor;
	}
	
	@Override
	public void setAndor(int andor) {
		this.andor = andor;
	}

	@Override
	public boolean isWhethercomplete() {
		return whethercomplete;
	}
	public void setWhethercomplete(boolean whethercomplete) {
		this.whethercomplete = whethercomplete;
	}
	public ArrayList<GoalComponent> getSubgoallist() {
		return subgoallist;
	}
	public void setSubgoallist(ArrayList<GoalComponent> subgoallist) {
		this.subgoallist = subgoallist;
	}

	@Override
	public boolean add(GoalComponent child) {
		this.subgoallist.add(child);
		return true;
	}

	@Override
	public boolean remove(GoalComponent child) {
		this.subgoallist.remove(child);
		return true;
	}
}
