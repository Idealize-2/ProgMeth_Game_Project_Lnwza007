package Item;

import AnimationEffect.Cooldownable;
import AnimationEffect.UsePotionEffect;
import Entity.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class HealItem extends Item implements Cooldownable{
	private int healPoint;
	public static boolean isMHealItemAvailable = true;
	final public long COOLDOWN = 5000; // 5 second
	
	public HealItem(String name, int price, String des, String itemIconStr , int healPoint) {
		super(name, price, des, itemIconStr);
		setHealPoint(healPoint);
	}
	public void useHealPotion(Player player) {
		
		player.setHp(player.getHp() + healPoint);
    	System.out.println("Healing "+ healPoint + " Player heal HP: " + player.getHp());
		UsePotionEffect.setHealing(true);
	    Timeline healOverTimeeffect = new Timeline(
		        new KeyFrame(Duration.millis(500), e -> 
		        {
		        	UsePotionEffect.setHealing(false);
		        }));
	    healOverTimeeffect.setCycleCount(1);
	    healOverTimeeffect.play();
	}
	
	public void useEffect(Player player)
	{
		if(HealItem.isMHealItemAvailable()  && getItemCount() > 0) 
		{	
			setItemCount( getItemCount() - 1 ); // decease item count by 1
			useHealPotion(player);
			runCooldown(COOLDOWN); // run cooldown
	
		}
		else
		{
			System.out.println("On Cooldowns");
		}
	}


	public int getHealPoint() {
		return healPoint;
	}


	public void setHealPoint(int healPoint) {
		this.healPoint = healPoint;
	}

	@Override
	public void runCooldown(long cooldown) {
		setMHealItemAvailable(false);
		Timeline buffCooldown = new Timeline(
			    new KeyFrame(Duration.millis(cooldown), e -> setMHealItemAvailable(true))
		);
	    
		buffCooldown.setCycleCount(1);
		buffCooldown.play();
		
	}


	public static boolean isMHealItemAvailable() {
		return isMHealItemAvailable;
	}


	public static void setMHealItemAvailable(boolean isMHealItemAvailable) {
		HealItem.isMHealItemAvailable = isMHealItemAvailable;
	}
	
	
	

}
