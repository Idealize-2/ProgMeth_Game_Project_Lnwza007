package Weapon;

import Entity.Monster;
import Entity.MonsterBoss;
import Entity.Player;
import Scene.GameScene;
import javafx.scene.canvas.GraphicsContext;

public class Pizza extends Bullet {

	public double bulletAngle;
	private double rotateSpeed = 3;
	static private long weaponCooldown = 400;
	static private double speed = 3;
    static private int weaponDamage = 50;
    static private int weaponLevel = 0;
    
    final public static long btoweaponCooldown = 400;
	final public static double btospeed = 3;
    final public static int btoweaponDamage = 50;
    final public static int btoweaponLevel = 0;

	public Pizza(double x, double y, double targetX, double targetY) {
		super(x, y, targetX, targetY, Pizza.getSpeed() );
		setImageStr("images/pizza.png");
		bulletAngle = Math.toDegrees(this.angle);
	}
	@Override
	public void update() {
        super.update();
        bulletAngle += rotateSpeed;
        if (bulletAngle >= 360)  bulletAngle  -= 360;
        
    }

	@Override
	public void render(GraphicsContext gc, double x, double y) {

		gc.save(); // Save the current state
		gc.translate(x + 15 , y + 15); // Move origin to bullet center (assuming 20x20 size)
		gc.rotate(bulletAngle); // Rotate towards target
		
		gc.drawImage(getBulletImage(), -10, -10 , 30, 30);
		
		gc.restore();
		
	}
	
	static public void backToOriginal() {
		Pizza.setWeaponCooldown(btoweaponCooldown);
		Pizza.setSpeed(btospeed);
		Pizza.setWeaponDamage(btoweaponDamage);
		Pizza.setWeaponLevel(0);
	}
	@Override
	public boolean checkCollision(Monster enemy) {
		double hitRange;
		double offset = 50;
		if(enemy instanceof MonsterBoss) hitRange = 80;
		else hitRange = 25;
		if(Math.hypot(x - enemy.x, y - enemy.y) < hitRange) {
			if(getWeaponLevel() + Player.upgradeWeapon == 1) {


				// Printing the current position of the bullet for debugging
				System.out.println("Initial position: " + x + ", " + y);

				// Right bullet: move to the right by offset
				GameScene.bullets.add(new SubPizza(x + offset , y, x + offset*2, y, speed));

				// Left bullet: move to the left by offset
				GameScene.bullets.add(new SubPizza(x - offset, y, x - offset*2, y, speed));

				// Down bullet: move down by offset
				GameScene.bullets.add(new SubPizza(x, y + offset, x, y + offset*2, speed));

				// Up bullet: move up by offset
				GameScene.bullets.add(new SubPizza(x, y - offset, x, y - offset*2, speed));


			}
			else if(getWeaponLevel() + Player.upgradeWeapon == 2) {
				
				GameScene.bullets.add(new SubPizza(x + offset,  y - offset, x + offset*2, y - offset*2, speed));

				// Top-left diagonal bullet (135 degrees)
				GameScene.bullets.add(new SubPizza(x - offset, y - offset, x - offset*2, y - offset*2, speed));

				// Bottom-left diagonal bullet (225 degrees)
				GameScene.bullets.add(new SubPizza(x - offset, y + offset, x - offset*2, y + offset*2, speed));

				// Bottom-right diagonal bullet (315 degrees)
				GameScene.bullets.add(new SubPizza(x + offset, y + offset, x + offset*2, y + offset*2, speed));
				
				// Right bullet: move to the right by offset
				GameScene.bullets.add(new SubPizza(x + offset , y, x + offset*2, y, speed));

				// Left bullet: move to the left by offset
				GameScene.bullets.add(new SubPizza(x - offset, y, x - offset*2, y, speed));

				// Down bullet: move down by offset
				GameScene.bullets.add(new SubPizza(x, y + offset, x, y + offset*2, speed));

				// Up bullet: move up by offset
				GameScene.bullets.add(new SubPizza(x, y - offset, x, y - offset*2, speed));
			}
		}
        return isOutOfBounds() || Math.hypot(x - enemy.x, y - enemy.y) < hitRange;
    }
	
	static public long getWeaponCooldown() {
		return weaponCooldown;
	}
	
	static public double getSpeed() {
		return speed;
	}

	static public void setSpeed(double speed) {
		Pizza.speed = speed;
	}

	static public int getWeaponDamage() {
		return weaponDamage;
	}

	static public void setWeaponDamage(int weaponDamage) {
		Pizza.weaponDamage = weaponDamage;
	}
	public static void setWeaponCooldown(long weaponCooldown) {
		Pizza.weaponCooldown = Math.max(weaponCooldown, 0);
	}
	public static int getWeaponLevel() {
		return weaponLevel;
	}
	public static void setWeaponLevel(int weaponLevel) {
		Pizza.weaponLevel = weaponLevel;
	}
	

}
