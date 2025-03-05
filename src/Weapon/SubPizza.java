package Weapon;

import javafx.scene.canvas.GraphicsContext;

public class SubPizza extends Bullet{
	
	public double bulletAngle;
	static private double speed = 3;
    static private int weaponDamage = 10;

	public SubPizza(double x, double y, double targetX, double targetY, double speed) {
		super(x, y, targetX, targetY, SubPizza.speed);
		setImageStr("images/Pizza1.jpg");
		bulletAngle = Math.toDegrees(this.angle);
	}

	@Override
	public void render(GraphicsContext gc, double x, double y) {
		gc.save(); // Save the current state
		gc.translate(x + 10 , y + 10); // Move origin to bullet center (assuming 20x20 size)
		gc.rotate(bulletAngle); // Rotate towards target
		
		gc.drawImage(getBulletImage(), -10, -10 , 40, 20);
		
		gc.restore();
		
	}
	
	

}
