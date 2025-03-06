package Entity;

import AnimationEffect.Animationable;
import Weapon.Bullet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class MonsterBoss extends Monster{
	
	public MonsterBoss(double x, double y)
	{
		 // x,y,speed,hp,damage,imgPath
		super(x , y);
		setReward(100);
		setSpeed(0.75);
		setHp(200);
		setDamage(40);
		setImgPath("/images/Boss.png");
	}
	
	public int monsterDMG() {
    	return this.damage;
    }

    public void update(Player player) {
        x += (player.x - x) > 0 ? speed : -speed;
        y += (player.y - y) > 0 ? speed : -speed;
    }
    

    @Override
    public boolean getHit(Bullet bullet) {
    	
        return Math.hypot(x - bullet.x, y - bullet.y) < 80;

    }
    private final Image[] frames = {
	        new Image("images/boss1.png"),
	        new Image("images/boss2.png")
	    };
	
	private final long frameDelay = 500_000_000;
	private int currentFrameIndex = 0;
	private long lastUpdate = System.nanoTime();
	private final int frameCount = frames.length;
	
	@Override
	public void renderAnimation(GraphicsContext gc, double x, double y) {
	    long now = System.nanoTime();
	    if (now - lastUpdate >= frameDelay) {
	         currentFrameIndex = (currentFrameIndex + 1) % frameCount; // Loop animation
	         lastUpdate = now;
	     }
	     gc.drawImage(frames[currentFrameIndex], x-87, y-70, 174, 140);
	}
}

