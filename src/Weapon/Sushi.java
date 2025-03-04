package Weapon;

import javafx.scene.canvas.GraphicsContext;

public class Sushi extends Bullet {
	
	public double bulletAngle;
	static final public long weaponCooldown = 75;

	public Sushi(double x, double y, double targetX, double targetY) {
		super(x, y, targetX, targetY);
		setSpeed(1);
		setWeaponDamage(20);
		setImageStr("images/sushi.jpg");
		bulletAngle = Math.toDegrees(this.angle);
		
	}
	@Override
	public void update() {
        super.update();
        
    }

	@Override
	public void render(GraphicsContext gc, double x, double y) {

		gc.save(); // Save the current state
		gc.translate(x + 10 , y + 10); // Move origin to bullet center (assuming 20x20 size)
		gc.rotate(bulletAngle); // Rotate towards target
		
		gc.drawImage(getBulletImage(), -10, -10 , 40, 20);
		
		gc.restore();
		
	}
	public long getWeaponCooldown() {
		return weaponCooldown;
	}

}
