package Entity;
import AnimationEffect.Animationable;
import Weapon.Bullet;
import Weapon.Croissant;
import Weapon.Pizza;
import Weapon.Chocolate;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Monster extends BaseEntity implements Animationable{
	public int reward = 10;
	
    public Monster(double x, double y) {
    	//     x, y, speed,hp,damage,    imgPath
        super( x , y , 1 , 40 , 10 ,"images/Enemy1.png");
    }
    public int monsterDMG() {
    	return this.damage;
    }

    public void update(Player player) {
        x += (player.x - x) > 0 ? speed : -speed;
        y += (player.y - y) > 0 ? speed : -speed;
    }
    
    public boolean getHit(Bullet bullet) {
    	
        return Math.hypot(x - bullet.x, y - bullet.y) < 25;

    }
	
	public boolean checkCollision(Bullet bullet,Player player) 
    {
    	if(getHit( bullet ) && !bullet.isHit()) // check if bullet get close(attack) Monster
    	{
    		int bulletDamage;
    		if(bullet instanceof Chocolate)bulletDamage = Chocolate.getWeaponDamage();
    		else if(bullet instanceof Croissant)bulletDamage = Croissant.getWeaponDamage();
    		else bulletDamage = Pizza.getWeaponDamage();
    		bullet.setHit(true);
    		setHp( getHp() - ( bulletDamage + player.getDamage() ) ); // setHp
    		System.out.println( "Monster got hit damage = " + bulletDamage + " Hp: " + getHp() );

    	}
        return getHp() <= 0;
    }
	
	public void setHp(int hp) {
		this.hp = hp;
	}

    

    public int getReward() {
    	System.out.println("Gain Reward: " + reward);
		return reward;
	}
	public void setReward(int reward) {
		this.reward = reward;
	}
	public void render(GraphicsContext gc , double x , double y) {
        gc.drawImage(getEntityImage() , x , y , 60 , 60);
    }
	private final Image[] frames = {
	        new Image("images/Enemy1.png"),
	        new Image("images/Enemy2.png")
	    };
	
	private final long frameDelay = 500_000_000;
	private int currentFrameIndex = 0;
	private long lastUpdate = System.nanoTime();
	private final int frameCount = frames.length;
	
	public void renderAnimation(GraphicsContext gc, double x, double y) {
	    long now = System.nanoTime();
	    if (now - lastUpdate >= frameDelay) {
	         currentFrameIndex = (currentFrameIndex + 1) % frameCount; // Loop animation
	         lastUpdate = now;
	     }
	     gc.drawImage(frames[currentFrameIndex], x, y, 40, 40);
	}
	
	
}
