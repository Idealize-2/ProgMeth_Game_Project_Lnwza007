package Entity;

import Weapon.Bullet;
import Weapon.Croissant;
import Weapon.Pizza;
import Weapon.Chocolate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class MonsterWeakness extends Monster {
	
	public MonsterWeakness(double x, double y)
	{
		 // x,y,speed,hp,damage,imgPath
		super(x , y);
		setReward(20);
		setSpeed(1.5);
		setHp(100);
		setDamage(20);
		setImgPath("/images/witchcroissant1.png");
	}
	
	public int monsterDMG() {
    	return this.damage;
    }

    public void update(Player player) {
        x += (player.x - x) > 0 ? speed : -speed;
        y += (player.y - y) > 0 ? speed : -speed;
    }
    
    public boolean checkCollision(Bullet bullet,Player player) 
    {
    	if(getHit( bullet ) && !bullet.isHit()) // check if bullet get close(attack) Monster
    	{
    		int bulletDamage;
    		if(bullet instanceof Chocolate)bulletDamage = Chocolate.getWeaponDamage();
    		else if(bullet instanceof Croissant)bulletDamage = Croissant.getWeaponDamage()*2;
    		else bulletDamage = Pizza.getWeaponDamage();
    		bullet.setHit(true);
    		setHp( getHp() - ( bulletDamage + player.getDamage() ) ); // setHp
    		if(bullet instanceof Croissant) System.out.println("Critical hit!!");
    		System.out.println( "Monster got hit damage = " + bulletDamage + " Hp: " + getHp() );

    	}
        return getHp() <= 0;
    }
    public void render(GraphicsContext gc , double x , double y) {
        gc.drawImage(getEntityImage() , x , y , 58 , 53);
    }
    
    private final Image[] frames = {
	        new Image("images/witchcroissant1.png"),
	        new Image("images/witchcroissant2.png")
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
	     gc.drawImage(frames[currentFrameIndex], x, y, 58, 53);
	}
}
