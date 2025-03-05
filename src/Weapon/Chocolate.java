package Weapon;

import java.util.HashSet;
import java.util.Set;

import Entity.Monster;
import javafx.scene.canvas.GraphicsContext;

public class Chocolate extends Bullet {
	
	public double bulletAngle;
	static private long weaponCooldown = 75;
	static private double speed = 3;
    static private int weaponDamage = 20;
    
    private int maxPenetration; 
    private Set<Monster> hitEnemies = new HashSet<>(); // Track enemies hit

    static private int weaponLevel = 0;
    
    final public static long btoweaponCooldown = 75;
	final public static double btospeed = 3;
    final public static int btoweaponDamage = 20;
    final public static int btoweaponLevel = 0;
    
    
	public Chocolate(double x, double y, double targetX, double targetY, int maxPenetration) {
		super(x, y, targetX, targetY, Chocolate.getSpeed());
		this.maxPenetration = maxPenetration;
		setImageStr("images/chocolate.png");
		bulletAngle = Math.toDegrees(this.angle);
		
	}
	 public boolean canHit(Monster enemy) {
	        return hitEnemies.size() < maxPenetration && !hitEnemies.contains(enemy) && Math.hypot(x - enemy.x, y - enemy.y) < 25;
	 }
	 public void hitEnemy(Monster enemy) {
	        hitEnemies.add(enemy); // Register hit
	 }
	 public boolean shouldRemove() {
	        return hitEnemies.size() >= maxPenetration; // Remove when max hits reached
	    }
	 public boolean checkCollision(Monster enemy) {
		if( canHit(enemy) ) {
			hitEnemy(enemy);
			System.out.println(hitEnemies.size());
		}
	    return isOutOfBounds() || shouldRemove();
	 }
	@Override
	public void update() {
        super.update();
        
    }
	static public void backToOriginal() {
		Chocolate.setWeaponCooldown(btoweaponCooldown);
		Chocolate.setSpeed(btospeed);
		Chocolate.setWeaponDamage(btoweaponDamage);
		Chocolate.setWeaponLevel(0);
	}

	@Override
	public void render(GraphicsContext gc, double x, double y) {

		gc.save(); // Save the current state
		gc.translate(x + 10 , y + 10); // Move origin to bullet center (assuming 20x20 size)
		gc.rotate(bulletAngle); // Rotate towards target
		
		gc.drawImage(getBulletImage(), -10, -10 , 40, 20);
		
		gc.restore();
		
	}
	static public long getWeaponCooldown() {
		return weaponCooldown;
	}
	
	static public double getSpeed() {
		return speed;
	}

	static public void setSpeed(double speed) {
		Chocolate.speed = speed;
	}

	static public int getWeaponDamage() {
		return weaponDamage;
	}

	static public void setWeaponDamage(int weaponDamage) {
		Chocolate.weaponDamage = weaponDamage;
	}
	public static void setWeaponCooldown(long weaponCooldown) {
		Chocolate.weaponCooldown = Math.max(weaponCooldown, 0);
	}
	public static int getWeaponLevel() {
		return weaponLevel;
	}
	public static void setWeaponLevel(int weaponLevel) {
		Chocolate.weaponLevel = weaponLevel;
	}

	
	
	
	
	
	

}
