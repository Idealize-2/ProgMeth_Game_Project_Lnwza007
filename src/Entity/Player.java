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

public class Player extends BaseEntity{
    private long lastShotTime = 1000;
    private final long SHOOT_DELAY = 0;
    //private Image playerImage;
    private int width = 40;  // ความกว้างที่ต้องการ
    private int height = 40; // ความสูงที่ต้องการ
    
    private final int HIT_DELAY = 1000;
    private boolean canBeHit = true;
    
    public Player(double x, double y) {
    	// x,y,speed,hp,damage,imgPath
    	super( x , y , 2 , 50 , 10 ,"images/Airi_plush.jpg");
//        this.x = x;
//        this.y = y;
//        try {
//            // โหลดภาพ PNG
//            playerImage = new Image("images/Airi_plush.jpg"); // ใส่ path ของไฟล์ PNG ที่ต้องการ
//
//          
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        
        
    }

//    public void moveUp() { y = Math.max(0, y - speed); }
//    public void moveDown() { y = Math.min(600 - 30, y + speed); }
//    public void moveLeft() { x = Math.max(0, x - speed); }
//    public void moveRight() { x = Math.min(800 - 30, x + speed); }
    public void setX(double x) 
    {
    	this.x = x;
    }
    public void setY(double y) 
    {
    	this.y = y;
    }
    

    public void render(GraphicsContext gc , double x , double y) {
        gc.drawImage(getEntityImage(), x, y, width, height);
    }
    
    public boolean getHit( Monster enemy ) {
    	return Math.hypot(x - enemy.x, y - enemy.y) < 25 ;
    }

    public boolean checkCollision(Monster enemy) {
    	if(getHit( enemy ) && canBeHit ) // check if monster get close(attack) player
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
        return getHp() == 0;
    }

    public boolean canShoot() {
        return System.currentTimeMillis() - lastShotTime >= SHOOT_DELAY;
    }

    public void setShootCooldown() {
        lastShotTime = System.currentTimeMillis();
    }
    
}
