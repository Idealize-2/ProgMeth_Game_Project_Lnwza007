package Item;

import AnimationEffect.Cooldownable;
import AnimationEffect.Effectable;
import AnimationEffect.UsePotionEffect;
import Entity.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class BuffItem extends Item implements Cooldownable{
	
	private buff buffType;
	final public int BUFFATF = 10;
	final public int BUFFHEAL = 10;
	final public int BUFFATKSPEED = 250;
	final public double BUFFMOVESPEED = 10;
	final public long COOLDOWN = 10000; // 10 second
	final public long BUFFTIME = 6000; // 6 second
	public static boolean isAvailable = true;
	private int itemCount = 100;

	public BuffItem(String name, int price, String des, String itemIconStr,buff buff) {
		super(name, price, des, itemIconStr);
		setBuffType(buff);
	}

	public buff getBuffType() {
		return buffType;
	}

	private void setBuffType(buff buffType) {
		this.buffType = buffType;
	}

	
	@Override
	public void runCooldown() {
		setAvailable(false);
		Timeline buffCooldown = new Timeline(
			    new KeyFrame(Duration.millis(COOLDOWN), e -> setAvailable(true))
		);
	    
		buffCooldown.setCycleCount(1);
		buffCooldown.play();
		
	}
	private void userBerserkPotion(Player player) 
	{
		UsePotionEffect.setIsbuffAvailable(true);
		System.out.println("USE BERSERK POTION!!!!");
		
	    player.setDamage(player.getDamage() + BUFFATF);
	    player.setAtkSpeed(player.getAtkSpeed() - BUFFATKSPEED);
	    player.setSpeed(player.getSpeed() + BUFFMOVESPEED);
	    // Buff expiration timeline
	    Timeline buffCooldown = new Timeline(
	        new KeyFrame(Duration.millis(BUFFTIME), e -> {
	            player.setDamage(player.getDamage() - BUFFATF);
	            player.setAtkSpeed(player.getAtkSpeed() + BUFFATKSPEED);
	            player.setSpeed(player.getSpeed() - BUFFMOVESPEED);
	            
	            UsePotionEffect.setIsbuffAvailable(false);
	            System.out.println("BERSERK POTION WORN OFF!!!!");
	        })
	    );
	    
	    buffCooldown.setCycleCount(1);
	    buffCooldown.play();
	    UsePotionEffect.setHealing(true);
	    System.out.println("appile heal effect");
    	
	    // Healing over time timeline
	   Timeline healOverTime = new Timeline(
	        new KeyFrame(Duration.millis(1000), e -> 
	        {
	        	player.setHp(player.getHp() + BUFFHEAL);
	        	System.out.println("Healing BUFFHEAL" + "Player heal HP: " + player.getHp());
	        }));
	    healOverTime.setCycleCount(6); // Heal every second for 6 seconds
	    healOverTime.play();
	    Timeline healOverTimeeffect = new Timeline(
		        new KeyFrame(Duration.millis(BUFFTIME), e -> 
		        {
		        	UsePotionEffect.setHealing(false);
		        }));
	    healOverTimeeffect.setCycleCount(1);
	    healOverTimeeffect.play();
	    
	}
	
	private void userSpecialPotion(Player player) 
	{
	    player.setDamage( ( player.getDamage() + BUFFATF ) * 3 );
	    player.setAtkSpeed(player.getAtkSpeed() + BUFFATKSPEED);
	    player.setUpgradeWeapon(1);
	    // Buff expiration timeline
	    Timeline buffCooldown = new Timeline(
	        new KeyFrame(Duration.millis(BUFFTIME), e -> 
	        {
	        	player.setDamage( ( player.getDamage() - BUFFATF ) / 3 );
	            player.setAtkSpeed( player.getAtkSpeed() - BUFFATKSPEED );
	            player.setUpgradeWeapon( 0 );
	        })
	    );
	    buffCooldown.setCycleCount(1);
	    buffCooldown.play();
	    
	}
	
	public void useEffect(Player player)
	{
		if(BuffItem.isAvailable  && getItemCount() > 0) 
		{	
			setItemCount( getItemCount() - 1 ); // decease item count by 1
			
			runCooldown(); // run cooldown
			
			switch (getBuffType()) {
			case BERSERK:
				userBerserkPotion(player);
				break;
			case SPECIAL:
				userSpecialPotion(player);
				break;
			default:
				break;
			}
		}
		else
		{
			System.out.println("On Cooldowns");
		}
			
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvilable) {
		BuffItem.isAvailable = isAvilable;
	}
	
	

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	
	
	
	
	
}
