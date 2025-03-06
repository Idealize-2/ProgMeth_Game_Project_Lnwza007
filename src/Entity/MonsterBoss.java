package Entity;

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
		setImgPath("images/boss.png");
	}


    @Override
    public boolean getHit(Bullet bullet) {
    	
    	return Math.hypot(x - bullet.x, y - bullet.y) < 65;

    }
    private final Image[] frames = {
    	    new Image(getClass().getClassLoader().getResource("images/boss1.png").toString()),
    	    new Image(getClass().getClassLoader().getResource("images/boss2.png").toString())
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

