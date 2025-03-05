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

public class Player extends BaseEntity implements Cooldownable,Animationable{
    private long lastShotTime = 1000;
    private long atkSpeed = 200;
    static private boolean canShoot = true;
    static public int upgradeWeapon = 0;

    //private Image playerImage;
    
    private final int HIT_DELAY = 1000;
    private boolean canBeHit = true;
    
    public Player(double x, double y) {
    	//     x,  y, speed, hp, damage,imgPath
    	super( x , y , 2.5 , 100 , 0 ,"images/Airi_plush.jpg");
        
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
    	return Math.hypot(x - enemy.x, y - enemy.y) < 25 ;
    }
    
    
    public boolean checkCollision(Monster enemy) {
    	if( getHit( enemy ) && canBeHit ) // check if monster get close(attack) player
    	{
    		setHp( getHp() - enemy.monsterDMG() ); // setHp
    		System.out.println( "got hit damage = " + enemy.monsterDMG() + " Hp: " + getHp() );
    		
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

//    public boolean canShoot() {
//        return System.currentTimeMillis() - lastShotTime >= SHOOT_DELAY;
//    }
//
//    public void setShootCooldown() {
//        lastShotTime = System.currentTimeMillis();
//    }

//	@Override
//	public void runAnimation() {
//		// TODO Auto-generated method stub
//		
//	}

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
		this.upgradeWeapon = upgradeValue;
	}

	@Override
	public void runCooldown(long cooldown) {
			setCanShoot(false);
			Timeline buffCooldown = new Timeline(
				    new KeyFrame(Duration.millis(cooldown), e -> setCanShoot(true))
			);
		    
			buffCooldown.setCycleCount(1);
			buffCooldown.play();

		
	}

	public static boolean CanShoot() {
		return canShoot;
	}

	public static void setCanShoot(boolean canShoot) {
		Player.canShoot = canShoot;
	}
	
	private Image pFrame1 = new Image("images/Airi_plush.jpg");
	private Image pFrame2 = new Image("images/Airi2.jpg");
	private Image pFrame3 = new Image("images/Airi3.png");
	
	private long frameDelay = 50 * 3;
	private long currentframe = 0;

	@Override
	public void renderAnimation(GraphicsContext gc, double x, double y) {
		if(currentframe/frameDelay == 0)
		{
			gc.drawImage(pFrame1, x, y,40,40);
			currentframe++;
		}
		else if(currentframe/frameDelay == 1)
		{
			gc.drawImage(pFrame2, x, y,40,40);
			currentframe++;
		}
		else if(currentframe/frameDelay == 2)
		{
			gc.drawImage(pFrame3, x, y,40,40);
			currentframe++;
		}
		else
		{
			currentframe = 0;
		}
		
	}
	

	
	
	
	
    
}
