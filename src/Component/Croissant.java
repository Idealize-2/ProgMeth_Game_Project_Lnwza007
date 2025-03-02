package Component;

import javafx.scene.canvas.GraphicsContext;

public class Croissant extends Bullet {

	public double bulletAngle;
	private double rotateSpeed = 2;

	public Croissant(double x, double y, double targetX, double targetY) {
		super(x, y, targetX, targetY);
		setSpeed(1);
		setWeaponDamage(15);
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

}
