package Item;

import AnimationEffect.Cooldownable;
import AnimationEffect.Effectable;

public class HealItem extends Item implements Cooldownable{
	private int healPoint;
	
	
	public HealItem(String name, int price, String des, String itemIconStr , int healPoint) {
		super(name, price, des, itemIconStr);
		setHealPoint(healPoint);
	}


	public int getHealPoint() {
		return healPoint;
	}


	public void setHealPoint(int healPoint) {
		this.healPoint = healPoint;
	}

	@Override
	public void runCooldown(long cooldown) {
		// TODO Auto-generated method stub
		
	}
	

}
