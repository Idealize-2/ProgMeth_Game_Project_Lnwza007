package Weapon;

import javafx.scene.canvas.GraphicsContext;

public class Croissant extends Bullet {

	public double bulletAngle;
	private double rotateSpeed = 15;
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
		setImageStr("images/croissant.png");
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

	    double centerX = x + 25;  // กึ่งกลางภาพ (กว้าง 50 / 2)
	    double centerY = y + 13.5; // กึ่งกลางภาพ (สูง 27 / 2)

	    gc.translate(centerX, centerY); // ย้ายจุดหมุนไปกึ่งกลาง
	    gc.rotate(bulletAngle); // หมุน

	    gc.drawImage(getBulletImage(), -25, -13.5, 50, 27); // วาดโดยให้ (0,0) เป็นศูนย์กลาง

	    gc.restore(); // Restore state
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
