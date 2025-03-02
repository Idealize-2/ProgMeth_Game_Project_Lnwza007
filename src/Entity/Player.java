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

public class Player extends BaseEntity implements Animationable{
    private long lastShotTime = 1000;
    private long atkSpeed = 500;
    private int upgradeWeapon = 0;

    //private Image playerImage;
    
    private final int HIT_DELAY = 1000;
    private boolean canBeHit = true;
    
    public Player(double x, double y) {
    	//     x,  y, speed, hp, damage,imgPath
    	super( x , y , 2.5 , 50 , 0 ,"images/Airi_plush.jpg");
        
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

	@Override
	public void runAnimation() {
		// TODO Auto-generated method stub
		
	}

	public long getAtkSpeed() {
		return atkSpeed;
	}

	public void setAtkSpeed(long atkSpeed) {
		this.atkSpeed = atkSpeed;
	}

	public int getUpgradeWeapon() {
		return upgradeWeapon;
	}

	public void setUpgradeWeapon(int upgradeValue) {
		this.upgradeWeapon = upgradeValue;
	}

	
	
	
	
    
}
