package Item;

import AnimationEffect.Cooldownable;
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
	final private int BUFFATF = 10;
	final private int BUFFHEAL = 10;
	final private int BUFFATKSPEED = 100;
	final private double BUFFMOVESPEED = 3;
	final private long COOLDOWN = 10000; // 10 second
	final private long BUFFTIME = 6000; // 6 second
	public static boolean isBuffItemAvailable = true;

	public BuffItem(String name, int price, String itemIconStr,buff buff) {
		super(name, price,itemIconStr);
		setBuffType(buff);
	}

	public buff getBuffType() {
		return buffType;
	}

	private void setBuffType(buff buffType) {
		this.buffType = buffType;
	}

	
	@Override
	public void runCooldown(long cooldown) {
		setAvailable(false);
		Timeline buffCooldown = new Timeline(
			    new KeyFrame(Duration.millis(cooldown), e -> setAvailable(true))
		);
	    
		buffCooldown.setCycleCount(1);
		buffCooldown.play();
		
	}
	private void userBerserkPotion(Player player) 
	{
		UsePotionEffect.setIsbuffAvailable(true);
		UsePotionEffect.setIsbuffBerserkAvailable(true);
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
	            UsePotionEffect.setIsbuffBerserkAvailable(false);
	            System.out.println("BERSERK POTION WORN OFF!!!!");
	        })
	    );
	    
	    buffCooldown.setCycleCount(1);
	    buffCooldown.play();
	    
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
	    
	    UsePotionEffect.setHealing(true);
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
		
		UsePotionEffect.setIsbuffAvailable(true);
		UsePotionEffect.setIsbuffSpecialAvailable(true);
		
		System.out.println("USE SPECIAL POTION!!!!");
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
	            System.out.println("SPECIAL POTION WORN OFF!!!!");
	            UsePotionEffect.setIsbuffAvailable(false);
	    		UsePotionEffect.setIsbuffSpecialAvailable(false);
	        })
	    );
	    buffCooldown.setCycleCount(1);
	    buffCooldown.play();
	    
	}
	
	public void useEffect(Player player)
	{
		if(BuffItem.isBuffItemAvailable  && getItemCount() > 0) 
		{	
			setItemCount( getItemCount() - 1 ); // decease item count by 1
			
			runCooldown(COOLDOWN); // run cooldown
			
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
			if(getItemCount() == 0) {
				System.out.println("You don't have this item pls buy");
			}
			else {
				System.out.println("On Cooldowns");
			}
		}
			
	}

	public boolean isAvailable() {
		return isBuffItemAvailable;
	}

	public void setAvailable(boolean isAvilable) {
		BuffItem.isBuffItemAvailable = isAvilable;
	}
	
	
	
	
	
}
