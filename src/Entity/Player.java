package Entity;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import AnimationEffect.Animationable;
import AnimationEffect.Cooldownable;
import Scene.GameScene;

public class Player extends BaseEntity implements Cooldownable,Animationable{
	
    private long atkSpeed = 200;
    static public int upgradeWeapon = 0;
    private final int HIT_DELAY = 1000;
    
    static private boolean canShoot = true;
    private boolean canBeHit = true;
    
    public Player(double x, double y) {
    	//     x,  y, speed, hp, damage,imgPath
    	super( x , y , 2.5 , 100 , 0 ,"images/playerChocolate1.png");
        
    }

    public void setX(double x) 
    {
    	this.x = x;
    }
    public void setY(double y) 
    {
    	this.y = y;
    }
    
    
    public boolean getHit( Monster enemy ) {
    	return Math.hypot(x - enemy.x, y - enemy.y) < 35 ;
    }
    
    
    public boolean checkCollision(Monster enemy) {
    	if( getHit( enemy ) && canBeHit ) // check if monster get close(attack) player
    	{
    		setHp( getHp() - enemy.getDamage() ); // setHp
    		System.out.println( "got hit damage = " + enemy.getDamage() + " Hp: " + getHp() );
    		
    		canBeHit = false; // player get hit so will wait cooldown to get hit again
    		// set cooldown
    	    Timeline hitCooldown = new Timeline(
    	    		new KeyFrame(Duration.millis(HIT_DELAY), e -> canBeHit = true)
    	    );
    	    
    	    hitCooldown.setCycleCount(1);
    	    hitCooldown.play();
    		
    	}
        return getHp() <= 0;
    }

	public long getAtkSpeed() {
		return atkSpeed;
	}

	public void setAtkSpeed(long atkSpeed) {
		this.atkSpeed = Math.max(atkSpeed, 0);
	}

	public int getUpgradeWeapon() {
		return upgradeWeapon;
	}

	public void setUpgradeWeapon(int upgradeValue) {
		Player.upgradeWeapon = upgradeValue;
	}

	@Override
	public void runCooldown(long cooldown) {
			setCanShoot(false);
			Timeline CanShootTime = new Timeline(
				    new KeyFrame(Duration.millis(cooldown), e -> setCanShoot(true))
			);
		    
			CanShootTime.setCycleCount(1);
			CanShootTime.play();

	}

	public static boolean CanShoot() {
		return canShoot;
	}

	public static void setCanShoot(boolean canShoot) {
		Player.canShoot = canShoot;
	}
	
	private final Image[] framesChocolate = {
	        new Image("images/playerChocolate1.png"),
	        new Image("images/playerChocolate2.png")
	    };

	private final Image[] framesCroissant = {
	        new Image("images/playerCroissant1.png"),
	        new Image("images/playerCroissant2.png"),
	    };
	
	private final Image[] framesPizza = {
	        new Image("images/playerPizza1.png"),
	        new Image("images/playerPizza2.png")
	    };
	
	private final int frameCount = 2;
	private final long frameDelay = 300_000_000 * 3; // Convert to nanoseconds (50ms * 3)
	private long lastUpdate = System.nanoTime();
	private int currentFrameIndex = 0;

	public void renderAnimation(GraphicsContext gc, double x, double y) {
		long now = System.nanoTime();
		if(GameScene.weaponSelect == 0) {
			if (now - lastUpdate >= frameDelay) {
		         currentFrameIndex = (currentFrameIndex + 1) % frameCount; // Loop animation
		         lastUpdate = now;
		     }
		     gc.drawImage(framesChocolate[currentFrameIndex], x-15, y-40, 80, 83);
			
		}
		if(GameScene.weaponSelect == 1) {
			if (now - lastUpdate >= frameDelay) {
		         currentFrameIndex = (currentFrameIndex + 1) % frameCount; // Loop animation
		         lastUpdate = now;
		     }
		     gc.drawImage(framesCroissant[currentFrameIndex], x-15, y-40, 80, 83);
			
		}
		if(GameScene.weaponSelect == 2) {
			if (now - lastUpdate >= frameDelay) {
		         currentFrameIndex = (currentFrameIndex + 1) % frameCount; // Loop animation
		         lastUpdate = now;
		     }
		     gc.drawImage(framesPizza[currentFrameIndex], x-15, y-40, 80, 83);
			
		}
	    
	    
	}
	

	
	
	
	
    
}
