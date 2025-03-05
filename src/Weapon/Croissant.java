package Weapon;

import javafx.scene.canvas.GraphicsContext;

public class Croissant extends Bullet {

	public double bulletAngle;
	private double rotateSpeed = 4;
	static private long weaponCooldown = 175;
	static private double speed = 2.5;
    static private int weaponDamage = 20;
    static private int weaponLevel = 0;
    
    final public static long btoweaponCooldown = 175;
	final public static double btospeed = 2.5;
    final public static int btoweaponDamage = 20;
    final public static int btoweaponLevel = 0;

	public Croissant(double x, double y, double targetX, double targetY) {
		super(x, y, targetX, targetY, Croissant.getSpeed() );
		setImageStr("images/cr.png");
		bulletAngle = Math.toDegrees(this.angle);
		// TODO Auto-generated constructor stub
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
		Croissant.setWeaponCooldown(btoweaponCooldown);
		Croissant.setSpeed(btospeed);
		Croissant.setWeaponDamage(btoweaponDamage);
		Croissant.setWeaponLevel(0);
	}
	
	
	static public long getWeaponCooldown() {
		return weaponCooldown;
	}
	
	static public double getSpeed() {
		return speed;
	}

	static public void setSpeed(double speed) {
		Croissant.speed = speed;
	}

	static public int getWeaponDamage() {
		return weaponDamage;
	}

	static public void setWeaponDamage(int weaponDamage) {
		Croissant.weaponDamage = weaponDamage;
	}
	public static void setWeaponCooldown(long weaponCooldown) {
		Croissant.weaponCooldown = Math.max(weaponCooldown, 0) ;
	}
	public static int getWeaponLevel() {
		return weaponLevel;
	}
	public static void setWeaponLevel(int weaponLevel) {
		Croissant.weaponLevel = weaponLevel;
	}
	
	

}
